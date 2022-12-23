package spaceinvaders;

import com.google.common.base.Splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PlayerScore implements Comparable<PlayerScore>{
    private String player;
    private int score;
    private static String path = "resources/highscores.csv";

    public PlayerScore(String player, int score) {
        super();
        this.player = player;
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    @Override
    public int compareTo(PlayerScore playerScore) {
        return playerScore.score - this.score;
    }
    public static TreeSet<PlayerScore> loadScores() {
        TreeSet<PlayerScore> r = new TreeSet<>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(path), UTF_8);
            while ((line = br.readLine()) != null) {
                List<String> arr = Splitter.onPattern(splitBy).splitToList(line);
                PlayerScore playerScore = new PlayerScore(arr.get(0), Integer.parseInt(arr.get(1)));
                r.add(playerScore);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return r;
    }
    public static void storeScores(TreeSet<PlayerScore> scores) {
        while (scores.size() > 10) scores.pollLast();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, UTF_8.name());writer.print("");
            for (PlayerScore score : scores) {
                writer.print(score.player + "," +  score.score + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof PlayerScore)) return false;
        PlayerScore playerScore = (PlayerScore) obj;
        return playerScore.player.equals(this.player) && playerScore.score == this.score;
    }

    @Override
    public int hashCode() {
        return player.hashCode() + score;
    }

    public static void setPath(String newPath) {
        path = newPath;
    }
}
