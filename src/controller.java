package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class controller {

	DES cipher = new DES();
	
 	
    @FXML
    private Button btnDecrypt;
    
    @FXML
    private Button btnClear;

    @FXML
    private Button btnEncrypt;

    @FXML
    private Button btnKey;
    
    @FXML
    private TextField txtKey;
    
    @FXML
    private TextField txtCiphertext;

    @FXML
    private TextField txtPlaintext;
    
    @FXML
    private Label lblKey;

    @FXML
    void Encrypt(ActionEvent event) {
    	   	
    	String PlainText = this.txtPlaintext.getText();
    	String Key = this.txtKey.getText();
    	if(!processor.ValidateString(Key)) {
    		processor.Err(this.lblKey);
    	}else {
    		processor.NoErr(this.lblKey);
    	PlainText = processor.String2Hex(PlainText);
    	String[] Vtext = processor.formatStrings(PlainText);
    	String encrypted = "";
    	
    	for(int i = 0;i < Vtext.length;i++) {
    		Vtext[i] = cipher.encrypt(Vtext[i], Key);
    		encrypted = encrypted + Vtext[i];
    	}
//    	encrypted = processor.hexToString(encrypted);
    	this.txtCiphertext.setText(encrypted);
    	this.txtPlaintext.setText("");
    	
    	}
    }

    
    @FXML
    void Decrypt(ActionEvent event) {
    	String CipherText = this.txtCiphertext.getText();
    	String Key = this.txtKey.getText();

    	if(!processor.ValidateString(Key)) {
    		processor.Err(this.lblKey);
    	}else {
    	processor.NoErr(this.lblKey);
//    	CipherText = processor.String2Hex(CipherText);
    	String[] Vtext = processor.formatStrings(CipherText);
    	int count = 0;
    	if(Vtext[Vtext.length-1].equals("0000000000000000")) {
    		count = Vtext.length-1;
    	}else {
    		count = Vtext.length;
    	}
    	
    	String[] Vtext2 = new String[count];
    	for(int i = 0; i < count;i++) {
    		Vtext2[i] = Vtext[i];
    	}
    	
    	String decrypted = "";
    	
    	for(int i = 0;i < Vtext2.length;i++) {
    		Vtext2[i] = cipher.decrypt(Vtext2[i], Key);
    		decrypted = decrypted + Vtext2[i];
    	}
    	
    	decrypted = processor.removeFill(decrypted);
    	decrypted = processor.hex2String(decrypted);
    	
    	this.txtPlaintext.setText(decrypted);
    	this.txtCiphertext.setText("");
    	
    	}
    }
    
    @FXML
    void generateKey(ActionEvent event) {
    	this.txtKey.setText(processor.getKey(this.txtKey.getText()));
    }
    
    @FXML
    void Clear(ActionEvent event) {
    this.txtCiphertext.setText("");
    this.txtPlaintext.setText("");
    this.txtKey.setText("");
    this.lblKey.setText("");
    }
        
    }