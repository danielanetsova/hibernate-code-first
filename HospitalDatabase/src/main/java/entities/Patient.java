package entities;

import enums.MedicalInsurance;

import javax.persistence.*;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    private String ssn;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    @Basic
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(columnDefinition = "BLOB")
    private Blob picture;

    //Тук слагам като поле enum защото искам в базата да се записва дали пациентът има застраховка само с
    //"YES" И "NO". Това по смисъл отговаря на констрайнта Check при създаване на таблица в базата кои стойности
    //могат да се вмъкват в съответната колона

    @Column(name = "medical_insurance")
    @Enumerated(EnumType.STRING)
    private MedicalInsurance insurance;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Visitation> visitations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Diagnose> diagnoses;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Medicament> medicaments;

    public Patient() {}

    public Patient(String ssn) {
        this.ssn = ssn;
        this.visitations = new ArrayList<>();
        this.diagnoses = new ArrayList<>();
        this.medicaments = new ArrayList<>();
    }

    public String getVisitations() {
        if (this.visitations.isEmpty()) {
            return "    none"
                    + System.lineSeparator();
        }

        return this.visitations
                .stream()
                .map(Visitation::getVisitationsInfo)
                .collect(Collectors.joining("\n"))
                + System.lineSeparator();
    }

    public String getDiagnoses() {
        if (this.diagnoses.isEmpty()) {
            return "none"
                    + System.lineSeparator();
        }
        return this.diagnoses
                .stream()
                .map(Diagnose::getNameAndComments)
                .collect(Collectors.joining("\n"))
                + System.lineSeparator();

    }

    public String getMedicaments() {
        if (this.medicaments.isEmpty()) {
            return "none"
                    + System.lineSeparator();
        }
        return this.medicaments
                .stream()
                .map(Medicament::getName)
                .collect(Collectors.joining("\n"))
                + System.lineSeparator();
    }

    public void printPatientInfo() {
        //нз как стават нещата със снимките
        System.out.printf("First name: %s\n" +
                "Last name: %s\n" +
                "Address: %s\n" +
                "E-mail: %s\n" +
                "Date Of Birth: %s\n" +
                "Insurance: %s\n" +
                "Visitations: %n%s" +
                "Diagnoses: %n%s" +
                "Medicaments: %n%s",
                firstName,
                lastName,
                address,
                email,
                dateToString(this.dateOfBirth),
                insurance,
                getVisitations(),
                getDiagnoses(),
                getMedicaments()
        );
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public void setInsurance(MedicalInsurance insurance) {
        this.insurance = insurance;
    }

    public void addDiagnose(Diagnose diagnose) {
        this.diagnoses.add(diagnose);
    }

    public void addVisitation(Visitation visitation) {
        this.visitations.add(visitation);
    }

    public void addMedicament(Medicament medicament) {
        this.medicaments.add(medicament);
    }

    private String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        return dateFormat.format(date);
    }
}
