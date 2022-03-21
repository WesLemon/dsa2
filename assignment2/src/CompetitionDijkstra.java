import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

    private final int sA;
    private final int sB;
    private final int sC;

    private final double[][] adjacencyMatrix;
    private int intersections;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) throws FileNotFoundException {

        this.sA = sA;
        this.sB = sB;
        this.sC = sC;

        Scanner inFile = new Scanner(new File(filename));

        intersections = -1;
        int streets = -1;
        if (inFile.hasNextInt()) {
            intersections = inFile.nextInt();
        }
        if (inFile.hasNextInt()) {
            streets = inFile.nextInt();
        }

        adjacencyMatrix = new double[intersections][intersections];
        int vertex1 = -1;
        int vertex2 = -1;

        for (int i = 0; i < streets; i++) {
            if (inFile.hasNextInt()) {
                vertex1 = inFile.nextInt();
            }
            if (inFile.hasNextInt()) {
                vertex2 = inFile.nextInt();
            }
            if (inFile.hasNextDouble()) {
                adjacencyMatrix[vertex1][vertex2] = inFile.nextDouble();
            }
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {

        int worstSpeed = sA;
        if (worstSpeed > sB) {
            worstSpeed = sB;
        }
        if (worstSpeed > sC) {
            worstSpeed = sC;
        }

        // Finds the worst-case source and destination vertices.
        double worstDist = -1;
        for (int i = 0; i < intersections; i++) {
            double worstFromI = worstCaseDijsktra(i);
            if (worstFromI > worstDist) {
                worstDist = worstFromI;
            }
        }
        double result = (worstDist*1000) / worstSpeed;
        return (int)Math.ceil(result);
    }

    // Dijkstra algorithm that returns the worst-case destination vertex from a given source.
    private double worstCaseDijsktra(int vertex) {
        double[] distTo = new double[intersections];
        boolean[] visited = new boolean[intersections];

        // Initialise dist array
        for (int i = 0; i < intersections; i++) {
            distTo[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distTo[vertex] = 0;

        // Get the shortest paths from source to each vertex.
        for (int i = 0; i < intersections-1; i++) {
            int vertex1 = minDistance(distTo, visited);
            visited[vertex1] = true;
            for (int vertex2 = 0; vertex2 < intersections; vertex2++) {
                if(adjacencyMatrix[vertex1][vertex2] != 0 && !visited[vertex2] && distTo[vertex1] != Integer.MAX_VALUE
                        && distTo[vertex1] + adjacencyMatrix[vertex1][vertex2] < distTo[vertex2]) {
                    distTo[vertex2] = distTo[vertex1] + adjacencyMatrix[vertex1][vertex2];
                }
            }
        }

        // Return the worst-case destination vertex from this source.
        double worstCase = -1;
        for (int i = 0; i < intersections; i++) {
            if(distTo[i] > worstCase)
            {
                worstCase = distTo[i];
            }
        }
        return worstCase;
    }

    // Helper method for Dijkstra.
    private int minDistance(double[] dist, boolean[] visited)
    {
        double min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int i = 0; i < intersections; i++) {
            if(!visited[i] && dist[i] < min)
            {
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }
}