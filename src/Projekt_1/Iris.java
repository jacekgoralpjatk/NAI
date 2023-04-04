package Projekt_1;

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

    public double calculateDistanceSquare(List<Double> coordinates){
        double distance = 0;
        List<Double> thisCoordinates = this.getAllCoordinatesList();
        for(int i=0; i<coordinates.size(); i++){
            distance += Math.pow((coordinates.get(i)-thisCoordinates.get(i)), 2);
        }
        return distance;
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
