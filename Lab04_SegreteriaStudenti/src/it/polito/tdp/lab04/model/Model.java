package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public List<Corso> listTuttiCorsi(){
		CorsoDAO dao= new CorsoDAO();
		return dao.getTuttiICorsi();
	}
	
	public List<Studente> listTuttiStudenti(){
		StudenteDAO dao= new StudenteDAO();
		return dao.getTuttiGliStudenti();
	}
	
	public Studente getStudente(int m){
		StudenteDAO dao = new StudenteDAO();
		return dao.getStudente(m);
	}
	
	public List<Studente> listIscritti(String codins){
		CorsoDAO dao = new CorsoDAO();
		List<Studente> stud = new ArrayList<Studente>();
		for(Integer m : dao.getStudentiIscrittiAlCorso(codins)) {
			for(Studente s : this.listTuttiStudenti()) {
				if(s.getMatricola() == m)
					stud.add(s);
			}
		}
		return stud;
	}

	public List<Corso> listCorsi(int matricola) {
		
		List<Corso> corsi = new ArrayList<Corso>();
		StudenteDAO dao = new StudenteDAO();
		for(String c : dao.getCorsiStudente(matricola)) {
			for(Corso corso : this.listTuttiCorsi()) {
				if(corso.getCodins().compareTo(c) == 0)
					corsi.add(corso);
			}
		}
		return corsi;
	}
	
	public boolean studenteIscrittoCorso(String nomeEsame, int matricola) {
		StudenteDAO sdao = new StudenteDAO();
		CorsoDAO cdao = new CorsoDAO();
		
		String codins = null;
		
		for(Corso c : this.listTuttiCorsi()) {
			if(c.getNome().equals(nomeEsame))
				codins = c.getCodins();
		}
		
		for(Integer m : cdao.getStudentiIscrittiAlCorso(codins)) {
			for(String c : sdao.getCorsiStudente(matricola)) {
				if(c.equals(codins) && m.equals(matricola))
					return true;
			}
		}
		return false;
	}
	
	public boolean iscrizione(String nomeEsame, int matricola) {
		CorsoDAO dao = new CorsoDAO();
		
		String codins = null;
		
		for(Corso c : this.listTuttiCorsi()) {
			if(c.getNome().equals(nomeEsame))
				codins = c.getCodins();
		}
		
		for(Studente s : this.listTuttiStudenti()) {
			for(Corso c : this.listTuttiCorsi()) {
				if(s.getMatricola() == matricola && c.getCodins().equals(codins)) {
					dao.iscriviStudenteACorso(s, c);
					return true;
				}
			}
		}
		return false;
		
	}
	
	
}
