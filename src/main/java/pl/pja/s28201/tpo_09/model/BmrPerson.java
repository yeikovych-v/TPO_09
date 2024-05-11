package pl.pja.s28201.tpo_09.model;

public class BmrPerson {

    private Gender gender = Gender.Undefined;
    private double weight;
    private double height;
    private int age;
    private double bmr;

    public BmrPerson(double weight, double height, int age) {
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getBmr() {
        return (int) bmr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public String returnBmrWithKCal() {
        return getBmr() + "kcal";
    }
}
