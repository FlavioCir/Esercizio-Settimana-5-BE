package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Prenotazione;

@Service
public class PrenotazioneService {

	@Autowired
    private PrenotazioneRepository prenotazioneRepo;

    public void insertPrenotazione(Prenotazione pre) {
        prenotazioneRepo.save(pre);
        System.out.println("Prenotazione inserita correttamente!");
    }
	
}
