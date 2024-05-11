package pl.pja.s28201.tpo_09.model;

public enum BmiType {

    Undetermined,
    UnderWeight,
    Normal,
    OverWeight,
    Obese;

    public static BmiType from(double bmi) {
        if (bmi < 18.5) return BmiType.UnderWeight;
        if (bmi >= 18.5 && bmi < 25) return BmiType.Normal;
        if (bmi >= 25 && bmi < 30) return BmiType.OverWeight;
        return BmiType.Obese;
    }
}
