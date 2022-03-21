import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RunWith(JUnit4.class)
public class CompetitionTests {

    public static String filename = "tinyEWD.txt";
    public static int speedA = 50;
    public static int speedB = 75;
    public static int speedC = 100;

    @Test
    public void testDijkstraConstructor() {
        CompetitionDijkstra test = new CompetitionDijkstra(filename, speedA, speedB, speedC);
    }

    @Test
    public void testFWConstructor() {
        //TODO
    }

    //TODO - more tests

}
