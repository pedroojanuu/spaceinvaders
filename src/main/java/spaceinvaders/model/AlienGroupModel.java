package spaceinvaders.model;

import spaceinvaders.view.AlienGroupViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AlienGroupModel extends ElementModel {
    private ArenaModel arena;
    private List<AlienModel> aliens;
    public AlienGroupModel(ArenaModel arena) {
        super(new PositionModel(0, 0));
        aliens = new ArrayList<AlienModel>();
        viewer = new AlienGroupViewer(this);
        createAliens();
        this.arena = arena;
    }

    private void createAliens() {
        for (int j = 0; j < 10; j++) {
            int x = 3 + 5 * j;
            AlienModel a = new AlienModel(new PositionModel(x, 3), "*", "#08F121");
            AlienModel b = new AlienModel(new PositionModel(x, 5), "/", "#0876F1");
            AlienModel c = new AlienModel(new PositionModel(x, 7), "-", "#DCFF00");
            AlienModel d = new AlienModel(new PositionModel(x, 9), ".", "#FF0000");
            AlienModel e = new AlienModel(new PositionModel(x, 11), ".", "#FFFFFF");
            aliens.add(a); aliens.add(b); aliens.add(c); aliens.add(d); aliens.add(e);
        }
    }
    public void addAlien(AlienModel alien) {
        aliens.add(alien);
    }
    public List<AlienModel> getAliens() {
        return aliens;
    }
    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public int getWidth() {
        return 0;
    }
    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public boolean isTangible() {
        return false;
    }
    @Override
    public void damage() {}
    @Override
    public boolean canIMove(boolean goingLeft) {
        for (AlienModel alien : aliens) {
            if (!alien.canIMove(goingLeft)) return false;
        }
        return true;
    }
    @Override
    public void move(int direction) {
        switch (direction) {
            case 0: // left
                this.position.setX(this.position.getX()-1);
                break;
            case 1: // right
                this.position.setX(this.position.getX()+1);
                break;
            case 2: // down
                this.position.setY(this.position.getY()+1);
                break;
        }
        for (AlienModel alien : aliens) alien.move(direction);
    }
    public void fire(float level) {
        if(aliens.size() > 0) {
            for(int i = 0; i < 3; i++) {
                int random = ThreadLocalRandom.current().nextInt(0, 2 * aliens.size());
                if (random < aliens.size()) aliens.get(random).fire(level);
            }
        }
    }
    @Override
    public boolean collideWith(ShotModel shot) {
        for (AlienModel alien : aliens) {
            if (alien.collideWith(shot)) {
                if ("*".equals(alien.getSymbol())){
                    arena.addScore(40);
                }
                else if ("/".equals(alien.getSymbol())){
                    arena.addScore(30);
                }
                else if ("-".equals(alien.getSymbol())){
                    arena.addScore(20);
                }
                else if (".".equals(alien.getSymbol())){
                    arena.addScore(10);
                }
                alien.damage();
                aliens.remove(alien);
                return true;
            }
        }
        return false;
    }
}
