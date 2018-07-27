package za.co.ioagentsmith.transfer.services.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  private String ibanAccountNumber;
  private String accountName;
  private double balance;

  public Account(String ibanAccountNumber, String accountName, double balance) {
    this.ibanAccountNumber = ibanAccountNumber;
    this.accountName = accountName;
    this.balance = balance;
  }

  public Account() {}

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(final String accountName) {
    this.accountName = accountName;
  }

  public String getIbanAccountNumber() {
    return ibanAccountNumber;
  }

  public void setIbanAccountNumber(final String ibanAccountNumber) {
    this.ibanAccountNumber = ibanAccountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(final double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "AccountDto{" +
            "ibanAccountNumber='" + ibanAccountNumber + '\'' +
            ", accountName='" + accountName + '\'' +
            ", balance=" + balance +
            '}';
  }
}
