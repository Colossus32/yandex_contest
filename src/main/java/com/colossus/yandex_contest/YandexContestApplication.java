package com.colossus.yandex_contest;

import com.colossus.yandex_contest.decisions.Alchemist;
import com.colossus.yandex_contest.decisions.LogOfEvents;
import com.colossus.yandex_contest.decisions.TrickyCipher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class YandexContestApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(YandexContestApplication.class, args);
        Alchemist t1 = new Alchemist();
        System.out.println(t1.canICraftPotions("alchemist_path.txt"));
        TrickyCipher cipher = new TrickyCipher();
        System.out.println(cipher.run("tricky_cipher_path.txt"));
        LogOfEvents logOfEvents = new LogOfEvents();
        System.out.println(logOfEvents.run("log_of_events.txt"));
    }
}
