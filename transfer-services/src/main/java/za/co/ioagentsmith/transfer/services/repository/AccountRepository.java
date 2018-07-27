package za.co.ioagentsmith.transfer.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.ioagentsmith.transfer.services.dto.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
