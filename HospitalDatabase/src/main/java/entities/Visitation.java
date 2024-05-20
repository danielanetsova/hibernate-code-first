package entities;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "ssn")
    private Patient patient;

    public String getVisitationsInfo() {
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String stringDate = dateFormat.format(this.date);
        return String.format("  Visitation date: %s\nComments: %s\n", stringDate, this.comments);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
