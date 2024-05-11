package pl.pja.s28201.tpo_09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28201.tpo_09.model.BmiPerson;
import pl.pja.s28201.tpo_09.model.BmrPerson;
import pl.pja.s28201.tpo_09.model.Gender;
import pl.pja.s28201.tpo_09.service.CalculationService;
import pl.pja.s28201.tpo_09.service.DataValidatorService;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {

    private final DataValidatorService dataValidatorService;
    private final CalculationService calculationService;

    @Autowired
    public ApiController(
            DataValidatorService dataValidatorService,
            CalculationService calculationService
    ) {
        this.dataValidatorService = dataValidatorService;
        this.calculationService = calculationService;
    }


    @GetMapping(value = "/BMI", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getBmi(
            @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
            @RequestParam("weight") double weight,
            @RequestParam("height") double height
    ) {
        if (dataValidatorService.isInvalidWeightOrHeight(weight, height)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Reason", "invalid data, weight and height parameters must be positive numbers")
                    .body("400 - Bad Request");
        }

        var human = new BmiPerson(weight, height);
        calculationService.calculateBmiFor(human);

        if (acceptHeader.equals("text/plain")) {
            return ResponseEntity.ok(human.returnDoubleValueBmi() + "");
        }

        return ResponseEntity.ok(human);
    }

    @GetMapping(value = "/BMR/{sex}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getBmr(
            @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
            @PathVariable String sex,
            @RequestParam("weight") double weight,
            @RequestParam("height") double height,
            @RequestParam("age") int age
    ) {
        if (dataValidatorService.isInvalidWeightOrHeightOrAge(weight, height, age)) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                    .header("Reason", "invalid data, weight, height and age parameters must be positive numbers")
                    .body("499 - Invalid Data");
        }

        BmrPerson person = new BmrPerson(weight, height, age);

        if (sex.equalsIgnoreCase("man")) {
            person.setGender(Gender.Man);
        } else if (sex.equalsIgnoreCase("woman")) {
            person.setGender(Gender.Woman);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Reason", "invalid gender data")
                    .body("400 - Bad Request / Invalid Gender Data");
        }

        calculationService.calculateBmrFor(person);

        if (acceptHeader.equals("text/plain")) {
            return ResponseEntity.ok(person.returnBmrWithKCal());
        }

        return ResponseEntity.ok(person);
    }
}
