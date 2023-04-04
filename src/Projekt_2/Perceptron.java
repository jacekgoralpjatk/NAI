package Projekt_2;

import java.util.Arrays;

public class Perceptron {
    private final Double alfa;
    private Double teta;
    private Double[] weights;

    public Perceptron(Double alfa, Double teta, Double[] weights) {
        this.alfa = alfa;
        this.teta = teta;
        this.weights = weights;
    }

    public int calculateOutput(Double[] inputs){
        assert inputs.length == weights.length;
        double net = 0;
        for(int i=0; i<weights.length; i++){
            net += weights[i]*inputs[i];
        }
        return net >= teta ? 1 : 0;
    }

    public void train(Double[] inputs, int correctDecision, int realDecision){
        assert inputs.length == weights.length;
        System.out.println("t: c: "+correctDecision+"r: "+realDecision);
        for(int i=0; i<weights.length; i++){
            //W' = W + (d - y)*a*X
            weights[i] += (correctDecision-realDecision)*alfa*inputs[i];
        }
        teta -= (correctDecision-realDecision)*alfa;
    }

    public Double getAlfa() {
        return alfa;
    }

    public Double getTeta() {
        return teta;
    }

    public void setTeta(Double teta) {
        this.teta = teta;
    }

    public Double[] getWeights() {
        return weights;
    }

    public void setWeights(Double[] weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "teta=" + teta +
                ", weights=" + Arrays.toString(weights) +
                '}';
    }
}
