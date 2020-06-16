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
            double gekregenKorting = 0;
            double kortingsPercentage = 0;
            double maxKorting = -1;
            double totalePrijs = 0; // Om mee te testen
            double aantalArtikelen = 0; // Om mee te testen

            while (it.hasNext()) {

            double prijs = it.next().getVerkoopPrijs();

            try {

                //Kortingskaarthouder controle
                double tempKorting = 0;


                if(klant.getKlant() instanceof KortingskaartHouder){
                    KortingskaartHouder kaartHouder = ((KortingskaartHouder)klant.getKlant());
                    kortingsPercentage = kaartHouder.geefKortingsPercentage();
                    tempKorting = prijs * kortingsPercentage;
                    if(kaartHouder.heeftMaximum())
                        maxKorting = kaartHouder.geefMaximum();
                    // korting controleern
                }
                //prijs  + korting betalen
                if(maxKorting == -1){
                    klant.getKlant().getBetaalwijze().betaal(prijs - tempKorting);
                    this.hoeveelheidGeldInKassa += prijs - tempKorting;
                } else {
                        if(gekregenKorting < maxKorting) {
                            if (gekregenKorting + tempKorting > maxKorting) {
                                tempKorting = maxKorting - gekregenKorting;
                                gekregenKorting = maxKorting;
                                klant.getKlant().getBetaalwijze().betaal(prijs - tempKorting);
                                this.hoeveelheidGeldInKassa += prijs - tempKorting;
                            } else {
                                gekregenKorting += tempKorting;
                                klant.getKlant().getBetaalwijze().betaal(prijs - tempKorting);
                                this.hoeveelheidGeldInKassa += prijs - tempKorting;
                            }
                        }
                        else {
                            klant.getKlant().getBetaalwijze().betaal(prijs);
                            this.hoeveelheidGeldInKassa += prijs ;
                        }
                }
                totalePrijs += prijs;
            }
            catch (TeWeinigGeldException e) {
                    System.out.println(klant.getKlant().getVoornaam() + " heeft te weinig geld!");
            }
            aantalArtikelen++;
            this.artikelen++;
        }
            //Even testen of het wel goed gaat...
//        if(klant.getKlant() instanceof KortingskaartHouder){
//            System.out.println(klant.getKlant().getVoornaam() + " heeft " + gekregenKorting + " gebruikt bij " + aantalArtikelen + " producten die totaal " + totalePrijs + " kosten. Klant had " + kortingsPercentage + " korting");
//        }
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
