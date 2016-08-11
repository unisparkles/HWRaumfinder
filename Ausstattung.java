package Verarbeitung;

import java.io.Serializable;


/**
 * <strong/>Zweck:</strong> Ermöglicht es, Ausstattungsobjekte zu erstellen, die Ausstattung und Kapazität eines Raumes beschreiben
 * <p><strong>Änderungshistorie:</strong> ...</p>
 * @version 2.1
 * @author Hanna Behnke, Alexander Reichenbach
 *
 */


public class Ausstattung implements VerarbeitungInterfaces.AusstattungIF, Serializable {


	//Attribute
	
	private boolean beamer;
	private boolean ohp;
	private boolean tafel;
	private boolean smartboard;
	private boolean whiteboard;
	private boolean computerraum;
	private int kapazitaet;
	
	
	//Konstruktor
	
	public Ausstattung(boolean beamer, boolean ohp, boolean tafel, boolean smartboard, boolean whiteboard,
			boolean computerraum, int kapazitaet) {
		
		this.beamer = beamer;
		this.ohp = ohp;
		this.tafel = tafel;
		this.smartboard = smartboard;
		this.whiteboard = whiteboard;
		this.computerraum = computerraum;
		this.kapazitaet=kapazitaet;
	}

	//Getter und Setter

	public boolean isBeamer() {
		return beamer;
	}


	public void setBeamer(boolean beamer) {
		this.beamer = beamer;
	}


	public boolean isOhp() {
		return ohp;
	}


	public void setOhp(boolean ohp) {
		this.ohp = ohp;
	}


	public boolean isTafel() {
		return tafel;
	}


	public void setTafel(boolean tafel) {
		this.tafel = tafel;
	}


	public boolean isSmartboard() {
		return smartboard;
	}


	public void setSmartboard(boolean smartboard) {
		this.smartboard = smartboard;
	}


	public boolean isWhiteboard() {
		return whiteboard;
	}


	public void setWhiteboard(boolean whiteboard) {
		this.whiteboard = whiteboard;
	}


	public boolean isComputerraum() {
		return computerraum;
	}


	public void setComputerraum(boolean computerraum) {
		this.computerraum = computerraum;
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public void setKapazitaet(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	/**
	 * <p><strong>Vorbedingungen:</strong> Es muss ein Ausstattungs-Objekt vorhanden sein, auf dem die Methode aufgerufen werden kann und eines, welches als "anforderung" übergeben wird.</p>
	 * <p><strong>Effekt:</strong> Berechnet einen Integer-Wert, der Auskunft darüber gibt, wie gut die Ausstattung (eines Raumes) den Anforderungen (des Nutzers) entspricht.</p>
	 * @param anforderung ein Ausstattungs-Objekt, das beschreibt, welche Ausstattungsgegenstände vom Nutzer gefordert werden (wird mit Austattung der Räume verglichen)
	 * @return erfuellteAnforderungen zählt, wie viele Anforderungen erfüllt werden, wird also hochgesetzt, wenn eine Anforderung erfüllt ist, aber auch, wenn der Raum eine Ausstattung hat, die nicht gefordert ist
	 *
	 * <p><strong>Anmerkung:</strong> Es wurde mit der in Java nicht integrierten, logischen Verknüpfung der Implikaition gearbeitet,
	 * welche mit den vorhandenen logischen Operatoren der Bool'schen Algebra realisiert wurde.</p>
	 */
	
	public int hatMindestens(Ausstattung anforderung){
		
		int erfuellteAnforderungen=0;
	
		if ((!anforderung.beamer || this.beamer)	// nicht gefordert oder existiert
						&& (!anforderung.ohp || this.ohp)
						&& (!anforderung.tafel || this.tafel)
						&& (!anforderung.smartboard || this.smartboard)
						&& (!anforderung.whiteboard || this.whiteboard)
						&& (!anforderung.computerraum || this.computerraum)
						&& (anforderung.getKapazitaet()<=this.getKapazitaet()))
		{
			if (anforderung.beamer && this.beamer) erfuellteAnforderungen ++;	//+= 2;
			if (anforderung.ohp && this.ohp) erfuellteAnforderungen++;
			if (anforderung.tafel && this.tafel) erfuellteAnforderungen++;
			if (anforderung.smartboard && this.smartboard) erfuellteAnforderungen ++;	//+= 2;
			if (anforderung.whiteboard && this.whiteboard) erfuellteAnforderungen++;
			if (anforderung.computerraum && this.computerraum) erfuellteAnforderungen ++;	//+= 3;
			if (anforderung.getKapazitaet()<=this.getKapazitaet()) erfuellteAnforderungen++;
		}
		
		return erfuellteAnforderungen;
	}

	public String[][] toStringArray() {
		String[][] erg = new String[2][7];

		erg[0][0] = "Beamer";
		erg[0][1] = "OH-Projektor";
		erg[0][2] = "Tafel";
		erg[0][3] = "Smartboard";
		erg[0][4] = "Whiteboard";
		erg[0][5] = "Computerraum";
		erg[0][6] = "Kapazität";

		if (beamer) erg[1][0]="Ja";
			else erg[1][0]="Nein";
		if (ohp) erg[1][1]="Ja";
			else erg[1][1]="Nein";
		if (tafel) erg[1][2]="Ja";
			else erg[1][2]="Nein";
		if (smartboard) erg[1][3]="Ja";
			else erg[1][3]="Nein";
		if (whiteboard) erg[1][4]="Ja";
			else erg[1][4]="Nein";
		if (computerraum) erg[1][5]="Ja";
			else erg[1][5]="Nein";
		erg[1][6] = Integer.toString(kapazitaet);

		return erg;
	}
}
