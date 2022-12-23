package spaceinvaders.model.menu;

import spaceinvaders.controller.menu.InfoMenuController;
import spaceinvaders.model.GameModel;

public class InfoCommand extends Command {
    GameModel gameModel;
    public InfoCommand(GameModel gameModel){
        this.title = "Info";
        this.gameModel = gameModel;
    }
    @Override
    public void execute(){
        InfoMenuModel model = InfoMenuModel.getInstance(gameModel);
        gameModel.setState(new MenuStateModel(model, InfoMenuController.getInstance(model)));
    }
    public GameModel getGameModel(){
        return gameModel;
    }

    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
}
