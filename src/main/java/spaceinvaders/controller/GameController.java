package spaceinvaders.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import spaceinvaders.model.GameModel;

import java.io.IOException;
import java.util.Objects;

public class GameController {
    private Screen screen;
    private GameModel model;
    private RunStateController state;
    private KeyStroke lastKey;

    public GameController(GameModel model, Screen screen) {
        this.screen = screen;
        this.model = model;
        state = new RunStateController(model.getState().getController());
    }

    public void processKey() throws IOException {
        KeyStroke key = screen.pollInput();
        while(key != null && lastKey != null && key.getKeyType() == lastKey.getKeyType() && Objects.equals(key.getCharacter(), lastKey.getCharacter()))
            key = screen.pollInput();
        lastKey = key;
        if (key == null) return;
        if (key.getKeyType() == KeyType.EOF) System.exit(0);

        if(state.getController() != model.getState().getController()){
            state = new RunStateController(model.getState().getController());
        }
        state.processKey(key);
    }

    public void setState(RunStateController state) {
        this.state = state;
    }

    public RunStateController getState() {
        return state;
    }
}
