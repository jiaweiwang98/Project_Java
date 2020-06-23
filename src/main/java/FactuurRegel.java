import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FACTUURREGEL")
@Embeddable
public class FactuurRegel implements Serializable {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = Factuur.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "FACTUUR_ID")
    private Factuur factuur;
    @Embedded
    @Column(name = "ARTIKEL")
    private Artikel artikel;

    public FactuurRegel () {}

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        return " \t\t  " + artikel.getNaam() + "  \t€ " + artikel.getVerkoopPrijs() + "\n";
    }
}
