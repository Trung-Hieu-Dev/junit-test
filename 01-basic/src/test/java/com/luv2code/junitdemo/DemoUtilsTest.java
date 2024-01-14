package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class) // create name automatically by removing parentheses of methods
class DemoUtilsTest {
    DemoUtils demoUtils;

    @BeforeEach
    void setUpBeforeEach() { // run before every method in this test class
        this.demoUtils = new DemoUtils();
    }

    @Test
//    @DisplayName("Equal or Not Equal") // configure name displayed
    void add() {
        // when
        int expected = 6;
        int unexpected = 10;

        // then
        assertEquals(expected, demoUtils.add(1, 5), "1 + 5 must equals to 6");
        assertNotEquals(unexpected, demoUtils.add(1, 5), "1 + 5 must not equals to 10");
    }

    @Test
//    @DisplayName("Null or Not Null") // configure name displayed
    void checkNull() {
        // when
        String str1 = null;
        String str2 = "Hello World";

        // then
        assertNull(demoUtils.checkNull(str1), "Obj is null");
        assertNotNull(demoUtils.checkNull(str2), "Obj is not null");
    }
}