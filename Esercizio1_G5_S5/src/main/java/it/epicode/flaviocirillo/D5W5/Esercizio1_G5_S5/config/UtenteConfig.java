package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Utente;

@Configuration
@PropertySource("classpath:application.properties")
public class UtenteConfig {

	@Bean
    public Utente u() {
        Utente u = new Utente();
        return u;
    }
	
}
