package spaceinvaders.model.menu;

import spaceinvaders.controller.menu.ControlsMenuController;
import spaceinvaders.model.GameModel;

public class ControlsCommand extends Command {
    GameModel gameModel;
    public ControlsCommand(GameModel gameModel){
        this.title = "Controls";
        this.gameModel = gameModel;
    }
    @Override
    public void execute(){
        ControlsMenuModel model = ControlsMenuModel.getInstance(gameModel);
        gameModel.setState(new MenuStateModel(model, ControlsMenuController.getInstance(model)));
    }
    public GameModel getGameModel() {
        return gameModel;
    }
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
