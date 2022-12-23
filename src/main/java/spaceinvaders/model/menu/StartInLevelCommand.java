package spaceinvaders.model.menu;

public class StartInLevelCommand extends Command {
    private StartCommand startCommand;
    public StartInLevelCommand(StartCommand startCommand, int level){
        this.title = "Start Game In Level";
        this.startCommand = startCommand;
        this.startCommand.setLevel(level);
    }
    @Override
    public void execute(){
        startCommand.execute();
    }
    public StartCommand getStartCommand() {
        return startCommand;
    }
}
