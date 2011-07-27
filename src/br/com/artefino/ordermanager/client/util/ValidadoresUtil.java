package br.com.artefino.ordermanager.client.util;

import java.util.Date;
import java.util.Map;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * Classe utilitária Validadores.
 *
 * @author Kermeson
 */
public class ValidadoresUtil {

	public static String EMAIL = "^[\\w!#$%&'*+\\/=?^`{|}~-]+(\\.[\\w!#$%&'*+\\/=?^`{|}~-]+)"
        + "*@(([\\w-]+\\.)+[A-Za-z]{2,6}|\\[\\d{1,3}(\\.\\d{1,3}){3}\\])$";

    public static Validator getValidadorEmail() {
        RegExpValidator regExpValidator = new RegExpValidator();
        regExpValidator.setExpression(EMAIL);
        regExpValidator.setErrorMessage("O email não é válido");
        return regExpValidator;
    }

    /***
     * Método utilizado para validar uma data.<br>
     * Para caracterizar um data válida, a mesma deve estar contida entre
     * 01/01/1900 e 31/12/2050.
     *
     * @return Validator - objeto utilizado para validar a data informada.
     */
    public static Validator getValidadorData() {
        CustomValidator validator = new CustomValidator() {
            @Override
            protected boolean condition(Object value) {
                if (value != null) {
                    try {
                        String data = DateTimeFormat.getFormat("dd/MM/yyyy")
                            .format((Date) value);
                        String[] aData = data.split("/");
                        int dia = Integer.parseInt(aData[0]);
                        int mes = Integer.parseInt(aData[1]);
                        int ano = Integer.parseInt(aData[2]);

                        if (dia <= 0 || dia > 31) {
                            return false;
                        }

                        if (mes <= 0 || mes > 12) {
                            return false;
                        }

                        if (ano < 1900 || ano > 2050) {
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }
                return true;
            }
        };
        validator
            .setErrorMessage("Data inválida. A data informada deve ser entre 01/01/1900 e 31/12/2050.");

        return validator;
    }

    /***
     * Válida se a data é menor que a data atual
     *
     * @return Validator
     */
    @SuppressWarnings("deprecation")
    public static Validator getValidadorDataMenorAtual() {
        DateRangeValidator dateRangeValidator = new DateRangeValidator();
        dateRangeValidator.setMax(new Date());
        dateRangeValidator.setMin(new Date(0, 0, 1));
        dateRangeValidator.setErrorMessage("Data inválida");
        return dateRangeValidator;
    }

    @SuppressWarnings("deprecation")
    public static boolean validateIntervaloDataFinalcurrent(Date currentdate,
            Date dataInicio) {
        dataInicio.setHours(0);
        currentdate.setHours(0);
        dataInicio.setMinutes(0);
        currentdate.setMinutes(0);
        dataInicio.setSeconds(0);
        currentdate.setSeconds(0);
        if (dataInicio != null && currentdate != null
            && currentdate.compareTo(dataInicio) >= 0) {

            return false;
        }
        return true;
    }

    public static Validator getValidadorCpfCnpj() {
        CustomValidator customValidator = new CustomValidator() {

            @Override
            protected boolean condition(Object value) {
                if (value != null && !value.toString().isEmpty()
                    && !CpfCnpjUtil.isValid(String.valueOf(value))) {
                    return false;
                }
                return true;
            }
        };
        customValidator.setErrorMessage("O CPF/CNPJ não é válido");
        return customValidator;
    }

    public static Validator getValidadorCpf() {
        Validator customValidator = getValidadorCpfCnpj();
        customValidator.setErrorMessage("O CPF não é válido");
        return customValidator;
    }

    public static Validator getValidadorMaximoDecimal(final double valorMaximo) {
        CustomValidator customValidator = new CustomValidator() {

            @Override
            protected boolean condition(Object value) {
                if (value != null) {
                    Double newValue = FormatadorUtil
                        .getFormatDouble((String) value);
                    if (newValue.doubleValue() <= valorMaximo) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        };
        customValidator.setErrorMessage("O valor deve ser menor ou igual a "
            + valorMaximo);
        return customValidator;
    }

    public static Validator getValidadorMinimoDecimal(final double valorMinimo) {
        CustomValidator customValidator = new CustomValidator() {

            @Override
            protected boolean condition(Object value) {
                if (value != null) {
                    Double newValue = FormatadorUtil
                        .getFormatDouble((String) value);
                    if (newValue.doubleValue() >= valorMinimo) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        };
        customValidator.setErrorMessage("O valor deve ser maior ou igual a "
            + valorMinimo);
        return customValidator;
    }

    public static Validator getValidadorCEP() {
        RegExpValidator maskValidator = new RegExpValidator();
        maskValidator.setExpression("^\\d{8}$");
        maskValidator.setErrorMessage("O CEP não é válido");
        return maskValidator;
    }

    public static boolean validarSenhaForte(String senha) {
        boolean value = false;
        if (senha != null && senha.length() >= 5
            && senha.matches("^.*[0-9].*$") && senha.matches("^.*[A-Z].*$")
            && senha.matches("^.*[a-z].*$")
            && senha.matches("^.*[#*\\.@%&_\\-\\+].*$")) {
            value = true;
        }
        return value;
    }


    public static int calcularIdade(Date dataNascimento, Date dataParaCalculo) {

        DateTimeFormat format = DateTimeFormat.getFormat("dd MM yyyy");

        String nasc = format.format(dataNascimento);
        final String REGEX = "(.{2}) (.{2}) (.{4})";
        int anoNasc = Integer.valueOf(nasc.replaceFirst(REGEX, "$3"));
        int mesNasc = Integer.valueOf(nasc.replaceFirst(REGEX, "$2"));
        int diaNasc = Integer.valueOf(nasc.replaceFirst(REGEX, "$1"));

        String calc = format.format(dataParaCalculo);
        int anoCalc = Integer.valueOf(calc.replaceFirst(REGEX, "$3"));
        int mesCalc = Integer.valueOf(calc.replaceFirst(REGEX, "$2"));
        int diaCalc = Integer.valueOf(calc.replaceFirst(REGEX, "$1"));

        int idade = anoCalc - anoNasc;
        if ((mesNasc > mesCalc) || (mesNasc == mesCalc && diaNasc > diaCalc)) {
            idade--;
        }

        return idade;
    }

    public static void validadorAno(final TextItem item) {

        item.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                if (event.getValue() != null) {
                    final String valor = event.getValue().toString();
                    if (valor.matches("\\d+")) {
                        if (valor.length() == 4) {
                            final int ano = Integer.parseInt(valor);
                            if (ano < 1900) {
                                item.setValue(limparCampo(valor));
                            }
                        } else if (valor.length() > 4) {
                            item.setValue(limparCampo(valor));
                        } else if (valor.length() == 2) {
                            final int ano = Integer.parseInt(valor);
                            if (ano < 19) {
                                item.setValue(limparCampo(valor));
                            }

                        }
                    } else {
                        item.setValue(limparCampo(valor));
                    }
                }
            }
        });
    }

    public static void validadorAno(final ListGridField item) {

        item
            .addChangedHandler(new com.smartgwt.client.widgets.grid.events.ChangedHandler() {
                @Override
                public void onChanged(
                        com.smartgwt.client.widgets.grid.events.ChangedEvent event) {
                    if (event.getValue() != null) {
                        final String valor = event.getValue().toString();
                        if (valor.matches("\\d+")) {
                            if (valor.length() == 4) {
                                final int ano = Integer.parseInt(valor);
                                if (ano < 1900) {
                                    event.getItem()
                                        .setValue(limparCampo(valor));
                                }
                            } else if (valor.length() > 4) {
                                event.getItem().setValue(limparCampo(valor));
                            } else if (valor.length() == 2) {
                                final int ano = Integer.parseInt(valor);
                                if (ano < 19) {
                                    event.getItem()
                                        .setValue(limparCampo(valor));
                                }

                            }
                        } else {
                            event.getItem().setValue(limparCampo(valor));
                        }
                    }
                }
            });
    }

    public static void validadorPlaca(final TextItem item) {

        item.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                if (event.getValue() != null) {
                    String valor = event.getValue().toString().toUpperCase();
                    if (valor.length() >= 1 && valor.length() <= 3
                        && !valor.matches("[a-zA-Z]{1,3}")) {
                        item.setValue(limparCampo(valor));
                    } else if (valor.length() > 3
                        && !valor.matches("[a-zA-Z]{3}\\d{1,4}")) {
                        item.setValue(limparCampo(valor));
                    } else {
                        item.setValue(valor);
                    }

                }

            }
        });

    }

    public static void validadorPlaca(final ListGridField item) {

        item
            .addChangedHandler(new com.smartgwt.client.widgets.grid.events.ChangedHandler() {
                @Override
                public void onChanged(
                        com.smartgwt.client.widgets.grid.events.ChangedEvent event) {
                    if (event.getValue() != null) {
                        final String valor = event.getValue().toString()
                            .toUpperCase();
                        if (valor.length() >= 1 && valor.length() <= 3
                            && !valor.matches("[a-zA-Z]{1,3}")) {
                            event.getItem().setValue(limparCampo(valor));
                        } else if (valor.length() > 3
                            && !valor.matches("[a-zA-Z]{3}\\d{1,4}")) {
                            event.getItem().setValue(limparCampo(valor));
                        } else {
                            event.getItem().setValue(valor);
                        }
                    }

                }
            });

    }

    private static String limparCampo(final String valor) {
        final int size = valor.length() - 1;
        if (size >= 0) {
            return valor.substring(0, size);
        }
        return valor;
    }

    public static void validadorChassi(final TextItem item) {

        item.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                if (event.getValue() != null) {
                    final String valor = event.getValue().toString();
                    if (!valor.matches("[\\d*[a-zA-Z]*]*")) {
                        event.getItem().setValue(limparCampo(valor));
                    }
                }
            }
        });
    }

    public static void validadorChassi(final ListGridField item, final int size) {

        item
            .addChangedHandler(new com.smartgwt.client.widgets.grid.events.ChangedHandler() {
                @Override
                public void onChanged(
                        com.smartgwt.client.widgets.grid.events.ChangedEvent event) {
                    if (event.getValue() != null) {
                        final String valor = event.getValue().toString();
                        if (valor.length() > size) {
                            event.getItem().setValue(limparCampo(valor));
                        }
                        if (!valor.matches("[\\d*[a-zA-Z]*]*")) {
                            event.getItem().setValue(limparCampo(valor));
                        }
                    }
                }
            });
    }

    public static void validorNumerico(final ListGridField item, final int size) {

        item
            .addChangedHandler(new com.smartgwt.client.widgets.grid.events.ChangedHandler() {
                @Override
                public void onChanged(
                        com.smartgwt.client.widgets.grid.events.ChangedEvent event) {
                    if (event.getValue() != null) {
                        final String valorItem = event.getValue().toString();
                        if (!valorItem.matches("\\d+")) {
                            event.getItem().setValue(limparCampo(valorItem));
                        }
                        if (valorItem.length() == size) {
                            event.getItem().setValue(limparCampo(valorItem));
                        }
                    }
                }
            });
    }

    public static void validorNumerico(final TextItem item) {

        item.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                if (event.getValue() != null) {
                    final String valor = event.getValue().toString();
                    if (!valor.matches("\\d+")) {
                        item.setValue(limparCampo(valor));
                    }
                }
            }
        });

    }

    public static void formatadorDecimal(final ListGridField item,
            final int size) {

        item
            .addChangedHandler(new com.smartgwt.client.widgets.grid.events.ChangedHandler() {
                @Override
                public void onChanged(
                        com.smartgwt.client.widgets.grid.events.ChangedEvent event) {
                    if (event.getValue() != null) {

                        String valorString1 = String.valueOf(event.getValue()
                            .toString().replace(".", "").replace(",", ""));
                        if (!valorString1.matches("\\d*")) {
                            event.getItem().setValue(limparCampo(valorString1));
                            return;
                        }
                        if (valorString1.length() == 1) {
                            event.getItem().setValue("0,0" + valorString1);
                        }
                        if (valorString1.length() == size) {
                            event.getItem().setValue("");
                            return;
                        }
                        StringBuffer valorPonto = new StringBuffer(valorString1);
                        for (int i = valorPonto.length() - 2; i > 0; i -= 3) {
                            valorPonto.insert(i, ".");

                        }
                        String numero = "";
                        if (valorPonto.length() - 2 > 0) {
                            numero = valorPonto.replace(
                                valorPonto.length() - 3,
                                valorPonto.length(),
                                ","
                                    + valorPonto.substring(
                                        valorPonto.length() - 2, valorPonto
                                            .length())).toString();
                            event.getItem().setValue(numero);
                        }
                        if (numero.startsWith("0")) {
                            String valorString2 = String.valueOf(numero
                                .replaceFirst("^0+\\.+", ""));
                            event.getItem().setValue(valorString2);
                        }
                    }
                }
            });

    }

    public static Double getFormatDouble(String valor) {
        return Double.parseDouble(valor.replace("R$ ", "").replace(".", "")
            .replace(",", "."));
    }

    public static Long getFormatLong(String valor) {
        return Long.parseLong(valor.replace(".", "").replace(",", ""));
    }

    public static Float getFormatFloat(String valor) {
        return Float.parseFloat(valor.replace(".", "").replace(",", "."));
    }

    public static Integer getFormatInteger(String valor) {
        return Integer.parseInt(valor.replace(".", "").replace(",", ""));
    }

    public static String getFormatDecimal(Double value) {
        NumberFormat nf = NumberFormat.getFormat("#,##0.00");
        return nf.format(value);
    }

    /**
     * Validar todos os DynamicForms.
     *
     * @param dynamicForm
     *            DynamicForm ...
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean getValidarDynamicForms(DynamicForm... dynamicForm) {
        boolean value = true;
        String msg = "";
        for (DynamicForm df : dynamicForm) {
            if (!df.validate()) {
                Map<String, Object> erros = df.getErrors();
                for (String fieldName : erros.keySet()) {
                    if (erros.get(fieldName) != null) {
                        msg += "<li>";
                        msg += "<strong>" + df.getField(fieldName).getTitle()
                            + ":</strong> "
                            + String.valueOf(erros.get(fieldName));
                        msg += "</li>";
                    }
                }
                value = false;
            }
        }
        if (!value) {
            SC.warn("<ul>" + msg + "</ul>");
        }
        return value;
    }

    /**
     * Valida se o valor do comboboxItem está contido na lista do mesmo.
     *
     * @param comboBoxItem
     *            ComboBoxItem
     * @param idAtributo
     *            String
     * @return true se o valor estiver na lista, false caso contrário.
     */
    public static boolean getValidaDialogBox(ComboBoxItem comboBoxItem,
            String idAtributo) {
        for (ListGridRecord record : comboBoxItem.getClientPickListData()) {
            if (record.getAttribute(idAtributo).equals(comboBoxItem.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Seta a criteria do comboBoxItemFilho, caso o valor do comboBoxItemPai
     * tenha sido alterado.
     *
     * @param comboBoxItemPai
     *            ComboBoxItem
     * @param comboBoxItemFilho
     *            ComboBoxItem
     * @param criteriaNova
     *            Criteria
     * @param atributoCriteria
     *            String
     */
    public static void getValidaCriteriaComboboxDependentes(
            ComboBoxItem comboBoxItemPai, ComboBoxItem comboBoxItemFilho,
            Criteria criteriaNova, String... atributoCriteria) {
        // ComboboxItemFilho já em criteria?
        if (comboBoxItemFilho.getOptionCriteria() != null) {
            Criteria optionCriteria = comboBoxItemFilho.getOptionCriteria();
            // Criteria é diferente da anterior?
            boolean criteriaDiferente = false;
            for (String atributo : atributoCriteria) {
                if (!optionCriteria.getAttribute(atributo).equals(
                    criteriaNova.getAttribute(atributo))) {
                    criteriaDiferente = true;
                }
            }
            if (criteriaDiferente) {
                comboBoxItemFilho.setOptionCriteria(criteriaNova);
                comboBoxItemFilho.fetchData();
                comboBoxItemFilho.clearValue();
            }
            // ComboboxItemFilho ainda não tem Criteria? Então adiciona.
        } else {
            comboBoxItemFilho.setOptionCriteria(criteriaNova);
        }
        comboBoxItemFilho.enable();
    }

    /**
     * Seta a criteria do comboBoxItemFilho, caso o valor do comboBoxItemPai
     * tenha sido alterado.
     *
     * @param comboBoxItemPai
     *            ComboBoxItem
     * @param comboBoxItemFilho
     *            ComboBoxItem
     * @param criteriaNova
     *            Criteria
     * @param atributoCriteria
     *            String
     * @param habilitaCmbFilho
     *            ?
     *            boolean
     */
    public static void getValidaCriteriaComboboxDependentes(
            ComboBoxItem comboBoxItemPai, ComboBoxItem comboBoxItemFilho,
            Criteria criteriaNova, boolean habilitaCmbFilho,
            String... atributoCriteria) {
        // ComboboxItemFilho já em criteria?
        if (comboBoxItemFilho.getOptionCriteria() != null) {
            Criteria optionCriteria = comboBoxItemFilho.getOptionCriteria();
            // Criteria é diferente da anterior?
            boolean criteriaDiferente = false;
            for (String atributo : atributoCriteria) {
                if (!optionCriteria.getAttribute(atributo).equals(
                    criteriaNova.getAttribute(atributo))) {
                    criteriaDiferente = true;
                }
            }
            if (criteriaDiferente) {
                comboBoxItemFilho.setOptionCriteria(criteriaNova);
                comboBoxItemFilho.clearValue();
            }
            // ComboboxItemFilho ainda não tem Criteria? Então adiciona.
        } else {
            comboBoxItemFilho.setOptionCriteria(criteriaNova);
        }
        if (habilitaCmbFilho) {
            comboBoxItemFilho.enable();
        }
    }

    @SuppressWarnings("deprecation")
    public static boolean validarIntervaloData(Date dataInicio, Date dataFinal) {
        dataInicio.setHours(0);
        dataFinal.setHours(0);
        dataInicio.setMinutes(0);
        dataFinal.setMinutes(0);
        dataInicio.setSeconds(0);
        dataFinal.setSeconds(0);
        if (dataInicio != null && dataFinal != null
            && dataInicio.compareTo(dataFinal) > 0) {
            SC.warn("A data final não pode ser menor que a data inicial.");

            return false;
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public static boolean validateCurrentDateInterval(Date currentdate,
            Date dataInicio) {
        dataInicio.setHours(0);
        currentdate.setHours(0);
        dataInicio.setMinutes(0);
        currentdate.setMinutes(0);
        dataInicio.setSeconds(0);
        currentdate.setSeconds(0);
        if (dataInicio != null && currentdate != null
            && currentdate.compareTo(dataInicio) > 0) {
            SC.warn("A data inicial deve ser maior ou igual a data atual.");
            return false;
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public static boolean validarIntervaloData(Date dataInicio, Date dataFinal,
            String msg) {
        dataInicio.setHours(0);
        dataFinal.setHours(0);
        dataInicio.setMinutes(0);
        dataFinal.setMinutes(0);
        dataInicio.setSeconds(0);
        dataFinal.setSeconds(0);
        if (dataInicio != null && dataFinal != null
            && dataInicio.compareTo(dataFinal) > 0) {
            SC.warn(msg);
            return false;
        }
        return true;
    }

    public static boolean validarFormatoArquivo(String arquivo, String... tipos) {
        if (tipos != null) {
            for (String tipo : tipos) {
                if (arquivo.toLowerCase().endsWith(tipo.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;

    }

    public static Criteria getCriteriaRandon() {
        Criteria criteria = new Criteria();
        criteria.addCriteria("rnd", new Date().getTime() + "");
        return criteria;
    }

}
