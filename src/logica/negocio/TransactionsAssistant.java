package logica.negocio;

import java.util.*;

public class TransactionsAssistant extends TransactionHistoric {

    void executeOperation(int amount, Date date, String operationType) {
        putDayOnMap(date);
        List<Integer> records = operationsByType(date, operationType);
        records.add(amount);
        historicOfOperations.get(date).put(operationType, records);
        updateDailyBalance();
    }

    private void putDayOnMap(Date date) {
        historicOfOperations.computeIfAbsent(date, x -> defaultInternalMap());
    }

    private Map<String, List<Integer>> defaultInternalMap() {
        Map<String, List<Integer>> defaultMap = new LinkedHashMap<>();
        List<Integer> defaultList = new ArrayList<>();
        defaultMap.put("deposits", defaultList);
        defaultMap.put("withdrawals", defaultList);
        defaultMap.put("transfers", defaultList);
        defaultMap.put("dayBalance", defaultList);
        return defaultMap;
    }

    private void updateDailyBalance() {
        int previusDayBalance = 0;
        String dayBalanceKey = "dayBalance";
        for (Map.Entry<Date, Map<String, List<Integer>>> recordsInDate : historicOfOperations.entrySet()) {
            Map<String, List<Integer>> dailyOperations = recordsInDate.getValue();
            int dailyCredits = sumList(dailyOperations.get("deposits")) + previusDayBalance;
            int dailyDebits = sumList(dailyOperations.get("withdrawals"));
            List<Integer> dayBalance = new ArrayList<>();
            dayBalance.add(dailyCredits - dailyDebits);
            dailyOperations.get(dayBalanceKey).clear();
            dailyOperations.put(dayBalanceKey, dayBalance);
            previusDayBalance = sumList(dailyOperations.get(dayBalanceKey));
        }
    }

    private int sumList(List<Integer> recordList) {
        return recordList.stream()
                .reduce(0, Integer::sum);
    }
}