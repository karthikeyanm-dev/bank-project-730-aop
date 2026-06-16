package com.karthi.bank730.transaction.repository;

import com.karthi.bank730.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
