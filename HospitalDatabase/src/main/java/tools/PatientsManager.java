package tools;

import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;
import enums.MedicalInsurance;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PatientsManager {
    public static void managePatients(EntityManager manager) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nType operation");
            String input = scanner.nextLine();

            switch (input) {
                case "Get info about a patient":
                    getPatientInfo(scanner, manager);
                    break;
                case "Add a new patient":
                    addNewPatient(scanner, manager);
                    break;
                case "Remove a patient":
                    removePatient(scanner, manager);
                    //премахване на обекта, визитациите и диагнозите
                    break;
                default:
                    System.out.println("Bye\n");
            }
            if (!input.equals("Exit")) {
                managePatients(manager);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private static void removePatient(Scanner scanner, EntityManager manager) {
        System.out.println("\nType ssn");
        String ssn = scanner.nextLine();
        Patient patient = manager.find(Patient.class, ssn);
        manager.remove(patient);
    }

    private static void getPatientInfo(Scanner scanner, EntityManager manager) {
        System.out.println("\nType ssn");
        String ssn = scanner.nextLine();
        Patient patient = manager.find(Patient.class, ssn);
        if (patient == null) {
            System.out.println("No such patient");
        } else {
            patient.printPatientInfo();
        }
    }

    private static void addNewPatient(Scanner scanner, EntityManager manager) throws ParseException {
        System.out.println("\nType SSN");
        final String ssn = scanner.nextLine();
        Patient patient = new Patient(ssn);

        System.out.println("\nType first name");
        patient.setFirstName(scanner.nextLine());

        System.out.println("\nType last name");
        patient.setLastName(scanner.nextLine());

        System.out.println("\nType address");
        patient.setAddress(scanner.nextLine());

        System.out.println("\nType email");
        patient.setEmail(scanner.nextLine());

        System.out.println("\nType date of birth");
        String inputDateOfBirth = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse(inputDateOfBirth);
        patient.setDateOfBirth(birthDate);


        System.out.println("\nHas medical insurance?");
        patient.setInsurance(MedicalInsurance.valueOf(scanner.nextLine().toUpperCase()));

        Diagnose diagnose = new Diagnose();
        System.out.println("\nDiagnose name");
        String diagnoseName = scanner.nextLine();

        if (!diagnoseName.equals("")) {
            diagnose.setName(diagnoseName);
            System.out.println("Diagnose comments");
            diagnose.setComments(scanner.nextLine());
            patient.addDiagnose(diagnose);
        }

        Visitation visitation = new Visitation();
        System.out.println("\nVisitation date");
        String inputVisitationDate = scanner.nextLine();

        if (!inputVisitationDate.equals("")) {
            Date visitationDate = dateFormat.parse(inputVisitationDate);
            visitation.setDate(visitationDate);
            System.out.println("Visitation comments");
            visitation.setComments(scanner.nextLine());
            patient.addVisitation(visitation);
        }

        Medicament medicament = new Medicament();
        System.out.println("\nType medicament name");
        String medicamentName = scanner.nextLine();

        if (!medicamentName.equals("")) {
            medicament.setName(medicamentName);

            List<Medicament> dbMedicaments = manager.
                    createQuery("FROM Medicament WHERE name = :someMedicamentName", Medicament.class)
                    .setParameter("someMedicamentName", medicamentName).getResultList();


            if (dbMedicaments.isEmpty()) {
                patient.addMedicament(medicament);
            } else {
                patient.addMedicament(dbMedicaments.get(0));
            }

        }

        manager.persist(patient);
    }
}
