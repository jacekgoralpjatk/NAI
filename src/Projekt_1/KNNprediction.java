package Projekt_1;

import java.io.*;
import java.util.*;

public class KNNprediction {
    public static int k;
    public static File trainSet;
    public static File testSet;
    public static List<Iris> trainList;
    public static List<Iris> testList;
    public static int countChecked = 0;
    public static int countSuccessfulChecked = 0;

    public static void main(String[] args) throws Exception {
         if(args.length != 3)
             throw new Exception();

         try{
             k = Integer.parseInt(args[0]);
             trainSet = new File(args[1]);
             testSet = new File(args[2]);
         } catch (NumberFormatException e) {
             e.printStackTrace();
         }
         train();
         checkTestList();
         while(true)
             checkUserIris();
    }

    public static void checkUserIris() throws IOException {
        System.out.println("""
                --------------
                1 - test new iris
                2 - exit
                """);
        switch(integerInput()){
            case 2 -> System.exit(0);
            case 1 -> {
                Iris testIris = getNewIrisData();
                System.out.println("Testing:\t" + testIris);
                String name = findKNearest(testIris);
                System.out.println("\tresult: " + name + " real name: " + testIris.getSpeciesName() + " :: accurate: " + testIris.getSpeciesName().equals(name));
                if(testIris.getSpeciesName().equals(name))
                    countSuccessfulChecked++;
                countChecked++;
                System.out.println("accuracy: " + 100*countSuccessfulChecked/countChecked);
            }
            default -> checkTestList();
        }
    }

    private static Iris getNewIrisData() {
        System.out.println("New Iris:\n");
        try {
            String[] irisParts = input().split(",");
            return new Iris(Double.parseDouble(irisParts[0]), Double.parseDouble(irisParts[1]), Double.parseDouble(irisParts[2]), Double.parseDouble(irisParts[3]), irisParts[4]);
        }catch (Exception e){
            System.out.println("invalid data");
            return getNewIrisData();
        }
    }

    public static int integerInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String input(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static double doubleInput(){
        Scanner scanner = new Scanner(System.in);
        double result;
        try{
            result = Double.parseDouble(scanner.nextLine());
        }catch (Exception e){
            System.out.println("Invalid input, try again: ");
            return doubleInput();
        }
        return result;
    }

    public static void train(){
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
    }

    public static void checkTestList() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(testSet))) {
            testList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] irisParts = line.split(",");
                testList.add(new Iris(Double.parseDouble(irisParts[0]), Double.parseDouble(irisParts[1]), Double.parseDouble(irisParts[2]), Double.parseDouble(irisParts[3]), irisParts[4]));
            }
        }
        for(Iris testIris : testList){
            System.out.println("Testing:\t" + testIris);
            String name = findKNearest(testIris);
            System.out.println("\tresult: " + name + " real name: " + testIris.getSpeciesName() + " :: accurate: " + testIris.getSpeciesName().equals(name));
            if(testIris.getSpeciesName().equals(name))
                countSuccessfulChecked++;
            countChecked++;
        }
        System.out.println("accuracy: " + 100*countSuccessfulChecked/countChecked);
    }

    public static String findKNearest(Iris testedIris){
        List<Pair> distances = new ArrayList<>();
        for(int i=0; i<trainList.size(); i++){
            distances.add(new Pair(i, trainList.get(i).calculateDistanceSquare(testedIris.getAllCoordinatesList())));
        }
        distances.sort(Comparator.comparingDouble(Pair::getDistance));
//        distances.forEach(x -> System.out.println(x.getDistance() + " " + trainList.get(x.getIndex()).getSpeciesName()));
        //0 - setosa, 1 - versicolor, 2 - virginica
        int[] count = {0,0,0};
        for(int i=0; i<k; i++){
            switch (trainList.get(distances.get(i).getIndex()).getSpeciesName()){
                case "Iris-setosa" -> count[0]++;
                case "Iris-versicolor" -> count[1]++;
                default -> count[2]++;
            }
        }
        int max = 0;
        for(int c : count)
            if(max<c)
                max = c;

        if(((count[0]==count[1]||count[1]==count[2])&&count[1]==max)||(count[2]==count[0]&&count[2]==max)) {
            return handleResultConflict(distances.subList(0, k - 1), testedIris);
        }
        return max==count[0] ? "Iris-setosa" : max==count[1] ? "Iris-versicolor" : "Iris-virginica";
    }

    public static String handleResultConflict(List<Pair> distances, Iris testedIris){
        //0 - setosa, 1 - versicolor, 2 - virginica
        double[] distance = {0,0,0};
        for(Pair pair : distances){
            switch (trainList.get(pair.getIndex()).getSpeciesName()){
                case "Iris-setosa" -> distance[0]+=trainList.get(pair.getIndex()).calculateDistanceSquare(testedIris.getAllCoordinatesList());
                case "Iris-versicolor" -> distance[1]+=trainList.get(pair.getIndex()).calculateDistanceSquare(testedIris.getAllCoordinatesList());
                default -> distance[2]+=trainList.get(pair.getIndex()).calculateDistanceSquare(testedIris.getAllCoordinatesList());
            }
        }
        double max = 0;
        for(double d : distance){
            if(max<d)
                max=d;
        }
        return max==distance[0] ? "Iris-setosa" : max==distance[1] ? "Iris-versicolor" : "Iris-virginica";
    }

    public static class Pair{
        int index;
        double distance;
        public Pair(int index, double distance){
            this.distance = distance;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public double getDistance() {
            return distance;
        }
    }

}
