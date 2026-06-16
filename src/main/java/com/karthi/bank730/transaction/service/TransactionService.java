package com.karthi.bank730.transaction.service;

import com.karthi.bank730.account.dto.AccountResponse;
import com.karthi.bank730.account.entity.Account;
import com.karthi.bank730.account.repository.AccountRepository;
import com.karthi.bank730.transaction.dto.DepositRequest;
import com.karthi.bank730.transaction.dto.TransactionResponse;
import com.karthi.bank730.transaction.dto.WithdrawRequest;
import com.karthi.bank730.transaction.enmus.TransactionStatus;
import com.karthi.bank730.transaction.enmus.TransactionType;
import com.karthi.bank730.transaction.entity.Transaction;
import com.karthi.bank730.transaction.repository.TransactionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    final private AccountRepository accountRepository;
    final private TransactionRepository transactionRepository;


//    deposit
    public TransactionResponse deposit(DepositRequest depositRequest){
        Account account = accountRepository.findAccountByAccountNumber(depositRequest.accountNumber())
                .orElseThrow(
                        () -> new RuntimeException(depositRequest.accountNumber()+" not found")
                );

        BigDecimal before = account.getBalance();
        account.setBalance(
                before.add(depositRequest.amount())
        );
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.DEPOSIT)
                .amount(depositRequest.amount())
                .balanceBefore(before)
                .balanceAfter(account.getBalance())
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionTime(LocalDateTime.now())
                .transactionReference(generateTransactionReference()) // We need to Create
                .account(account).build();

        transactionRepository.save(transaction);
        return transactionMapper(transaction);
    }


    public TransactionResponse withdraw(@Valid WithdrawRequest withdrawRequest) {
        Account account = accountRepository.findAccountByAccountNumber(withdrawRequest.accountNumber())
                .orElseThrow(
                        () -> new RuntimeException(withdrawRequest.accountNumber()+" not found")
                );

        if(account.getBalance().compareTo(withdrawRequest.amount()) < 0){
            throw new RuntimeException("Insufficient funds");
        }

        BigDecimal before = account.getBalance();
        account.setBalance(
                before.subtract(withdrawRequest.amount())
        );
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.WITHDRAW)
                .amount(withdrawRequest.amount())
                .balanceBefore(before)
                .balanceAfter(account.getBalance())
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionTime(LocalDateTime.now())
                .transactionReference(generateTransactionReference())
                .account(account).build();

        transactionRepository.save(transaction);
        return transactionMapper(transaction);
    }



//    Generate Transaction Number
    public String generateTransactionReference(){
        return "TXN"+LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + UUID.randomUUID()
                    .toString()
                    .substring(0,4)
                    .toUpperCase();
    }

//  Mapper to Transaction DTO

    public TransactionResponse transactionMapper(Transaction transaction){
        return  new TransactionResponse(
                transaction.getTransactionReference(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getBalanceAfter()
        );
    }


}
