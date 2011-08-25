package br.com.artefino.ordermanager.client.ui.widgets;

import br.com.artefino.ordermanager.client.util.ValidadoresUtil;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.ChangeEvent;
import com.smartgwt.client.widgets.grid.events.ChangeHandler;

public class ListGridFieldDecimal extends ListGridField {

    private String prefix = "";

    private String centsSeparator = ",";

    private String thousandsSeparator = ".";

    private int limit = 0;

    private int centsLimit = 2;

    private boolean negativo;

    public ListGridFieldDecimal(String name, String title) {
        super(name, title);
        setAlign(Alignment.RIGHT);
        addChangeHandler(new HandlerChanged());
    }

    public ListGridFieldDecimal(String name, String title, String prefix) {
        super(name, title);
        this.prefix = prefix;
        setAlign(Alignment.RIGHT);
        addChangeHandler(new HandlerChanged());
    }

    public ListGridFieldDecimal(String name, String title, String prefix,
            boolean negativo) {
        super(name, title);
        this.prefix = prefix;
        this.negativo = negativo;
        setAlign(Alignment.RIGHT);
        addChangeHandler(new HandlerChanged());
    }

    public ListGridFieldDecimal(String name, String title, boolean negativo) {
        super(name, title);
        setAlign(Alignment.RIGHT);
        addChangeHandler(new HandlerChanged());
        this.negativo = negativo;
    }

    public ListGridFieldDecimal(String name, String title, Double min,
            Double max) {
        super(name, title);
        if (max != null && min != null) {
            this.setValidators(ValidadoresUtil.getValidadorMinimoDecimal(min),
                ValidadoresUtil.getValidadorMaximoDecimal(max));
        }
        if (max != null) {
            this.setValidators(ValidadoresUtil.getValidadorMaximoDecimal(max));
        }
        if (min != null) {
            this.setValidators(ValidadoresUtil.getValidadorMinimoDecimal(min));
        }
        setAlign(Alignment.RIGHT);
        addChangeHandler(new HandlerChanged());
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCentsSeparator() {
        return centsSeparator;
    }

    public void setCentsSeparator(String centsSeparator) {
        this.centsSeparator = centsSeparator;
    }

    public String getThousandsSeparator() {
        return thousandsSeparator;
    }

    public void setThousandsSeparator(String thousandsSeparator) {
        this.thousandsSeparator = thousandsSeparator;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCentsLimit() {
        return centsLimit;
    }

    public void setCentsLimit(int centsLimit) {
        this.centsLimit = centsLimit;
    }

    // format as price
    public String price_format(String str) {

        // formatting settings
        String formatted = fill_with_zeroes(to_numbers(str));
        String thousandsFormatted = "";
        int thousandsCount = 0;

        // split integer from cents
        String centsVal = formatted.substring(
            (formatted.length() - centsLimit), formatted.length());
        String integerVal = formatted.substring(0, formatted.length()
            - centsLimit);

        // apply cents pontuation
        formatted = integerVal + centsSeparator + centsVal;

        // apply thousands pontuation
        if (thousandsSeparator != null) {
            for (int j = integerVal.length(); j > 0; j--) {

                String c = integerVal.substring(j - 1, j);

                thousandsCount++;

                if (thousandsCount % 3 == 0) {
                    c = thousandsSeparator + c;
                }

                thousandsFormatted = c + thousandsFormatted;
            }

            if (thousandsFormatted.substring(0, 1).equals(thousandsSeparator)) {

                thousandsFormatted = thousandsFormatted.substring(1,
                    thousandsFormatted.length());
            }

            formatted = thousandsFormatted + centsSeparator + centsVal;
        }

        // apply the prefix
        if (prefix != null) {
            formatted = prefix + formatted;
        }

        return formatted;

    }

    // skip everything that isn't a number
    // and also skip the left zeroes
    public String to_numbers(String str) {
        String formatted = "";
        for (int i = 0; i < (str.length()); i++) {
            Character c = str.charAt(i);
            if (formatted.length() == 0 && c.equals('0')) {
                c = null;
            }
            if (negativo) {
                if (c != null && c.equals('-') && !prefix.contains("-")) {
                    prefix = prefix + c;
                }
                if (c != null && c.equals('+') && prefix.contains("-")) {
                    prefix = "";
                }
            }
            if (c != null && Character.isDigit(c)) {
                if (limit != 0) {
                    if (formatted.length() < limit) {
                        formatted = formatted + c;
                    }
                } else {
                    formatted = formatted + c;
                }
            }
        }
        return formatted;
    }

    // format to fill with zeros to complete cents chars
    public String fill_with_zeroes(String str) {
        while (str.length() < (centsLimit + 1)) {
            str = "0" + str;
            if (negativo) {
                if (Integer.parseInt(str) == 0 && prefix.equals("-")) {
                    prefix = "";
                }
            }
        }
        return str;
    }

    class HandlerChanged implements ChangeHandler {
        @Override
        public void onChange(ChangeEvent event) {
            String value = "";
            if (event.getValue() != null) {
                value = (String) event.getValue();
            }
            String price = price_format(value);
            if (!value.equals(price)) {
                event.getItem().setValue(price);
            }

        }
    }
}
