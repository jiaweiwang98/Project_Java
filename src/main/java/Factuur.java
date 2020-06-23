import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


    @Entity
    @Table(name = "FACTUUR")
    public class Factuur implements Serializable {
        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(name = "DATUM", nullable = false)
        private LocalDate datum;
        @Column(name = "SUBTOTAAL", nullable = false)
        private double subtotaal;
        @Column(name = "KORTING", nullable = false)
        private double korting;
        @Column(name = "TOTAAL", nullable = false)
        private double totaal;
        @Column(name = "AANTAL_ARTIKELEN", nullable = false)
        private int artikelenOpDienblad;

        @Column(name = "FACTUURREGEL", nullable = false)
        @ElementCollection
        @OneToMany(targetEntity = FactuurRegel.class, cascade = CascadeType.ALL)
        private List<FactuurRegel> regels;

    public Factuur() {
        totaal = 0;
        korting = 0;
        regels = new ArrayList<>();
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingentoe.
     * <p>
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param klant
     */

    private void verwerkBestelling(Dienblad klant) {
        double kortingVanKaart = 0;
        double bedragVanKlant = 0;
        double kortingsbedrag = 0;

        artikelenOpDienblad = 0;
        Artikel volgendeArtikel;

        Iterator<Artikel> it = klant.getArtikelIterator();
        while (it.hasNext()) {
            volgendeArtikel = it.next();

            //Wanneer de korting van artikel groter is dan 0, dan wordt het kortingsbedrag gevuld met de korting van artikel
            if (volgendeArtikel.getKorting() > 0)
                kortingsbedrag += volgendeArtikel.getKorting();

                //Anders, wanneer de korting gelijk is aan 0.. dus geen korting, dan wordt het bedrag gevuld met de prijs van artikel
            else if (volgendeArtikel.getKorting() == 0)
                bedragVanKlant += volgendeArtikel.getVerkoopPrijs();

            //FactuurRegel
            FactuurRegel factuurRegel = new FactuurRegel(this, volgendeArtikel);
            regels.add(factuurRegel);

            //Totaal bedrag van alle artikelen (zonder korting)
            subtotaal += volgendeArtikel.getVerkoopPrijs();

            artikelenOpDienblad++;
        }

        //Kortingskaarthouder controle
        if (klant.getKlant() instanceof KortingskaartHouder) {

            KortingskaartHouder kaartHouder = ((KortingskaartHouder) klant.getKlant());
            kortingVanKaart = bedragVanKlant * kaartHouder.geefKortingsPercentage();

            if (kaartHouder.heeftMaximum()) {
                double maxKorting = kaartHouder.geefMaximum();
                if (korting <= maxKorting) { //Korting kleinder dan de maxKorting
                    bedragVanKlant -= kortingVanKaart; //Korting gaat dan van het bedrag af
                } else {
                    bedragVanKlant -= maxKorting;
                    kortingVanKaart = maxKorting; //Anders wordt de maximale korting de korting die eraf gaat
                }
                //Waneer er geen maxKorting is...
            } else
                bedragVanKlant -= kortingVanKaart;

            totaal = subtotaal - (kortingVanKaart + kortingsbedrag);

        } else {
            totaal = subtotaal - kortingsbedrag;
        }
        this.korting = kortingVanKaart + kortingsbedrag;
    }

    public int getAantalArtikelen() {
        return artikelenOpDienblad;
    }

    /**
     * @return het totaalbedrag
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * @return de toegeaste korting
     */
    public double getKorting() {
        return korting;
    }

    /**
     * @return een printbaar bonnetje
     */
    public String toString(Dienblad klant) {
         return " \n"
                + "--------------------------------- \n"
                + "AANTAL \t OMSCHRIJVING \t BEDRAG \n"
                + "- - - - - - - - - - - - - - - - - \n"
                + regels.toString() + "\n"
                + "- - - - - - - - - - - - - - - - - \n"
                + artikelenOpDienblad + "\t\t  SUBTOTAAL \t € " + Math.round(subtotaal* 100.0) / 100.0 + "\n"
                + "UW VOORDEEL \t\t\t € " + Math.round(getKorting()* 100.0) / 100.0 + "\n"
                + "Totaal \t\t\t\t\t € " + Math.round(getTotaal()* 100.0) / 100.0 + "\n"
                + "- - - - - - - - - - - - - - - - - \n"
                + "Datum  \t\t\t\t" +  datum + " \n"
                + klant.getKlant().toString() + " \n"
                + "Betaalwijze \t" + klant.getKlant().getBetaalwijze()+ "\n"
                + "--------------------------------- \n"
                + "\t  Bedankt en tot ziens!\t\n"
                + " \n";
    }
}
