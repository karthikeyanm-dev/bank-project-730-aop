package com.karthi.bank730.account.repository;

import com.karthi.bank730.account.dto.AccountResponse;
import com.karthi.bank730.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> getAllByCustomer_CustomerId(String customerCustomerId);
}
