package spaceinvaders.model.menu;

public abstract class Command {
    protected String title;
    public abstract void execute();
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}
