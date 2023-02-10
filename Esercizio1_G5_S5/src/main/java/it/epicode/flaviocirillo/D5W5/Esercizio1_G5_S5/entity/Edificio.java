package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "edifici")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Builder
public class Edificio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int edificio_id;
	
	private String nome;
	private String indirizzo;
	private String citta;
	
	@OneToMany(mappedBy = "edificio")
	private Set<Postazione> postazioni;
	
}
