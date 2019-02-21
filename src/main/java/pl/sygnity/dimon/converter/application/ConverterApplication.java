package pl.sygnity.dimon.converter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.sygnity.dimon.converter.rest"})
public class ConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterApplication.class, args);
	}
}
