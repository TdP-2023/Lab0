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
	
	private final int TMax = 3;
	private int tentativi;

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
    void doAccedi(ActionEvent event) {
    	//leggi nome
    	String nome = this.txtNome.getText();
    	
    	//controlla nome
    	if(nome.isEmpty()) {
    		this.lblResult.setText("Inserire Nome!");
    		return;
    	}
    	
    	
    	
    	//leggi password
    	String pwd = this.txtPwd.getText();
    	
    	
    	
    	//controlla password
    	if(pwd.isEmpty()) {
    		this.lblResult.setText("Inserire Password!");
    		return;
    	}
    	
    	this.tentativi++;
    	
    	
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
    	
    	if(!pwdOK) {
    		this.lblTentativi.setText("Tentativi rimasti: " + (TMax-this.tentativi));
    		if(this.tentativi==TMax) {
    			this.lblResult.setText("Tentativi esauriti!");
    			this.disable();
    			
    		}
    	}else {
    		this.reset();
    		this.lblResult.setText("Success!");
//    		this.disable();
    	}
    	
    }
    
    @FXML
    void doClear(ActionEvent event) {
    	this.reset();
    }
    
    
    private void disable() {
    	this.txtNome.setDisable(true);
		this.txtPwd.setDisable(true);
		this.btnAccedi.setDisable(true);
    }

    private void reset() {
    	this.tentativi=0;
    	this.txtNome.clear();
    	this.txtNome.setDisable(false);
    	this.txtPwd.clear();
    	this.txtPwd.setDisable(false);
    	this.btnAccedi.setDisable(false);
    	this.lblTentativi.setText("Tentativi rimasti: " + TMax);

    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAccedi != null : "fx:id=\"btnAccedi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblResult != null : "fx:id=\"lblResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPwd != null : "fx:id=\"txtPwd\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTentativi != null : "fx:id=\"lblTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        
        this.tentativi = 0;

    }

}
