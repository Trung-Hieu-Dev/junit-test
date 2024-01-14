package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {
    // If number is divisible by 3, print Fizz
    @Test
    @DisplayName("Divisible By Three")
    @Order(1)
    void testForDivisibleByThree() {
        String expected = "Fizz";

        assertEquals(expected, FizzBuzz.compute(3), "Should return Fizz");
    }

    // If number is divisible by 5, print Buzz
    @Test
    @DisplayName("Divisible By Five")
    @Order(2)
    void testForDivisibleByFive() {
        String expected = "Buzz";

        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    // If number is divisible by 3 and 5, print FizzBuzz
    @Test
    @DisplayName("Divisible By Three and Five")
    @Order(3)
    void testForDivisibleByThreeAndFive() {
        String expected = "FizzBuzz";

        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    // If number is NOT divisible by 3 or 5, then print the number
    @Test
    @DisplayName("Not Divisible By Three and Five")
    @Order(4)
    void testForNotDivisibleByThreeAndFive() {
        String expected = "8";

        assertEquals(expected, FizzBuzz.compute(8), "Should return 8");
    }

    // test loop over array
    @Test
    @DisplayName("Loop over Arrays")
    @Order(5)
    void testForLoopOverArrays() {

        String [][] data = {
                // {"value, "expected}
                {"1", "1"},
                {"2", "2"},
                {"3", "Fizz"},
                {"4", "4"},
                {"5", "Buzz"},
                {"6", "Fizz"},
                {"7", "7"},
        };

        for (String[] item : data) {
            String value = item[0];
            String expected = item[1];
            assertEquals(expected, FizzBuzz.compute(Integer.parseInt(value)), "Should return " + expected);
        }

    }

    // use @ParameterizedTest to test with given data => Config data source: @CsvSource, @ValueSource, .....
    @ParameterizedTest
    @DisplayName("Testing with CSV data")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, Fizz",
            "4, 4",
            "5, Buzz",
            "6, Fizz",
            "7, 7"
    })
    @Order(6)
    void testCSVData(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value), "Should return " + expected);
    }

    // testing with data file
    @ParameterizedTest(name = "value = {0}, expected= {1}")
    @DisplayName("Testing with small data file")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(7)
    void testDataFile(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value), "Should return " + expected);
    }
}
