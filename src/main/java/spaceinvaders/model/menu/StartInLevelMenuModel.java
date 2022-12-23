package spaceinvaders.model.menu;

import spaceinvaders.model.GameModel;
import spaceinvaders.view.menu.StartInLevelMenuViewer;

public class StartInLevelMenuModel extends MenuModel {
    private static StartInLevelMenuModel instance = null;
    private GameModel gameModel;
    private Command exitCommand;
    private String level = "";
    private StartCommand startCommand;
    private StartInLevelMenuModel(GameModel gameModel, StartCommand startCommand) {
        this.gameModel = gameModel;
        this.startCommand = startCommand;
        this.viewer = StartInLevelMenuViewer.getInstance(this);
        exitCommand = new ExitToMenuCommand(gameModel);
    }
    public static StartInLevelMenuModel getInstance(GameModel gameModel, StartCommand startCommand) {
        if(instance == null){
            instance = new StartInLevelMenuModel(gameModel, startCommand);
        }
        return instance;
    }
    public static StartInLevelMenuModel getInstance() {
        return instance;
    }
    public Command getExitCommand(){
        return exitCommand;
    }
    public void setExitCommand(Command exitCommand){
        this.exitCommand = exitCommand;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getLevel() {
        return this.level;
    }
    public GameModel getGameModel() {
        return this.gameModel;
    }
    public StartCommand getStartCommand() {
        return startCommand;
    }
    public void resetLevel() {
        this.level = "";
    }
    public static void reset() {
        instance = null;
    }
}
