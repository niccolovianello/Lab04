package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private List<Corso> corsi;
	private List<Studente> studenti;
	
	public Model() {
		this.corsi = new ArrayList<Corso>(); 
		this.studenti = new ArrayList<Studente>(); 
	}
}
