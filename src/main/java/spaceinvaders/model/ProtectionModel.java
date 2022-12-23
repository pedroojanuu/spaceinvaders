package spaceinvaders.model;

import spaceinvaders.view.ProtectionViewer;

public class ProtectionModel extends ElementModel {
    private int life;
    private int height = 2;
    private int width = 4;

    public ProtectionModel(PositionModel position, int life) {
        super(position);
        this.life = life;
        this.viewer = new ProtectionViewer(this);
    }

    @Override
    public int getWidth() { return width; }

    @Override
    public int getHeight() { return height; }

    public int getLife() { return life; }
    @Override
    public ProtectionViewer getViewer() { return (ProtectionViewer) viewer; }

    @Override
    public void damage() {
        life--;
    }

    @Override
    public boolean isAlive() {
        return life > 0;
    }
    public void kill() {
        life = 0;
    }
}
