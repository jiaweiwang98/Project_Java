import java.util.ArrayList;

public class Dienblad extends Artikel {
    private ArrayList<Artikel> artikelen;
    private double totalePrijs;
    private int aantalArtikelen;


    /**
     * Constructor
     */
    public Dienblad() {
        this.aantalArtikelen = 0;
        this.totalePrijs= 0.0;
        artikelen = new ArrayList<>();

    }


    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
       artikelen.add(artikel);
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {

        int aantalArtikelen = artikelen.size();
        return aantalArtikelen;
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        double totaalprijs = 0;
        for(Artikel artikel : artikelen){
            if(artikel != null){
                totaalprijs = getVerkoopPrijs() * getAantalArtikelen();
            }
        }
        return totaalprijs;
    }

}

