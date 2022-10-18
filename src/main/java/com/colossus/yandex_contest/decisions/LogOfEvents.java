package com.colossus.yandex_contest.decisions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LogOfEvents {

    static class MyLog {
        int time;
        char status;

        public MyLog(int time, char status) {
            this.time = time;
            this.status = status;
        }
    }

    public String run(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String firstLine = reader.readLine();
        int logsQuantity = Integer.parseInt(firstLine);
        String[] logs = new String[logsQuantity];
        int index = 0;
        while (index < logsQuantity){
            logs[index++] = reader.readLine();
        }
        reader.close();

        Map<Integer, List<MyLog>> shipsLogs = new HashMap<>();

        for (String s: logs) {
            String[] parts = s.split(" ");
            int time = Integer.parseInt(parts[0]) * 24 * 60 + Integer.parseInt(parts[1]) * 60 + Integer.parseInt(parts[2]);
            char status = parts[4].charAt(0);
            int id = Integer.parseInt(parts[3]);
            if (!shipsLogs.containsKey(id)) {
                List<MyLog> arrayList = new ArrayList<>();
                arrayList.add(new MyLog(time, status));
                shipsLogs.put(id, arrayList);
            } else {
                List<MyLog> array = shipsLogs.get(id);
                array.add(new MyLog(time, status));
                shipsLogs.put(id, array);
            }
        }

            List<Integer> sortedIds = new ArrayList<>(shipsLogs.keySet());
            sortedIds.sort(Integer::compareTo);

            StringBuilder answer = new StringBuilder();
        for (int currentId : sortedIds) {
            List<MyLog> logsFrom = shipsLogs.get(currentId);
            logsFrom.sort(Comparator.comparingInt(a -> a.time));
            answer.append(getTime(logsFrom)).append(" ");
        }
            return answer.toString().trim();
        }

    private String getTime(List<MyLog> logsFrom) {
        int sum = 0;
        int previousTime = 0;
        for (MyLog myLog : logsFrom) {

            int currentTime = myLog.time;
            char currentStatus = myLog.status;
            //   A - B - S   |   A - B - C   |   A - C     guaranteed
            if (currentStatus == 'A') previousTime = currentTime;
            if (currentStatus == 'C' || currentStatus == 'S') sum += currentTime - previousTime;
        }
        return String.valueOf(sum);
    }
}
