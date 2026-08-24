/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital;
import java.util.ArrayList;

/**
 *
 * @author Maesgo
 */
public class PatientManager {
    
    private ArrayList<Patient> patients;

    public PatientManager() {
        patients = new ArrayList<>();
    }
    
    public void registerPatient(Patient patient) {

        patients.add(patient);

        System.out.println("Patient registered successfully.");
        
    }
        
        public Patient searchPatient(String patientID) {

    for (Patient patient : patients) {

        if (patient.getPatientID().equals(patientID)) {
            return patient;
        }

    }

    return null;
    
    
        }   
        
        public boolean updatePatient(String patientID, String firstName, String lastName,
                             int age, String gender, String medicalCondition,
                             PatientCategory category) {

    Patient patient = searchPatient(patientID);

    if (patient != null) {

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);
        patient.setCategory(category);

        System.out.println("Patient updated successfully.");
        return true;
    }

    System.out.println("Patient not found.");
    return false;
}
        
       // Delete a patient
public boolean deletePatient(String patientID) {

    Patient patient = searchPatient(patientID);

    if (patient != null) {
        patients.remove(patient);
        System.out.println("Patient deleted successfully.");
        return true;
    }

    System.out.println("Patient not found.");
    return false;
}

// Display all registered patients
public void displayAllPatients() {

    if (patients.isEmpty()) {
        System.out.println("No patients registered.");
        return;
    }

    for (Patient patient : patients) {
        patient.displayDetails();
        System.out.println("-------------------------");
    }
}  
   
public int getPatientCount() {
    return patients.size();
}
}
