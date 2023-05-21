package ru.makcnm.exam11.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import ru.makcnm.exam11.Constants;
import ru.makcnm.exam11.Exam11;
import ru.makcnm.exam11.controller.QuestionController;
import ru.makcnm.exam11.pojo.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionUtil {
    public static ArrayList<String> getNumbers(String lessonName, String actionName) {
        ArrayList<String> numbers = new ArrayList<>();

        switch (actionName) {
            case "Вариант" -> {
                File lesson = new File(Constants.LESSON_DIR + lessonName);
                String[] variantNames = lesson.list();
                if (variantNames == null) return null;
                Collections.addAll(numbers, variantNames);
            }

            case "Задание" -> {
                File lesson = new File(Constants.LESSON_DIR + lessonName);
                File[] lessonVariants = lesson.listFiles();
                if (lessonVariants == null) return null;

                for (File lessonVariant : lessonVariants) {
                    String[] questionNames = lessonVariant.list();
                    if (questionNames == null) continue;
                    for (String fileName : questionNames) {
                        String onlyName = fileName.replace(".fxml", "");
                        if (!numbers.contains(onlyName))
                            numbers.add(onlyName);
                    }
                }
            }
        }

        return numbers;
    }

    public static ArrayList<Question> getQuestions(String lessonName, String actionName, int number) {
        ArrayList<Question> questions = new ArrayList<>();

        switch (actionName) {
            case "Вариант" -> {
                File variant = new File(Constants.LESSON_DIR + lessonName + "/" + number);
                Collection<File> variantQuestions = sortFilesByNum(Arrays.asList(variant.listFiles()));
                questions.addAll(variantQuestions.stream().map(file -> new Question(file, getQuestionAnswer(file), "")).toList());
            }

            case "Задание" -> {
                File lesson = new File(Constants.LESSON_DIR + lessonName);
                File[] lessonVariants = lesson.listFiles();
                if (lessonVariants == null) return null;
                String numberFxml = number + ".fxml";

                for (File variant : lessonVariants) {
                    File[] variantQuestions = variant.listFiles();
                    if (variantQuestions == null) continue;
                    for (File question : variantQuestions)
                        if (question.getName().equals(numberFxml))
                            questions.add(new Question(question, getQuestionAnswer(question), ""));
                }
            }
        }

        return questions.size() == 0 ? null : questions;
    }

    private static Collection<File> sortFilesByNum(List<File> files) {
        HashMap<Integer, File> mapForSort = new HashMap<>();

        for (File file : files) mapForSort.put(Integer.parseInt(file.getName().split("\\.")[0]), file);

        return new TreeMap<>(mapForSort).values();
    }

    public static Scene getQuestionScene(Question question) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(question.getQuestionFile().toURI().toURL());
            QuestionController mainController = new QuestionController();
            fxmlLoader.setController(mainController);
            Scene scene = new Scene(fxmlLoader.load());
            mainController.getAnswerField().setText(question.getInputted());
            return scene;
        } catch (Exception e) {
            HelpWindowUtil.openInfoWindow(Exam11.stage.getScene(), "Ошибка", "Непредвиденная ошибка");
            e.printStackTrace();
            return null;
        }
    }

    private static String getQuestionAnswer(File questionFile) {
        try {
            BufferedReader questionReader = new BufferedReader(new FileReader(questionFile));
            Pattern pattern = Pattern.compile("answer=(\\S+)");
            String line;

            while ((line = questionReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) return matcher.group(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
