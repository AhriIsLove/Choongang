package com.oracle.oBootSeqcurity01.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oracle.oBootSeqcurity01.domain.Account;
import com.oracle.oBootSeqcurity01.model.AccountContext;
import com.oracle.oBootSeqcurity01.model.AccountDto;
import com.oracle.oBootSeqcurity01.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service("userDetailService")
@RequiredArgsConstructor
//UserDetails: Spring Security에서 사용자의 정보를 담는 인터페이스
//UserDetailsService: Spring Security에서 유저의 정보를 가져오는 인터페이스
public class FormUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username(ID)가 동일한 사용자 찾기
		Account account = userRepository.findByUsername(username);
//		if(account == null) {
//			throw new 
//		}
		// 사용자의 권한만 추출
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRoles()));
		// Entity -> DTO
		ModelMapper mapper = new ModelMapper();
		AccountDto accountDto = mapper.map(account, AccountDto.class);

		System.out.println("authorities : " + authorities);
		System.out.println("accountDto : " + accountDto);

		// Account : DB용 Entity
		// AccountDto : 데이터 전달용 DTO
		// AccountContext : 서버에서 사용할 직접 만든 계정 Class
		return new AccountContext(accountDto, authorities);
	}

}
