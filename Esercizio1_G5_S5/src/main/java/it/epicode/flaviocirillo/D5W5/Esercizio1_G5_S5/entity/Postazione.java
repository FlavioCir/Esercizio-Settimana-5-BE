package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "postazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Builder
public class Postazione {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postazione_id;
	
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;
	
	private int nMaxOccupanti;
	
	@ManyToOne
	@JoinColumn(name = "edificio_id")
	private Edificio edificio;
	
	@OneToMany(mappedBy = "postazione")
	private Set<Prenotazione> prenotazioni;

	private boolean isLibero;

	@Override
	public String toString() {
		return "ID POSTAZIONE: - " + postazione_id + " " + " | Nome edificio: " + edificio.getNome() +" | Città: " + edificio.getCitta() + " | Indirizzo: " + edificio.getIndirizzo();
	}	
	
}
