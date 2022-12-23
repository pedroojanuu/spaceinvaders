package spaceinvaders.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.menu.OptionsMenuModel;

import static com.googlecode.lanterna.input.KeyType.ArrowDown;
import static com.googlecode.lanterna.input.KeyType.ArrowUp;

public class OptionsMenuController implements Controller {
    private OptionsMenuModel model;
    private static OptionsMenuController instance = null;

    private OptionsMenuController(OptionsMenuModel model) {
        this.model = model;
    }
    public static OptionsMenuController getInstance(OptionsMenuModel model){
        if(instance == null){
            instance = new OptionsMenuController(model);
        }
        return instance;
    }
    public static OptionsMenuController getInstance(){
        return instance;
    }

    @Override
    public void processKey(KeyStroke key) {
        if(key.getKeyType() == ArrowUp){
            model.upSelectedCommand();
        }
        else if (key.getKeyType() == ArrowDown){
            model.downSelectedCommand();
        }
        else if (key.getKeyType() == KeyType.Enter){
            model.getSelectedCommand().execute();
        }
        else if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') ||
                key.getKeyType() == KeyType.Escape) {
            model.getExitCommand().execute();
        }
    }

    public static void reset(){
        instance = null;
    }
}
