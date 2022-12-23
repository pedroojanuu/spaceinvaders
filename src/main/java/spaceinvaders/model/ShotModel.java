package spaceinvaders.model;

import spaceinvaders.view.ShotViewer;

public class ShotModel extends ElementModel {
    float floatY;
    private float speed;
    private boolean direction; // true = up, false = down
    private char character;

    public ShotModel(PositionModel position, float speed, boolean direction, char character) {
        super(position);
        this.speed = speed;
        this.direction = direction;
        this.character = character;
        this.floatY = position.getY();
        this.viewer = new ShotViewer(this);
    }
    @Override
    public PositionModel getPosition() {
        return position;
    }

    @Override
    public void damage() {}

    public void update() {
        if (direction)
            floatY -= speed;
        else
            floatY += speed;
        if (floatY % 1 == 0)
            position.setY((int) floatY);
    }
    static public boolean up = true;
    static public boolean down = false;
    @Override
    public boolean isAlive() {
        return false;
    }
    public float getSpeed() {
        return speed;
    }
    public char getCharacter() {
        return character;
    }
    @Override
    public boolean isTangible(){
        return false;
    }
}
