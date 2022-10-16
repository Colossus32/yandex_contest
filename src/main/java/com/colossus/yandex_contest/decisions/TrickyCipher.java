package com.colossus.yandex_contest.decisions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TrickyCipher {
    public String run(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String countOfPersons = reader.readLine();
        int persons = Integer.parseInt(countOfPersons);

        String[] data = new String[persons];
        int index = 0;
        while (persons-- > 0){
            data[index++] = reader.readLine();
        }

        StringBuilder answer = new StringBuilder();

        for (String datum : data) {
            String cipher = getCipher(datum);
            answer.append(cipher).append(" ");
        }

        return answer.toString().trim();
    }

    private String getCipher(String datum) {
        String[] array = datum.split(",");
        int firstPart = getFirstPart(array);
        int secondPart = getSecondPart(array);
        int thirdPart = getThirdPart(array);
        String answer = Integer.toHexString(firstPart + secondPart + thirdPart);
        return answer.substring(answer.length() - 3).toUpperCase();
    }

    private int getThirdPart(String[] array) {
        char firstLetter = array[0].toLowerCase().charAt(0);

        String abc = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < abc.length(); i++) {
            if (firstLetter == abc.charAt(i)) return (i + 1) * 256;
        }
        return 0;
    }

    private int getSecondPart(String[] datum) {

        String dayMonth = datum[3] + datum[4];
        int sum = 0;
        for (int i = 0; i < dayMonth.length(); i++) {
            sum += Integer.parseInt(String.valueOf(dayMonth.charAt(i)));
        }
        return sum * 64;
    }

    private int getFirstPart(String[] datum) {

        String firstThree = datum[0] + datum[1] + datum[2];
        Set<Character> uniqueLetters = new HashSet<>();
        for (int i = 0; i < firstThree.length(); i++) {
            uniqueLetters.add(firstThree.charAt(i));
        }
        return uniqueLetters.size();
    }
}
