package Projekt_3;

import Projekt_2.Perceptron;

import java.util.List;

public class Layer {
    private List<Perceptron> perceptrons;

    public Layer(List<Perceptron> perceptrons){
        this.perceptrons = perceptrons;
    }

    public void trainLayer(Double[] inputs, int[] expectedOutput){
        int[] outputs = new int[perceptrons.size()];
        for(int i=0; i<perceptrons.size(); i++){
            int out = perceptrons.get(i).calculateOutput(inputs);
            perceptrons.get(i).train(inputs, expectedOutput[i], out);
        }
    }

}
