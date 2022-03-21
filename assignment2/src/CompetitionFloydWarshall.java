/**
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *  Each contestant walks at a given estimated speed.
 *  The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 * <p>
 * This class implements the competition using Floyd-Warshall algorithm
 *
 * @author Wesley Shaw
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CompetitionFloydWarshall {

    private final int sA;
    private final int sB;
    private final int sC;

    private final double[][] graph;
    private int intersections;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {

        this.sA = sA;
        this.sB = sB;
        this.sC = sC;

        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int streets = -1;

        assert inFile != null;
        if (inFile.hasNextInt()) {
            intersections = inFile.nextInt();
        }
        if (inFile.hasNextInt()) {
            streets = inFile.nextInt();
        }

        graph = new double[intersections][intersections];
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
                graph[vertex1][vertex2] = inFile.nextDouble();
            }
        }

        for (int i = 0; i < intersections; i++) {
            for (int j = 0; j < intersections; j++) {
                if (graph[i][j] == 0 && i != j) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
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

        if(sA < 50 || sA > 100 || sB < 50 || sB > 100 || sC < 50 || sC > 100)
        {
            return -1;
        }

        // Finds the worst-case source and destination vertices.
        double worstDist = -1;
        for (int i = 0; i < intersections; i++) {
            double worstFromI = worstCaseFloydWarshall(i);
            if (worstFromI > worstDist) {
                worstDist = worstFromI;
            }
        }
        double result = (worstDist * 1000) / worstSpeed;

        return (int) Math.ceil(result);
    }

    private double worstCaseFloydWarshall(int vertex) {

        double[][] dist = new double[intersections][intersections];
        for (int i = 0; i < intersections; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, intersections);
        }

        for (int k = 0; k < intersections; k++) {
            for (int i = 0; i < intersections; i++) {
                for (int j = 0; j < intersections; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return getMax(dist);
    }

    private double getMax(double[][] a) {
        double max = -1;
        for (int i = 0; i < intersections; i++) {
            for (int j = 0; j < intersections; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                }
            }
        }
        return max;
    }

}