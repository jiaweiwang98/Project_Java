package nl.hanze.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentKaart implements Serializable {

	@Column(name = "kaartNummer")
	private int kaartNummer;
	@Column(name = "vervalDatum")
	private String vervalDatum;

	public StudentKaart() {
	}

	public StudentKaart(int kaartNummer, String vervalDatum) {
		this.kaartNummer = kaartNummer;
		this.vervalDatum = vervalDatum;
	}

	public int getKaartNummer() {
		return kaartNummer;
	}

	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}

	public String getVervalDatum() {
		return vervalDatum;
	}

	public void setVervalDatum(String vervalDatum) {
		this.vervalDatum = vervalDatum;
	}

	@Override
	public String toString() {
		return "Kaartnummer: " + kaartNummer + "\t Vervadatum: " + vervalDatum;
	}
}
