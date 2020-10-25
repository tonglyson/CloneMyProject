package com.example.clonemyproject;

import com.example.clonemyproject.entities.Role;
import com.example.clonemyproject.entities.User;
import com.example.clonemyproject.repository.RoleRepository;
import com.example.clonemyproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ClonemyprojectApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(ClonemyprojectApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
				= new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
//	@Override
//	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setFirstName("Ty");
//		user.setLastName("Nguye");
//		user.setEmail("ty@gmail.com");
//		user.setPassword("123");
//		Role role = new Role();
//		role.setName("ROLE_ADMIN");
//		Role role2 = new Role();
//		role2.setName("ROLE_USER");
//		roleRepository.save(role);
//		roleRepository.save(role2);
//
//		user.getRoles().add(roleRepository.findById(1).get());
//		userRepository.save(user);
//	}
}
