package spaceinvaders.model.menu;

import spaceinvaders.model.GameModel;
import spaceinvaders.view.menu.MainMenuViewer;

import java.util.ArrayList;
import java.util.List;

public class MainMenuModel extends MenuModel {
    private static MainMenuModel instance = null;
    private GameModel gameModel;
    private List<Command> commands;
    private boolean continueEnabled;
    private int selectedCommand = 0;
    private StartCommand startCommand;
    private MainMenuModel(GameModel gameModel){
        this.startCommand = new StartCommand(gameModel);
        startCommand.setTitle("Continue Game");
        this.gameModel = gameModel;
        this.viewer = MainMenuViewer.getInstance(this);
        commands = new ArrayList<>();
        addCommands();
        continueEnabled = false;
    }
    public void addCommands(){
        Command s = new RestartCommand(startCommand);
        s.setTitle("Start Game");
        commands.add(s);
        commands.add(new StartInLevelMenuCommand(gameModel, startCommand));
        commands.add(new HighScoreCommand(gameModel));
        commands.add(new OptionsCommand(gameModel));
        commands.add(new ExitCommand());
    }
    public static MainMenuModel getInstance(GameModel gameModel){
        if(instance == null){
            instance = new MainMenuModel(gameModel);
        }
        if(!instance.getStartCommand().getArena().isLost() && !instance.isContinueEnabled()){
            instance.addContinueCommand();
        } else if(instance.isContinueEnabled() && instance.getStartCommand().getArena().isLost()){
            instance.removeContinueCommand();
        }

        return instance;
    }
    public void addContinueCommand(){
        commands.get(0).setTitle("Restart Game");
        commands.get(1).setTitle("Restart In Level");
        commands.add(0, startCommand);
        continueEnabled = true;
    }
    public void removeContinueCommand(){
        commands.get(1).setTitle("Start Game");
        commands.get(2).setTitle("Start In Level");
        startCommand.restartArena();
        if(isContinueEnabled())
            commands.remove(0);
        continueEnabled = false;
    }
    public List<Command> getCommands(){
        return commands;
    }
    public int getSelectedCommandInt(){
        return selectedCommand;
    }
    public Command getSelectedCommand(){
        return commands.get(selectedCommand);
    }
    public void upSelectedCommand(){
        if (selectedCommand > 0){
            selectedCommand--;
        } else {
            selectedCommand = commands.size() - 1;
        }
    }
    public void downSelectedCommand(){
        selectedCommand = (selectedCommand + 1) % commands.size();
    }
    public GameModel getGameModel(){
        return gameModel;
    }
    public boolean isContinueEnabled(){
        return continueEnabled;
    }
    public void setContinueEnabled(boolean continueEnabled){
        this.continueEnabled = continueEnabled;
    }
    public StartCommand getStartCommand(){
        return startCommand;
    }
    public void clearCommands(){
        commands.clear();
    }
    public void setStartCommand(StartCommand startCommand){
        this.startCommand = startCommand;
    }
    public static void reset(){
        instance = null;
    }
}
