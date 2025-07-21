package com.oracle.oBootSeqcurity01.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.oracle.oBootSeqcurity01.details.FormWebAuthenticationDetails;
import com.oracle.oBootSeqcurity01.exception.SecretException;
import com.oracle.oBootSeqcurity01.model.AccountContext;

import lombok.RequiredArgsConstructor;

@Component("authenticationProvider")
@RequiredArgsConstructor
//DB에서 가져온 정보와 input 된 정보가 비교되서 체크되는 로직이 포함되어있는 인터페이스
//사용자가 입력한 아이디와 비밀번호가 맞는지, 그리고 추가적인 보안 키까지 확인해서 이 사람이 진짜로 우리 서비스에 들어올 자격이 있는지 인증(Authentication) 검증
public class FormAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userdetailsService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// ID, PW
		String loginId = authentication.getName();
		String password = (String) authentication.getCredentials();

		// ID에 맞는 계정 정보
		AccountContext accountContext = (AccountContext) userdetailsService.loadUserByUsername(loginId);

		System.out.println("Raw password: [" + password + "]");
		System.out.println("Encoded password from DB: [" + accountContext.getPassword() + "]");
		System.out.println("Matches result: " + passwordEncoder.matches(password, accountContext.getPassword()));

		// 비밀번호 일치 확인
		if (!passwordEncoder.matches(password, accountContext.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}

		System.out.println("성공1");

		// SeccretKey(암호키,암구호) 검증 : 외부 사이트 접근 확인
		FormWebAuthenticationDetails authenticationDetails = (FormWebAuthenticationDetails) authentication.getDetails();
		String secretKey = (authenticationDetails).getSecretKey();
		if (secretKey == null || !secretKey.equals("secret")) {
			throw new SecretException("Invalid Secret");
		}

		System.out.println("성공2");

		// 인증 성공 시 Authentication 객체 반환
		// 1) principal: 인증된 사용자 주체 (여기서는 accountContext.getAccountDto())
		// 2) credentials: 사용자의 자격 증명 (비밀번호 같은 거!) -> 보안상 null
		// 3) authorities: 사용자의 권한 (예: ROLE_USER, ROLE_ADMIN 등)
		return new UsernamePasswordAuthenticationToken(accountContext.getAccountDto(), null,
				accountContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
