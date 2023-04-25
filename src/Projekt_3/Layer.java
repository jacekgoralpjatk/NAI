package Projekt_3;

import Projekt_2.Perceptron;
import org.apache.commons.math.stat.descriptive.SummaryStatistics;

import java.util.Arrays;
import java.util.List;

public class Layer {
    private int checks = 0;
    private int accurateChecks = 0;
    private int eac = 0;
    private int pac = 0;
    private List<Perceptron> perceptrons;

    public Layer(List<Perceptron> perceptrons){
        this.perceptrons = perceptrons;
    }

    public void trainLayer(Double[] inputs, int[] expectedOutput){
        for(int i=0; i<perceptrons.size(); i++){
            int out = perceptrons.get(i).calculateOutput(inputs);
            perceptrons.get(i).train(inputs, expectedOutput[i], out);
            System.out.println(i + 1 + " weights: " + perceptrons.get(i));
        }
    }

    public void test(Double[] inputs, int[] expectedOutput){
//        inputs = normalizeInputs(inputs);
        double[] outputs = new double[expectedOutput.length];
        for(int i=0; i<perceptrons.size(); i++){
            outputs[i] = perceptrons.get(i).getNet(inputs);
        }
        checks++;
        int index=0;
        for (int i=0; i<outputs.length; i++){
            if(outputs[i]>outputs[index])
                index = i;
        }
        boolean correct = false;
        if(expectedOutput[index]==1)
            correct = true;

        if(correct)
            accurateChecks++;
        System.out.println("correct: " + Arrays.toString(expectedOutput) + " real: " + Arrays.toString(outputs) + " accurracy: " + (100*accurateChecks/checks) +
                (correct ? " yes" : " no"));
    }

    private Double[] normalizeInputs(Double[] inputs){
        SummaryStatistics stats = new SummaryStatistics();

        // Dodajemy każdą wartość wagi do obiektu SummaryStatistics
        for (double input : inputs) {
            stats.addValue(input);
        }

        // Obliczamy średnią wartość i odchylenie standardowe
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();

        // Tworzymy nowy wektor, który będzie przechowywał znormalizowane wagi
        Double[] normalizedInputs = new Double[inputs.length];

        // Normalizujemy wagi poprzez podzielenie każdej wartości wagi przez odchylenie standardowe
        for (int i = 0; i < inputs.length; i++) {
            normalizedInputs[i] = (inputs[i] - mean) / std;
        }

        return normalizedInputs;
    }

    public void normalizePerceptrons(){
        perceptrons.forEach(x->x.normalize());
    }

    public int getChecks() {
        return checks;
    }

    public int getAccurateChecks() {
        return accurateChecks;
    }
}

