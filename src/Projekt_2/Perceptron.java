package Projekt_2;

import org.apache.commons.math.stat.descriptive.SummaryStatistics;

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
        return getNet(inputs) >= 0 ? 1 : 0;
    }

    public void train(Double[] inputs, int correctDecision, int realDecision){
        assert inputs.length == weights.length;
        System.out.println("t: c: "+correctDecision+" r: "+realDecision);
        for(int i=0; i<weights.length; i++){
            //W' = W + (d - y)*a*X
            weights[i] += (correctDecision-realDecision)*alfa*inputs[i];
        }
        teta -= (correctDecision-realDecision)*alfa;
    }

    public Double getNet(Double[] inputs){
        assert inputs.length == weights.length;
        double net = 0;
        for(int i=0; i<weights.length; i++){
            net += weights[i]*inputs[i];
        }
        net -= teta;
        return net;
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

    public void normalize() {
        // Tworzymy obiekt SummaryStatistics do obliczania średniej i odchylenia standardowego
        SummaryStatistics stats = new SummaryStatistics();

        // Dodajemy każdą wartość wagi do obiektu SummaryStatistics
        for (double weight : weights) {
            stats.addValue(weight);
        }

        // Obliczamy średnią wartość i odchylenie standardowe
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();

        // Tworzymy nowy wektor, który będzie przechowywał znormalizowane wagi
        Double[] normalizedWeights = new Double[weights.length];

        // Normalizujemy wagi poprzez podzielenie każdej wartości wagi przez odchylenie standardowe
        for (int i = 0; i < weights.length; i++) {
            normalizedWeights[i] = (weights[i] - mean) / std;
        }

        weights = normalizedWeights;
    }

}
