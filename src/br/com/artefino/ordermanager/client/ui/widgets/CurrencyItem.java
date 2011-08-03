package br.com.artefino.ordermanager.client.ui.widgets;

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyDownEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyDownHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;

public class CurrencyItem extends TextItem {
    private String prefix = "R$ ";

    private String centsSeparator = ",";

    private String thousandsSeparator = ".";

    private int limit = 0;

    private int centsLimit = 2;

    public CurrencyItem() {
        init();
    }

    public void init() {
        this.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                boolean functional = false;
                @SuppressWarnings("unused")
                String str = "";
                if (CurrencyItem.this.getValue() != null) {
                    str = String.valueOf(CurrencyItem.this.getValue());
                }
                String typed = event.getKeyName();
                // String newValue = price_format(str + typed);

                if (typed.matches("[0-9]")) {
                    functional = true;
                }

                if (typed.equalsIgnoreCase("Backspace")) {
                    functional = true;
                }
                if (typed.equalsIgnoreCase("Tab")) {
                    functional = true;
                }
                if (typed.equalsIgnoreCase("Enter")) {
                    functional = true;
                }
                if (typed.equalsIgnoreCase("Arrow_Left")) {
                    functional = true;
                }
                if (typed.equalsIgnoreCase("Arrow_Right")) {
                    functional = true;
                }
                if (!functional) {
                    event.cancel();
                    // if (!str.equals(newValue)) {
                    // CurrencyItem.this.setValue(newValue);
                    // }
                }
            }
        });

        this.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                price_it();
            }
        });

    }

    public CurrencyItem(String prefix, String centsSeparator,
            String thousandsSeparator, int limit, int centsLimit) {
        this.prefix = prefix;
        this.centsSeparator = centsSeparator;
        this.thousandsSeparator = thousandsSeparator;
        this.limit = limit;
        this.centsLimit = centsLimit;
        init();
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
        }
        return str;
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

    public void price_it() {
        String str = "";
        if (getValue() != null) {
            str = getValue().toString();
        }
        String price = price_format(str);
        if (!str.equals(price)) {
            this.setValue(price);
        }
    }

    public Double getValor() {
        String value = (String) getValue();
        if (value != null) {
            if (getPrefix() != null) {
                value = value.replace(getPrefix(), "");
            }
            return Double.parseDouble(value.replace(".", "").replace(",", "."));
        }
        return null;
    }

    public void setValor(Double value) {
        NumberFormat nf = NumberFormat.getFormat("#,##0.00");
        if (value != null) {
            if (getPrefix() != null) {
                setValue(getPrefix() + nf.format(value));
            } else {
                setValue(nf.format(value));
            }
        }

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

}
