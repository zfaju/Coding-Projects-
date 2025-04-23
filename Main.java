//Zoe Faju
//CS 3305 Section W01
//Spring 2025
//Emin Mary Abraham
//Assignment 7
//IntelliJ IDEA

import java.util.Scanner;
public class Main  {
    /**
     * Displays the main menu
     */
    private static void displayMenu() {
        System.out.println("\n\n-----MAIN MENU--------------------------------------");
        System.out.println("1. Run HF1 (Division method with Linear Probing)");
        System.out.println("2. Run HF2 (Division method with Quadratic Probing)");
        System.out.println("3. Run HF3 (Division method with Double Hashing)");
        System.out.println("4. Run HF4 (Student Designed HF)");
        System.out.println("5. Exit program");
        System.out.println("");
        System.out.print("Enter option number: ");
    }

    /**
     * Resets the hash table to empty state
     */
    private static void resetTable() {
        for (int i = 0; i < 50; i++) {
            HashFunctions.table[i][0] = 0;  // Key
            HashFunctions.table[i][1] = 0;  // Probes
        }
    }

    /**
     * Displays the current state of the hash table
     */
    private static void displayTable() {

        System.out.println("Index   Key    Probes");
        System.out.println("-------------------------------------");
        for (int i = 0; i < 50; i++) {
            System.out.println(i + "\t\t" + HashFunctions.table[i][0] + "\t" + HashFunctions.table[i][1]);
        }
        System.out.println("-------------------------------------");
    }

    /**
     * Calculates the sum of all probe values in the table
     * @return sum of all probes
     */
    private static int sumProbes() {
        int sum = 0;
        for (int i = 0; i < 50; i++) {
            sum += HashFunctions.table[i][1];
        }
        return sum;
    }

    /**
     * Hash Function 1: Division method with Linear Probing
     */
    private static void HF1() {
        for (int key : HashFunctions.keys) {
            int index = key % 50;  // Division method
            int probes = 1;

            // Linear probing
            while (HashFunctions.table[index][0] != 0 && probes <= 50) {
                index = (index + 1) % 50;
                probes++;
            }

            if (probes > 50) {
                System.out.println("Unable to hash key " + key + " to the table");
                HashFunctions.unhashedKeys++;
            } else {
                HashFunctions.table[index][0] = key;
                HashFunctions.table [index][1] = probes;
            }
        }
    }

    /**
     * Hash Function 2: Division method with Quadratic Probing
     */
    private static void HF2() {
        for (int key : HashFunctions.keys) {
            int index = key % 50;  // Division method
            int probes = 1;
            int i = 1;

            // Quadratic probing
            while (HashFunctions.table[index][0] != 0 && probes <= 50) {
                index = (key % 50 + i * i) % 50;
                i++;
                probes++;
            }

            if (probes > 50) {
                System.out.println("Unable to hash key " + key + " to the table");
                HashFunctions.unhashedKeys++;
            } else {
               HashFunctions.table[index][0] = key;
                HashFunctions.table[index][1] = probes;
            }
        }
    }

    /**
     * Hash Function 3: Division method with Double Hashing
     */
    private static void HF3() {
        for (int key : HashFunctions.keys) {
            int index = key % 50;  // First hash function
            int probes = 1;
            int j = 1;

            // Second hash function
            int h2 = 30 - (key % 25);

            // Double hashing
            while (HashFunctions.table[index][0] != 0 && probes <= 50) {
                index = (key % 50 + j * h2) % 50;
                j++;
                probes++;
            }

            if (probes > 50) {
                System.out.println("Unable to hash key " + key + " to the table");
                HashFunctions.unhashedKeys++;
            } else {
                HashFunctions.table[index][0] = key;
                HashFunctions.table[index][1] = probes;
            }
        }
    }

    /**
     * Hash Function 4: Student Designed Hash Function
     *
     * This implementation uses the Multiplication method with Folding for the hash function
     * and Brent's variation of Double Hashing for collision resolution.
     *
     * The multiplication method provides better distribution than simple division,
     * and Brent's method reduces clustering while minimizing probe counts.
     */
    private static void HF4() {
        final double A = (Math.sqrt(5) - 1) / 2;  // Knuth's multiplicative constant

        for (int key : HashFunctions.keys) {
            // Multiplication method with folding
            double product = key * A;
            double fractional = product - (int) product;
            int index = (int) (50 * fractional);

            int probes = 1;
            int j = 1;

            // Brent's variation of double hashing
            int h2 = 1 + (key % 49);  // Secondary hash (relatively prime to table size)

            while (HashFunctions.table[index][0] != 0 && probes <= 50) {
                // Instead of fixed increment, use a sequence that reduces clustering
                if (HashFunctions.table[index][1] > 0) {  // If current slot was probed before
                    // Try to move the existing key to a new position
                    int existingKey = HashFunctions.table[index][0];
                    int existingProbes = HashFunctions.table[index][1];
                    int newIndex = (index + h2) % 50;
                    int newProbes = existingProbes + 1;

                    if (HashFunctions.table[newIndex][0] == 0 && newProbes <= 50) {
                        // Move existing key to new position
                        HashFunctions.table[newIndex][0] = existingKey;
                        HashFunctions.table[newIndex][1] = newProbes;
                        // Place new key in current position
                        HashFunctions.table[index][0] = key;
                        HashFunctions.table[index][1] = probes;
                        break;
                    }
                }

                // Standard double hashing if Brent's method doesn't apply
                index = (index + j * h2) % 50;
                j++;
                probes++;
            }

            if (probes > 50) {
                System.out.println("Unable to hash key " + key + " to the table");
                HashFunctions.unhashedKeys++;
            } else if (HashFunctions.table[index][0] == 0) {
                HashFunctions.table[index][0] = key;
                HashFunctions.table[index][1] = probes;
            }
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;




        do {

            displayMenu();
            option = scanner.nextInt();

            if (option ==1 ) {
                System.out.println("\nHash table resulted from: HF1\n");
            }

            else if (option ==2 ) {
                System.out.println("\nHash table resulted from: HF2\n");
            }
            else if (option ==3 ) {
                System.out.println("\nHash table resulted from: HF3\n");
            }
            else if (option ==4 ) {
                System.out.println("\nHash table resulted from: HF4\n");
            }
            // Reset table and unhashed keys counter before each run
            resetTable();
            HashFunctions.unhashedKeys = 0;

            switch(option) {
                case 1:
                    HF1();
                    break;
                case 2:
                    HF2();
                    break;
                case 3:
                    HF3();
                    break;
                case 4:
                    HF4();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (option >= 1 && option <= 4) {
                displayTable();
                System.out.println("Sum of probe values = " + sumProbes());
                if (HashFunctions.unhashedKeys > 0) {
                    System.out.println("Unable to hash " + HashFunctions.unhashedKeys + " keys to the table");
                }
                System.out.println();
            }
        } while (option != 5);

        scanner.close();
    }
}