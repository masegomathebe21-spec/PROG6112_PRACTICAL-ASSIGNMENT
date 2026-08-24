/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital;

/**
 *
 * @author Maesgo
 */
public class BedManager {
    
    private String[][] beds = new String[4][5];
    
    private boolean[][] occupied = new boolean[4][5];
    
    public BedManager() {

    int bedNumber = 1;

    for (int row = 0; row < 4; row++) {

        for (int column = 0; column < 5; column++) {

            beds[row][column] = String.format("B%02d", bedNumber);

            bedNumber++;
        }
    }
}
    
    public void displayWardLayout() {

        System.out.println("===== WARD BED LAYOUT =====");

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                if (occupied[row][column]) {
                    System.out.print(beds[row][column] + " [OCCUPIED]   ");
                } else {
                    System.out.print(beds[row][column] + " [AVAILABLE]  ");
                }
            }

            System.out.println();
        }
    }

public boolean allocateBed(Inpatient patient) {

    for (int row = 0; row < 4; row++) {

        for (int column = 0; column < 5; column++) {

            if (!occupied[row][column]) {

                occupied[row][column] = true;

                String bedNumber = beds[row][column];

                patient.setBedNumber(bedNumber);

                System.out.println("Bed " + bedNumber + " allocated to "
                        + patient.getFirstName() + " " + patient.getLastName());

                return true;
            }
        }
    }

    System.out.println("No beds are available.");
    return false;
}

// Release a bed
public boolean releaseBed(String bedNumber) {

    for (int row = 0; row < 4; row++) {

        for (int column = 0; column < 5; column++) {

            if (beds[row][column].equals(bedNumber)) {

                if (occupied[row][column]) {

                    occupied[row][column] = false;

                    System.out.println("Bed " + bedNumber + " has been released.");

                    return true;
                } else {

                    System.out.println("Bed " + bedNumber + " is already available.");

                    return false;
                }
            }
        }
    }

    System.out.println("Bed not found.");
    return false;
}

// Display all available beds
public void displayAvailableBeds() {

    System.out.println("===== AVAILABLE BEDS =====");

    for (int row = 0; row < 4; row++) {

        for (int column = 0; column < 5; column++) {

            if (!occupied[row][column]) {
                System.out.println(beds[row][column]);
            }
        }
    }
}

// Display all occupied beds
public void displayOccupiedBeds() {

    System.out.println("===== OCCUPIED BEDS =====");

    for (int row = 0; row < 4; row++) {

        for (int column = 0; column < 5; column++) {

            if (occupied[row][column]) {
                System.out.println(beds[row][column]);
            }
        }
    }
}

// Count occupied beds
public int getOccupiedBedCount() {

    int count = 0;

    for (int row = 0; row < 4; row++) {

        for (int column = 0; column < 5; column++) {

            if (occupied[row][column]) {
                count++;
            }
        }
    }

    return count;
}

// Calculate ward occupancy percentage
public double getOccupancyPercentage() {

    int occupied = getOccupiedBedCount();

    return (occupied / 20.0) * 100;
}
}

