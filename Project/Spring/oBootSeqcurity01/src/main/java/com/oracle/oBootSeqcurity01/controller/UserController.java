package com.oracle.oBootSeqcurity01.controller;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootSeqcurity01.domain.Account;
import com.oracle.oBootSeqcurity01.model.AccountDto;
import com.oracle.oBootSeqcurity01.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public String signup(AccountDto accountDto) {
		// DTO -> Entity
		ModelMapper mapper = new ModelMapper();
		Account account = mapper.map(accountDto, Account.class);

		// DTO의 password를 암호화 하여 Entity의 password에 설정
		System.out.println("signup accountDto.getPassword() : " + accountDto.getPassword());
		account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

		// DB 저장
		userService.createUser(account);

		return "redirect:/";
	}

}
