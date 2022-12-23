package spaceinvaders.model;

public class PositionModel {
    private int x;
    private int y;

    public PositionModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PositionModel(PositionModel position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
