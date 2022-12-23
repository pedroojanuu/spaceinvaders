package spaceinvaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.*;
import spaceinvaders.model.menu.Command;
import spaceinvaders.view.ArenaViewer;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static com.google.common.base.Charsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ArenaModelTest {
    private GameModel gameModel;
    private ArenaModel arena;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        arena = new ArenaModel(gameModel);
    }

    @Test
    public void addedObservers(){
        assertTrue(arena.getShip().getObservers().contains(arena));
        assertTrue(arena.getAlienGroup().getObservers().contains(arena));
    }

    @Test
    public void addedObservers2(){
        arena.incrementLevel();
        assertTrue(arena.getShip().getObservers().contains(arena));
        assertTrue(arena.getAlienGroup().getObservers().contains(arena));
    }

    @Test
    public void getLevelTest() {
        arena.getLevel();
        assertEquals(arena.getLevel(), 1);
    }

    @Test
    public void getLevelTest2() {
        arena = new ArenaModel(gameModel, 42);
        arena.getLevel();
        assertEquals(arena.getLevel(), 42);
    }

    @Test
    public void getExitCommandTest() {
        Command exitCommand = Mockito.mock(Command.class);
        arena.setExitCommand(exitCommand);
        assertEquals(arena.getExitCommand(), exitCommand);
    }

    @Test
    public void updateTest() {
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        assertEquals(arena.getShots().get(0), shotMock);
    }

    @Test
    public void runTest() {
        arena.setTargetTime(0);
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        arena.setAliens(alienGroupMock);
        arena.run();
        assertEquals(arena.getHasRan(), true);
        Mockito.verify(alienGroupMock, Mockito.times(1)).fire(Mockito.anyFloat());
    }

    @Test
    public void runTest1() {
        arena.incrementLevel();
        arena.setTargetTime(0);
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        List<AlienModel> alienList = new ArrayList<>();
        alienList.add(new AlienModel(new PositionModel(0, 0), "P", "FFFFFF"));
        Mockito.when(alienGroupMock.getAliens()).thenReturn(alienList);
        arena.setAliens(alienGroupMock);
        arena.run();
        assertEquals(arena.getHasRan(), true);
        Mockito.verify(alienGroupMock, Mockito.times(1)).fire(1);
        assertEquals(arena.getTargetTime(), arena.getElapsedTime() + 1500);
        assertTrue(arena.getElapsedTime() < System.currentTimeMillis());
    }

    @Test
    public void runTest1_2() {
        arena.setTargetTime(0);
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        List<AlienModel> alienList = new ArrayList<>();
        alienList.add(new AlienModel(new PositionModel(0, 0), "P", "FFFFFF"));
        Mockito.when(alienGroupMock.getAliens()).thenReturn(alienList);
        arena.setAliens(alienGroupMock);
        arena.run();
        assertEquals(arena.getHasRan(), true);
        Mockito.verify(alienGroupMock, Mockito.atLeast(1)).move(Mockito.anyInt());
    }

    @Test
    public void runTest2() {
        List aliens = new ArrayList();
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.getAliens()).thenReturn(aliens);
        arena.setAliens(alienGroupMock);
        arena.run();
        assertEquals(arena.getHasRan(), true);
        assertEquals(arena.getYouWon(), true);
    }

    @Test
    public void runTest3() {
        arena.setYouWon(true);
        arena.setYouWonTime(0);
        arena.run();
        assertEquals(arena.getHasRan(), true);
        assertEquals(arena.getYouWon(), false);
    }

    @Test
    public void runTest4() {
        ProtectionModel protectionMock = Mockito.mock(ProtectionModel.class);
        arena.addProtection(protectionMock);
        List aliens = new ArrayList();
        aliens.add(new AlienModel(new PositionModel(0, 100), "P", "FFFFFF"));
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.getAliens()).thenReturn(aliens);
        arena.setAliens(alienGroupMock);
        arena.run();
        Mockito.verify(protectionMock, Mockito.times(1)).kill();
    }

    @Test
    public void runTest5() throws LineUnavailableException, IOException {
        ShipModel shipMock = Mockito.mock(ShipModel.class);
        Mockito.when(shipMock.isAlive()).thenReturn(false);
        arena.setShip(shipMock);
        Clip clipMock = Mockito.mock(Clip.class);
        arena.setClip(clipMock);
        arena.run();
        Assertions.assertTrue(arena.isLost());
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
        Mockito.verify(clipMock, Mockito.times(1)).open(Mockito.any());
        Mockito.verify(clipMock, Mockito.times(1)).start();
        Mockito.verify(clipMock, Mockito.times(1)).close();
    }

    @Test
    public void runTest6() {
        ProtectionModel protectionMock = Mockito.mock(ProtectionModel.class);
        Mockito.when(protectionMock.getY()).thenReturn(19);
        arena.addProtection(protectionMock);
        List aliens = new ArrayList();
        aliens.add(new AlienModel(new PositionModel(10, 19), "P", "FFFFFF"));
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.getAliens()).thenReturn(aliens);
        arena.setAliens(alienGroupMock);
        arena.run();
        Mockito.verify(protectionMock, Mockito.times(1)).kill();
    }

    @Test
    public void runTest6_2() {
        ProtectionModel protectionMock = Mockito.mock(ProtectionModel.class);
        Mockito.when(protectionMock.getY()).thenReturn(19);
        arena.addProtection(protectionMock);
        List aliens = new ArrayList();
        aliens.add(new AlienModel(new PositionModel(10, 25), "P", "FFFFFF"));
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.getAliens()).thenReturn(aliens);
        arena.setAliens(alienGroupMock);
        arena.run();
        Mockito.verify(protectionMock, Mockito.times(1)).kill();
    }

    @Test
    public void runTest7() {
        ShipModel shipMock = Mockito.mock(ShipModel.class);
        Mockito.when(shipMock.isAlive()).thenReturn(false);
        arena.setShip(shipMock);
        arena.run();
        Assertions.assertTrue(arena.isLost());
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void runTestsCheckDead(){
        ElementModel elementMock = Mockito.mock(ElementModel.class);
        Mockito.when(elementMock.isAlive()).thenReturn(false);
        int sizeBeforeElement = arena.getElements().size();
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.addElement(elementMock);
        assertEquals(arena.getElements().size(), sizeBeforeElement + 1);
        arena.run();
        assertEquals(arena.getElements().size(), sizeBeforeElement);

    }

    @Test
    public void runTestsCheckShot(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(-5);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.run();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void moveAliensTest() {
        arena.setLastAlienDirection(0);
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.canIMove(true)).thenReturn(true);
        arena.setAliens(alienGroupMock);
        arena.moveAliens();
        Mockito.verify(alienGroupMock, Mockito.times(1)).move(0);
    }

    @Test
    public void moveAliensTest2() {
        arena.setLastAlienDirection(0);
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.canIMove(true)).thenReturn(false);
        arena.setAliens(alienGroupMock);
        arena.moveAliens();
        Mockito.verify(alienGroupMock, Mockito.times(1)).move(2);
        Mockito.verify(alienGroupMock, Mockito.times(1)).move(1);
        assertEquals(arena.getLastAlienDirection(), 1);
    }

    @Test
    public void moveAliensTest3() {
        arena.setLastAlienDirection(1);
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.canIMove(false)).thenReturn(true);
        arena.setAliens(alienGroupMock);
        arena.moveAliens();
        Mockito.verify(alienGroupMock, Mockito.times(1)).move(1);
    }

    @Test
    public void moveAliensTest4() {
        arena.setLastAlienDirection(1);
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        Mockito.when(alienGroupMock.canIMove(false)).thenReturn(false);
        arena.setAliens(alienGroupMock);
        arena.moveAliens();
        Mockito.verify(alienGroupMock, Mockito.times(1)).move(2);
        Mockito.verify(alienGroupMock, Mockito.times(1)).move(0);
        assertEquals(arena.getLastAlienDirection(), 0);
    }

    @Test
    public void incrementLevelTest() {
        arena.incrementLevel();
        assertEquals(arena.getLevel(), 2);
        assertEquals(arena.getElements().size(), 5);
        assertEquals(arena.getProtections().size(), 3);
        for(ProtectionModel protection : arena.getProtections()) {
            assertEquals(protection.getLife(), 29);
        }
    }

    @Test
    public void incrementLevelTest3() {
        arena.getAliens().clear();
        arena.run();
        assertEquals(arena.getLevel(), 2);
        assertEquals(arena.getElements().size(), 5);
        assertEquals(arena.getProtections().size(), 3);
        for(ProtectionModel protection : arena.getProtections()) {
            assertEquals(protection.getLife(), 29);
        }
    }

    @Test
    public void levelConstructorTest() {
        arena = new ArenaModel(gameModel, 16);
        assertEquals(arena.getLevel(), 16);
        assertEquals(arena.getElements().size(), 5);
        assertEquals(arena.getProtections().size(), 3);
        for(ProtectionModel protection : arena.getProtections()) {
            assertEquals(protection.getLife(), 15);
        }
    }

    @Test
    public void levelConstructorTest2() {
        arena = new ArenaModel(gameModel, 42);
        assertEquals(arena.getLevel(), 42);
        assertEquals(arena.getElements().size(), 5);
        assertEquals(arena.getProtections().size(), 3);
        for(ProtectionModel protection : arena.getProtections()) {
            assertEquals(protection.getLife(), 5);
        }
    }

    @Test
    public void checkDead(){
        ElementModel elementMock = Mockito.mock(ElementModel.class);
        Mockito.when(elementMock.isAlive()).thenReturn(false);
        int sizeBeforeElement = arena.getElements().size();
        arena.addElement(elementMock);
        assertEquals(arena.getElements().size(), sizeBeforeElement + 1);
        arena.checkDead();
        assertEquals(arena.getElements().size(), sizeBeforeElement);
    }

    @Test
    public void checkDead2(){
        ElementModel elementMock = Mockito.mock(ElementModel.class);
        Mockito.when(elementMock.isAlive()).thenReturn(true);
        int sizeBeforeElement = arena.getElements().size();
        arena.addElement(elementMock);
        arena.checkDead();
        assertEquals(arena.getElements().size(), sizeBeforeElement + 1);
    }

    @Test
    public void checkShotTest() {
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(-5);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void checkShotTest2() {
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(10);
        Mockito.when(shotMock.getY()).thenReturn(-5);
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void checkShotTest3() {
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(100);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void checkShotTest4() {
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(10);
        Mockito.when(shotMock.getY()).thenReturn(100);
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void checkShotTest5() {
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getY()).thenReturn(10);
        Mockito.when(shotMock.getX()).thenReturn(10);
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 1);
    }

    @Test
    public void runTestsCheckShot6(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(0);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 1);
    }

    @Test
    public void runTestsCheckShot7(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(-1);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void runTestsCheckShot8(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(50);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 1);
    }

    @Test
    public void runTestsCheckShot9(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(51);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 1);
    }

    @Test
    public void runTestsCheckShot14(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(52);
        Mockito.when(shotMock.getY()).thenReturn(10);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void runTestsCheckShot10(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(10);
        Mockito.when(shotMock.getY()).thenReturn(0);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 1);
    }

    @Test
    public void runTestsCheckShot11(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(10);
        Mockito.when(shotMock.getY()).thenReturn(-1);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void runTestsCheckShot12(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(10);
        Mockito.when(shotMock.getY()).thenReturn(26);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 1);
    }

    @Test
    public void runTestsCheckShot13(){
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        Mockito.when(shotMock.getX()).thenReturn(10);
        Mockito.when(shotMock.getY()).thenReturn(27);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));
        arena.update(shotMock);
        assertEquals(arena.getShots().size(), 1);
        arena.checkShot();
        assertEquals(arena.getShots().size(), 0);
    }

    @Test
    public void checkCollisionsTest() {
        arena.clearElements();
        ShotModel shotMock = Mockito.mock(ShotModel.class);
        ElementModel elementMock = Mockito.mock(ElementModel.class);
        Mockito.when(elementMock.collideWith(shotMock)).thenReturn(true);
        arena.update(shotMock);
        arena.addElement(elementMock);
        assertEquals(arena.getShots().size(), 1);
        assertEquals(arena.getElements().size(), 1);
        arena.checkCollisions();
        assertEquals(arena.getShots().size(), 0);
        Mockito.verify(elementMock, Mockito.times(1)).damage();
    }

    @Test
    public void getAliensTest() {
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        List<AlienModel> aliens = new ArrayList<>();
        Mockito.when(alienGroupMock.getAliens()).thenReturn(aliens);
        arena.setAliens(alienGroupMock);
        assertEquals(arena.getAliens(), aliens);
    }

    @Test
    public void getAlienGroupTest() {
        AlienGroupModel alienGroupMock = Mockito.mock(AlienGroupModel.class);
        arena.setAliens(alienGroupMock);
        assertEquals(arena.getAlienGroup(), alienGroupMock);
    }

    @Test
    public void getScoreTest() {
        assertEquals(arena.getScore(), 0);
    }

    @Test
    public void getScoreTest2() {
        arena.addScore(10);
        assertEquals(arena.getScore(), 10);
    }

    @Test
    public void isLostTest(){
        Assertions.assertTrue(arena.isLost());
    }

    @Test
    public void isLostTest2(){
        arena.run();
        assertFalse(arena.isLost());
    }

    @Test
    public void isLost3() throws FileNotFoundException {
        arena.run();
        ShipModel shipMock = Mockito.mock(ShipModel.class);
        Mockito.when(shipMock.isAlive()).thenReturn(false);
        arena.setShip(shipMock);
        arena.setAliens(Mockito.mock(AlienGroupModel.class));

        PlayerScore.setPath("resources/test_highscores.csv");
        try {
            PrintWriter writer = new PrintWriter("resources/test_highscores.csv", UTF_8.name());
            writer.print("");
        } catch (Exception e) {
            throw new RuntimeException();
        }

        TreeSet<PlayerScore> scores = PlayerScore.loadScores();

        arena.addScore(42);
        scores.add(new PlayerScore(System.getProperty("user.name"), 42));
        while (scores.size() > 10) scores.pollLast();

        Assertions.assertTrue(arena.isLost());
        assertEquals(scores, PlayerScore.loadScores());

        assertEquals(scores, PlayerScore.loadScores());

        PlayerScore.setPath("resources/highscores.csv");
    }

    @Test
    public void isLost4() {
        ShipModel shipMock = Mockito.mock(ShipModel.class);
        Mockito.when(shipMock.isAlive()).thenReturn(true);
        Mockito.when(shipMock.getY()).thenReturn(100);
        arena.setShip(shipMock);

        arena.run();

        Assertions.assertTrue(arena.isLost());
    }

    @Test
    public void isLost5() {
        ShipModel shipMock = Mockito.mock(ShipModel.class);
        Mockito.when(shipMock.isAlive()).thenReturn(true);
        Mockito.when(shipMock.getY()).thenReturn(100);
        arena.setShip(shipMock);
        arena.getAliens().add(new AlienModel(new PositionModel(0, 120), "P", "FFFFFF"));

        arena.run();

        Assertions.assertTrue(arena.isLost());
    }

    @Test
    public void isLost6() {
        ShipModel shipMock = Mockito.mock(ShipModel.class);
        Mockito.when(shipMock.isAlive()).thenReturn(true);
        Mockito.when(shipMock.getUpperBound()).thenReturn(1000);
        arena.setShip(shipMock);
        arena.getAliens().clear();
        arena.getAliens().add(new AlienModel(new PositionModel(10, 1000), "P", "FFFFFF"));

        arena.run();

        Assertions.assertTrue(arena.isLost());
    }

    @Test
    public void dieSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Clip clipMock = Mockito.mock(Clip.class);
        arena.setClip(clipMock);
        arena.dieSound();
        Mockito.verify(clipMock, Mockito.times(1)).open(Mockito.any());
        Mockito.verify(clipMock, Mockito.times(1)).start();
        Mockito.verify(clipMock, Mockito.times(1)).close();
    }

    @Test
    public void dieSound2(){
        Clip clipMock = Mockito.mock(Clip.class);
        arena.setClip(clipMock);
        arena.dieSound();
        Mockito.verify(clipMock, Mockito.times(1)).start();
    }

    @Test
    public void getShipTest(){
        ShipModel shipMock = Mockito.mock(ShipModel.class);
        arena.setShip(shipMock);
        assertEquals(arena.getShip(), shipMock);
    }

    @Test
    public void getViewerTest(){
        ArenaViewer viewerMock = Mockito.mock(ArenaViewer.class);
        arena.setViewer(viewerMock);
        assertEquals(arena.getViewer(), viewerMock);
    }

    @Test
    public void update(){
        ShotModel shot = Mockito.mock(ShotModel.class);
        assertEquals(0, arena.getShots().size());
        arena.update(shot);
        assertEquals(1, arena.getShots().size());
    }

    @Test
    public void incrementLevelTest2(){
        arena.incrementLevel();
        assertEquals(2,arena.getLevel());
    }

    @Test
    public void checkDeadTest(){
        ElementModel element = Mockito.mock(ElementModel.class);
        arena.getElements().add(element);
        Assertions.assertEquals(6, arena.getElements().size());
        element.damage();
        arena.checkDead();
        Assertions.assertEquals(5, arena.getElements().size());
    }

    @Test
    public void checkShotTest6(){
        ShotModel shot = Mockito.mock(ShotModel.class);
        arena.getShots().add(shot);
        arena.checkShot();
        Mockito.verify(shot, Mockito.times(1)).update();
    }

    @Test
    public void checkCollisions(){
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        ElementModel element = Mockito.mock(ElementModel.class);
        Mockito.when(element.collideWith(any())).thenReturn(true);
        arena.getShots().add(shotModel);
        arena.getElements().add(element);
        arena.checkCollisions();
        Mockito.verify(element, Mockito.atLeast(1)).collideWith(any());
        Mockito.verify(element, Mockito.times(1)).damage();
        assertEquals(0, arena.getShots().size());
    }

    @Test
    public void addScoreTest(){
        arena.addScore(10);
        assertEquals(10, arena.getScore());
    }

    @Test
    public void checkScoreTest() throws FileNotFoundException {
        PlayerScore.setPath("resources/test_highscores.csv");
        PrintWriter writer = new PrintWriter("resources/test_highscores.csv");
        writer.print("");

        TreeSet<PlayerScore> scores = PlayerScore.loadScores();

        arena.addScore(42);
        scores.add(new PlayerScore(System.getProperty("user.name"), 42));
        while (scores.size() > 10) scores.pollLast();
        assertTrue(arena.checkScore());

        assertEquals(scores, PlayerScore.loadScores());

        PlayerScore.setPath("resources/highscores.csv");
    }

    @Test
    public void checkScoreTest2() throws FileNotFoundException {
        PlayerScore.setPath("resources/test_highscores.csv");
        PrintWriter writer = new PrintWriter("resources/test_highscores.csv");
        writer.print("");

        TreeSet<PlayerScore> scores = PlayerScore.loadScores();
        arena.addScore(0);
        assertTrue(arena.checkScore());

        assertEquals(scores, PlayerScore.loadScores());

        PlayerScore.setPath("resources/highscores.csv");
    }

    @Test
    public void checkScoreTest3() throws FileNotFoundException {
        PlayerScore.setPath("resources/test_highscores.csv");
        PrintWriter writer = new PrintWriter("resources/test_highscores.csv");
        writer.print("");

        TreeSet<PlayerScore> scores = PlayerScore.loadScores();
        arena.addScore(-1);
        assertTrue(arena.checkScore());

        assertEquals(scores, PlayerScore.loadScores());

        PlayerScore.setPath("resources/highscores.csv");
    }

    @Test
    public void getHasRanTest(){
        assertFalse(arena.getHasRan());
    }

    @Test
    public void getHasRanTest2(){
        arena.run();
        assertTrue(arena.getHasRan());
    }

    @Test
    public void getAliensTest2(){
        assertEquals(arena.getAliens(), arena.getAlienGroup().getAliens());
    }

}
