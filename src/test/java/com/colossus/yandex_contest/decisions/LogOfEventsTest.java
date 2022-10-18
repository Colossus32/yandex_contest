package com.colossus.yandex_contest.decisions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LogOfEventsTest {
    LogOfEvents logOfEvents = new LogOfEvents();
    String PATH = "log_of_events.txt";

    @Test
    void log_of_events_should_be_ok() throws IOException {
        assertEquals("156 142", logOfEvents.run(PATH));
    }
}