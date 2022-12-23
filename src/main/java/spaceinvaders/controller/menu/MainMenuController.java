package spaceinvaders.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.menu.MainMenuModel;

import static com.googlecode.lanterna.input.KeyType.ArrowDown;
import static com.googlecode.lanterna.input.KeyType.ArrowUp;

public class MainMenuController implements Controller {
    protected MainMenuModel model;
    protected static MainMenuController instance = null;
    protected MainMenuController(MainMenuModel model){
        this.model = model;
    }
    public static MainMenuController getInstance(MainMenuModel model){
        if(instance == null){
            instance = new MainMenuController(model);
        }
        return instance;
    }
    public static MainMenuController getInstance(){
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
    }
    public static void reset(){
        instance = null;
    }
}
