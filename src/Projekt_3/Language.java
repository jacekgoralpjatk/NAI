package Projekt_3;

import java.io.*;
import java.util.ArrayList;

public class Language {
    private final String languageName;
    private ArrayList<String> texts;
    private final String path;
    private int[] correctAnswer;

    public Language(String languageName, String path) {
        this.languageName = languageName;
        this.path = path;
        texts = new ArrayList<>();
        fillListWithFileContents();
        System.out.println("============");
        System.out.println(languageName + "\n" + texts);
    }

    private void fillListWithFileContents(){
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            try (BufferedReader reader = new BufferedReader((new FileReader(file)))){
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                texts.add(removeNonLatin(builder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String removeNonLatin(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                builder.append(c);
            }
        }
        return builder.toString().toLowerCase();
    }

    public String getLanguageName() {
        return languageName;
    }

    public ArrayList<String> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<String> texts) {
        this.texts = texts;
    }

    public String getPath() {
        return path;
    }

    public int[] getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int listLength, int index) {
        correctAnswer = new int[listLength];
        for (int i=0; i<correctAnswer.length; i++){
            correctAnswer[i] = 0;
        }
        correctAnswer[index] = 1;
    }
}
