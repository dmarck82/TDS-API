package br.edu.utfpr.tdsapi.tdsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.edu.utfpr.tdsapi.tdsapi.config.property.TdsApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(TdsApiProperty.class)
public class TdsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdsApiApplication.class, args);
	}

}