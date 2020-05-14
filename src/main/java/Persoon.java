package main.java;

public class Persoon {
    private int bsn;
    private String voornaam;
    private String achternaam;
    private int geboortedatum;
    private char geslacht;


    public Persoon(int bsn, String voornaam, String achternaam, int geboortedatum, char geslacht) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.geslacht = geslacht;
    }

        public void setBsn ( int bsn){
            this.bsn = bsn;
        }

        public void setVoornaam (String voornaam){
            this.voornaam = voornaam;
        }

        public void setAchternaam (String achternaam){
            this.achternaam = achternaam;
        }

        public void setGeboortedatum ( int geboortedatum){
            this.geboortedatum = geboortedatum;
        }

        public void setGeslacht ( char geslacht){
                this.geslacht = geslacht;

        }
         public char getGeslacht() {
             switch (geslacht) {
                 case 'm':
                 case 'v':
                     System.out.println("Deze gender is valide");
                     break;
                 default:
                     throw new IllegalStateException("Unexpected value: " + geslacht);
             }
             return geslacht;
         }

        public int getBsn() {
            return bsn;
        }

        public String getVoornaam() {
            return voornaam;
        }

        public String getAchternaam() {
            return achternaam;
        }

        public int getGeboortedatum() {
            return geboortedatum;
        }
}


