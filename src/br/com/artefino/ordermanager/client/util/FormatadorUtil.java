package br.com.artefino.ordermanager.client.util;

import java.util.Map;

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * Classe utilitária para trabalhar com formatações.
 *
 * @author Kermeson
 */
public class FormatadorUtil {

    public static String formatarCPF(Long cpf) {
        NumberFormat numberFormat = NumberFormat.getFormat("00000000000");
        return numberFormat.format(cpf).replaceFirst(
            "(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String formatarCNPJ(Long cnpj) {
        NumberFormat numberFormat = NumberFormat.getFormat("00000000000000");
        return numberFormat.format(cnpj).replaceFirst(
            "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    public static String formataHora(String data) {
        return data.replaceFirst("(.*T)(\\d{2}):(\\d{2})(:.*)", "$2:$3");
    }

    public static String formataCEP(String cep) {
        return cep.replaceFirst("^(\\d{2})(\\d{3})(\\d{3})$", "$1.$2-$3");
    }

    public static CellFormatter getCellFormatterDecimal() {
        CellFormatter cellFormatter = new CellFormatter() {
            public String format(Object value, ListGridRecord record,
                    int rowNum, int colNum) {
                if (value == null) {
                    return null;
                }
                NumberFormat nf = NumberFormat.getFormat("#,##0.00");
                try {
                    return nf.format(Double.valueOf(value.toString()));
                } catch (Exception e) {
                    return value.toString();
                }
            }
        };
        return cellFormatter;
    }

    public static CellFormatter getCellFormatterData() {
        CellFormatter cellFormatter = new CellFormatter() {
            public String format(Object value, ListGridRecord record,
                    int rowNum, int colNum) {
                String valueFormated = null;
                if (value != null) {
                    valueFormated = DataUtil.formataData(value.toString());
                }
                return valueFormated;
            }
        };
        return cellFormatter;
    }

    public static CellFormatter getCellFormatterMoeda() {
        CellFormatter cellFormatter = new CellFormatter() {
            public String format(Object value, ListGridRecord record,
                    int rowNum, int colNum) {
                if (value != null) {
                    NumberFormat nf = NumberFormat.getFormat("#,##0.00");
                    try {
                        return "R$ "
                            + nf.format(Double.valueOf(value.toString()));
                    } catch (Exception e) {
                        return value.toString();
                    }
                }
                return null;
            }
        };
        return cellFormatter;
    }

    @SuppressWarnings("unchecked")
    public static String formatarErrosFormulario(DynamicForm form) {
        Map<String, Object> erros = form.getErrors();
        String msg = "";
        if (erros.size() > 0) {
            msg += "<ul>";
            for (String fieldName : erros.keySet()) {
                if (erros.get(fieldName) != null) {
                    msg += "<li>";
                    msg += "<strong>" + form.getField(fieldName).getTitle()
                        + ":</strong> " + String.valueOf(erros.get(fieldName));
                    msg += "</li>";
                }
            }
            msg += "</ul>";
        }
        return msg;
    }

    public static Double getFormatDouble(String valor) {
        Double retorno = 0.0;
        if (!valor.equals("null")) {
            retorno = Double.parseDouble(valor.replace("R$ ", "").replace(".",
                "").replace(",", "."));
        }
        return retorno;
    }

    public static Float getFormatFloat(String valor) {
        return Float.parseFloat(valor.replace(".", "").replace(",", "."));
    }

    public static String formatarDecimal(Double value) {
        NumberFormat nf = NumberFormat.getFormat("#,##0.00");
        return nf.format(value);
    }

}
