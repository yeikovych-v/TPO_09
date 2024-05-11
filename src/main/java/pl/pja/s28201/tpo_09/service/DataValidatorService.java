package pl.pja.s28201.tpo_09.service;

import org.springframework.stereotype.Service;

@Service
public class DataValidatorService {

    public boolean isInvalidWeightOrHeight(double weight, double height) {
        return weight <= 0 || height <= 0;
    }

    public boolean isInvalidWeightOrHeightOrAge(double weight, double height, int age) {
        return weight <= 0 || height <= 0 || age <= 0;
    }
}
