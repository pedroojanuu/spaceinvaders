package spaceinvaders.model.menu;

import spaceinvaders.model.ArenaModel;
import spaceinvaders.model.ArenaStateModel;
import spaceinvaders.model.GameModel;

public class StartCommand extends Command {
    protected ArenaModel arena;
    protected GameModel gameModel;
    public StartCommand(GameModel gameModel){
        this.title = "Start Game";
        this.arena = new ArenaModel(gameModel);
        this.gameModel = gameModel;
    }
    @Override
    public void execute(){
        gameModel.setState(new ArenaStateModel(arena));
    }
    public void restartArena(){
        this.arena = new ArenaModel(gameModel);
    }
    public ArenaModel getArena(){
        return arena;
    }
    public void setLevel(int level) {
        arena = new ArenaModel(gameModel, level);
    }
    public GameModel getGameModel(){
        return gameModel;
    }
}
