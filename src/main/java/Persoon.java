public class Persoon {
    private static int geboortedatum;
    private int bsn;
    private static String voornaam;
    private static String achternaam;
    private char geslacht;


    public Persoon(int bsn, String voornaam, String achternaam, int geboortedatum, char geslacht) {
        this.bsn = bsn;
        Persoon.voornaam = voornaam;
        Persoon.achternaam = achternaam;
        Persoon.geboortedatum = geboortedatum;
        this.geslacht = geslacht;
    }

    @Override
    public String toString() {
        return "Persoon{" + "bsn=" + bsn + ", voornaam='" + voornaam + '\'' + ", achternaam='" + achternaam + '\'' + ", geslacht=" + geslacht + '}';
    }

    public void setBsn (int bsn){
            this.bsn = bsn;
        }

        public void setVoornaam (String voornaam){
            Persoon.voornaam = voornaam;
        }

        public void setAchternaam (String achternaam){
            Persoon.achternaam = achternaam;
        }

        public void setGeboortedatum ( int geboortedatum){
            Persoon.geboortedatum = geboortedatum;
        }

        public void setGeslacht ( char geslacht){
                this.geslacht = geslacht;

        }
         public char getGeslacht() {
             switch (geslacht) {
                 case 'm':
                     System.out.println("Man");
                 case 'v':
                     System.out.println("Vrouw");
                     break;
                 default:
                     throw new IllegalStateException("Unexpected value: " + geslacht);
             }
             return geslacht;
         }

        public int getBsn() {
            return bsn;
        }

        public static String getVoornaam() {
            return voornaam;
        }

        public static String getAchternaam() {
            return achternaam;
        }

        public static int getGeboortedatum() {
            return geboortedatum;
        }
}


