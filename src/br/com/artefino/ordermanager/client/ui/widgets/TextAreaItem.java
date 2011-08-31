package br.com.artefino.ordermanager.client.ui.widgets;

import com.smartgwt.client.types.CharacterCasing;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

public class TextAreaItem extends
		com.smartgwt.client.widgets.form.fields.TextAreaItem {
	private CharacterCasing characterCasing;

	public TextAreaItem() {
		addChangeHandler(new HandlerChanged());
	}

	public TextAreaItem(String name) {
		super(name);
		addChangeHandler(new HandlerChanged());
	}

	public void setCharacterCasing(CharacterCasing characterCasing) {
		this.characterCasing = characterCasing;
	}

	public CharacterCasing getCharacterCasing() {
		return characterCasing;
	}

	class HandlerChanged implements ChangeHandler {
		@Override
		public void onChange(ChangeEvent event) {
			String value = "";
			if (event.getValue() != null) {
				value = (String) event.getValue();
			}

			if (getCharacterCasing().equals(CharacterCasing.UPPER)) {
				event.getItem().setValue(value.toUpperCase());
			} else if (getCharacterCasing().equals(CharacterCasing.UPPER)) {
				event.getItem().setValue(value.toLowerCase());
			} else {
				event.getItem().setValue(value);
			}

		}

	}

}
