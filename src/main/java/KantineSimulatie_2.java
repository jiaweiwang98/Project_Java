import java.util.*;

class KantineSimulatie {

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
            new String[] {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[] {1.50, 2.10, 1.65, 1.65};

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
    private int aantalDocenten= 10;
    private int aantalKantineMedewerkers= 1;

    /**
     * Constructor
     *
     */
    public KantineSimulatie() {
        kantine = new Kantine();
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

        // Omzet
        double[] omzet = new double[7];

        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = aantalStudenten + aantalKantineMedewerkers + aantalDocenten ;

            // laat de personen maar komen...
                for (int j = 0; j < aantalpersonen; j++) {

                    // maak persoon en dienblad aan, koppel ze
                    Persoon persoonKantine = new Persoon();
                    int randomNummer = random.nextInt(100);

                    if(randomNummer == 0) {
                        persoonKantine = new KantineMedewerker(987654321, "Mathilda", "van der Vaart", new Datum(9,1,1979), 'V', 4104, false);
                    } else if (randomNummer <= 10) {
                        persoonKantine = new Docent(147258369, "Gregore", "Dijkstra", new Datum(24,8,1981), 'M', "DiGr", "Java");
                    } else {
                        persoonKantine = new Student(123456789, "Kayla", "Chu", new Datum(16,03,2000), 'V', "405455", "NSE");
                    }

                Dienblad dienbladVanPersoon = new Dienblad(persoonKantine);

                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(
                    aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);
                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienbladVanPersoon, artikelen);
                    System.out.println(persoonKantine.toString());
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen

            System.out.println(" ");
            System.out.println("Dagtotalen van dag "  + (i + 1) + ": ");
            System.out.println("---------------------------------");
            System.out.println("Aantal bediende personen: " +aantalpersonen);
            System.out.println("Aantal verkochte artikelen: " + kantine.getKassa().aantalArtikelen());
            System.out.println("Hoeveelheid geld in kassa: €" + Math.round(kantine.getKassa().hoeveelheidGeldInKassa() * 100.0) / 100.0);
            System.out.println(" ");

            //Omzet
            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();

            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }

        // Administratie
        System.out.println(" ");
        System.out.println("Administratie");
        System.out.println("---------------------------------");
        //System.out.println("Gemiddelde aantal: " + Administratie.berekenGemiddeldAantal());
        System.out.println("Gemiddelde omzet: €" + (float)Math.round(Administratie.berekenGemiddeldeOmzet(omzet) * 100) / 100);
        System.out.println("Omzet per dag van de week:");
        double[] temp = Administratie.berekenDagOmzet(omzet);
        for(int i = 0; i < temp.length; i++) {
            String dag = "";
            switch(i) {
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
            System.out.println("    " + dag + " €" + (float)Math.round(Administratie.berekenDagOmzet(omzet)[i] * 100) / 100);
        }
    }
}
