package spaceinvaders.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.menu.HighScoreMenuModel;

public class HighScoreMenuController implements Controller {
    private HighScoreMenuModel model;
    private static HighScoreMenuController instance = null;

    private HighScoreMenuController(HighScoreMenuModel model) {
        this.model = model;
    }

    public static HighScoreMenuController getInstance(HighScoreMenuModel model) {
        if (instance == null) {
            instance = new HighScoreMenuController(model);
        }
        return instance;
    }

    public static HighScoreMenuController getInstance() {
        return instance;
    }

    @Override
    public void processKey(KeyStroke key) {
        if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') ||
                key.getKeyType() == KeyType.Escape || key.getKeyType() == KeyType.Enter) {
            model.getExitCommand().execute();
        }
    }

    public static void reset() {
        instance = null;
    }
}
