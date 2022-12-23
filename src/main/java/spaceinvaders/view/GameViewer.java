package spaceinvaders.view;

import com.googlecode.lanterna.screen.Screen;
import spaceinvaders.model.GameModel;

import java.io.IOException;

public class GameViewer {
    private GameModel model;
    private Screen screen;

    public GameViewer(GameModel model, Screen screen) {
        this.model = model;
        this.screen = screen;
    }

    public void draw() throws IOException {
        screen.clear();
        model.getState().getViewer().draw(screen.newTextGraphics());
        screen.refresh();
    }
}
