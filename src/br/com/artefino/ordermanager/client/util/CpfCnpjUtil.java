package br.com.artefino.ordermanager.client.util;

/**
 * Represents a CPF or CNPJ .<br>
 *
 * <pre>
 * CPF: <b>C</B>adastro de <b>P</b>essoas <B>F</b>ísicas
 * it means: National People Registration
 * <br>
 * CNPJ: <B>C</b>adastro <B>N</b>acional de <B>P</b>essoas <B>J</b>urídicas
 * it means: National Enterprises Registration
 * </pre>
 *
 * Based on the setCpfCnpj() method the object will become CPF or CNPJ depending
 * of the char count.<br>
 * CPF has 11 digits, CNPJ has 14 digits.
 *
 * @author Douglas Siviotti
 */
public class CpfCnpjUtil {
    /** CNPJ digit count */
    public static final int CNPJ_DIGITS = 14;

    /** CNPJ mask */
    public static final String CNPJ_MASK = "##.###.###/####-##";

    /** CPF digit count */
    public static final int CPF_DIGITS = 11;

    /** CPF mask */
    public static final String CPF_MASK = "###.###.###-##";

    /**
     * Validate a CPF or CNPJ.<br>
     *
     * @param cpfOrCnpj
     *            The CPF or CNPJ to validate
     * @return True if is valid or false if is not
     */
    public static boolean isValid(String cpfOrCnpj) {
        String n = cpfOrCnpj.replaceAll("[^0-9]*", "");
        boolean isCnpj = n.length() == CNPJ_DIGITS;
        boolean isCpf = n.length() == CPF_DIGITS;

        if (!isCpf && !isCnpj)
            return false;

        if (isCpf) {
            if (n.equals("00000000000") || n.equals("11111111111")
                || n.equals("22222222222") || n.equals("33333333333")
                || n.equals("44444444444") || n.equals("55555555555")
                || n.equals("66666666666") || n.equals("77777777777")
                || n.equals("88888888888") || n.equals("99999999999"))
                return false;
        } else if (isCnpj) {
            if (n.equals("00000000000000"))
                return false;
        }

        int i;
        int j; // just count
        int digit; // A number digit
        int coeficient; // A coeficient
        int sum; // The sum of (Digit * Coeficient)
        int[] foundDv = {
            0, 0
        }; // The found Dv1 and Dv2
        int dv1 = Integer.parseInt(String.valueOf(n.charAt(n.length() - 2)));
        int dv2 = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));
        for (j = 0; j < 2; j++) {
            sum = 0;
            coeficient = 2;
            for (i = n.length() - 3 + j; i >= 0; i--) {
                digit = Integer.parseInt(String.valueOf(n.charAt(i)));
                sum += digit * coeficient;
                coeficient++;
                if (coeficient > 9 && isCnpj)
                    coeficient = 2;
            }
            foundDv[j] = 11 - sum % 11;
            if (foundDv[j] >= 10)
                foundDv[j] = 0;
        }
        return dv1 == foundDv[0] && dv2 == foundDv[1];
    }

    /** The internal mask of CPF or CNPJ */
    private String mask = null;

    /** Internal number */
    private String number = null;

    /**
     * Simple Constructor
     */
    public CpfCnpjUtil() {
        super();
    }

    /**
     * Parameter Constructor.<br>
     * CpfCnpj c = new CpfCnpj("12345678911");<br>
     * Or<br>
     * CpfCnpj c = new CpfCnpj("123.456.789-11");<br>
     *
     * @param cpfCnpj
     *            The Cpf or Cnpj number
     */
    public CpfCnpjUtil(String cpfCnpj) {
        super();
        setCpfCnpj(cpfCnpj);
    }

    /**
     * Returns the Mask (CPF Mask or CNPJ Mask).<br>
     *
     * @see br.com.usix.bae.util.utilities.brazilutils.utilities.NumberComposed#getMask()
     */
    public String getMask() {
        return mask;
    }

    /**
     * Returns the simple number, without mask.<br>
     *
     * @see br.com.usix.bae.util.utilities.brazilutils.utilities.NumberComposed#getNumber()
     */
    public String getNumber() {
        return number;
    }

    /**
     * Determines if the object is a CNPJ
     *
     * @return true if the object is a CNPJ and False if the object is a CPF or
     *         invalid
     */
    public boolean isCnpj() {
        return number != null && number.length() == CNPJ_DIGITS;
    }

    /**
     * Determines if the object is a CPF
     *
     * @return true if the object is a CPF and False if the object is a CNPJ or
     *         invalid
     */
    public boolean isCpf() {
        return number != null && number.length() == CPF_DIGITS;
    }

    /**
     * Determines if the format is valid.<br>
     * The object must be a CPF or a CNPJ.
     *
     * @return True - is Valid, False - is Invalid
     */
    public boolean isFormatValid() {
        return (isCpf() || isCnpj());// Must be CPF or CNPJ
    }

    /**
     * @see br.com.usix.bae.util.validation.brazilutils.validation.Validable#isValid()
     * @return true id is valid and false if is not
     */
    public boolean isValid() {
        return isValid(getNumber());
    }

    /**
     * Set the CPF or CNPJ number as String<br>
     * You can use "123.456.789-01" or "12345678901"
     *
     * @param cpfCnpj
     *            The Cpf or Cnpj number
     */
    public void setCpfCnpj(String cpfCnpj) {
        number = cpfCnpj.replaceAll("[^0-9]*", "");
        if (isCpf()) {
            mask = CPF_MASK;
        } else if (isCnpj()) {
            mask = CNPJ_MASK;
        }
    }

    /**
     * Returns a Long represents the internal number.<br>
     *
     * @see br.com.usix.bae.util.utilities.brazilutils.utilities.NumberComposed#toLong()
     */
    public long toLong() {
        if (number != null && number.length() > 0)
            return Long.parseLong(number);
        else
            return 0;
    }

    /**
     * Returns the <code>getValue()</code> Method.<br>
     *
     * @see java.lang.Object#toString()
     * @see br.com.usix.bae.util.utilities.brazilutils.utilities.NumberComposed#getValue()
     */
    // public String toString() {
    // return getValue();
    // }

    /**
     * @see br.com.usix.bae.util.validation.brazilutils.validation.Validable#validate()
     */
    // public void validate() throws ValidationException {
    // if (!isValid()) throw new ValidationException();
    // }
}
