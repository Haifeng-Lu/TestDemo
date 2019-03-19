package com.datamaster.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.datamaster.service.SecurityService;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

	@Autowired
	private SecurityService securityService;
	
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	
	class AuditorAwareImpl implements AuditorAware<String> {

		@Override
		public Optional<String> getCurrentAuditor() {
			String currentUser = securityService.findLoggedInUser();
			if(currentUser == null) {
				currentUser = "DmVisitor";
			}
			return Optional.of(currentUser);
		}
		
	}
}
