package nl.hanze.jpa;

import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;

public class Main {

    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;


    public static void main(String[] args) {
        Main runner = new Main();
        runner.runVoorbeeld();
    }

    public void runVoorbeeld() {
        // Create an EntityManager
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        // transactions omitted
        manager.close();
        ENTITY_MANAGER_FACTORY.close();

        // Aanmaken studenten met studies en telefoons
        StudieInschrijving studie1 = new StudieInschrijving("SCMI", "HBO_ICT");
        StudieInschrijving studie11 = new StudieInschrijving("SCMI", "GDD");
        StudieInschrijving studie2 = new StudieInschrijving("SCMI", "GDD");
        StudieInschrijving studie3 = new StudieInschrijving("SCMI", "HBO_ICT");

        Student alice = new Student(1, "Alice", 18);
        Student bob = new Student(2, "Bob", 20);
        Student charlie = new Student(3, "Charlie", 25);

        Telefoon appel = new Telefoon("0612345678", "Apple iPhone");
        Telefoon samsung = new Telefoon("0687654321", "Samsung");

        StudentKaart kaart1 = new StudentKaart(192837, "1-1-2021");
        StudentKaart kaart2 = new StudentKaart(564738, "1-6-2021");
        StudentKaart kaart3 = new StudentKaart(384756, "5-1-2019");

        alice.addStudie(studie1);
        alice.addStudie(studie11);
        alice.setKaart(kaart1);

        bob.addStudie(studie2);
        bob.setKaart(kaart2);

        charlie.addTelefoon(appel);
        charlie.addTelefoon(samsung);
        charlie.addStudie(studie3);
        charlie.setKaart(kaart3);

        listStudenten(readAll(), "Stap 0: Alles aangemaakt, nog niet weggeschreven");

        // In de database zetten
        create(alice);
        create(bob);
        create(charlie);

        listStudenten(readAll(), "Stap 1: Alles aangemaakt, weggeschreven");
        listStudieInschrijvingen(readAllStudies(), "Lijstje met studies");
        listTelefoons(readAllTelefoons(), "Lijstje met telefoons");

        // Update de leeftijd en studie vanf Bob
        studie2.setSchool_name("Verpleegkunde");
        studie2.setStudie_name("AVV");
        bob.setAge(24);
        update(bob);
        listStudenten(readAll(), "Stap 2: Na de update");

        // Delete Alice uit de database door gebruik te maken van de id
        delete(1);
        listStudenten(readAll(), "Stap 3: Na de delete");

        // Maak een selectie op de studentenset
        listStudenten(getStudentPerSchool("SCMI"), "Stap 4: De query");
        listStudieInschrijvingen(readAllStudies(), "Lijstje met studies");
        listTelefoons(readAllTelefoons(), "Lijstje met telefoons");

        // Verwijder een telefoon
        deleteTelefoon(appel);
        listStudenten(readAll(), "Stap 5: Telefoon verloren?");
        listStudieInschrijvingen(readAllStudies(), "Lijstje met studies");
        listTelefoons(readAllTelefoons(), "Lijstje met telefoons");

        // Verwijder een telefoon
        charlie.removeTelefoon(appel);
        update(charlie);
        listStudenten(readAll(), "Stap 6: Telefoon echt verloren!");
        listStudieInschrijvingen(readAllStudies(), "Lijstje met studies");
        listTelefoons(readAllTelefoons(), "Lijstje met telefoons");
        aggregateExamples();

        // Close the EntityManager
        manager.close();
        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
        ENTITY_MANAGER_FACTORY.close();
    }

    public void aggregateExamples() {
        createExampleStudents();
        Query query = manager
                .createQuery("SELECT o.school_name, AVG(s.age) FROM Student s JOIN s.studies o "
                        + "GROUP BY o.school_name ");
        List<Object[]> resultList = query.getResultList();
        resultList.forEach(r -> System.out.println(Arrays.toString(r)));
        double totaalGemiddelde = 0.0;
        for (Object[] r : resultList) {
            totaalGemiddelde += (Double) r[1];
        }
        System.out.println("Totaal:" + totaalGemiddelde);
        query = manager.createQuery("SELECT COUNT(*) FROM Student s");
        Long result = (Long) query.getSingleResult();
        System.out.println("Aantal:" + result);
    }

    public void createExampleStudents() {
        StudieInschrijving studie1 = new StudieInschrijving("SCMI", "HBO_ICT");
        StudieInschrijving studie11 = new StudieInschrijving("SCMI", "GDD");
        StudieInschrijving studie2 = new StudieInschrijving("SCMI", "HBO_ICT");
        StudieInschrijving studie3 = new StudieInschrijving("SCMI", "GDD");
        StudieInschrijving studie4 = new StudieInschrijving("SCMI", "GDD");
        StudieInschrijving studie5 = new StudieInschrijving("SCMI", "GDD");

        Student st1 = new Student(11, "Jan", 18);
        Student st2 = new Student(12, "Kees", 20);
        Student st3 = new Student(13, "Anna", 25);
        Student st4 = new Student(14, "Piet", 21);
        Student st5 = new Student(15, "Joanna", 19);

        Telefoon appel = new Telefoon("0612345678", "Apple iPhone");
        Telefoon samsung = new Telefoon("0687654321", "Samsung");

        StudentKaart kaart1 = new StudentKaart(192837, "1-1-2021");
        StudentKaart kaart2 = new StudentKaart(564738, "1-6-2021");
        StudentKaart kaart3 = new StudentKaart(384756, "5-1-2019");
        StudentKaart kaart4 = new StudentKaart(112233, "10-7-2020");
        StudentKaart kaart5 = new StudentKaart(445566, "15-4-2021");

        st1.addStudie(studie1);
        st1.addStudie(studie11);
        st1.setKaart(kaart1);

        st2.addStudie(studie2);
        st2.setKaart(kaart2);
        st3.addStudie(studie3);
        st3.setKaart(kaart3);
        st4.addStudie(studie4);
        st4.setKaart(kaart4);
        st5.addStudie(studie5);
        st5.setKaart(kaart5);

        st2.addTelefoon(appel);
        st2.addTelefoon(samsung);

        // In de database zetten
        create(st1);
        create(st2);
        create(st3);
        create(st4);
        create(st5);
    }

    /**
     * Toon een lijst van alle studenten.
     *
     * @param students
     * @param comment
     */
    public void listStudenten(List<Student> students, String comment) {
        System.out.println(comment);
        if (!students.isEmpty()) {
            for (Student stu : students) {
                System.out.println(stu);
            }
        } else {
            System.out.println("Leeg");
        }
    }

    /**
     * Toon een lijst van alle studieinschrijvingen.
     *
     * @param studies
     * @param comment
     */
    public void listStudieInschrijvingen(List<StudieInschrijving> studies, String comment) {
        System.out.println(comment);
        if (!studies.isEmpty()) {
            for (StudieInschrijving stu : studies) {
                System.out.println(stu);
            }
        } else {
            System.out.println("Leeg");
        }
    }

    /**
     * Toon een lijst van alle telefoons.
     *
     * @param telefoons
     * @param comment
     */
    public void listTelefoons(List<Telefoon> telefoons, String comment) {
        System.out.println(comment);
        if (!telefoons.isEmpty()) {
            for (Telefoon tel : telefoons) {
                System.out.println(tel);
            }
        } else {
            System.out.println("Leeg");
        }
    }

    /**
     * Create a new Student.
     *
     * @param student
     */
    public void create(Student student) {
        EntityTransaction transaction = null;
        try {
            // Get a transaction, sla de student gegevens op en commit de transactie
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(student);
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /**
     * Delete een Student op basis van een id.
     *
     * @param id
     */
    public void delete(int id) {
        EntityTransaction transaction = null;
        try {
            // Get a transaction, haal de student op, verwijder de student en commit de
            // transactie
            transaction = manager.getTransaction();
            transaction.begin();
            Student stu = manager.find(Student.class, id);
            manager.remove(stu);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /**
     * Update the existing Student.
     *
     * @param student
     */
    public void update(Student student) {
        EntityTransaction transaction = null;
        try {
            // Get a transaction, update de student gegevens en commit de transactie
            transaction = manager.getTransaction();
            transaction.begin();
            student = manager.merge(student);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /**
     * Read alle Studenten.
     *
     * @return een List van Studenten
     */
    public List<Student> readAll() {
        // gebruik van native sql:
        // List<Student> students = manager.createQuery("SELECT s FROM Student s",
        // Student.class).getResultList();

        // beter is om gebruik te maken van hibernate query language, omdat je dan geen last hebt
        // van verschillende
        // SQL dialecten: dat wordt door hibernate voor je geabstraheerd.
        // Zie https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html

        // Eerst halen we een sessie op van de EntityManager - zie
        // https://docs.oracle.com/javaee/6/api/javax/persistence/EntityManager.html#unwrap%28java.lang.Class%29
        Session session = manager.unwrap(Session.class);
        List<Student> students = session.createQuery("from Student").list();
        return students;
    }

    /**
     * Read een selectie van de Studenten.
     *
     * @return een List van Studenten
     */
    public List<Student> getStudentPerSchool(String name) {
        TypedQuery<Student> query = manager.createNamedQuery("Student.findBySchool", Student.class);
        query.setParameter("name", name);
        List<Student> results = query.getResultList();
        return results;
    }

    /**
     * Read alle Studieinschrijvingen.
     *
     * @return een List van Studieinschrijvingen
     */
    public List<StudieInschrijving> readAllStudies() {
        // native SQL (zie eventueel het commentaar bij readAll() )

        // List<StudieInschrijving> studieInschrijvingen = manager
        // .createQuery("SELECT stu FROM StudieInschrijving stu",
        // StudieInschrijving.class).getResultList();

        // HQL
        Session session = manager.unwrap(Session.class);
        List<StudieInschrijving> studieInschrijvingen =
                session.createQuery("from StudieInschrijving ").list();
        return studieInschrijvingen;
    }

    /**
     * Delete een Telefoon
     *
     * @param telefoon
     */
    public void deleteTelefoon(Telefoon telefoon) {
        EntityTransaction transaction = null;
        try {
            // Get a transaction, verwijder de telefoon en commit de transactie
            transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(telefoon);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /**
     * Read alle Telefoons.
     *
     * @return een List van Telefoons
     */
    public List<Telefoon> readAllTelefoons() {
        // native SQL (zie eventueel het commentaar bij readAll() )
        // List<Telefoon> telefoons = manager.createQuery("SELECT tel FROM Telefoon tel",
        // Telefoon.class).getResultList();

        // HQL:
        Session session = manager.unwrap(Session.class);
        List<Telefoon> telefoons = session.createQuery("from Telefoon").list();
        return telefoons;
    }
}
