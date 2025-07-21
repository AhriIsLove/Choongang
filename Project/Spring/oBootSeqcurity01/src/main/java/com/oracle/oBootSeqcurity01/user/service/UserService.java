package com.oracle.oBootSeqcurity01.user.service;

import org.springframework.stereotype.Service;

import com.oracle.oBootSeqcurity01.domain.Account;
import com.oracle.oBootSeqcurity01.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public void createUser(Account account) {
		userRepository.save(account);
	}
}
