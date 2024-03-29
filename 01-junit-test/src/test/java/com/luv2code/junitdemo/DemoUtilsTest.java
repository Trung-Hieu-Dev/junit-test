package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// @DisplayNameGeneration(DisplayNameGenerator.Simple.class) // create name automatically by removing parentheses of methods
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class) // auto create displayed name with <DemoUtilsTest> <methods name>
//@TestMethodOrder(MethodOrderer.DisplayName.class) // run test case order by displayed name
//@TestMethodOrder(MethodOrderer.MethodName.class) // run test case order by method name
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // run test case order by @Order. small num run first
class DemoUtilsTest {
    DemoUtils demoUtils;

    @BeforeEach
    void setUpBeforeEach() { // run before every method in this test class
        this.demoUtils = new DemoUtils();
    }

    @Test
    @DisplayName("Equal or Not Equal") // configure name displayed
    @Order(1)
    void add() {
        // when
        int expected = 6;
        int unexpected = 10;

        // then
        assertEquals(expected, demoUtils.add(1, 5), "1 + 5 must equals to 6");
        assertNotEquals(unexpected, demoUtils.add(1, 5), "1 + 5 must not equals to 10");
    }

    @Test
    @DisplayName("Null or Not Null") // configure name displayed
    @Order(3)
    void checkNull() {
        // when
        String str1 = null;
        String str2 = "Hello World";

        // then
        assertNull(demoUtils.checkNull(str1), "Obj is null");
        assertNotNull(demoUtils.checkNull(str2), "Obj is not null");
    }

    @Test
    @DisplayName("Same or Not Same")
    @Order(2)
    void sameOrNotSame() { // test if referring to the same obj or not
        String str1 = "luv2code";

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Refer to the same obj");
        assertNotSame(str1, demoUtils.getAcademy(), "Not referring to the same obj");
    }

    @Test
    @DisplayName("Test true false")
    @Order(5)
    void isGreater() {
        int num1 = 9;
        int num2 = 5;

        // test true false
        assertTrue(demoUtils.isGreater(num1, num2), "num1 is greater than num2");
        assertFalse(demoUtils.isGreater(num2, num1), "num2 is not greater than num1");
    }

    @Test
    @DisplayName("Arrays equals") // compare arrays
    @Order(4)
    void getFirstThreeLettersOfAlphabet() {
        String[] myChars = {"A", "B", "C"};

        assertArrayEquals(myChars, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
    }

    @Test
    @DisplayName("Iterable equal") // test ArrayList, HashSet, HasMap... of Collection
    @Order(6)
    void getAcademyInList() {
        List<String> myList = List.of("luv", "2", "code");

        assertIterableEquals(myList, demoUtils.getAcademyInList(), "Lists should be the same");
    }

    @Test
    @DisplayName("Lines match") // compare both list of strings match
    @Order(7)
    void testLinesMatch() {
        List<String> myList = List.of("luv", "2", "code");

        assertLinesMatch(myList, demoUtils.getAcademyInList(), "Lists should be the same");
    }

    @Test
    @DisplayName("Throws or Not throws")
    @Order(8)
    void throwException() {
        int validNum = 5;
        int invalidNum = -1;

        assertThrows(Exception.class, () -> {demoUtils.throwException(invalidNum);}, "Should throw exception");
        assertDoesNotThrow(() -> {demoUtils.throwException(validNum);}, "Should not throw exception");
    }

    @Test
    @DisplayName("Timeout")
    @Order(9)
    void checkTimeout() {
        assertTimeout(Duration.ofSeconds(3), () -> {demoUtils.checkTimeout();}, "Method should execute in 3 seconds");
    }
}