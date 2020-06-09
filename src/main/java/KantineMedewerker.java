public class KantineMedewerker extends Persoon implements KortingskaartHouder {

    private int medewerkersNummer;
    private boolean kassaRechten;

    public KantineMedewerker(int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkersNummer, boolean kassaRechten) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersNummer = medewerkersNummer;
        this.kassaRechten = kassaRechten;
    }

    public int getMedewerkersNummer() {
        return medewerkersNummer;
    }

    public void setMedewerkersNummer(int medewerkersNummer) {
        this.medewerkersNummer = medewerkersNummer;
    }

    public boolean isKassaRechten() {
        return kassaRechten;
    }

    public void setKassaRechten(boolean kassaRechten) {
        this.kassaRechten = kassaRechten;
    }

    @Override
    public String toString() {
        return "Kantinemedewerker";
    }

    @Override
    public double geefKortingsPercentage() {
        return 0;
    }

    @Override
    public boolean heeftMaximum() {
        return false;
    }

    @Override
    public double geefMaximum() {
        return 0;
    }
}
