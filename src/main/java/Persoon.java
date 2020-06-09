
class Persoon {
    private int bsn;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;
    private Betaalwijze betaalwijze;
    private Dienblad dienblad;

    // constructor
    public Persoon(int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.geslacht = geslacht;
    }

    // lege constructor
    public Persoon() {
        geslacht = 1;
        setGeslacht('M');
        geslacht = 2;
        setGeslacht('V');
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getVolledigeNaam() { return voornaam +" "+achternaam; }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum.getDatumAsString();
    }

    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public char getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
    }

    public String toString() {
        return " ";
    }

    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    public void setBetaalwijze(Betaalwijze betaalwijze) {
        this.betaalwijze = betaalwijze;
    }

    public Dienblad getDienblad() { return dienblad; }

    public void pakDienblad(Dienblad dienblad) { if (dienblad != null) { this.dienblad = dienblad; }

    }
}