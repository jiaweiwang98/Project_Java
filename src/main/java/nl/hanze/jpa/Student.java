package nl.hanze.jpa;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Embedded;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

@Entity
@Table(name = "student")
@NamedQuery(name = "Student.findBySchool",
        query = "SELECT DISTINCT(s) FROM Student s JOIN s.studies stu WHERE stu.school_name=:name AND stu.student=s.id")
// De vergelijkbare query in MySQL is:
// SELECT * FROM Student s JOIN studie stu WHERE stu.school_name="SCMI" AND
// stu.student_id=s.id
// Echter als je dit SQL statement uitvoert krijg je rijen met één student en
// één studie,
// Hibernate vertaalt dit naar objecten waardoor meerdere rijen met elkaar
// gecombineerd worden.
// Daarom heb je hier de DISTINCT nodig.
public class Student implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "student_age", nullable = false)
    private int age;

    @OneToMany(targetEntity = StudieInschrijving.class, mappedBy = "student",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudieInschrijving> studies;

    @Embedded
    private StudentKaart kaart;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "student_telefoon", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "telefoon_id"))
    private List<Telefoon> telefoons;

    public Student() {
        this.studies = new ArrayList<StudieInschrijving>();
        this.telefoons = new ArrayList<Telefoon>();
    }

    public Student(int id, String naam, int age) {
        this();
        this.id = id;
        this.name = naam;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentKaart getKaart() {
        return kaart;
    }

    public void setKaart(StudentKaart kaart) {
        this.kaart = kaart;
    }

    public List<StudieInschrijving> getStudie() {
        return studies;
    }

    public void addStudie(StudieInschrijving studie) {
        if (!studies.contains(studie)) {
            studies.add(studie);
            studie.setStudent(this);
        }
    }

    public List<Telefoon> getTelefoons() {
        return telefoons;
    }

    public void addTelefoon(Telefoon telefoon) {
        if (!telefoons.contains(telefoon)) {
            telefoons.add(telefoon);
        }
    }

    public void removeTelefoon(Telefoon telefoon) {
        if (telefoons.contains(telefoon)) {
            telefoons.remove(telefoon);
        }
    }

    @Override
    public String toString() {
        String studiesLijst = "";
        for (StudieInschrijving studie : studies) {
            studiesLijst += studie.toString() + "\n";
        }
        String telefoonLijst = "";
        for (Telefoon telefoon : telefoons) {
            telefoonLijst += telefoon.toString() + "\n";
        }
        return id + "\t" + name + "\t" + age + "\n" + kaart.toString() + "\n" + studiesLijst
                + telefoonLijst;
    }
}
