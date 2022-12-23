package spaceinvaders.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.menu.StartInLevelCommand;
import spaceinvaders.model.menu.StartInLevelMenuModel;

public class StartInLevelMenuController implements Controller {
    private StartInLevelMenuModel model;
    private static StartInLevelMenuController instance = null;
    private String level;

    private StartInLevelMenuController(StartInLevelMenuModel model) {
        this.model = model;
    }

    public static StartInLevelMenuController getInstance(StartInLevelMenuModel model) {
        if (instance == null) {
            instance = new StartInLevelMenuController(model);
        }
        instance.level = "";
        return instance;
    }

    public static StartInLevelMenuController getInstance() {
        return instance;
    }

    @Override
    public void processKey(KeyStroke key) {
        if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') ||
                key.getKeyType() == KeyType.Escape) {
            model.getExitCommand().execute();
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() >= '0' && key.getCharacter() <= '9') {
            level += key.getCharacter();
            model.setLevel(level);
        }
        if (key.getKeyType() == KeyType.Enter && !"".equals(level)) {
            new StartInLevelCommand(model.getStartCommand(), Integer.parseInt(level)).execute();
            model.resetLevel();
        }
        if(key.getKeyType() == KeyType.Backspace && !"".equals(level)){
            level = level.substring(0, level.length() - 1);
            model.setLevel(level);
        }
    }

    public String getLevel(){
        return level;
    }

    public static void reset() {
        instance = null;
    }
    public void setModel(StartInLevelMenuModel model){
        this.model = model;
    }
}
