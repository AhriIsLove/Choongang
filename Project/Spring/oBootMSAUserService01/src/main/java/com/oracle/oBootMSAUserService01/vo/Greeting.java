package com.oracle.oBootMSAUserService01.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Greeting {
	@Value("${greeting.message}")
	private String message;
}
