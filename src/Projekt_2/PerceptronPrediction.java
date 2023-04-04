package Projekt_2;

import java.io.*;
import java.util.*;

public class PerceptronPrediction {
    public static File trainSet;
    public static File testSet;
    public static List<Iris> trainList;
    public static List<Iris> testList;
    public static double alfa;
    public static Perceptron perceptron;
    public static int checks = 0;
    public static int correctChecks = 0;

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args){
        assert args.length == 3;

        alfa = Double.parseDouble(args[0]);
        trainSet = new File(args[1]);
        testSet = new File(args[2]);
        try(BufferedReader br = new BufferedReader(new FileReader(trainSet))) {
            trainList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] irisParts = line.split(",");
                trainList.add(new Iris(Double.parseDouble(irisParts[0]), Double.parseDouble(irisParts[1]), Double.parseDouble(irisParts[2]), Double.parseDouble(irisParts[3]), irisParts[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(testSet))) {
            testList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] irisParts = line.split(",");
                testList.add(new Iris(Double.parseDouble(irisParts[0]), Double.parseDouble(irisParts[1]), Double.parseDouble(irisParts[2]), Double.parseDouble(irisParts[3]), irisParts[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Double[] weights = {(Double) 0.5, (Double) 0.5, (Double) 0.5, (Double) 0.5};
        System.out.println(Arrays.toString(weights));
        perceptron = new Perceptron((Double)alfa, (Double)0d, weights);
        train();
        test();

        testUI();
    }

    public static void testUI(){
        System.out.println("""
                \n--------------
                1. test new iris
                2. exit""");
        int in = new Scanner(System.in).nextInt();
        switch (in){
            case 1 -> testNewIris();
            case 2 -> System.exit(1);
            default -> testUI();
        }
        testUI();
    }

    public static void testNewIris() {
        System.out.println("""
                --------------
                new iris:
                """);
        String nIris = new Scanner(System.in).nextLine();
        try{
            String[] irisParts = nIris.split(",");
            Iris tmp = new Iris(Double.parseDouble(irisParts[0]), Double.parseDouble(irisParts[1]), Double.parseDouble(irisParts[2]), Double.parseDouble(irisParts[3]), irisParts[4]);
            int decision = perceptron.calculateOutput(tmp.getAllCoordinatesArray());
            int correctDecision = Objects.equals(tmp.getSpeciesName(), "Iris-setosa") ? 0 : 1;
            checks++;
            if(decision == correctDecision)
                correctChecks++;
            System.out.println("decision: " + (decision == 0? "Iris-setosa" : "Iris-versicolor") +
                    "\tcorrect decision: " + (decision == 0? "Iris-setosa" : "Iris-versicolor") +
                    "\tcurrent accuracy: " + correctChecks*100/checks + "%");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }

    //0 - setosa, 1 - versicolor
    public static void train(){
        Collections.shuffle(trainList);
        System.out.println("""
                --------------
                TRAINING BEGAN
                --------------""");
        trainList.forEach(x->{
            int decision = perceptron.calculateOutput(x.getAllCoordinatesArray());
            int correctDecision = Objects.equals(x.getSpeciesName(), "Iris-setosa") ? 0 : 1;
            perceptron.train(x.getAllCoordinatesArray(), correctDecision, decision);
            System.out.println(perceptron);
        });
        System.out.println("""
                --------------
                TRAINING ENDED
                --------------""");
    }

    public static void test(){
        System.out.println("""
                -------------
                TESTING BEGAN
                -------------""");
        testList.forEach(x->{
            int decision = perceptron.calculateOutput(x.getAllCoordinatesArray());
            int correctDecision = Objects.equals(x.getSpeciesName(), "Iris-setosa") ? 0 : 1;
            checks++;
            if(decision == correctDecision)
                correctChecks++;
            System.out.println("decision: " + (decision == 0? "Iris-setosa" : "Iris-versicolor") +
                    "\tcorrect decision: " + (decision == 0? "Iris-setosa" : "Iris-versicolor") +
                    "\tcurrent accuracy: " + correctChecks*100/checks + "%");
        });
        System.out.println("""
                -------------
                TESTING ENDED
                -------------""");
    }

}
