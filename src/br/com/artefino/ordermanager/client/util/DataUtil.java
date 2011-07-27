package br.com.artefino.ordermanager.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public final class DataUtil {
    static DateTimeFormat dtf = DateTimeFormat
        .getFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static String formatarData(Date data) {
        return dtf.format(data);
    }

    public static String formatarData(Date data, String mascara) {
        DateTimeFormat dtf = DateTimeFormat.getFormat(mascara);
        return dtf.format(data);
    }

    public static Date retornarData(String data) {
        if (data != null) {
            return dtf.parse(data.substring(0, 19));
        }
        return null;
    }

    public static Date retornarData(String data, String mascara) {
        if (data != null) {
            DateTimeFormat dtf = DateTimeFormat.getFormat(mascara);
            return dtf.parse(data);
        }
        return null;
    }

    public static String formataData(String data) {
        return data.replaceFirst("(\\d{4})-(\\d{2})-(\\d{2})(T.*)", "$3/$2/$1");
    }

    public static String addAno(String data) {
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        int ano = Integer.valueOf(data.substring(6, 10)) + 1;
        String mesStr = mes;
        String diaStr = dia;
        if (ano % 4 == 0 && mes.equals("02") && dia.equals("29")) {
            dia = "28";
        }
        return diaStr + "/" + mesStr + "/" + ano;
    }

    /**
     * Método que retorna a diferença entre datas
     * em dias
     *
     * @param start
     * @param end
     * @return String
     */
    public static String diffInDays(Date start, Date end) {
        double diff = end.getTime() - start.getTime();
        return String.valueOf((diff / 1000) / 60 / 60 / 24).split("\\.")[0];
    }
}
