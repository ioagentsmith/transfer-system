package za.co.ioagentsmith.transfer.services.utils;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;

public class IbanValidatorUtilTest {

    private static final String VALID_IBAN_NUMBER_1 = "NL91 ABNA 0417 1643 00";
    private static final String VALID_IBAN_NUMBER_2 = "DE91 1000 0000 0123 4567 89";
    private static final String VALID_IBAN_NUMBER_3 = "DE91100000000123456789";
    private static final String INVALID_IBAN_NUMBER = "62364824569";

    @Test
    public void isValidIban_isValid() {
        assertThat(IbanValidatorUtil.isValidIban(VALID_IBAN_NUMBER_1), is(true));
        assertThat(IbanValidatorUtil.isValidIban(VALID_IBAN_NUMBER_2), is(true));
        assertThat(IbanValidatorUtil.isValidIban(VALID_IBAN_NUMBER_3), is(true));
    }

    @Test
    public void isValidIban_isInvalid() {
        assertThat(IbanValidatorUtil.isValidIban(INVALID_IBAN_NUMBER), is(false));
    }

    @Test
    public void isValidIban_isNullAccount() {
        assertThat(IbanValidatorUtil.isValidIban(INVALID_IBAN_NUMBER), is(false));
    }
}
