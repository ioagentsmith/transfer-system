package za.co.ioagentsmith.transfer.services.service;

import java.util.Optional;

import za.co.ioagentsmith.transfer.services.dto.Account;
import za.co.ioagentsmith.transfer.services.dto.Transfer;

public interface TransferService {

	boolean openAccounts(Account account);

    Optional<Account> findAccounts(String ibanAccountNumberToSearchFor);

    boolean transferFunds(Transfer transfer);
}
