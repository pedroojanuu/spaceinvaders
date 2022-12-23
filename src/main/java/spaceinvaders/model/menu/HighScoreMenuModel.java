package spaceinvaders.model.menu;

import spaceinvaders.model.GameModel;
import spaceinvaders.view.menu.HighScoreMenuViewer;

public class HighScoreMenuModel extends MenuModel{
    private static HighScoreMenuModel instance = null;
    private Command exitCommand;
    private HighScoreMenuModel(GameModel gameModel){
        this.viewer = HighScoreMenuViewer.getInstance(this);
        exitCommand = new ExitToMenuCommand(gameModel);
    }
    public static HighScoreMenuModel getInstance(GameModel gameModel){
        if(instance == null){
            instance = new HighScoreMenuModel(gameModel);
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }
    public Command getExitCommand(){
        return exitCommand;
    }
    public void setExitCommand(Command exitCommand){
        this.exitCommand = exitCommand;
    }
}

