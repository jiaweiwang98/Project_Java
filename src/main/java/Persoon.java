import java.util.ArrayList;

class Persoon {
    private int bsn;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;;
    private Dienblad dienbladPersoon;
    private ArrayList<Artikel> artikelen;
    /**
     *
     * Hiermee worden alle velden ingesteld wanneer dit object 
     * gemaakt wordt. 
     */
    public Persoon(int bsn, String voornaam, String achternaam,
                   Datum geboortedatum, char geslacht)
    {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geslacht = geslacht;
        this.geboortedatum = geboortedatum;

    }

    /**
     * Hiermee worden alle velden ingesteld wanneer dit object 
     * gemaakt wordt. 
     * geslacht: man = 1 en vrouw = 0
     */
    public Persoon()
    {
        /*geslacht = 1;
        setGeslacht('M');

        geslacht = 2;
        setGeslacht('V');*/

    }

    @Override
    public String toString() {
        return "Persoon{" +
                "geboortedatum=" + geboortedatum +
                ", geslacht=" + geslacht +
                '}';
    }

    /**
     * Verander het BSN.
     */
    public void setBSN(int bsn)
    {
        this.bsn = bsn;
    }

    /**
     * Verander de Voornaam.
     */
    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    /**
     * Verander de Achternaam.
     */
    public void setAchternaam(String achternaam)
    {
        this.achternaam = achternaam;
    }

    /**
     * Verander het Geslacht.
     * Man = 1    en    Vrouw = 0
     */
    public void setGeslacht(char geslacht)
    {
        if(geslacht == 'M'){
            this.geslacht = geslacht;
        }else if(geslacht == 'V'){
            this.geslacht = geslacht;
        }else{
            this.geslacht = 'X';
        }
    }

    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    /**
     * Getter voor geslacht
     * @return geslacht.
     */

    public String getGeslacht() {
        if(geslacht == 'M')
        {
            return "man";
        }else if(geslacht == 'V')
        {
            return "vrouw";
        }else
        {
            return "Onbekend";
        }
    }

    /**
     * Getter voor Voornaam
     * @return Voornaam.
     */

    public String getVoornaam() {
        return voornaam;
    }

    /**
     * Getter voor Achternaam
     * @return Achternaam.
     */

    public String getAchternaam() {
        return achternaam;
    }

    /**
     * Getter voor BSN
     * @return BSN.
     */

    public int getBSN() {
        return bsn;
    }

    public String getGeboortedatum() {
        return geboortedatum.getDatumAsString();
    }

    /**
     * Methode om dienblad te koppelen aan een persoon
     * @param persoon
     */
    public void pakDienblad(Dienblad persoon) {
        dienbladPersoon = new Dienblad();
    }

    /**
     * Methode om artikel te pakken en te plaatsen op het dienblad
     * @param artikel
     */
    public void pakArtikel(Artikel artikel) {
        dienbladPersoon.voegToe(artikel);//method body omitted
    }

    /**
     * Methode om de totaalprijs van de artikelen 
     * op dienblad dat bij de persoon hoort uit te rekenen
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        return dienbladPersoon.getTotaalPrijs();
    }

    /**
     * Methode om het aantal artikelen op dienblad dat bij de 
     * persoon hoort te tellen
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        return dienbladPersoon.getAantalArtikelen();
    }
}