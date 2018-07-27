package za.co.ioagentsmith.transfer.services.dto;

public class Transfer {

  private Account fromAccount;
  private Account toAccount;
  private double debitAmount;

  public Transfer(Account fromAccount, Account toAccount, double debitAmount) {
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.debitAmount = debitAmount;
  }

  public Transfer() {}

  public Account getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(final Account fromAccount) {
    this.fromAccount = fromAccount;
  }

  public Account getToAccount() {
    return toAccount;
  }

  public void setToAccount(final Account toAccount) {
    this.toAccount = toAccount;
  }

  public double getDebitAmount() {
    return debitAmount;
  }

  public void setDebitAmount(final double debitAmount) {
    this.debitAmount = debitAmount;
  }
}
