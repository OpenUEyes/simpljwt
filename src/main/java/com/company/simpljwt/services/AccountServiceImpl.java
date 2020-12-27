package com.company.simpljwt.services;

import com.company.simpljwt.model.Account;
import com.company.simpljwt.model.Role;
import com.company.simpljwt.repositories.AccountRepository;
import com.company.simpljwt.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account saveAccount(Account account) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        account.setRole(userRole);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Account findByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    @Override
    public Account findByLoginAndPassword(String login, String password) {
        Account account = findByLogin(login);
        if (account != null) {
            if (passwordEncoder.matches(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

}