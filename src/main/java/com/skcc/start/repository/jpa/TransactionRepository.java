package com.skcc.start.repository.jpa;

import com.skcc.fwk.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {



}
