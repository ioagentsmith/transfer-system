package za.co.ioagentsmith.transfer.services.service;

import org.junit.Before;
import org.junit.Test;
import za.co.ioagentsmith.transfer.services.dto.Account;
import za.co.ioagentsmith.transfer.services.dto.Transfer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountValidationServiceImplTest {

    private static final String ACCOUNT_NAME_1 = "Account 1";
    private static final String ACCOUNT_NAME_2 = "Account 2";
    private static final double VALID_BALANCE = 1000;
    private static final double INVALID_BALANCE = -1000;
    private static final double VALID_DEBIT_AMOUNT = 500;
    private static final double INVALID_DEBIT_AMOUNT = -500;
    private static final String VALID_IBAN_NUMBER_1 = "NL91 ABNA 0417 1643 00";
    private static final String VALID_IBAN_NUMBER_2 = "DE91 1000 0000 0123 4567 89";
    private static final String INVALID_IBAN_NUMBER = "62364824569";

    private AccountValidationService accountValidationService;
    private Account fromAccountDto;
    private Account toAccountDto;
    private Transfer Transfer;

    @Before
    public void setup() {
        accountValidationService = new AccountValidationServiceImpl();
    }

    @Test
    public void isValidAccount_isValid() {
        fromAccountDto = new Account(VALID_IBAN_NUMBER_1, ACCOUNT_NAME_1, VALID_BALANCE);
        assertThat(accountValidationService.isValidAccount(fromAccountDto), is(true));

        fromAccountDto = new Account(VALID_IBAN_NUMBER_2, ACCOUNT_NAME_1, VALID_BALANCE);
        assertThat(accountValidationService.isValidAccount(fromAccountDto), is(true));
    }

    @Test
    public void isValidAccount_isInvalidAccount() {
        fromAccountDto = new Account(INVALID_IBAN_NUMBER, ACCOUNT_NAME_1, VALID_BALANCE);
        assertThat(accountValidationService.isValidAccount(fromAccountDto), is(false));
    }

    @Test
    public void isValidAccount_isInvalidBalance() {
        fromAccountDto = new Account(VALID_IBAN_NUMBER_1, ACCOUNT_NAME_1, INVALID_BALANCE);
        assertThat(accountValidationService.isValidAccount(fromAccountDto), is(false));
    }

    @Test
    public void isValidAccount_isNullAccount() {
        fromAccountDto = new Account(null, ACCOUNT_NAME_1, VALID_BALANCE);
        assertThat(accountValidationService.isValidAccount(fromAccountDto), is(false));
    }

    @Test
    public void isValidTransaction_isValid() {
        fromAccountDto = new Account(VALID_IBAN_NUMBER_1, ACCOUNT_NAME_1, VALID_BALANCE);
        toAccountDto = new Account(VALID_IBAN_NUMBER_2, ACCOUNT_NAME_2, VALID_BALANCE);
        Transfer = new Transfer(fromAccountDto, toAccountDto, VALID_DEBIT_AMOUNT);
        assertThat(accountValidationService.isValidTransaction(Transfer), is(true));
    }

    @Test
    public void isValidTransaction_isInvalidDebitAmount() {
        fromAccountDto = new Account(VALID_IBAN_NUMBER_1, ACCOUNT_NAME_1, VALID_BALANCE);
        toAccountDto = new Account(VALID_IBAN_NUMBER_2, ACCOUNT_NAME_2, VALID_BALANCE);
        Transfer = new Transfer(fromAccountDto, toAccountDto, INVALID_DEBIT_AMOUNT);
        assertThat(accountValidationService.isValidTransaction(Transfer), is(false));
    }

    @Test
    public void isValidTransaction_isInvalidFromAccountBalance() {
        fromAccountDto = new Account(VALID_IBAN_NUMBER_1, ACCOUNT_NAME_1, INVALID_BALANCE);
        toAccountDto = new Account(VALID_IBAN_NUMBER_2, ACCOUNT_NAME_2, VALID_BALANCE);
        Transfer = new Transfer(fromAccountDto, toAccountDto, VALID_DEBIT_AMOUNT);
        assertThat(accountValidationService.isValidTransaction(Transfer), is(false));
    }

    @Test
    public void isValidTransaction_isInvalidToAccountBalance() {
        fromAccountDto = new Account(VALID_IBAN_NUMBER_1, ACCOUNT_NAME_1, VALID_BALANCE);
        toAccountDto = new Account(VALID_IBAN_NUMBER_2, ACCOUNT_NAME_2, INVALID_BALANCE);
        Transfer = new Transfer(fromAccountDto, toAccountDto, VALID_DEBIT_AMOUNT);
        assertThat(accountValidationService.isValidTransaction(Transfer), is(false));
    }

    @Test
    public void isValidTransaction_isInvalidTransfer() {
        assertThat(accountValidationService.isValidTransaction(null), is(false));
    }
}