package spaceinvaders.model;

import spaceinvaders.view.AlienViewer;

public class AlienModel extends ElementModel {
    boolean alive = true;
    private String symbol;
    private String color;

    public AlienModel(PositionModel position, String symbol, String color) {
        super(position);
        this.symbol = symbol;
        this.color = color;
        this.viewer = new AlienViewer(this);
    }
    @Override
    public void damage() {
        alive = false;
    }
    @Override
    public boolean isAlive() {
        return alive;
    }
    public String getSymbol(){
        return symbol;
    }
    public String getColor() {
        return color;
    }
    public void fire(float level) {
        AlienShotModel shot = new AlienShotModel(new PositionModel(getX(), getY() + 1), level);
        notifyObservers(shot);
    }
    @Override
    public boolean collideWith(ShotModel shot){
        return !(shot instanceof AlienShotModel) && super.collideWith(shot);
    }
}
