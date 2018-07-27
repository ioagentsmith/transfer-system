package za.co.ioagentsmith.transfer.services.dto;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class AccountDtoJsonTest {

    @Autowired
    private JacksonTester<Account> json;

    private static final String ACCOUNT_NAME = "Mr. Christoff Smith";
    private static final String IBAN_ACCOUNT_NUMBER = "NL91 ABNA 0417 1643 00";
    private static final double BALANCE = 0;

    private static final String JSON_TO_DESERIALIZE =
            "{\"accountName\":\""
                    + ACCOUNT_NAME
                    + "\",\"ibanAccountNumber\":\""
                    + IBAN_ACCOUNT_NUMBER
                    + "\",\"balance\":"
                    + BALANCE
                    + "}";

    private Account accountDto;

    @Before
    public void setup() throws ParseException {
        accountDto = new Account(IBAN_ACCOUNT_NUMBER, ACCOUNT_NAME, BALANCE);
    }

    @Test
    public void accountNameSerializes() throws IOException {
        assertThat(this.json.write(accountDto))
                .extractingJsonPathStringValue("@.accountName")
                .isEqualTo(ACCOUNT_NAME);
    }

    @Test
    public void ibanAccountNumberSerializes() throws IOException {
        assertThat(this.json.write(accountDto))
                .extractingJsonPathStringValue("@.ibanAccountNumber")
                .isEqualTo(IBAN_ACCOUNT_NUMBER);
    }

    @Test
    public void balanceSerializes() throws IOException {
        assertThat(this.json.write(accountDto))
                .extractingJsonPathNumberValue("@.balance")
                .isEqualTo(BALANCE);
    }

    @Test
    public void accountNameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getAccountName()).isEqualTo(ACCOUNT_NAME);
    }

    @Test
    public void ibanAccountNumberDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getIbanAccountNumber()).isEqualTo(IBAN_ACCOUNT_NUMBER);
    }

    @Test
    public void balanceDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getBalance()).isEqualTo(BALANCE);
    }
}
