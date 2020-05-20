import java.text.NumberFormat;
import java.util.ArrayList;

public class Artikel {
    private String naam;
    private int verkoopPrijs;
    private ArrayList<Artikel> artikelen;
    private Persoon persoon;
    /**
     * De contructor van Artikel
     * @param naam naam van artikel
     * @param verkoopPrijs verkoop prijs van het artikel
     */
    public Artikel(String naam, int verkoopPrijs) {
        this.naam = naam;
        this.verkoopPrijs = verkoopPrijs;
    }

    public Artikel() {
        this.naam ="";
        this.verkoopPrijs = 0;
    }

    public ArrayList<Artikel> getArtikel(){
        return artikelen;
    }

    @Override
    public String toString() {
        return "Artikel{" + "naam='" + naam + '\'' + ", verkoopPrijs=" + verkoopPrijs + '}';
    }

    public String getKlantVoornaam(){
        return persoon.getVoornaam();
    }
    public String getKlantAchternaam(){
        return persoon.getAchternaam();
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

}
