package spaceinvaders.model.menu;

import spaceinvaders.controller.menu.MainMenuController;
import spaceinvaders.model.GameModel;

public class ExitToMenuCommand extends Command {
    GameModel gameModel;
    public ExitToMenuCommand(GameModel gameModel){
        this.title = "Exit to Menu";
        this.gameModel = gameModel;
    }
    @Override
    public void execute(){
        MainMenuModel model = MainMenuModel.getInstance(gameModel);
        gameModel.setState(new MenuStateModel(model, MainMenuController.getInstance(model)));
    }
    public GameModel getGameModel(){
        return gameModel;
    }
    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
}
