package pl.sygnity.dimon.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"pl.sygnity.dimon.converter", "pl.sygnity.dimon.converter.dao", 
		"pl.sygnity.dimon.converter.dao.repositories",
		"pl.sygnity.dimon.converter.logic", "pl.sygnity.dimon.converter.rest"})
@ComponentScan({"pl.sygnity.dimon.converter.dao", "pl.sygnity.dimon.converter.dao.repositories" })
public class ConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterApplication.class, args);
	}
}
