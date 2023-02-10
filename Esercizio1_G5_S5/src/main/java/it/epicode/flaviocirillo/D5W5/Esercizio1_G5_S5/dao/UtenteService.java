package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Utente;

@Service
public class UtenteService {

	@Autowired
    private UtenteRepository utenteRepo;

    public void insertUtente(Utente u) {
        utenteRepo.save(u);
        System.out.println("Utente inserito correttamente!");
    }
    
    public Optional<Utente> getById(int id) {
		return utenteRepo.findById(id);
	}
	
}
