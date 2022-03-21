import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;


@RunWith(JUnit4.class)
public class CompetitionTests {

    public static String filename = "tinyEWD.txt";

    @Test
    public void testDijkstraConstructor() throws FileNotFoundException {

        CompetitionDijkstra test1 = new CompetitionDijkstra(filename, 60, 75, 90);
        assertEquals(test1.timeRequiredforCompetition(), 31);

        CompetitionDijkstra test2 = new CompetitionDijkstra(filename, 90, 75, 60);
        assertEquals(test2.timeRequiredforCompetition(), 31);
    }

//    @Test
//    public void testFWConstructor() {
//        //TODO
//    }
//
//    //TODO - more tests

}
