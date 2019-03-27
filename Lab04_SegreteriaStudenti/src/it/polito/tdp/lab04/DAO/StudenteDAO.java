package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");

				Studente studente = new Studente(matricola, nome, cognome, cds);
				studenti.add(studente);
			}

			return studenti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore DB");
		}
	}
	
	public Studente getStudente(int m) {

		final String sql = "SELECT * FROM studente WHERE matricola = "+m;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");

				Studente studente = new Studente(matricola, nome, cognome, cds);
				return studente;
			}

			return null;

		} catch (SQLException e) {
			throw new RuntimeException("Errore DB");
		}
	}
	
	public List<String> getCorsiStudente(int m) {
		
		List<String> codici = new LinkedList<String>();
		final String sql = "SELECT * FROM iscrizione WHERE matricola = "+m;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String codins = rs.getString("codins");
				codici.add(codins);
			}

			return codici;

		} catch (SQLException e) {
			throw new RuntimeException("Errore DB");
		}
	}


}
