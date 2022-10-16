package com.colossus.yandex_contest.decisions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlchemistTest {

    Alchemist alchemist = new Alchemist();
    String PATH = "alchemist_path.txt";

    @Test
    void should_return_100() throws IOException {
        assertEquals("100", alchemist.canICraftPotions(PATH));
    }
}