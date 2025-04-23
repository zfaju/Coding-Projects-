//Zoe Faju
//CS 3305 Section W01
//Spring 2025
//Emin Mary Abraham
//Assignment 7
//IntelliJ IDEA

public class HashFunctions {

    // Hard-coded keys as specified in the assignment
    public  static final int[] keys = {
            1234, 8234, 7867, 1009, 5438, 4312, 3420, 9487, 5418, 5299,
            5078, 8239, 1208, 5098, 5195, 5329, 4543, 3344, 7698, 5412,
            5567, 5672, 7934, 1254, 6091, 8732, 3095, 1975, 3843, 5589,
            5439, 8907, 4097, 3096, 4310, 5298, 9156, 3895, 6673, 7871,
            5787, 9289, 4553, 7822, 8755, 3398, 6774, 8289, 7665, 5523
    };

    // Hash table structure: 50 rows, 2 columns (key, probes)
    public static int[][] table = new int[50][2];
    public  static int unhashedKeys = 0;

}
