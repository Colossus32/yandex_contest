package com.colossus.yandex_contest.decisions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alchemist {

    public String canICraftPotions(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String firstLine = reader.readLine();
        int numberOfReceipts = Integer.parseInt(firstLine) - 2; // bcz 1 - A, 2 - B

        List<List<Integer>> receipts = new ArrayList<>();
        receipts.add(Collections.singletonList(0));
        receipts.add(Collections.singletonList(1));
        receipts.add(Collections.singletonList(2));

        while (numberOfReceipts-- > 0){
            String[] parts = reader.readLine().split(" ");
            List<Integer> temp = new ArrayList<>();

            for (int i = 1; i < parts.length; i++) {
                temp.add(Integer.parseInt(parts[i]));
            }
            receipts.add(temp);
        }

        String secondLine = reader.readLine();
        int countOfQuestions = Integer.parseInt(secondLine);

        List<List<Integer>> questions = new ArrayList<>();
        while (countOfQuestions-- > 0){
            String[] parts = reader.readLine().split(" ");
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < parts.length; i++) {
                temp.add(Integer.parseInt(parts[i]));
            }
            questions.add(temp);
        }

        reader.close();

        for (int i = 3; i < receipts.size(); i++) {
            List<Integer> current = receipts.get(i);
            List<Integer> converted = convert(i, current, receipts);
            if (converted.contains(0)) converted = Collections.singletonList(0);
            receipts.set(i, converted);
        }

        return checkIngredients(receipts,questions);
    }

    private String checkIngredients(List<List<Integer>> receipts, List<List<Integer>> questions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            List<Integer> currentQuestion = questions.get(i);
            int potionNumber = currentQuestion.get(2);
            List<Integer> requestedReceipt = receipts.get(potionNumber);
            if (requestedReceipt.contains(0)) sb.append(0);
            else {
                int countA = currentQuestion.get(0);
                int countB = currentQuestion.get(1);
                int[] receiptRequest = compact(receipts.get(potionNumber));
                if (countA >= receiptRequest[0] && countB >= receiptRequest[1]) sb.append("1");
                else sb.append("0");
            }
        }
        return sb.toString();
    }

    private int[] compact(List<Integer> integers) {

        int sumA = 0;
        int sumB = 0;
        for (int i: integers) {
            if (i % 2 == 0) sumB++;
            else sumA++;
        }
        return new int[]{sumA,sumB};
    }

    private List<Integer> convert(int i, List<Integer> current, List<List<Integer>> receipts) {
        if (current.contains(0)) return Collections.singletonList(0);
        List<Integer> result = new ArrayList<>();
        for (int j = 0; j < current.size(); j++) {
            int cur = current.get(j);
            if (cur < 3) result.add(cur);
            else {
                List<Integer> toCall = receipts.get(cur);
                if (toCall.contains(i) || toCall.contains(0)) return Collections.singletonList(0);
                else result.addAll(convert(cur, receipts.get(cur),receipts));
            }
        }
        return result;
    }
}
