package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;
    
    @FXML
    private ChoiceBox<String> tendina;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    @FXML
    private Button btnCheck;

    @FXML
    void doCercaCorsi(ActionEvent event) {

    	txtResult.clear();
    	
	    try {
	    	if(!tendina.getValue().equals("")) {
		    	for(Studente s : model.listTuttiStudenti()) {
	    			if(s.getMatricola()==Integer.parseInt(txtMatricola.getText())) {
	    				if(model.studenteIscrittoCorso(tendina.getValue(), Integer.parseInt(txtMatricola.getText())))
	    					txtResult.appendText("Studente già iscritto al corso.\n");
	    				else
	    					txtResult.appendText("Studente non iscritto al corso selezionato.\n");
	    			}
	    				
	    		}
	    	}
	    	
	    	else {
	    		List<Corso> corsi = new LinkedList<Corso>();
		   		
		   		for(Studente s : model.listTuttiStudenti()) {
		   			if(s.getMatricola()==Integer.parseInt(txtMatricola.getText()))
	    				corsi = model.listCorsi(s.getMatricola());
		   		}
		    	
		   		for(Corso c : corsi) {
		   			txtResult.appendText(c.toString()+"\n");
		   		}
	    		
	    	}
	   	}
    	catch(NullPointerException npe) {	    		
    		txtResult.appendText("Lo studente cercato non è presente nel database.\n");
	    }
	    catch(NumberFormatException nfe) {	    		
    		txtResult.appendText("Lo studente cercato non è presente nel database.\n");
	    }

    }
    
    @FXML
    void doCheck(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	
	    try {
	   		Studente s = model.getStudente(Integer.parseInt(txtMatricola.getText()));
	   		txtNome.appendText(s.getNome());
	   		txtCognome.appendText(s.getCognome());
	   	}
    	catch(NumberFormatException nfe) {
    		txtResult.appendText("Il numero di matricola può contenere solo interi.\n");
    	}
	    	
	    catch(NullPointerException npe) {
	   		txtResult.appendText("Il numero di matricola inserito non appartiene a nessuno studente.\n");
	   	}
	    	
    }
    

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	try {
    		List<Studente> iscritti = new LinkedList<Studente>();
    		
    		for(Corso c : model.listTuttiCorsi()) {
    			if(c.getNome().compareTo(tendina.getValue())==0)
    				iscritti = model.listIscritti(c.getCodins());
    		}
    		
    		for(Studente s : iscritti) {
    			txtResult.appendText(s.toString());
    		}
    			
    	}
    	catch(NullPointerException npe) {
    		txtResult.appendText("Scegliere un corso.\n");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtResult.clear();
    	
    	try {
    		if(model.iscrizione(tendina.getValue(), Integer.parseInt(txtMatricola.getText()))) {
    			txtResult.appendText("Iscrizione effettuata con successo.\n");
    		}
    		
    		else {
    			txtResult.appendText("Iscrizione non effettuata.\n");
    		}

    	}
    	catch(NullPointerException npe) {
    		txtResult.appendText("Lo studente o il corso cercato non sono presenti nel database.\\n");
    	}
    	catch(NumberFormatException nfe) {
    		txtResult.appendText("Il numero di matricola può contenere solo interi.\n");
    	}
    	catch(Exception nfe) {
    		txtResult.appendText("Studente già iscritto al corso selezionato.\n");
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    }

    @FXML
    void initialize() {
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		tendina.getItems().add("");
		for(Corso c : model.listTuttiCorsi())
			tendina.getItems().add(c.toString());
	}
}

