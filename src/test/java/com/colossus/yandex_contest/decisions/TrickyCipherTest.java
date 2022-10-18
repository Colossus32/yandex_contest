package com.colossus.yandex_contest.decisions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TrickyCipherTest {

    TrickyCipher cipher = new TrickyCipher();
    String PATH = "tricky_cipher_path.txt";
    @Test
    void tricky_cipher_should_be_ok() throws IOException {
        assertEquals("710 64F", cipher.run(PATH));
    }
}