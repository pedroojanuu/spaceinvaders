package spaceinvaders.model.menu;

import spaceinvaders.model.GameModel;
import spaceinvaders.view.menu.InfoMenuViewer;

public class InfoMenuModel extends MenuModel{
    private static InfoMenuModel instance = null;
    private Command exitCommand;
    private InfoMenuModel(GameModel gameModel){
        this.viewer = InfoMenuViewer.getInstance(this);
        exitCommand = new OptionsCommand(gameModel);
    }
    public static InfoMenuModel getInstance(GameModel gameModel){
        if(instance == null){
            instance = new InfoMenuModel(gameModel);
        }
        return instance;
    }
    public Command getExitCommand(){
        return exitCommand;
    }
    public void setExitCommand(Command exitCommand){
        this.exitCommand = exitCommand;
    }
    public static void reset(){
        instance = null;
    }
}
