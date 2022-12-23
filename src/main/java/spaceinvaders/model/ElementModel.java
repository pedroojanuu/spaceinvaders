package spaceinvaders.model;

import spaceinvaders.view.ElementViewer;

import java.util.HashSet;

public abstract class ElementModel implements ShotSubjectModel {
    protected PositionModel position;
    protected ElementViewer viewer;

    public ElementModel(PositionModel position) {
        this.position = position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public PositionModel getPosition() {
        return position;
    }

    public int getWidth() { return 1; }

    public int getHeight() { return 1; }

    public abstract void damage();

    public abstract boolean isAlive();
    public ElementViewer getViewer() {
        return viewer;
    }
    public void setViewer(ElementViewer viewer) {
        this.viewer = viewer;
    }
    public boolean isTangible() {
        return true;
    }
    public boolean canIMove(boolean goingLeft) {
        if (goingLeft) {
            return this.getX() > 0;
        } else {
            return this.getX() < 49;
        }
    }
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
    }
    @Override
    public void addObserver(ShotObserverModel observer){
        observers.add(observer);
    }
    @Override
    public void removeObserver(ShotObserverModel observer){
        observers.remove(observer);
    }
    public void clearObservers(){
        observers.clear();
    }
    @Override
    public void notifyObservers(ShotModel shot){
        for(ShotObserverModel observer : observers){
            observer.update(shot);
        }
    }
    public HashSet<ShotObserverModel> getObservers() {
        return observers;
    }
    public boolean collideWith(ShotModel shot){
        return isTangible() &&
                (shot.getX() >= position.getX() &&
                shot.getX() <= position.getX() + getWidth() - 1 &&
                shot.getY() >= position.getY() &&
                shot.getY() <= position.getY() + getHeight() - 1);
    }
}
