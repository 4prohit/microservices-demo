package com.cg.rp.demo.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.cg.rp.demo.domain.User;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
	// To expose Entity ID in Spring Data REST response
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
    }
}
