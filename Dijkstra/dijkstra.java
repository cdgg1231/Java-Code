import java.io.*;
import java.util.*;

public class dijkstra {

    static final int INF = Integer.MAX_VALUE;
    static int[][] cost;
    static int[] d;
    static int[] p;
    static boolean[] visited;
    static int n;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Prompt user to input the total number of routers
        System.out.print("Enter the total number of routers: ");
        int n = sc.nextInt();
        
        // Keep asking for input until a valid input is provided
        while (n < 2) {
            System.out.println("Error: the total number of routers must be greater than or equal to 2.");
            System.out.print("Enter the total number of routers: ");
            n = sc.nextInt();
        }

       

        // Initialize the cost matrix
        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }
        
        // Read the input file
        File inputFile;
        Scanner inputFileScanner = null;
        do {
            System.out.print("Enter the name of the cost input file: ");
            String inputFileName = sc.next();
            inputFile = new File(inputFileName);
            try {
                inputFileScanner = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                System.out.println("Error: file " + inputFileName + " not found.");
                continue;
            }
            int invalidRow = -1;
            while (inputFileScanner.hasNext()) {
                int i = inputFileScanner.nextInt();
                int j = inputFileScanner.nextInt();
                int c = inputFileScanner.nextInt();
                if (i < 0 || i >= n || j < 0 || j >= n || c <= 0) {
                    invalidRow = inputFileScanner.nextInt();
                    break;
                }
                cost[i][j] = c;
                cost[j][i] = c;
            }
            if (invalidRow != -1) {
                System.out.println("Error: invalid number in row " + invalidRow + ".");
            }

        } while (inputFileScanner == null || inputFileScanner.hasNext());

        sc.close();
        // Initialize the Dijkstra's algorithm
        d = new int[n];
        p = new int[n];
        visited = new boolean[n];

        Arrays.fill(d, INF);
        Arrays.fill(p, -1);
        d[0] = 0;

        // Dijkstra's algorithm
        for (int i = 0; i < n - 1; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || d[j] < d[u])) {
                    u = j;
                }
            }
            visited[u] = true;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && cost[u][v] != INF && d[u] + cost[u][v] < d[v]) {
                    d[v] = d[u] + cost[u][v];
                    p[v] = u;
                }
            }
        }

       // Build the forwarding table
       System.out.println("Forwarding table:");
       System.out.println("Destination\tLink");
       for (int i = 1; i < n; i++) {
           System.out.print("V" + i + "\t\t");
           int j = i;
           while (j != 0) {
               System.out.print("V" + j + " ");
               j = p[j];
           }
           System.out.println("(V0, " + getLink(i) + ")");
       }
       
   }
   
 
   
   // Helper method to get the link to a destination router
   private static String getLink(int dest) {
       int pred = p[dest];
       while (pred != 0) {
           dest = pred;
           pred = p[dest];
       }
       return "V" + dest;
   }
}
