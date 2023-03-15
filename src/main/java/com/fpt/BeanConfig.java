package com.fpt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public Customer getCustomerBeauty() {
		Customer customer = new Customer();
		customer.setId(9);
		customer.setName("Nguyễn Phương Tín");
		customer.setEmail("tin63711@gmail.com");
		customer.setAge(21);
		return customer;
	}
}
