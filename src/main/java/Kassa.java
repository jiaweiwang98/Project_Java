import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;


public class Kassa {

    private int aantalGescandeArtikelen;
    private double hoeveelheidGeldInKassa;
    private KassaRij kassaRij;
    private double bedragVanKlant;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager manager) {
        this.kassaRij = kassarij;
        this.manager = manager;
        resetKassa();
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant, int i) throws TeWeinigGeldException{

                Factuur factuur = new Factuur(klant,LocalDate.now().plusDays(i));

            try {
                klant.getKlant().getBetaalwijze().betaal(factuur.getTotaal());
                this.hoeveelheidGeldInKassa += bedragVanKlant;
                aantalGescandeArtikelen += factuur.getAantalArtikelen(); // Artikelen halen van de factuur
                hoeveelheidGeldInKassa += factuur.getTotaal();
                save(factuur);

            } catch (TeWeinigGeldException e) {
                System.out.println(klant.getKlant().getVoornaam() + " heeft te weinig geld!");
            } finally {
                System.out.println(factuur.toString(klant));
            }
    }



    /**
     * Geeft de totaal aantal afgerekende artikelen terug
     * afkomstig van de factuur.
     *
     * @return aantal artikelen
     */
    public int aantalGescandeArtikelen() {
        return aantalGescandeArtikelen; // artikelen afkomstig van de factuur!!
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
        aantalGescandeArtikelen = 0;
        hoeveelheidGeldInKassa = 0;
    }


    /**
     * Maakt een nieuwe factuur en slaat hem op in de databases
     * (zie JPA voorbeeld)
     *
     * @param factuur
     */
    public void save(Factuur factuur) {

        EntityTransaction transaction = null;
        try {
            // Get a transaction, sla de student gegevens op en commit de transactie
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(factuur);
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            //ex.getMessage();
            //ex.printStackTrace();
        }
    }

}
