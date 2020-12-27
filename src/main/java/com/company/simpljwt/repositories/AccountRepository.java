package com.company.simpljwt.repositories;

import com.company.simpljwt.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByLogin(String login);
}
