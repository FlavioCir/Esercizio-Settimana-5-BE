package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Builder
public class Prenotazione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prenotazione_id;
	
	private LocalDate dataPrenotazione;
	private LocalDate dataScadenza;
	
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "postazione_id")
	private Postazione postazione;
	
}
