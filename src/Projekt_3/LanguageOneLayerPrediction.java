package Projekt_3;
import Projekt_2.Perceptron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LanguageOneLayerPrediction {
    public static HashMap<String,String> trainMap;
    public static ArrayList<Perceptron> perceptrons;
    public static Layer layer;

    public static void main(String[] args){
        trainMap = new HashMap<>();

        List<String> englishTexts = new ArrayList<>();
        List<String> polishTexts = new ArrayList<>();

        // Read English texts
        File englishFolder = new File("resources/angielski");
        for (File file : englishFolder.listFiles()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
                englishTexts.add(removeNonLatin(builder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Read Polish texts
        File polishFolder = new File("resources/polski");
        for (File file : polishFolder.listFiles()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
                polishTexts.add(removeNonLatin(builder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        perceptrons = new ArrayList<>();

        for(int i=0; i<2; i++){
            Double[] weights = new Double[26];
            for(int j=0; j<weights.length; j++){
                weights[j] = (Double) Math.random();
            }
            perceptrons.add(new Perceptron(0.2, Math.random(), weights));
        }

        layer = new Layer(perceptrons);

        for(int i=0; i<10; i++){
//            layer.trainLayer();
        }

    }

    public static int[] countLetters(String str) {
        int[] freqArray = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = Character.toLowerCase(c);
                freqArray[c - 'a']++;
            }
        }
        return freqArray;
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
