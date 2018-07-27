package za.co.ioagentsmith.transfer.services.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.ioagentsmith.transfer.services.dto.Account;
import za.co.ioagentsmith.transfer.services.dto.Transfer;
import za.co.ioagentsmith.transfer.services.service.TransferService;

@RestController
public class TransferRestController {

  private static final String APPLICATION_JSON = "application/json";

  @Autowired
  private TransferService transferService;

  @RequestMapping(value = "/openAccounts", method = RequestMethod.POST)
  public ResponseEntity<Account> openAccounts(@RequestBody Account account) {
    ResponseEntity<Account> responseEntity = ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

    if (transferService.openAccounts(account)) {
      responseEntity = ResponseEntity.status(HttpStatus.CREATED).build();
    }

    return responseEntity;
  }

  @RequestMapping(value = "/findAccounts/{ibanAccountNumberToSearchFor}", method = RequestMethod.GET, produces = APPLICATION_JSON)
  public ResponseEntity<Account> findAccounts(@PathVariable String ibanAccountNumberToSearchFor) {
    ResponseEntity<Account> responseEntity = ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

    Optional<Account> accountSearchedFor = transferService.findAccounts(ibanAccountNumberToSearchFor);
    if (accountSearchedFor.isPresent()) {
      responseEntity = new ResponseEntity<>(accountSearchedFor.get(), HttpStatus.OK);
    }

    return responseEntity;
  }

  @RequestMapping(value = "/transferFunds", method = RequestMethod.PATCH)
  public ResponseEntity<String> transferFunds(@RequestBody Transfer transfer) {
    ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

    if (transferService.transferFunds(transfer)) {
      responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    return responseEntity;
  }
}
