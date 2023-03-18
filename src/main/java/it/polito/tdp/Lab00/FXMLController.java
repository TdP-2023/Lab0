/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.Lab00;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private final int TMax = 3; //Variabile che rappresenta il numero massimo di tentativi
	private int tentativi; //Variabile che rappresenta i tentativi fatti. Viene inizializzata nella funzione initialize().

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAccedi"
    private Button btnAccedi; // Value injected by FXMLLoader

    @FXML // fx:id="lblResult"
    private Label lblResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtPwd"
    private TextField txtPwd; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblTentativi"
    private Label lblTentativi; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML
    /**
     * funzione che viene chiamata quando viene schiacciato il pulsante accedi
     * @param event
     */
    void doAccedi(ActionEvent event) {
    	//1. Se siamo ancora al primo tentativo, occorre leggere il nome. Altrimenti il campo nome è disabilitato 
    	if (this.tentativi==0) {
    		//1a. Leggere lo username dall'area di testo 
    		String nome = this.txtNome.getText();
    	
    		//1b. Effettuare un controllo per verificare che lo username letto non sia una stringa vuota.
    		// Se la stringa è vuota, esci dalla funzione senza proseguire oltre
    		if(nome.isEmpty()) {
    			this.lblResult.setText("Inserire Nome!");
    			return;
    		}
    	}
    	
    	
    	//2. Leggere la password dalla corrispondente area di testo
    	String pwd = this.txtPwd.getText();
    	
    	
    	//2a. Controllare che la password non sia vuota. Se la password non è vuota, 
    	// possiamo incrementare il numero di tentativi effettuati
    	if(pwd.isEmpty()) {
    		this.lblResult.setText("Inserire Password!");
    		return;
    	} 	
    	this.tentativi++;
    	//2b Visto che non siamo a zero tentativi, dobbiamo disabilitare il campo nome, bloccandolo
    	this.txtNome.setDisable(true);
    	
    	
    	//2c. Fare tutti gli altri controlli sulla password. In questa soluzione viene
    	// usata una variabile booleana per uscire dai check al primo controllo fallito,
    	// in modo da dare un messaggio di errore specifico all'utente. Altre implementazioni
    	// sono naturalmente possibili
    	boolean pwdOK = true;
    	if (pwd.length()<7) {
    		this.lblResult.setText("La password deve contenere almeno 7 caratteri!");
    		pwdOK = false;
    	}else if(!pwd.matches("^.*[0-9].*$") ) {
    		this.lblResult.setText("La password deve contenere almeno un numero!");
    		pwdOK = false;
    	}else if(!pwd.matches("^.*[A-Z].*$") ) {
    		this.lblResult.setText("La password deve contenere almeno una maiuscola!");
    		pwdOK = false;
    	} else if(!pwd.matches("^.*[a-z].*$") ) {
    		this.lblResult.setText("La password deve contenere almeno una minuscola!");
    		pwdOK = false;
    	} else if(!pwd.matches("^.*[!,?,@,#].*$") ) {
    		this.lblResult.setText("La password deve contenere almeno carattere speciale!");
    		pwdOK = false;
    	}
    	
    	//2d. se i controlli della password non sono andati a buon fine, ridurre il numero di tentativi
    	// rimasti ed eventualmente procedere a disabilitare i comandi della GUI. Altrimenti
    	//informare l'utente della corretta registrazione e resettare la GUI e lo stato dell'applicazione
    	if(!pwdOK) {
    		this.lblTentativi.setText("Tentativi rimasti: " + (TMax-this.tentativi));
    		if(this.tentativi==TMax) {
    			this.lblResult.setText("Tentativi esauriti!");
    			this.txtPwd.setDisable(true);
    			this.btnAccedi.setDisable(true);
    			
    		}
    	}else {
    		this.reset();
    		this.lblResult.setText("Success!");
    	}
    	
    }
    
    @FXML
    /**
     * funzione che viene chiamata alla pressione del pulsante clear e che resetta
     * la GUI e lo stato dell'applicazione
     * @param event
     */
    void doClear(ActionEvent event) {
    	this.reset();
    }
     

    /**
     * funzione helper che resetta la GUI e lo stato dell'applicazione
     */
    private void reset() {
    	this.tentativi=0;
    	this.txtNome.clear();
    	this.txtNome.setDisable(false);
    	this.txtPwd.clear();
    	this.txtPwd.setDisable(false);
    	this.btnAccedi.setDisable(false);
    	this.lblTentativi.setText("Tentativi rimasti: " + TMax);
    	this.lblResult.setText(null);
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAccedi != null : "fx:id=\"btnAccedi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblResult != null : "fx:id=\"lblResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPwd != null : "fx:id=\"txtPwd\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTentativi != null : "fx:id=\"lblTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        
        //inizializzazione delle nostre variabili private
        this.tentativi = 0; 
    }

}
