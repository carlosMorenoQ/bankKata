package logica.negocio;


import java.util.*;

public class TransactionHistoric {

    protected Map<Date, Map<String, List<Integer>>> historicOfOperations = new TreeMap<>();

    protected List<Integer> operationsByType(Date date, String type) {
        return new ArrayList<>(historicOfOperations.get(date)
                    .get(type));
    }

    protected List<Date> daysWithRecords(){
        return new ArrayList<>(historicOfOperations.keySet());
    }

    protected int sumOperationsByType(Date date, String type){
        return historicOfOperations.get(date)
                .get(type)
                .stream()
                .reduce(0,Integer::sum);
    }
}
