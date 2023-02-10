package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Edificio;

@Service
public class EdificioService {

	@Autowired
    private EdificioRepository edificioRepo;

    public void insertEdificio(Edificio e) {
        edificioRepo.save(e);
        System.out.println("Edificio inserito correttamente!");
    }
    
    public Optional<Edificio> getById(int id) {
		return edificioRepo.findById(id);
	}
	
}
