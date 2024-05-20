package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(targetEntity = Patient.class, mappedBy = "medicaments")
    private Set<Patient> patient;

    public String getName() {
        return String.format("  %s: ", this.name);
    }

    public String getSimpleName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
