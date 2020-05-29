public class Student extends Persoon {

    private String studentNummer;
    private String studieRichting;

    public Student(int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String studentNummer, String studieRichting) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.studentNummer = studentNummer;
        this.studieRichting = studieRichting;
    }

    public String getStudentNummer() {
        return studentNummer;
    }

    public void setStudentNummer(String studentNummer) {
        this.studentNummer = studentNummer;
    }

    public String getStudieRichting() {
        return studieRichting;
    }

    public void setStudieRichting(String studieRichting) {
        this.studieRichting = studieRichting;
    }
}
