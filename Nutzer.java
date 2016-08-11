package Verarbeitung;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <strong/>Zweck:</strong> Definiert einen Nutzer, dem Name, verschlüsseltes Passwort, eine Sicherheitsfrage und die verschlüsselte Antwort darauf zugeordnet sind
 * <p><strong>Änderungshistorie:</strong></p>
 * @version 
 * @author Alexander Reichenbach
 * 
 */

public abstract class Nutzer implements Serializable {

	// Attribute
    protected String name, pwHash, sicherheitsFrage, sicherheitsAntwortHash;
    protected static MessageDigest messageDigest;		// more or less solved

    // Konstruktor
    protected Nutzer (String name, String password, String sicherheitsFrage, String sicherheitsAntwort) {

        this.name=name;
        this.sicherheitsFrage = sicherheitsFrage;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            setPwHash(password);
            setSicherheitsAntwortHash(sicherheitsAntwort);
        } catch (NoSuchAlgorithmException wtf){
            System.err.println("Interner Fehler: Hash-Algorithmus nicht vorhanden [Nutzer-Konstr]");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String password) {
    	if (messageDigest==null) {
    		try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException wtf){
                System.err.println("Interner Fehler: Hash-Algorithmus nicht vorhanden [Nutzer-Konstr]");
            }
    	}
        messageDigest.update(password.getBytes());
        pwHash = new String(messageDigest.digest());
    }

    public String getSicherheitsFrage() {
        return sicherheitsFrage;
    }

    public void setSicherheitsFrage(String sicherheitsFrage) {
        this.sicherheitsFrage = sicherheitsFrage;
    }

    public String getSicherheitsAntwortHash() {
        return sicherheitsAntwortHash;
    }

    public void setSicherheitsAntwortHash(String sicherheitsAntwort) {
    	if (messageDigest==null) {
    		try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException wtf){
                System.err.println("Interner Fehler: Hash-Algorithmus nicht vorhanden [Nutzer-Konstr]");
            }
    	}
        messageDigest.update(sicherheitsAntwort.getBytes());
        sicherheitsAntwortHash = new String(messageDigest.digest());
    }

    /**
     * 
     * @param password
     * @return
     */
    
    public boolean checkPw (String password){
    	if (messageDigest==null) {
    		try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException wtf){ //ummm vllt nicht wtf? :D
                System.err.println("Interner Fehler: Hash-Algorithmus nicht vorhanden [Nutzer-Konstr]");
            }
    	}
        messageDigest.update(password.getBytes());
        if (pwHash.equals(new String (messageDigest.digest()))) return true;
        else return false;
    }

    /**
     * 
     * @param antwort
     * @return
     */
    
    public boolean checkFrage (String antwort){
    	if (messageDigest==null) {
    		try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException wtf){
                System.err.println("Interner Fehler: Hash-Algorithmus nicht vorhanden [Nutzer-Konstr]");
            }
    	}
        messageDigest.update(antwort.getBytes());
        if (sicherheitsAntwortHash.equals(new String (messageDigest.digest()))) return true;
        else return false;
    }
}
