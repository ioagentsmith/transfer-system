package za.co.ioagentsmith.transfer.services.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import za.co.ioagentsmith.transfer.services.dto.Account;
import za.co.ioagentsmith.transfer.services.dto.Transfer;
import za.co.ioagentsmith.transfer.services.utils.IbanValidatorUtil;

@Service
public class AccountValidationServiceImpl implements AccountValidationService {

  private static final double ZERO_BALANCE = 0.0D;

  @Override
  public boolean isValidAccount(final Account accountDto) {
    return Optional.ofNullable(accountDto).isPresent()
            && hasValidAccountDetails(accountDto)
            && hasValidBalance(accountDto);
  }

  private boolean hasValidAccountDetails(final Account accountDto) {
    return Optional.ofNullable(accountDto.getAccountName()).isPresent()
            && Optional.ofNullable(accountDto.getIbanAccountNumber()).isPresent()
            && IbanValidatorUtil.isValidIban(accountDto.getIbanAccountNumber());
  }

  private boolean hasValidBalance(final Account accountDto) {
    return Optional.ofNullable(accountDto.getBalance()).isPresent()
            && accountDto.getBalance() >= ZERO_BALANCE;
  }

  @Override
  public boolean isValidTransaction(final Transfer transfer) {
    return isValidTransactionAmount(transfer)
            && hasEnoughFundsToDebit(transfer.getFromAccount(), transfer.getDebitAmount())
            && isValidAccount(transfer.getToAccount());
  }

  private boolean isValidTransactionAmount(final Transfer transfer) {
    return Optional.ofNullable(transfer).isPresent() && transfer.getDebitAmount() > 0;
  }

  private boolean hasEnoughFundsToDebit(final Account accountDto, final double debitAmount) {
      return isValidAccount(accountDto) && accountDto.getBalance() >= debitAmount;
  }
}
