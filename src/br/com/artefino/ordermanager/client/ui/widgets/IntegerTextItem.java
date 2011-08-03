package br.com.artefino.ordermanager.client.ui.widgets;

import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

public class IntegerTextItem extends TextItem {
    private int limit = 0;

    public IntegerTextItem() {
        addChangeHandler(new HandlerChanged());
    }

    public IntegerTextItem(String name) {
        super(name);
        addChangeHandler(new HandlerChanged());
    }

    class HandlerChanged implements ChangeHandler {
        @Override
        public void onChange(ChangeEvent event) {
            String value = "";
            if (event.getValue() != null) {
                value = (String) event.getValue();
            }
            String newValue = to_numbers(value);
            if (!value.equals(newValue)) {
                event.getItem().setValue(newValue);
            }

        }

        public String to_numbers(String str) {
            String formatted = "";
            for (int i = 0; i < (str.length()); i++) {
                Character c = str.charAt(i);
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
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
