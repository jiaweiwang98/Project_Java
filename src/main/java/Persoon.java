import java.util.ArrayList;

class Persoon {
    private int bsn;
    private String voornaam;
    private String achternaam;
    private int dag;
    private int maand;
    private int jaar;
    private char geslacht;
    private ArrayList<Dienblad> dienbladen;
    private ArrayList<Artikel> artikelen;
    private Dienblad dienbladPersoon;
    private Artikel artikel;

    /**
     *
     * Hiermee worden alle velden ingesteld wanneer dit object 
     * gemaakt wordt. 
     */
    public Persoon(int persoonBSN, String persoonvoornaam, String persoonachternaam,
                   int persoondag, int persoonmaand, int persoonjaar, char persoongeslacht)
    {
        setBSN(persoonBSN);
        setVoornaam(persoonvoornaam);
        setAchternaam(persoonachternaam);
        setGeslacht(persoongeslacht);
        setGeboortedatum(persoondag, persoonmaand, persoonjaar);
    }

    /**
     * Hiermee worden alle velden ingesteld wanneer dit object 
     * gemaakt wordt. 
     * geslacht: man = 1 en vrouw = 0 
     */
    public Persoon()
    {
        bsn = 12341;
        voornaam = "Erik";
        achternaam = "Hoekstra";
        geslacht = 1;
        setGeslacht('M');
        dag = 26;
        maand = 1;
        jaar = 1994;
        setGeboortedatum(26, 1, 1994);
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
     * Verander de Geboortedatum.
     */
    public void setGeboortedatum(int dag, int maand, int jaar)
    {
        boolean uitkomst;
        this.dag = dag;
        this.maand = maand;
        this.jaar = jaar;
        if(dag>=1 && maand>=1 && maand<=12 && jaar>=1900 && jaar<=2100)
        {
            switch (maand) {
                case 1: case 3: case 5:
                case 7: case 8: case 10:
                case 12:
                    if (dag >=1 && dag <=31)
                    {
                        uitkomst = true;
                        break;
                    }
                    else
                    {
                        uitkomst = false;
                        break;
                    }
                case 4: case 6:
                case 9: case 11:
                    if (dag >=1 && dag <=30)
                    {
                        uitkomst = true;
                        break;
                    }
                    else
                    {
                        uitkomst = false;
                        break;
                    }
                case 2:
                    if (((jaar % 4 == 0) &&
                            !(jaar % 100 == 0))
                            || (jaar % 400 == 0)){
                        if (dag >=1 && dag <=29)
                        {
                            uitkomst = true;
                            break;
                        }else
                        {
                            uitkomst = false;
                            break;
                        }
                    } else if (dag >=1 && dag <=28)
                    {
                        uitkomst = true;
                        break;
                    }
                    else{
                        uitkomst = false;
                        break;
                    }
                default:
                    uitkomst = false;
                    break;

            }
        }else
        {
            uitkomst = false;
        }

        if(uitkomst == false)
        {
            this.dag = 0;
            this.maand = 0;
            this.jaar = 0;
        }else if(uitkomst == true)
        {
            this.dag = dag;
            this.maand = maand;
            this.jaar = jaar;
        }
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

    /**
     * laat de waarden van de instantievariabelen zien.
     */
    public void DrukAf()
    {
        System.out.println("#######################");
        System.out.println("# BSN: " + getBSN());
        System.out.println("# Naam: " + getVoornaam() + " "+ getAchternaam());
        System.out.println("# Geboortedatum: "+getGeboorteDatum());
        System.out.println("# Geslacht: " + getGeslacht());
        System.out.println("#######################");
    }

    /**
     * Getter voor geboortedatum
     * @return Geboortedatum
     */
    public String getGeboorteDatum() {
        String temp;
        if (dag==0 && maand==0 && jaar==0) {
            temp="Onbekend";
        } else {
            temp=dag+"/"+maand+"/"+jaar;
        }
        return temp;
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

    /**
     * Methode om dienblad te koppelen aan een persoon
     * @param persoon
     */
    public void pakDienblad(Dienblad persoon) {
        dienbladPersoon = new Dienblad(artikelen.get(0), 0,0.0);
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