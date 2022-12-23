package spaceinvaders;

import com.google.common.base.Splitter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerScoreTest {

    @Test
    public void getScoreTest() {
        PlayerScore playerScore = new PlayerScore("", 0);
        assertEquals(0, playerScore.getScore());
        PlayerScore playerScore2 = new PlayerScore("", 100);
        assertEquals(100, playerScore2.getScore());
    }
    @Test
    public void compareToTest() {
        int a = 10;
        int b = 20;
        int expected = 10;
        PlayerScore playerScoreA = new PlayerScore("", a);
        PlayerScore playerScoreB = new PlayerScore("", b);
        assertEquals(expected, playerScoreA.compareTo(playerScoreB));
    }
    @Test
    public void testLoadScores() {
        TreeSet<PlayerScore> old = PlayerScore.loadScores();
        TreeSet<PlayerScore> expected = new TreeSet<>();
        expected.add(new PlayerScore("", 880));
        PlayerScore.storeScores(expected);

        assertEquals(expected, PlayerScore.loadScores());

        PlayerScore.storeScores(old);
    }
    @Test
    public void testLoadScores2() throws IOException {
        TreeSet<PlayerScore> scores = PlayerScore.loadScores();
        if(scores.isEmpty()){
            scores.add(new PlayerScore(System.getProperty("user.name"), 1));
            PlayerScore.storeScores(scores);
            scores = PlayerScore.loadScores();
        }
        BufferedReader br = Files.newBufferedReader(Paths.get("resources/highscores.csv"), UTF_8);
        String line = br.readLine();
        List<String> arr = Splitter.onPattern(",").splitToList(line);
        assertEquals(Integer.parseInt(arr.get(1)), scores.first().getScore());
    }

    @Test
    public void testStoreScores()  {
        TreeSet<PlayerScore> t = PlayerScore.loadScores();
        boolean changed = false;
        if(t.contains(new PlayerScore("player1", 100))){
            t.remove(new PlayerScore("player1", 100));
            changed = true;
        }
        if(t.contains(new PlayerScore("player2", 200))){
            t.remove(new PlayerScore("player2", 200));
            changed = true;
        }
        if(t.contains(new PlayerScore("player3", 300))){
            t.remove(new PlayerScore("player3", 300));
            changed = true;
        }
        if(changed){
            PlayerScore.storeScores(t);
            t = PlayerScore.loadScores();
        }
        int r = t.size();
        t.add(new PlayerScore("player1", 100));
        t.add(new PlayerScore("player2", 200));
        t.add(new PlayerScore("player3", 300));
        PlayerScore.storeScores(t);
        TreeSet<PlayerScore> scores2 = PlayerScore.loadScores();
        int result = scores2.size();
        assertEquals(r+3, result);
        TreeSet<PlayerScore> clean = new TreeSet<>();
        PlayerScore.storeScores(clean);

    }

    @Test
    public void hashCodeTest() {
        int expected = 0;
        PlayerScore playerScore = new PlayerScore("", 0);
        Assertions.assertEquals(expected, playerScore.hashCode());
    }

    @Test
    public void equalsTest() {
        PlayerScore playerScoreA = new PlayerScore("A", 5);
        PlayerScore playerScoreB = new PlayerScore("A", 5);
        Assertions.assertEquals(playerScoreA, playerScoreB);
    }

    @Test
    public void equalsTest2() {
        PlayerScore playerScoreA = new PlayerScore("A", 5);
        PlayerScore playerScoreB = new PlayerScore("B", 5);
        Assertions.assertFalse(playerScoreA.equals(playerScoreB));
    }

    @Test
    public void equalsTest3() {
        PlayerScore playerScoreA = new PlayerScore("A", 5);
        PlayerScore playerScoreB = new PlayerScore("A", 3);
        Assertions.assertFalse(playerScoreA.equals(playerScoreB));
    }

    @Test
    public void equalsTest4() {
        PlayerScore playerScoreA = new PlayerScore("A", 5);
        Assertions.assertFalse(playerScoreA.equals(null));
    }

    @Test
    public void equalsTest5() {
        PlayerScore playerScoreA = new PlayerScore("A", 5);
        Assertions.assertTrue(playerScoreA.equals(playerScoreA));
    }

    @Test
    public void equalsTest6() {
        PlayerScore playerScoreA = new PlayerScore("A", 5);
        String sTest = "Amarelo";
        Assertions.assertFalse(playerScoreA.equals(sTest));
    }

    @Test
    public void hashCodeTest2() {
        int expected = "player".hashCode() + 5;
        PlayerScore playerScore = new PlayerScore("player", 5);
        Assertions.assertEquals(expected, playerScore.hashCode());
    }

}
