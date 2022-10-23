package com.colossus.yandex_contest.decisions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SomethingDreamedTest {

    SomethingDreamed somethingDreamed = new SomethingDreamed();
    String PATH = "something_dreamed.txt";
    @Test
    void something_dreamed_should_be_ok() throws IOException {
        assertEquals("7 10 5 2 8 4 9 1 6 3", somethingDreamed.run(PATH));
    }
}