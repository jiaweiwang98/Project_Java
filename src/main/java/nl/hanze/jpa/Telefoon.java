package nl.hanze.jpa;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Telefoon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nummer")
    private String nummer;
    @Column(name = "type")
    private String type;

    public Telefoon() {

    }

    public Telefoon(String nummer, String type) {
        this.nummer = nummer;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Telefoon [id=" + id + ", nummer=" + nummer + ", type=" + type + "]";
    }
}
