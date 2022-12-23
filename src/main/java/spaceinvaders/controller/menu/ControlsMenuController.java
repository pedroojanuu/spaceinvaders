package spaceinvaders.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.menu.ControlsMenuModel;

public class ControlsMenuController implements Controller {
    private ControlsMenuModel model;
    private static ControlsMenuController instance = null;

    private ControlsMenuController(ControlsMenuModel model) {
        this.model = model;
    }

    public static ControlsMenuController getInstance(ControlsMenuModel model) {
        if (instance == null) {
            instance = new ControlsMenuController(model);
        }
        return instance;
    }

    public static ControlsMenuController getInstance() {
        return instance;
    }

    @Override
    public void processKey(KeyStroke key) {
        if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') ||
                key.getKeyType() == KeyType.Escape || key.getKeyType() == KeyType.Enter) {
            model.getExitCommand().execute();
        }
    }
    public static void reset(){
        instance = null;
    }
}
