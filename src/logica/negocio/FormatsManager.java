package logica.negocio;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatsManager {

    private FormatsManager() {
        // Esta clase es de soporte, por lo que no se instancia
    }

    static Date date(String dateString)  {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parseDate = new Date();
        try {
            parseDate = simpleDateFormat.parse(dateString);
        }catch (Exception ignored){
            //
        }
        return parseDate;
    }

    static int amountOf(int amount) {
        return amount;
    }

    static String printNumberFormat(int number){
        DecimalFormat printFormat = new DecimalFormat("###,###,###.00");
        String retorno = "";
        if(number!=0){
            retorno = printFormat.format(number);
        }
        return retorno;
    }

    static String printDateFormat(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
}