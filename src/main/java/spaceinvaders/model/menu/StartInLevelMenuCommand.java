package spaceinvaders.model.menu;

import spaceinvaders.controller.menu.StartInLevelMenuController;
import spaceinvaders.model.GameModel;

public class StartInLevelMenuCommand extends Command {
    GameModel gameModel;
    StartCommand startCommand;
    public StartInLevelMenuCommand(GameModel gameModel, StartCommand startCommand){
        this.title = "Start In Level";
        this.startCommand = startCommand;
        this.gameModel = gameModel;
    }
    @Override
    public void execute(){
        StartInLevelMenuModel model = StartInLevelMenuModel.getInstance(gameModel, startCommand);
        gameModel.setState(new MenuStateModel(model, StartInLevelMenuController.getInstance(model)));
    }
    public GameModel getGameModel() {
        return gameModel;
    }
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    public StartCommand getStartCommand() {
        return startCommand;
    }
}
