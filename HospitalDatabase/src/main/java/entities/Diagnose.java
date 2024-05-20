package entities;

import javax.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "ssn")
    private Patient patient;

    public void setName(String name) {
        this.name = name;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNameAndComments(){
        return String.format("  %s\n  Comments: %s\n", this.name, this.comments);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
