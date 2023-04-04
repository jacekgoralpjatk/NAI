package Projekt_2;

import java.util.List;

public class Iris {
    private final double sepalLengthCm;
    private final double sepalWidthCm;
    private final double petalLengthCm;
    private final double petalWidthCm;
    private String speciesName;

    public Iris(double sepalLengthCm, double sepalWidthCm, double petalLengthCm, double petalWidthCm, String speciesName) {
        this.sepalLengthCm = sepalLengthCm;
        this.sepalWidthCm = sepalWidthCm;
        this.petalLengthCm = petalLengthCm;
        this.petalWidthCm = petalWidthCm;
        this.speciesName = speciesName;
    }

    public Iris(double sepalLengthCm, double sepalWidthCm, double petalLengthCm, double petalWidthCm) {
        this.sepalLengthCm = sepalLengthCm;
        this.sepalWidthCm = sepalWidthCm;
        this.petalLengthCm = petalLengthCm;
        this.petalWidthCm = petalWidthCm;
    }

    public List<Double> getAllCoordinatesList(){
        return List.of(sepalLengthCm, sepalWidthCm, petalLengthCm, petalWidthCm);
    }

    public Double[] getAllCoordinatesArray(){
        return getAllCoordinatesList().toArray(new Double[0]);
    }

    public double getSepalLengthCm() {
        return sepalLengthCm;
    }

    public double getSepalWidthCm() {
        return sepalWidthCm;
    }

    public double getPetalLengthCm() {
        return petalLengthCm;
    }

    public double getPetalWidthCm() {
        return petalWidthCm;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "sepalLengthCm=" + sepalLengthCm +
                ", sepalWidthCm=" + sepalWidthCm +
                ", petalLengthCm=" + petalLengthCm +
                ", petalWidthCm=" + petalWidthCm +
                '}';
    }
}
