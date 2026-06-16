package com.karthi.bank730.account.repository;

import com.karthi.bank730.account.dto.AccountResponse;
import com.karthi.bank730.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> getAllByCustomer_CustomerId(String customerCustomerId);
    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
