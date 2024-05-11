package pl.pja.s28201.tpo_09.model;

public class BmiPerson {

    private double weight;
    private double height;
    private double bmi;
    private BmiType bmiType = BmiType.Undetermined;

    public BmiPerson(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getBmi() {
        return (int) bmi;
    }

    public double returnDoubleValueBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setBmiType(BmiType bmiType) {
        this.bmiType = bmiType;
    }

    public BmiType getBmiType() {
        return bmiType;
    }
}
