package spaceinvaders.model;

import java.util.HashSet;

public interface ShotSubjectModel {
    HashSet<ShotObserverModel> observers = new java.util.HashSet<>();
    public void addObserver(ShotObserverModel observer);
    public void removeObserver(ShotObserverModel observer);
    public void notifyObservers(ShotModel shot);
}
