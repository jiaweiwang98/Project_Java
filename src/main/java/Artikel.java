import java.text.NumberFormat;
import java.util.ArrayList;

public class Artikel {
    private String naam;
    private int verkoopPrijs;
    private int hoeveelheid;
    private ArrayList<Artikel> artikelen;
    /**
     * De contructor van Artikel
     * @param naam naam van artikel
     * @param verkoopPrijs verkoop prijs van het artikel
     */
    public Artikel(String naam, int verkoopPrijs, int hoeveelheid) {
        this.naam = naam;
        this.verkoopPrijs = verkoopPrijs;
        this.hoeveelheid = hoeveelheid;
    }

    public Artikel() {
        this.naam ="";
        this.verkoopPrijs = 0;
    }

    public ArrayList<Artikel> getArtikel(){
        return artikelen;
    }

    @Override
    public String toString ()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        return (naam + "\t" + fmt.format(verkoopPrijs) + "\t" + hoeveelheid + "\t"
                + fmt.format(verkoopPrijs*hoeveelheid));
    }

    public String getKlantVoornaam(){
        return Persoon.getVoornaam();
    }
    public String getKlantAchternaam(){
        return Persoon.getAchternaam();
    }

    /**
     *
     * @return naam van artikel
     */
    public String getNaam() {
        return naam;
    }

    /**
     *
     * @param naam wordt naar gegeven aan een artikel
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     *
     * @return verkoop prijs van het artikel
     */

    public int getVerkoopPrijs() {
        return verkoopPrijs;
    }

    /**
     *
     * @param verkoopPrijs word een prijs gegeven aan het artikel
     */
    public void setVerkoopPrijs(int verkoopPrijs) {
        this.verkoopPrijs = verkoopPrijs;
    }

    public int getHoeveelheid(){
        return hoeveelheid;
    }
}
