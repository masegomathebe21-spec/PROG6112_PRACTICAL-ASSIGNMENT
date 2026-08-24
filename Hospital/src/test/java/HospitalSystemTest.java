package com.mycompany.hospital;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {

    @Test
    public void testRegisterPatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P001",
                "John",
                "Smith",
                35,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        Patient result = manager.searchPatient("P001");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }
}

