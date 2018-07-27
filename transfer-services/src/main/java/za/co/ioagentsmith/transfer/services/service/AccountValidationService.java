package za.co.ioagentsmith.transfer.services.service;

import za.co.ioagentsmith.transfer.services.dto.Account;
import za.co.ioagentsmith.transfer.services.dto.Transfer;

public interface AccountValidationService {

	boolean isValidAccount(Account accountDto);

	boolean isValidTransaction(Transfer transfer);
}
