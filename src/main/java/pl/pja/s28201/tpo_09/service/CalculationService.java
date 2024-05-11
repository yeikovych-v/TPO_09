package pl.pja.s28201.tpo_09.service;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_09.model.BmiPerson;
import pl.pja.s28201.tpo_09.model.BmiType;
import pl.pja.s28201.tpo_09.model.BmrPerson;
import pl.pja.s28201.tpo_09.model.Gender;

@Service
public class CalculationService {

    public void calculateBmiFor(BmiPerson bmiPerson) {
        var weight = bmiPerson.getWeight();
        var height = bmiPerson.getHeight();

        var bmi = calculateBmi(weight, height);

        bmiPerson.setBmi(bmi);
        bmiPerson.setBmiType(BmiType.from(bmi));
    }

    private double calculateBmi(double weight, double height) {
        var bmi = weight / ((height / 100.) * (height / 100.));
        return Math.round(bmi * 100.) / 100.;
    }

    public void calculateBmrFor(BmrPerson person) {
        if (person.getGender() == Gender.Undefined) return;

        var weight = person.getWeight();
        var height = person.getHeight();
        var age = person.getAge();

        switch (person.getGender()) {
            case Man -> person.setBmr(calculateBmrForMen(weight, height, age));
            case Woman -> person.setBmr(calculateBmrForWomen(weight, height, age));
        }
    }

    private double calculateBmrForMen(double weight, double height, int age) {
        var bmr = 66.473 + (13.7516 * weight) + (5.0033 * height) - (6.755 * age);
        return Math.round(bmr * 100.) / 100.;
    }

    private double calculateBmrForWomen(double weight, double height, int age) {
        var bmr = 655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age);
        return Math.round(bmr * 100.) / 100.;
    }
}
