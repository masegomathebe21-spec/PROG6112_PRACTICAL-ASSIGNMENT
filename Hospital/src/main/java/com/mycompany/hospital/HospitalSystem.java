/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital;

/**
 *
 * @author Maesgo
 */
import java.util.Scanner;

public class HospitalSystem {

    private PatientManager patientManager;
    private BedManager bedManager;
    private Scanner scanner;

    public HospitalSystem() {
        patientManager = new PatientManager();
        bedManager = new BedManager();
        scanner = new Scanner(System.in);
    }

    // Main menu
    public void startSystem() {

        int choice;

        do {

            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    updatePatient();
                    break;

                case 4:
                    deletePatient();
                    break;

                case 5:
                    patientManager.displayAllPatients();
                    break;

                case 6:
                    allocateBed();
                    break;

                case 7:
                    releaseBed();
                    break;

                case 8:
                    bedManager.displayWardLayout();
                    break;

                case 9:
                    bedManager.displayAvailableBeds();
                    break;

                case 10:
                    bedManager.displayOccupiedBeds();
                    break;

                case 11:
                    displayReports();
                    break;

                case 0:
                    System.out.println("Thank you for using MediCare Hospital System.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);

    }

    // Display Menu
    public void displayMenu() {

        System.out.println("\n========== MEDICARE HOSPITAL ==========");
        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. Display All Patients");
        System.out.println("6. Allocate Bed");
        System.out.println("7. Release Bed");
        System.out.println("8. Display Ward Layout");
        System.out.println("9. Display Available Beds");
        System.out.println("10. Display Occupied Beds");
        System.out.println("11. Reports");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // Register Patient
    public void registerPatient() {

        
        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        System.out.println("Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        int option = scanner.nextInt();
        scanner.nextLine();
        

        PatientCategory category;

        switch (option) {

            case 1:
                category = PatientCategory.INPATIENT;
                break;

            case 2:
                category = PatientCategory.OUTPATIENT;
                break;

            default:
                category = PatientCategory.EMERGENCY;
        }

        Patient patient;

        if (category == PatientCategory.INPATIENT) {

            System.out.print("Ward Number: ");
            String ward = scanner.nextLine();

            patient = new Inpatient(
                    id,
                    firstName,
                    lastName,
                    age,
                    gender,
                    condition,
                    category,
                    ward,
                    ""
            );

        } else {

            patient = new Patient(
                    id,
                    firstName,
                    lastName,
                    age,
                    gender,
                    condition,
                    category
            );
        }

        patientManager.registerPatient(patient);

System.out.println("Patient registered successfully.");

    }
// Search for a patient
    public void searchPatient() {

        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();

        Patient patient = patientManager.searchPatient(patientID);

        if (patient != null) {
            patient.displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Update a patient
public void updatePatient() {

    System.out.print("Enter Patient ID: ");
    String patientID = scanner.nextLine();

    Patient patient = patientManager.searchPatient(patientID);

    if (patient == null) {
        System.out.println("Patient not found.");
        return;
    }

    System.out.print("New First Name: ");
    String firstName = scanner.nextLine();

    System.out.print("New Last Name: ");
    String lastName = scanner.nextLine();

    System.out.print("New Age: ");
    int age = scanner.nextInt();
    scanner.nextLine();

    System.out.print("New Gender: ");
    String gender = scanner.nextLine();

    System.out.print("New Medical Condition: ");
    String condition = scanner.nextLine();

    System.out.println("Patient Category:");
    System.out.println("1. Inpatient");
    System.out.println("2. Outpatient");
    System.out.println("3. Emergency");

    int option = scanner.nextInt();
    scanner.nextLine();

    PatientCategory category;

    switch (option) {

        case 1:
            category = PatientCategory.INPATIENT;
            break;

        case 2:
            category = PatientCategory.OUTPATIENT;
            break;

        default:
            category = PatientCategory.EMERGENCY;
    }

    patientManager.updatePatient(
            patientID,
            firstName,
            lastName,
            age,
            gender,
            condition,
            category);

    System.out.println("Patient updated successfully.");
}

// Delete a patient
public void deletePatient() {

    System.out.print("Enter Patient ID to delete: ");
    String patientID = scanner.nextLine();

    boolean deleted = patientManager.deletePatient(patientID);

    if (deleted) {
        System.out.println("Patient deleted successfully.");
    } else {
        System.out.println("Patient not found.");
    }
}

// Allocate a bed
public void allocateBed() {

    System.out.print("Enter Patient ID: ");
    String patientID = scanner.nextLine();

    Patient patient = patientManager.searchPatient(patientID);

    if (patient == null) {
        System.out.println("Patient not found.");
        return;
    }

    if (patient instanceof Inpatient) {

        Inpatient inpatient = (Inpatient) patient;
        bedManager.allocateBed(inpatient);

    } else {

        System.out.println("Only inpatients can be allocated a bed.");

    }

}

// Release a bed
public void releaseBed() {

    System.out.print("Enter Bed Number (e.g. B01): ");
    String bedNumber = scanner.nextLine();

    boolean released = bedManager.releaseBed(bedNumber);

    if (released) {
        System.out.println("Bed released successfully.");
    } else {
        System.out.println("Unable to release bed.");
    }
}

// Display hospital reports
public void displayReports() {

    System.out.println("\n========== HOSPITAL REPORTS ==========");

    System.out.println("Total registered patients: "
            + patientManager.getPatientCount());

    System.out.println("Total occupied beds: "
            + bedManager.getOccupiedBedCount());

    System.out.println("Ward occupancy: "
            + bedManager.getOccupancyPercentage() + "%");

    System.out.println("======================================");
}
}