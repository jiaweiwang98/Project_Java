import java.util.*;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

class KantineSimulatie {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    // dagen
    public static final int DAGEN = 7;

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
            new String[]{"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[]{1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    // Overeerving van Persoon
    private int aantalStudenten = 89;
    private int aantalDocenten = 10;
    private int aantalKantineMedewerkers = 1;

    /**
     * Constructor
     */
    public KantineSimulatie() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        KantineSimulatie kantineSimulatie = new KantineSimulatie();
        kantineSimulatie.simuleer(dagen);
    }

    private void genereerDagaanbieding() {

        // willekeurig aantal artikelen die dagkorting krijgen
        int r = getRandomValue(1, 2);
        System.out.println("Vandaag hebben: " + r + " artikel(en) korting.");
        Artikel artikel;
        // for loop om artikelen korting te geven
        for (int j = 0; j < r; j++) {
            int getal = getRandomValue(0, artikelnamen.length - 1);
            //int count = 0;
            artikel = kantineaanbod.getArtikel(artikelnamen[getal]);
            artikel.setKorting(artikel.getVerkoopPrijs() * 0.2);
            //System.out.println(artikel.getNaam() + " heeft " + artikel.getKorting() + " korting");
            ArrayList<Artikel> dagAanbieding = new ArrayList<>();
            for (int i = 0; i < kantineaanbod.getArrayList(artikelnamen[getal]).size(); i++) {
                dagAanbieding.add(artikel);
                //count++;
            }
            kantineaanbod.aanbod.put(artikelnamen[getal], dagAanbieding);
            //System.out.println(count + " artikelen toegevoegd");
        }
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Omzet
        double[] omzet = new double[dagen];
        int[] vertkochtAantalArtikelen = new int[dagen];

        // for lus voor dagen
        for (int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            genereerDagaanbieding();
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);
            Persoon kantinebezoeker;


            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                int randomNummer = random.nextInt(100);

                if (randomNummer == 0) {
                    kantinebezoeker = new KantineMedewerker(987654321, "Mathilda", "van der Vaart", new Datum(9, 1, 1979), 'V', 4104, false);
                } else if (randomNummer <= 10) {
                    kantinebezoeker = new Docent(147258369, "Gregore", "Dijkstra", new Datum(24, 8, 1981), 'M', "DiGr", "Java");
                } else {
                    kantinebezoeker = new Student(123456789, "Kayla", "Chu", new Datum(16, 03, 2000), 'V', "405455", "NSE");
                }


                Dienblad dienbladVanPersoon = new Dienblad(kantinebezoeker);

                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(
                        aantalartikelen, 0, AANTAL_ARTIKELEN - 1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);
                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienbladVanPersoon, artikelen);
                //System.out.println(dienbladVanPersoon.getKlant().toString());
                // betaalwijze
                if (random.nextInt(2) == 0) {
                    dienbladVanPersoon.getKlant().setBetaalwijze(new Contant());
                } else {
                    dienbladVanPersoon.getKlant().setBetaalwijze(new Pinpas());
                }
                dienbladVanPersoon.getKlant().getBetaalwijze().setSaldo(random.nextInt(500));
            }


            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            System.out.println(" ");
            System.out.println("Dagtotalen van dag " + (i + 1) + ": ");
            System.out.println("---------------------------------");
            System.out.println("Aantal bediende personen: " + aantalpersonen);
            System.out.println("Aantal verkochte artikelen: " + kantine.getKassa().aantalGescandeArtikelen());
            System.out.println("Hoeveelheid geld in kassa: €" + Math.round(kantine.getKassa().hoeveelheidGeldInKassa() * 100.0) / 100.0);
            System.out.println(" ");

            //Omzet
            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();

            //Artikelen
            vertkochtAantalArtikelen[i] = kantine.getKassa().aantalGescandeArtikelen();

            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }

        // Administratie
        System.out.println(" ");
        System.out.println("Administratie");
        System.out.println("---------------------------------");
        System.out.println("Omzet per dag van de week:");
        double[] temp = Administratie.berekenDagOmzet(omzet);
        for (int i = 0; i < temp.length; i++) {
            String dag = "";
            switch (i) {
                case 0:
                    dag = "maandag";
                    break;
                case 1:
                    dag = "dinsdag";
                    break;
                case 2:
                    dag = "woensdag";
                    break;
                case 3:
                    dag = "donderdag";
                    break;
                case 4:
                    dag = "vrijdag";
                    break;
                case 5:
                    dag = "zaterdag";
                    break;
                case 6:
                    dag = "zondag";
                    break;
            }
            System.out.println("    " + dag + "  \t €" + (float) Math.round(Administratie.berekenDagOmzet(omzet)[i] * 100) / 100);
        }
        System.out.println("Gemiddelde aantal verkochte artikelen: " + Math.round(Administratie.berekenGemiddeldAantal(vertkochtAantalArtikelen) * 100) / 100);
        System.out.println("Gemiddelde omzet: €" + (float) Math.round(Administratie.berekenGemiddeldeOmzet(omzet) * 100) / 100);
        System.out.println("- - - - - - - - - - - - - - - - -");
        System.out.println("Totale omzet en toegepaste korting: " + getTotaleOmzetKortingDB());
        System.out.println("De gemiddelde omzet: " +  getGemiddeldeOmzetDB());
        System.out.println("De gemiddelde prijs: " + getGemiddeldePrijsDB());
        System.out.println("De gemiddelde korting: " + getGemiddeldeKortingDB());
        System.out.println("- - - - - - - - - - - - - - - - -");
        System.out.println("Top 3 facturen: ");

        for (Object[] factuur : getDrieHoogsteFacturenDB()) {
            System.out.println(Arrays.toString(factuur));
        }
        System.out.println(" ");
        System.out.println("Totale omzet per artikel: ");
        for (Object[] artikel : getTotaleOmzetPerArtikelDB()) {
            System.out.println(Arrays.toString(artikel));
        }
        System.out.println(" ");
        System.out.println("\nDe totale omzet per artikel, per dag:");
        for (Object[] artikel : getTotaleOmzetPerArtikelDB()) {
            System.out.println(Arrays.toString(artikel));
        }
        System.out.println(" ");
        System.out.println("Top 3 artikelen: ");
        for (Object[] artikel : getDriePopulaireArtikelenDB()) {
            System.out.println(Arrays.toString(artikel));
        }
        System.out.println(" ");
        System.out.println("Top 3 artikelen met de hoogste omzet: ");
        for (Object[] artikel : getDrieArtikelenMetHoogsteOmzetDB()) {
            System.out.println(Arrays.toString(artikel));
        }
        System.out.println(" ");
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Totale omzet en toegepaste korting opvragen
     * uit de database
     */
    public double getTotaleOmzetKortingDB() {
        Query query = manager.createQuery("SELECT ROUND(SUM(korting)) FROM  Factuur");
         return (Double)  query.getSingleResult();
    }

     /**
     * Bereken gemiddelde omzet van alle facturen
     * @return gemiddelde ozmet van alle facturen
     */
    public double getGemiddeldeOmzetDB() {
        Query query = manager.createQuery("SELECT ROUND(AVG(totaal - korting)) FROM Factuur");
        return (Double) query.getSingleResult();
    }

    public double getGemiddeldeKortingDB() {
        Query query = manager.createQuery("SELECT ROUND(AVG(korting)) FROM  Factuur");
        return (Double) query.getSingleResult();
    }

    public double getGemiddeldePrijsDB() {
        Query query = manager.createQuery("SELECT ROUND(AVG(totaal)) FROM  Factuur");
        return (Double) query.getSingleResult();
    }

    /**
     * Verzamelt de drie hoogste facturen
     * @return de drie hoogste facturen
     */
    public List<Object[]> getDrieHoogsteFacturenDB() {
        Query query = manager.createQuery(
                "SELECT id, datum, korting, totaal FROM Factuur ORDER BY (totaal-korting) DESC");
        query.setMaxResults(3);
        return query.getResultList();
    }
    /**
     * Verzamelt gegevens over de totale omzet per artikel
     * @return de totale omzetten van de artikelen
     */
    public List<Object[]> getTotaleOmzetPerArtikelDB() {
        Query query = manager.createQuery(
                "SELECT f.datum, fr.artikel.naam, SUM(fr.artikel.verkoopPrijs - fr.artikel.korting) FROM FactuurRegel fr JOIN fr.factuur f GROUP BY fr.artikel.naam, f.datum"
        );
        return query.getResultList();
    }
    /**
     * Toon totale omzet uit database
     * @return
     */
    public List<Object[]> getDriePopulaireArtikelenDB() {
        Query query = manager.createQuery(
                "SELECT artikel.naam, COUNT(artikel.naam) FROM FactuurRegel GROUP BY artikel.naam ORDER BY count(artikel.naam) DESC"
        );
        query.setMaxResults(3);
        return query.getResultList();
    }

    /**
     * Verzamelt gegevens over de artikelen met de hoogste omzet
     * @return de top drie artikelen met de hoogste omzet
     */
    public List<Object[]> getDrieArtikelenMetHoogsteOmzetDB() {
        Query query = manager
                .createQuery("SELECT artikel.naam, SUM(artikel.verkoopPrijs - artikel.korting) FROM FactuurRegel GROUP BY artikel.naam ORDER BY SUM(artikel.verkoopPrijs) DESC");
        query.setMaxResults(3);
        return query.getResultList();
    }

}

