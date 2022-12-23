package spaceinvaders.model;

import spaceinvaders.PlayerScore;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.ExitToMenuCommand;
import spaceinvaders.view.ArenaViewer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static java.lang.Math.max;

public class ArenaModel implements ShotObserverModel {
    private ArenaViewer viewer;
    private GameModel gameModel;
    private ShipModel ship;
    private AlienGroupModel aliens;
    private List<ElementModel> elements;
    private List<ShotModel> shots;
    private List<ProtectionModel> protections;
    private long startTime;
    private long elapsedTime;
    private long targetTime;
    private int lastAlienDirection;
    private int level;
    private Command exitCommand;
    private boolean youWon;
    private long youWonTime;
    private int score = 0;
    private boolean hasRan = false;
    private Clip clip = null;

    private TreeSet<PlayerScore> scores;

    public ArenaModel(GameModel gameModel){
        this.gameModel = gameModel;
        this.exitCommand = new ExitToMenuCommand(gameModel);
        viewer = new ArenaViewer(this);
        ship = new ShipModel();
        elements = new ArrayList<>();
        shots = new ArrayList<>();
        aliens = new AlienGroupModel(this);
        aliens.addObserver(this);
        startTime = System.currentTimeMillis();
        youWon = false;
        elapsedTime = 0;
        lastAlienDirection = 0;
        level = 1;
        elements.add(aliens);
        elements.add(ship);
        this.protections = new ArrayList<>();
        ProtectionModel p1 = new ProtectionModel(new PositionModel(8, 19), 30);
        elements.add(p1);
        protections.add(p1);
        ProtectionModel p2 = new ProtectionModel(new PositionModel(23, 19), 30);
        elements.add(p2);
        protections.add(p2);
        ProtectionModel p3 = new ProtectionModel(new PositionModel(38, 19), 30);
        elements.add(p3);
        protections.add(p3);
    }
    public ArenaModel(GameModel gameModel, int level) {
        this(gameModel);
        for (int i = 1; i < level; i++) incrementLevel();
    }
    public Command getExitCommand(){
        return exitCommand;
    }
    public void setExitCommand(Command exitCommand){
        this.exitCommand = exitCommand;
    }

    @Override
    public void update(ShotModel shot) {
        shots.add(shot);
    }

    public void run() {
        hasRan = true;
        elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= targetTime) {
            moveAliens();
            aliens.fire((float) (0.5*level));
            targetTime = elapsedTime + (3000 / level);
        }
        if(aliens.getAliens().size()==0){
            youWon = true;
            incrementLevel();
            youWonTime = elapsedTime + 2000;
        }
        if(youWon && elapsedTime >= youWonTime) {
            youWon = false;
        }
        for(AlienModel alien : aliens.getAliens()){
            for(ProtectionModel protection : protections){
                if(alien.getPosition().getY() >= protection.getY()){
                    protection.kill();
                }
            }
        }
        checkDead();
        checkShot();
        checkCollisions();
        if(isLost()){
            new ExitToMenuCommand(gameModel).execute();
            dieSound();
        }
    }

    public void moveAliens() {
        if (lastAlienDirection == 0) {
            if (aliens.canIMove(true)) aliens.move(0);
            else if (!aliens.canIMove(true)) {
                aliens.move(2);
                aliens.move(1);
                lastAlienDirection = 1;
            }
        } else if (lastAlienDirection == 1) {
            if (aliens.canIMove(false)) aliens.move(1);
            else if (!aliens.canIMove(false)) {
                aliens.move(2);
                aliens.move(0);
                lastAlienDirection = 0;
            }
        }
    }
    public void incrementLevel() {
        this.level++;
        ship = new ShipModel();
        elements = new ArrayList<>();
        shots = new ArrayList<>();
        aliens = new AlienGroupModel(this);
        lastAlienDirection = 0;
        elements.add(aliens);
        elements.add(ship);
        this.protections = new ArrayList<>();
        ProtectionModel p1 = new ProtectionModel(new PositionModel(8, 19), max(31-level, 5));
        elements.add(p1);
        protections.add(p1);
        ProtectionModel p2 = new ProtectionModel(new PositionModel(23, 19), max(31-level, 5));
        elements.add(p2);
        protections.add(p2);
        ProtectionModel p3 = new ProtectionModel(new PositionModel(38, 19), max(31-level, 5));
        elements.add(p3);
        protections.add(p3);
    }

    public void checkDead() {
        List<ElementModel> dead = new ArrayList<>();
        for (ElementModel element : elements)
            if(!element.isAlive())
                dead.add(element);
        elements.removeAll(dead);
    }

    public void checkShot() {
        List<ShotModel> outOfScreen = new ArrayList<>();
        for (ShotModel shot : shots) {
            shot.update();
            if(shot.getX() < 0 || shot.getX() > 51 || shot.getY() < 0 || shot.getY() > 26) {
                outOfScreen.add(shot);
            }
        }
        shots.removeAll(outOfScreen);
    }

    public void checkCollisions() {
        List<ShotModel> collided = new ArrayList<>();
        for (ElementModel element : elements) {
            for (ShotModel shot : shots) {
                if (element.collideWith(shot)) {
                    element.damage();
                    collided.add(shot);
                }
            }
        }
        shots.removeAll(collided);
    }
    public List<ShotModel> getShots() {
        return shots;
    }

    public List<ElementModel> getElements() {
        return elements;
    }

    public List<AlienModel> getAliens() {
        return aliens.getAliens();
    }
    public AlienGroupModel getAlienGroup() {
        return aliens;
    }

    public ShipModel getShip() {
        return ship;
    }

    public ArenaViewer getViewer() {
        return viewer;
    }

    public int getScore(){
        return score;
    }
    public boolean getYouWon(){
        return youWon;
    }
    public int getLevel(){
        return level;
    }
    public void addScore(int points) {
        this.score += points;
    }

    public boolean isLost(){
        if(!hasRan) return true;
        if(!ship.isAlive()) return checkScore();
        for(AlienModel alien : aliens.getAliens())
            if(alien.getY() >= ship.getUpperBound()) return checkScore();
        return false;
    }

    public boolean checkScore() {
        if (score > 0) {
            scores = PlayerScore.loadScores();
            String name = System.getProperty("user.name");
            scores.add(new PlayerScore(name, score));
            PlayerScore.storeScores(scores);
        }
        return true;
    }

    public void dieSound() {
        File f = new File("resources/sound/die.wav");

        try {
            if(clip == null) clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f));
            clip.start();
            Thread.sleep(1000);
            clip.close();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public boolean getHasRan() {
        return hasRan;
    }

    public void setTargetTime(long i) {
        targetTime = i;
    }

    public void setAliens(AlienGroupModel alienGroup) {
        aliens = alienGroup;
    }

    public void setYouWon(boolean b) {
        youWon = b;
    }

    public void setYouWonTime(int i) {
        youWonTime = i;
    }

    public void addProtection(ProtectionModel protection) {
        protections.add(protection);
    }

    public void setShip(ShipModel ship) {
        this.ship = ship;
    }

    public void setLastAlienDirection(int i) {
        lastAlienDirection = i;
    }

    public int getLastAlienDirection() {
        return lastAlienDirection;
    }

    public List<ProtectionModel> getProtections() {
        return protections;
    }

    public void addElement(ElementModel element) {
        elements.add(element);
    }

    public void clearElements() {
        elements.clear();
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public void setViewer(ArenaViewer viewer) {
        this.viewer = viewer;
    }

    public long getTargetTime() {
        return targetTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
