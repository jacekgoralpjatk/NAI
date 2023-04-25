package Projekt_3;
import Projekt_2.Perceptron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class LanguageOneLayerPrediction {
    public static HashMap<String,String> trainMap;
    public static ArrayList<Perceptron> perceptrons;
    public static Layer layer;
    public static List<Language> languages;
    public static String languagesListedAsString = "";
    public static String path = "C:\\Users\\jacek\\OneDrive\\Pulpit\\PJATK\\NAI\\NAI\\src\\Projekt_3\\resurces\\train";

    public static void main(String[] args){
        trainMap = new HashMap<>();
        languages = new ArrayList<>();

        File[] files = new File(path).listFiles();

        for (File file : files) {
            languages.add(new Language(file.getName(), file.getAbsolutePath()));
        }

        for (int i=0; i<languages.size(); i++){
            languages.get(i).setCorrectAnswer(languages.size(), i);
        }

//        List<String> englishTexts = new ArrayList<>();
//        List<String> polishTexts = new ArrayList<>();
//
//        // Read English texts
//        File englishFolder = new File("C:\\Users\\jacek\\OneDrive\\Pulpit\\PJATK\\NAI\\NAI\\src\\Projekt_3\\resurces\\angielski");
//        for (File file : englishFolder.listFiles()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line;
//                StringBuilder builder = new StringBuilder();
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line);
//                    builder.append("\n");
//                }
//                englishTexts.add(removeNonLatin(builder.toString()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Read Polish texts
//        File polishFolder = new File("C:\\Users\\jacek\\OneDrive\\Pulpit\\PJATK\\NAI\\NAI\\src\\Projekt_3\\resurces\\Niemiecki");
//        for (File file : polishFolder.listFiles()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line;
//                StringBuilder builder = new StringBuilder();
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line);
//                    builder.append("\n");
//                }
//                polishTexts.add(removeNonLatin(builder.toString()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        perceptrons = new ArrayList<>();

        for(int i=0; i<languages.size(); i++){
            Double[] weights = new Double[26];
            for(int j=0; j<weights.length; j++){
                weights[j] = Math.random();
            }
            perceptrons.add(new Perceptron(0.15, Math.random(), weights));
        }

        layer = new Layer(perceptrons);

        for(int j=1; j<30; j++) {
            System.out.println(j + " epoch");
            for (int i = 0; i < 10; i++) {
                for(int k=0; k<languages.size(); k++){
                    layer.trainLayer(countLetters(languages.get(k).getTexts().get(i)), languages.get(k).getCorrectAnswer());
                }
            }
        }

        System.out.println("""
                
                =======================================================
                ========================TESTING========================
                =======================================================
                """);

        for (int i = 0; i < 10; i++) {
            for(int k=0; k<languages.size(); k++){
                layer.test(countLetters(languages.get(k).getTexts().get(i)), languages.get(k).getCorrectAnswer());
            }
        }

        for (int i=0; i<files.length; i++){
            languagesListedAsString += i+1 + ". " + files[i].getName() + " ";
        }

        testUI();

    }

    public static void testUI(){
        System.out.println("""
                \n--------------
                1. test new text
                2. exit""");
        int in = new Scanner(System.in).nextInt();
        switch (in){
            case 1 -> {
                System.out.println("New text path: ");
                String path = new Scanner(System.in).nextLine();

                System.out.println("Correct (" + languagesListedAsString + "): ");
                int correctIndex = new Scanner(System.in).nextInt() - 1;
                int[] correctAnswer = new int[languages.size()];
                for (int i=0; i<correctAnswer.length; i++){
                    correctAnswer[i] = 0;
                }
                correctAnswer[correctIndex] = 1;
                layer.test(countLetters(fileToString(path)), correctAnswer);
            }
            case 2 -> System.exit(1);
            default -> testUI();
        }
        testUI();
    }

    public static String fileToString(String path){
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            return removeNonLatin(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Double[] countLetters(String str) {
        int[] freqArray = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = Character.toLowerCase(c);
                freqArray[c - 'a']++;
            }
        }
        Double[] out = new Double[freqArray.length];
        for(int i=0; i<freqArray.length; i++){
            out[i] = (double)freqArray[i]/str.length();
        }
        return out;
    }

    public static String removeNonLatin(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                builder.append(c);
            }
        }
        return builder.toString().toLowerCase();
    }

    public static void train(){

    }

}
