import java.util.ArrayList;
import java.text.NumberFormat;


public class Dienblad extends Artikel {
    private ArrayList<Artikel> artikelen;
    private int capacity;
    private double totalePrijs;
    private int aantalArtikelen;


    /**
     * Constructor
     */
    public Dienblad(Artikel naam,int capacity, double totalePrijs) {
        aantalArtikelen = 0;
        this.capacity = 5;
        this.totalePrijs= 0.0;
        artikelen = new ArrayList<>();

    }

    public Dienblad(Persoon persoon){

    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {

       artikelen.add(artikel);
       totalePrijs += getVerkoopPrijs() * getHoeveelheid();
       aantalArtikelen++;
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        for(Artikel artikel : artikelen){
            int aantalArtikelen = artikelen.size();
          System.out.println("Het aantal artikelen zijn:\n"+artikel);
          System.out.println("Het totaal bedrag: "+getTotaalPrijs());
        }
        return getAantalArtikelen();
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        return totalePrijs;
    }

}

