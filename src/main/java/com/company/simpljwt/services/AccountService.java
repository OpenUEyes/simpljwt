package com.company.simpljwt.services;

import com.company.simpljwt.model.Account;

public interface AccountService {

    Account saveAccount(Account account);

    Account findByLogin(String login);

    Account findByLoginAndPassword(String login, String password);
}
