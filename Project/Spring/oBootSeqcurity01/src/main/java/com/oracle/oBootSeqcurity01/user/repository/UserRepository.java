package com.oracle.oBootSeqcurity01.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.oBootSeqcurity01.domain.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
	
	Account findByUsername(String username);

}
