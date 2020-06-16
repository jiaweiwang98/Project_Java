public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineaanbod;


    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    public Kassa getKassa() {
        return kassa;
    }

    public KantineAanbod getKantineaanbod() {
        return kantineaanbod;
    }

    public void setKantineAanbod(KantineAanbod kantineaanbod) {
        this.kantineaanbod = kantineaanbod;
    }

    /**
     * In deze methode wordt een Dienblad met artikelen
     * in de kassarij geplaatst
     *
     * @param dienblad
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        System.out.println(" ");

        for(String artikel : artikelnamen) {
            dienblad.voegToe(kantineaanbod.getArtikel(artikel));
        }

        kassarij.sluitAchteraan(dienblad);
      }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() throws TeWeinigGeldException {
        while (kassarij.isErEenRij()) {
            kassa.rekenAf(kassarij.eerstePersoonInRij());
        }
    }
    /**z
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return Het aantal gepasseerde artikelen.
     */
    public int getAantalArtikelen() {
        return kassa.aantalArtikelen();
    }
}
