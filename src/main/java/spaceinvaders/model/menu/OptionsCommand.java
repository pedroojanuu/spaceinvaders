package spaceinvaders.model.menu;

import spaceinvaders.controller.menu.OptionsMenuController;
import spaceinvaders.model.GameModel;

public class OptionsCommand extends Command {
    GameModel gameModel;
    public OptionsCommand(GameModel gameModel){
        this.title = "Options";
        this.gameModel = gameModel;
    }
    @Override
    public void execute(){
        OptionsMenuModel model = OptionsMenuModel.getInstance(gameModel);
        gameModel.setState(new MenuStateModel(model, OptionsMenuController.getInstance(model)));
    }

    public GameModel getGameModel() {
        return gameModel;
    }
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
