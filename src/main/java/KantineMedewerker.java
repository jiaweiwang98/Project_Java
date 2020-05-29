public class KantineMedewerker extends Persoon {

    private String medewerkersNummer;
    private boolean kassaRechten;

    public KantineMedewerker(int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String medewerkersNummer, boolean kassaRechten) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersNummer = medewerkersNummer;
        this.kassaRechten = kassaRechten;
    }

    public String getMedewerkersNummer() {
        return medewerkersNummer;
    }

    public void setMedewerkersNummer(String medewerkersNummer) {
        this.medewerkersNummer = medewerkersNummer;
    }

    public boolean isKassaRechten() {
        return kassaRechten;
    }

    public void setKassaRechten(boolean kassaRechten) {
        this.kassaRechten = kassaRechten;
    }
}
