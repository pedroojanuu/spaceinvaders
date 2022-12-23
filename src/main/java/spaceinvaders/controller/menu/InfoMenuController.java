package spaceinvaders.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.menu.InfoMenuModel;

public class InfoMenuController implements Controller {
    private InfoMenuModel model;
    private static InfoMenuController instance = null;

    private InfoMenuController(InfoMenuModel model) {
        this.model = model;
    }

    public static InfoMenuController getInstance(InfoMenuModel model) {
        if (instance == null) {
            instance = new InfoMenuController(model);
        }
        return instance;
    }

    public static InfoMenuController getInstance() {
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
