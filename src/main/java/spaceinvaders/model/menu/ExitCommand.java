package spaceinvaders.model.menu;

public class ExitCommand extends Command {
    @Override
    public void execute(){
        System.exit(0);
    }
    @Override
    public String getTitle(){
        return "Exit";
    }
}
