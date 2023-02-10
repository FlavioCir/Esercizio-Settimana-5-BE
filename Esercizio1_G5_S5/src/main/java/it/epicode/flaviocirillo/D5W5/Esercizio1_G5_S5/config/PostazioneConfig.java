package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Postazione;

@Configuration
@PropertySource("classpath:application.properties")
public class PostazioneConfig {

	@Bean
    public Postazione pos() {
        Postazione pos = new Postazione();
        return pos;
    }
	
}
