package Projekt_3;

import Projekt_2.Perceptron;

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
        int[] outputs = new int[expectedOutput.length];
        for(int i=0; i<perceptrons.size(); i++){
            outputs[i] = perceptrons.get(i).calculateOutput(inputs);
        }
        checks++;
        boolean correct = outputs[0] == expectedOutput[0] && outputs[1] == expectedOutput[1];
        if(correct)
            accurateChecks++;
        System.out.println("correct: " + Arrays.toString(expectedOutput) + " real: " + Arrays.toString(outputs) + " accurracy: " + (100*accurateChecks/checks) +
                (correct ? " yes" : " no"));
    }

}
