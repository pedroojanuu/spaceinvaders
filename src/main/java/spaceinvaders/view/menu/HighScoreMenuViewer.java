package spaceinvaders.view.menu;

import com.google.common.base.Splitter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.menu.HighScoreMenuModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HighScoreMenuViewer implements MenuViewer {
    private static HighScoreMenuViewer instance = null;
    private HighScoreMenuModel model;
    String path;
    private HighScoreMenuViewer(HighScoreMenuModel model){
        this.model = model;
        this.path = "resources/highscores.csv";
    }
    public static HighScoreMenuViewer getInstance(HighScoreMenuModel model){
        if(instance == null){
            instance = new HighScoreMenuViewer(model);
        }
        return instance;
    }
    public static HighScoreMenuViewer getInstance(){
        return instance;
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(8, 2, "HighScores");
        String line = "";
        String splitBy = ",";
        try {
            int y = 5;
            BufferedReader br = Files.newBufferedReader(Paths.get(path), UTF_8);
            while ((line = br.readLine()) != null) {
                List<String> arr = Splitter.onPattern(splitBy).splitToList(line);
                graphics.putString(8, y, arr.get(0));
                graphics.putString(38, y, arr.get(1));
                y++;
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC300"));
        graphics.putString(8, 20, "> Exit");
    }
    public static void reset(){
        instance = null;
    }
    public void setPath(String path){
        this.path = path;
    }
}
