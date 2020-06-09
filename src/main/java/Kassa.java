import java.util.Iterator;

public class Kassa {

    private int artikelen;
    private double hoeveelheidGeldInKassa;
    private KassaRij kassaRij;
    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        this.kassaRij = kassarij;
        resetKassa();
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Iterator<Artikel> it = klant.getArtikelIterator();
        while (it.hasNext()) {

            double prijs = it.next().getVerkoopPrijs();

            try {
                klant.getKlant().getBetaalwijze().betaal(prijs);
                this.hoeveelheidGeldInKassa += prijs;
            } catch (TeWeinigGeldException e) {
                System.out.println(klant.getKlant().getVoornaam() + " heeft te weinig geld!");
            }
            this.artikelen++;
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return this.artikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kassa zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return hoeveelheidGeldInKassa;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        artikelen = 0;
        hoeveelheidGeldInKassa = 0;
    }
}
