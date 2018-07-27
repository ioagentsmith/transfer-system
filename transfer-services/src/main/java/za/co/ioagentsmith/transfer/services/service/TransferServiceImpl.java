package za.co.ioagentsmith.transfer.services.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.ioagentsmith.transfer.services.dto.Account;
import za.co.ioagentsmith.transfer.services.dto.Transfer;
import za.co.ioagentsmith.transfer.services.repository.AccountRepository;
import za.co.ioagentsmith.transfer.services.utils.IbanValidatorUtil;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountValidationService accountValidationService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean openAccounts(final Account account) {
        boolean returnStatus = false;

        if (accountValidationService.isValidAccount(account)) {
            account.setIbanAccountNumber(IbanValidatorUtil.removeAllSpacesInIban(account.getIbanAccountNumber()));
            accountRepository.saveAndFlush(account);
            returnStatus = true;
        }

        return returnStatus;
    }

    @Override
    public Optional<Account> findAccounts(final String ibanAccountNumberToSearchFor) {
        Optional<Account> account = Optional.empty();

        if (IbanValidatorUtil.isValidIban(ibanAccountNumberToSearchFor)) {
            account = accountRepository.findById(IbanValidatorUtil.removeAllSpacesInIban(ibanAccountNumberToSearchFor));
        }

        return account;
    }

    @Override
    public boolean transferFunds(final Transfer transfer) {
        boolean returnStatus = false;
        Optional<Account> newFromAccount;
        Optional<Account> newToAccount;

        if (Optional.ofNullable(transfer).isPresent()
                && Optional.ofNullable(transfer.getFromAccount()).isPresent()
                && Optional.ofNullable(transfer.getToAccount()).isPresent())
        {
            newFromAccount = findAccounts(transfer.getFromAccount().getIbanAccountNumber());
            newToAccount = findAccounts(transfer.getToAccount().getIbanAccountNumber());

            if (newFromAccount.isPresent() && newToAccount.isPresent()) {
                transfer.setFromAccount(newFromAccount.get());
                transfer.setToAccount(newToAccount.get());
                if (accountValidationService.isValidTransaction(transfer)) {
                    double newFromAccountBalance = transfer.getFromAccount().getBalance() - transfer.getDebitAmount();
                    double newToAccountBalance = transfer.getToAccount().getBalance() + transfer.getDebitAmount();
                    transfer.getFromAccount().setBalance(newFromAccountBalance);
                    transfer.getToAccount().setBalance(newToAccountBalance);
                    accountRepository.save(transfer.getFromAccount());
                    accountRepository.saveAndFlush(transfer.getToAccount());
                    returnStatus = true;
                }
            }
        }

        return returnStatus;
    }
}
