import java.text.NumberFormat;
import java.util.ArrayList;

public class Artikel {
    private String naam;
    private double verkoopPrijs;
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

    public Artikel(String naam, double verkoopPrijs) {
        this.naam = naam;
        this.verkoopPrijs = verkoopPrijs;

    }


    public Artikel() {
        this.naam ="";
        this.verkoopPrijs = 0;
        this.korting = 0;
    }



    @Override
    public String toString() {
        return "Artikel{" +
                "naam='" + naam + '\'' +
                ", verkoopPrijs=" + verkoopPrijs +
                '}';
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

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }
}
