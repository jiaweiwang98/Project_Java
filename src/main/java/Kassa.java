import java.util.Iterator;

public class Kassa {

    private int artikelen;
    private double hoeveelheidGeldInKassa;
    private KassaRij kassaRij;
    private Artikel volgendArtikel;
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
        double kortingsPercentage;
        double kortingBedrag = 0;

        double prijs = it.next().getVerkoopPrijs();
        double korting;

        double totalePrijs = 0; // Om mee te testen
        double aantalArtikelen = 0; // Om mee te testen

        while (it.hasNext()) {
            volgendArtikel = it.next();
            if (volgendArtikel.getKorting() > 0) {
                kortingBedrag += volgendArtikel.getKorting();
            } else if (volgendArtikel.getKorting() == 0) {
                prijs += volgendArtikel.getVerkoopPrijs();
            }

            try {

                //Kortingskaarthouder controle
                if (klant.getKlant() instanceof KortingskaartHouder) {

                    KortingskaartHouder kaartHouder = ((KortingskaartHouder) klant.getKlant());
                    kortingsPercentage = kaartHouder.geefKortingsPercentage();

                    korting = prijs * kortingsPercentage;

                    if (kaartHouder.heeftMaximum()) {
                        double maxKorting = kaartHouder.geefMaximum();
                        if (korting <= maxKorting) {
                            prijs -= korting;
                        } else {
                            prijs -= maxKorting;
                        }
                    } else
                        prijs -= korting;
                }

                totalePrijs += prijs;
                klant.getKlant().getBetaalwijze().betaal(prijs);
                this.hoeveelheidGeldInKassa += prijs;

            } catch (TeWeinigGeldException e) {
                System.out.println(klant.getKlant().getVoornaam() + " heeft te weinig geld!");
            }
            aantalArtikelen++; // om mee te oefenen
            this.artikelen++;

            //Even testen of het wel goed gaat...
//       if(klant.getKlant() instanceof KortingskaartHouder){
//            System.out.println(klant.getKlant().getVoornaam() + " heeft " + gekregenKorting + " gebruikt bij " + aantalArtikelen + " producten die totaal " + totalePrijs + " kosten. Klant had " + kortingsPercentage + " korting");
//        }
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
