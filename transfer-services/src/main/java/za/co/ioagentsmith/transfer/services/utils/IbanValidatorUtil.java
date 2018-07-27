package za.co.ioagentsmith.transfer.services.utils;

import java.math.BigInteger;
import java.util.Optional;

public final class IbanValidatorUtil {

    private static final int IBANNUMBER_MIN_SIZE = 15;
    private static final int IBANNUMBER_MAX_SIZE = 34;
    private static final BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97");
    private static final String SPACE = " ";
    private static final String EMPTY = "";

    private IbanValidatorUtil() {
    }

    public static final boolean isValidIban(String ibanAccountNumber) {
        boolean valid = false;

        if (Optional.ofNullable(ibanAccountNumber).isPresent()) {
            String newIbanAccountNumber = ibanAccountNumber.trim();

            // Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid. We could also check
            // for specific length according to country, but for now we won't
            if (newIbanAccountNumber.length() >= IBANNUMBER_MIN_SIZE && newIbanAccountNumber.length() <= IBANNUMBER_MAX_SIZE) {

                // Move the four initial characters to the end of the string.
                newIbanAccountNumber = newIbanAccountNumber.substring(4) + newIbanAccountNumber.substring(0, 4);

                // Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35.
                StringBuilder numericAccountNumber = new StringBuilder();
                int numericValue;
                for (char character : newIbanAccountNumber.toCharArray()) {
                    numericValue = Character.getNumericValue(character);
                    if (numericValue > -1) {
                        numericAccountNumber.append(numericValue);
                    }
                }

                // Interpret the string as a decimal integer and compute the remainder of that number on division by 97.
                BigInteger ibanNumber = new BigInteger(numericAccountNumber.toString());
                if (ibanNumber.mod(IBANNUMBER_MAGIC_NUMBER).intValue() == 1) {
                    valid = true;
                }
            }
        }

        return valid;
    }

    public static final String removeAllSpacesInIban(String ibanAccountNumber) {
        if (Optional.ofNullable(ibanAccountNumber).isPresent() && !ibanAccountNumber.isEmpty()) {
            return ibanAccountNumber.replaceAll(SPACE, EMPTY);
        }

        return EMPTY;
    }
}
