package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

}
