import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){

        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert inFile != null;

        int intersections = -1;
        int streets = -1;
        if(inFile.hasNextInt()){
            intersections = inFile.nextInt();
        }
        if(inFile.hasNextInt()){
            streets = inFile.nextInt();
        }

        double[][] adjacency = new double[intersections][intersections];
        int vertex1 = -1;
        int vertex2 = -1;

        for (int i = 0; i < streets; i++) {
            if(inFile.hasNextInt())
            {
                vertex1 = inFile.nextInt();
            }
            if(inFile.hasNextInt()) {
                vertex2 = inFile.nextInt();
            }
            if(inFile.hasNextDouble()) {
                adjacency[vertex1][vertex2] = inFile.nextDouble();
            }
        }
        System.out.println(Arrays.deepToString(adjacency));
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }
}
