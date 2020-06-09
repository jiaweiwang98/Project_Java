import java.util.Iterator;

public class Kassa {

    private int aantalArtikelen;
    private double hoeveelheidGeldInKassa;
    private KassaRij kassaRij;
    double totaalPrijs;
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
     * @param dienblad die moet afrekenen
     */
    public void rekenAf(Dienblad dienblad) {
        Persoon klant = dienblad.getKlant();
        try {
            if((klant instanceof KantineMedewerker)) {
                KantineMedewerker kantineMedewerker = ((KantineMedewerker)klant);

                double korting = (getTotaalPrijs(dienblad) * (kantineMedewerker.geefKortingsPercentage() / 100));
                totaalPrijs += (getTotaalPrijs(dienblad) - korting);
                aantalArtikelen += dienblad.getAantalArtikelen();
                klant.getBetaalwijze().betaal(getTotaalPrijs(dienblad));
            } else if((klant instanceof Docent)) {
                Docent docent = ((Docent)klant);

                double korting = (getTotaalPrijs(dienblad) * (docent.geefKortingsPercentage() / 100));
                totaalPrijs += (getTotaalPrijs(dienblad) - korting);
                aantalArtikelen += dienblad.getAantalArtikelen();
                klant.getBetaalwijze().betaal(getTotaalPrijs(dienblad));
            } else {
                totaalPrijs += getTotaalPrijs(dienblad);
                aantalArtikelen += dienblad.getAantalArtikelen();
                klant.getBetaalwijze().betaal(getTotaalPrijs(dienblad));
            }
        } catch(TeWeinigGeldException e) {
            System.out.println(klant.getVolledigeNaam()+ ": betaling is afgewezen, " + e + ".");
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return aantalArtikelen;
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
        aantalArtikelen = 0;
        hoeveelheidGeldInKassa = 0;
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen.
     *
     * @return De totaalprijs.
     */
    public double getTotaalPrijs(Dienblad artikelen) {
        double totaal = 0.0;
        Iterator<Artikel> iterator = artikelen.getArtikelIterator();
        while (iterator.hasNext()) {
            totaal += ((Artikel) iterator.next()).getVerkoopPrijs();
        }
        return totaal;
    }
}
