import java.text.NumberFormat;
import java.util.ArrayList;

public class Artikel {
    private String naam;
    private double verkoopPrijs;
    private Persoon persoon;
    private double korting;


    /**
     * De contructor van Artikel
     * @param naam naam van artikel
     * @param verkoopPrijs verkoop prijs van het artikel
     */
    public Artikel(String naam, double verkoopPrijs, double korting) {

        this.naam = naam;
        this.verkoopPrijs = verkoopPrijs;
        this.korting = korting;

    }

    public Artikel() {
        this.naam ="";
        this.verkoopPrijs = 0;
        this.korting = 0;
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "naam='" + naam + '\'' +
                ", verkoopPrijs=" + verkoopPrijs +
                '}';
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

    public double getVerkoopPrijs() {
        return verkoopPrijs;
    }

    /**
     *
     * @param verkoopPrijs word een prijs gegeven aan het artikel
     */
    public void setVerkoopPrijs(double verkoopPrijs) {
        this.verkoopPrijs = verkoopPrijs;
    }



}
