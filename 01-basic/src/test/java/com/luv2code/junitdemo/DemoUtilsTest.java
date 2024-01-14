package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    @Test
    void add() {
        // given
        DemoUtils demoUtils = new DemoUtils();

        // when
        int expected = 6;
        int unexpected = 10;

        // then
        assertEquals(expected, demoUtils.add(1, 5), "1 + 5 must equals to 6");
        assertNotEquals(unexpected, demoUtils.add(1, 5), "1 + 5 must not equals to 10");
    }

    @Test
    void checkNull() {
        // given
        DemoUtils demoUtils = new DemoUtils();

        // when
        String str1 = null;
        String str2 = "Hello World";

        // then
        assertNull(demoUtils.checkNull(str1), "Obj is null");
        assertNotNull(demoUtils.checkNull(str2), "Obj is not null");
    }
}