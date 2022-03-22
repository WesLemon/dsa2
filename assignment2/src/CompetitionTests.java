import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Wesley Shaw
 */

@RunWith(JUnit4.class)
public class CompetitionTests {

    public static String filename = "tinyEWD.txt";

    @org.junit.Test
    public void testDijkstraConstructor() {

        CompetitionDijkstra test1 = new CompetitionDijkstra(filename, 60, 75, 90);
        assertEquals(test1.timeRequiredforCompetition(), 31);

        CompetitionDijkstra test2 = new CompetitionDijkstra(filename, 90, 75, 60);
        assertEquals(test2.timeRequiredforCompetition(), 31);

    }

    @org.junit.Test
    public void testFWConstructor() {

        CompetitionFloydWarshall test1 = new CompetitionFloydWarshall(filename, 60, 75, 90);
        assertEquals(test1.timeRequiredforCompetition(), 31);

        CompetitionFloydWarshall test2 = new CompetitionFloydWarshall(filename, 90, 75, 60);
        assertEquals(test2.timeRequiredforCompetition(), 31);
    }

    @org.junit.Test
    public void additionalTestA(){

        CompetitionDijkstra test1 = new CompetitionDijkstra("input-A.txt", 60, 50, 75);
        assertEquals(test1.timeRequiredforCompetition(), -1);

        CompetitionDijkstra test2 = new CompetitionDijkstra("input-A.txt", 50, 50, 50);
        assertEquals(test2.timeRequiredforCompetition(), -1);

        CompetitionFloydWarshall test3 = new CompetitionFloydWarshall("input-A.txt", 50, 50, 50);
        assertEquals(test3.timeRequiredforCompetition(), -1);
    }

    @org.junit.Test
    public void additionalTestK(){

        CompetitionDijkstra test2 = new CompetitionDijkstra("input-K.txt", 51, 70, 88);
        assertEquals(test2.timeRequiredforCompetition(), 314);

        CompetitionFloydWarshall test4 = new CompetitionFloydWarshall("input-K.txt", 51, 70, 88);
        assertEquals(test4.timeRequiredforCompetition(), 314);

    }

    @org.junit.Test
    public void additionalTestB(){

        CompetitionDijkstra test3 = new CompetitionDijkstra("input-B.txt", 51, 70, 88);
        assertEquals(test3.timeRequiredforCompetition(), 9804);

    }

    @org.junit.Test
    public void additionalTestJ() {

        CompetitionDijkstra test1 = new CompetitionDijkstra("input-J.txt", 60, 75, 61);
        assertEquals(test1.timeRequiredforCompetition(), -1);

    }

}
