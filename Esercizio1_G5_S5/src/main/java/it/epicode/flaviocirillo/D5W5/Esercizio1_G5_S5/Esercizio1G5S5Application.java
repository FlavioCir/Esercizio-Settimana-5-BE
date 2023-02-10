package it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.config.EdificioConfig;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.config.PostazioneConfig;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.config.PrenotazioneConfig;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.config.UtenteConfig;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao.EdificioService;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao.PostazioneService;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao.PrenotazioneService;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.dao.UtenteService;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Edificio;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Postazione;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Prenotazione;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.TipoPostazione;
import it.epicode.flaviocirillo.D5W5.Esercizio1_G5_S5.entity.Utente;

@SpringBootApplication
public class Esercizio1G5S5Application implements CommandLineRunner {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Esercizio1G5S5Application.class, args);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	EdificioService es;

	@Autowired
	PostazioneService ps;

	@Autowired
	UtenteService us;

	@Autowired
	PrenotazioneService prens;

	ApplicationContext ctxE = new AnnotationConfigApplicationContext(EdificioConfig.class);
	ApplicationContext ctxP = new AnnotationConfigApplicationContext(PostazioneConfig.class);
	ApplicationContext ctxU = new AnnotationConfigApplicationContext(UtenteConfig.class);
	ApplicationContext ctxPren = new AnnotationConfigApplicationContext(PrenotazioneConfig.class);

	Scanner in = new Scanner(System.in);

	@Override
	public void run(String... args) throws Exception {
		
		gestore();
		//findAllPostazioni();

		((AnnotationConfigApplicationContext) ctxE).close();
		((AnnotationConfigApplicationContext) ctxP).close();
		((AnnotationConfigApplicationContext) ctxU).close();
		((AnnotationConfigApplicationContext) ctxPren).close();
		
	}
	
	public void gestore() {
		try {
			System.out.println("--- Benvenuto nel gestore delle prenotazioni ---");
			System.out.println("-------------------------------------");
			System.out.println("1 - Area amministrativa");
			System.out.println("2 - Crea un utente");
			System.out.println("3 - Prenota postazione");
			System.out.println("4 - Ricerca");
			System.out.println("Seleziona un numero:");
			int selezione = in.nextInt();
			switch (selezione) {
			case (1):
				selezioneAmministrazione();
				break;
			case (2):
				creaUtente();
				break;
			case (3):
				creaPrenotazione();
				break;
			case (4):
				ricerca();
			break;
			default:
				System.out.println("Inserisci un valore corretto!");
			}
			
		}catch (Exception e) {
			System.out.println("Errore!");
		}
		

	}

	static String tipoSelect;
	public void ricerca() {
		System.out.println("--- Benvenuto nella ricerca! ---");
		System.out.println("1 - Ricerca per Tipo");
		System.out.println("2 - Ricerca per città");
		System.out.println("3 - Ricerca per tipo e città");
		System.out.println("Inserisci un numero:");
		int selezione = in.nextInt();
		switch(selezione) {
		case(1):
			ricercaPerTipo();
		break;
		case(2):
			ricercaPerCitta();
		break;
		case(3):
			ricercaPerTipoAndCitta();
		break;
		default:
			System.out.println("Inserisci un valore esatto!");
		break;
		}
		
	}
	public void ricercaPerTipo() {
		System.out.println("1 - PRIVATO");
		System.out.println("2 - OPENSPACE");
		System.out.println("3 - SALA_RIUNIONI");
		System.out.println("Inserisci un numero:");
		int selezioneTipo = in.nextInt();
		
		if (selezioneTipo == 1) {
			tipoSelect = "PRIVATO";
		} else if (selezioneTipo == 2) {
			tipoSelect = "OPENSPACE";
		} else if (selezioneTipo == 3) {
			tipoSelect= "SALA_RIUNIONI";
		} else {
			System.out.println("Inserisci un valore esatto!");
		}	
		
		findFromTipo(tipoSelect);
	}
	public void ricercaPerCitta() {
		System.out.println("Inserisci nome città: ");
		String citta = in.next();
		findFromCitta(citta);
	}
	
	public void ricercaPerTipoAndCitta() {
		System.out.println("1 - PRIVATO");
		System.out.println("2 - OPENSPACE");
		System.out.println("3 - SALA_RIUNIONI");
		System.out.println("Inserisci un numero:");
		int selezioneTipo = in.nextInt();
		
		if (selezioneTipo == 1) {
			tipoSelect = "PRIVATO";
		} else if (selezioneTipo == 2) {
			tipoSelect = "OPENSPACE";
		} else if (selezioneTipo == 3) {
			tipoSelect= "SALA_RIUNIONI";
		} else {
			System.out.println("Inserisci un valore esatto!");
		}	
		in.nextLine();
		System.out.println("Inserisci nome città: ");
		String citta = in.nextLine();
		findFromTipoAndCitta(tipoSelect, citta);
	}
	
	public void creaPrenotazione() {
		in.nextLine();
		System.out.println("Inserisci in che data vuoi prenotare(AAA-MM-DD)");
		String dataPren = in.nextLine();
		LocalDate dataPrenotazione = LocalDate.parse(dataPren);
		LocalDate dataScad = LocalDate.parse(dataPren).plusDays(1);
		System.out.println("Inserisci l'id della postazione da prenotare");
		int idPos = in.nextInt();
		Postazione postazione = getPostazione(idPos);
		System.out.println("Inserisci l'id dell'utente");
		int idUser = in.nextInt();
		Utente utente = getUtente(idUser);
		
		long countElementi = prens.getCountPrenotazioni(dataPrenotazione, idPos);
		System.out.println(countElementi);
		int nMaxOccupanti = postazione.getNMaxOccupanti();
		if(countElementi == nMaxOccupanti) {
			System.out.println("La postazione " + idPos + " è completa per la data scelta! Non puoi prenotare!");
			System.exit(0);
		} else if(countElementi < nMaxOccupanti){
			long contaElementi = prens.getCountPrenotazioniUtente(dataPrenotazione, idUser);
			if(contaElementi >= 1) {
				System.out.println("Hai già una prenotazione per questa data. Non puoi prenotare altre postazioni!");
				System.exit(0);
			} else {
				insertPrenotazione(dataPrenotazione, dataScad, postazione, utente);

			}
		}
	}

	private Utente getUtente(int idUser) {

		Optional<Utente> utenteOpt = us.getById(idUser);
		Utente utente = utenteOpt.get();
		return utente;
	}

	public void findAllPostazioni() {
		String sep = "-----------------------------------";
		System.out.println("Tutte le postazioni:");
		List<Postazione> postazione = ps.getAllPostazioni();
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	public void findFromTipoAndCitta(String tipo, String citta) {
		String sep = "-----------------------------------";
		List<Postazione> postazione = ps.getFromTipoAndCitta(tipo, citta);
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	public void findFromCitta(String citta) {
		String sep = "-----------------------------------";
		List<Postazione> postazione = ps.getByCitta(citta);
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	public void findFromTipo(String tipo) {
		String sep = "-----------------------------------";
		List<Postazione> postazione = ps.getByTipo(tipo);
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}
	
	public void check(String data, int id) {
		
	}

	public void selezioneAmministrazione() {
		System.out.println("1 - Crea un edificio");
		System.out.println("2 - Crea una postazione");
		System.out.println("Seleziona un numero:");
		int selezione = in.nextInt();
		switch (selezione) {
		case (1):
			creaEdificio();
			break;
		case (2):
			creaPostazione();
		default:
			System.out.println("Inserisci un valore corretto!");
			break;
		}

	}

	public void creaEdificio() {
		in.nextLine();
		System.out.println("Inserisci nome:");
		String nome = in.nextLine();
		System.out.println("Inserisci città:");
		String citta = in.nextLine();
		System.out.println("Inserisci Indirizzo:");
		String indirizzo = in.nextLine();
		insertEdificio(citta, indirizzo, nome);
	}

	static TipoPostazione tipoPostazione;

	public void creaPostazione() {
		System.out.println("1 - Privato");
		System.out.println("2 - Openspace");
		System.out.println("3 - Sala riunioni");
		System.out.println("Inserisci il tipo:");
		int selezioneTipo = in.nextInt();
		if (selezioneTipo == 1) {
			tipoPostazione = TipoPostazione.PRIVATO;
		} else if (selezioneTipo == 2) {
			tipoPostazione = TipoPostazione.OPENSPACE;
		} else if (selezioneTipo == 3) {
			tipoPostazione = TipoPostazione.SALA_RIUNIONI;
		} else {
			System.out.println("Inserisci un valore esatto!");
		}
		System.out.println("Inserisci il numero massimo di occupanti:");
		int nMaxOccupanti = in.nextInt();
		System.out.println("Inserisci l'id dell'edificio");
		int idEdificio = in.nextInt();
		Edificio edificio = getEdificio(idEdificio);
		insertPostazione(nMaxOccupanti, tipoPostazione, edificio);
	}

	public void creaUtente() {
		in.nextLine();
		System.out.println("Inserisci username:");
		String username = in.nextLine();
		System.out.println("Inserisci nome e cognome:");
		String nome = in.nextLine();
		System.out.println("Inserisci email:");
		String email = in.nextLine();
		insertUtente(nome, email, username);
	}

	public Edificio insertEdificio(String citta, String indirizzo, String nome) {
		Edificio e = (Edificio) ctxE.getBean("e");
		e.setCitta(citta);
		e.setIndirizzo(indirizzo);
		e.setNome(nome);
		es.insertEdificio(e);
		return e;
	}

	public Edificio getEdificio(int id) {
		Optional<Edificio> edificioOpt = es.getById(id);
		Edificio edificio = edificioOpt.get();
		return edificio;
	}

	public Postazione getPostazione(int id) {
		Optional<Postazione> postazioneOpt = ps.getById(id);
		Postazione postazione = postazioneOpt.get();
		return postazione;
	}

	public Postazione insertPostazione(int nMaxOccupanti, TipoPostazione tipo, Edificio edificio) {
		Postazione pos = (Postazione) ctxP.getBean("pos");
		pos.setEdificio(edificio);
		pos.setNMaxOccupanti(nMaxOccupanti);
		pos.setTipo(tipo);
		ps.insertPostazione(pos);
		edificio.setPostazioni(new HashSet<Postazione>() {
			{
				add(pos);
			}
		});
		return pos;
	}

	public Utente insertUtente(String nome, String email, String username) {
		Utente u = (Utente) ctxU.getBean("u");
		u.setNome(nome);
		u.setEmail(email);
		u.setUsername(username);
		us.insertUtente(u);
		return u;
	}

	public Prenotazione insertPrenotazione(LocalDate dataPren, LocalDate dataScad, Postazione postazione,
			Utente utente) {
		Prenotazione pre = (Prenotazione) ctxPren.getBean("p");
		pre.setDataPrenotazione(dataPren);
		pre.setDataScadenza(dataScad);
		pre.setPostazione(postazione);
		pre.setUtente(utente);
		prens.insertPrenotazione(pre);
		return pre;
	}

}
