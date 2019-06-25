package chc.eletrica8.uteis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtil {

    public static String Atual() {

        String date = getDia() + "/" + getMes() + "/" + getAno();

        return date;
    }

    public static Calendar dateToCalendar(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return cal;
    }

    public static String dataToString(Date data1) {

        Calendar data = DataUtil.dateToCalendar(data1);

        int dia = data.get(Calendar.DAY_OF_MONTH);
        int mes = data.get(Calendar.MONTH) + 1;
        int ano = data.get(Calendar.YEAR);
        String date = dia + "/" + mes + "/" + ano;

        return date;
    }

    public static String dataToString(Calendar data) {

        int dia = data.get(Calendar.DAY_OF_MONTH);
        int mes = data.get(Calendar.MONTH) + 1;
        int ano = data.get(Calendar.YEAR);
        String date = dia + "/" + mes + "/" + ano;

        return date;
    }

    public static int getAno() {
        Calendar cal = new GregorianCalendar();
        int ano = cal.get(Calendar.YEAR);

        return ano;
    }

    public static int getDia() {
        Calendar cal = new GregorianCalendar();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return dia;
    }

    public static int getMes() {
        Calendar cal = new GregorianCalendar();
        int mes = cal.get(Calendar.MONTH) + 1;

        return mes;
    }

    public static Calendar stringToCalendar(String data) {

        Calendar cal = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cal = Calendar.getInstance();
            cal.setTime(sdf.parse(data));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }

    public static Date stringToData(String data) {
        Date dat = DataUtil.stringToCalendar(data).getTime();

        return dat;
    }

    private DataUtil() {
        throw new IllegalStateException("Utility class");

    }
}
