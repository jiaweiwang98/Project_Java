import javax.persistence.EntityManager;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineaanbod;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kantine(EntityManager manager) {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij, manager);
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
        for(String artikel : artikelnamen) {
            dienblad.voegToe(kantineaanbod.getArtikel(artikel));
        }
        kassarij.sluitAchteraan(dienblad);
      }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa(int i) {
        while (kassarij.isErEenRij()) {
            try {
                kassa.rekenAf(kassarij.eerstePersoonInRij(),i);
            } catch (TeWeinigGeldException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return Het aantal gepasseerde artikelen.
     */
    public int getAantalArtikelen() {
        return kassa.aantalGescandeArtikelen();
    }
}
