import java.util.Stack;
import java.util.Iterator;


public class Dienblad extends Artikel {
    private Stack<Artikel> artikelen;
    private Iterator<Artikel> artikelIterator;
    private double totalePrijs;
    private int aantalArtikelen;
    private Persoon klant;


    /**
     * Constructor
     */
    public Dienblad() {
        aantalArtikelen = 0;
        this.totalePrijs= 0.0;
        artikelen = new Stack<>();

    }

    /**
     * Constructor voor klant
     */
    public Dienblad(Persoon klant) {
        this.klant = klant;
        artikelen = new Stack<>();
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {

       artikelen.add(artikel);
       totalePrijs += getVerkoopPrijs();
       aantalArtikelen++;
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        return artikelen.size();
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
                totaalprijs = getVerkoopPrijs()*getAantalArtikelen();
            }
        }
        return totaalprijs;
    }

    public Persoon getKlant() {
        return klant;
    }

    public void setKlant(Persoon klant) {
        this.klant = klant;
    }

    public Iterator<Artikel> getArtikelIterator() {
        artikelIterator = artikelen.iterator();
        return artikelIterator;
    }
}
