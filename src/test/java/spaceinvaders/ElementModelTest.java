package spaceinvaders;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.*;
import spaceinvaders.view.ElementViewer;

import static org.junit.jupiter.api.Assertions.*;

public class ElementModelTest {

    @Test
    public void getX() {
        ElementModel elementModel = new ProtectionModel(new PositionModel(1, 2), 3);
        assertEquals(1, elementModel.getX());
    }

    @Test
    public void getY() {
        ElementModel elementModel = new ProtectionModel(new PositionModel(1, 2), 3);
        assertEquals(2, elementModel.getY());
    }

    @Test
    public void getPosition() {
        ElementModel elementModel = new ProtectionModel(new PositionModel(1, 2), 3);
        assertEquals(1, elementModel.getPosition().getX());
        assertEquals(2, elementModel.getPosition().getY());
    }

    @Test
    public void getHeight() {
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        assertEquals(1, elementModel.getHeight());
    }

    @Test
    public void getWidth() {
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        assertEquals(1, elementModel.getWidth());
    }

    @Test
    public void getViewer() {
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ElementViewer elementViewer = Mockito.mock(ElementViewer.class);
        elementModel.setViewer(elementViewer);
        assertEquals(elementViewer, elementModel.getViewer());
    }

    @Test
    public void isTangible() {
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        assertEquals(true, elementModel.isTangible());
    }

    @Test
    public void canIMove() {
        ElementModel elementModel= new AlienModel(new PositionModel(0, 2), "P", "FFFFFF");
        assertFalse(elementModel.canIMove(true));
        assertTrue(elementModel.canIMove(false));
    }

    @Test
    public void canIMove2() {
        ElementModel elementModel= new AlienModel(new PositionModel(-10, 2), "P", "FFFFFF");
        assertFalse(elementModel.canIMove(true));
        assertTrue(elementModel.canIMove(false));
    }


    @Test
    public void canIMove3() {
        ElementModel elementModel2 = new AlienModel(new PositionModel(49, 2), "P", "FFFFFF");
        assertFalse(elementModel2.canIMove(false));
        assertTrue(elementModel2.canIMove(true));
    }

    @Test
    public void canIMove4() {
        ElementModel elementModel = new AlienModel(new PositionModel(100, 2), "P", "FFFFFF");
        assertTrue(elementModel.canIMove(true));
        assertFalse(elementModel.canIMove(false));
    }

    @Test
    public void canIMove5() {
        ElementModel elementModel = new AlienModel(new PositionModel(20, 2), "P", "FFFFFF");
        assertTrue(elementModel.canIMove(true));
        assertTrue(elementModel.canIMove(false));
    }

    @Test
    public void addObserver() {
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ShotObserverModel shotObserverModel = Mockito.mock(ShotObserverModel.class);
        elementModel.clearObservers();
        elementModel.addObserver(shotObserverModel);
        assertEquals(1, elementModel.getObservers().size());
        assertTrue(elementModel.getObservers().contains(shotObserverModel));
    }

    @Test
    public void removeObserver() {
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ShotObserverModel shotObserverModel = Mockito.mock(ShotObserverModel.class);
        elementModel.clearObservers();
        elementModel.addObserver(shotObserverModel);
        elementModel.removeObserver(shotObserverModel);
        assertEquals(0, elementModel.getObservers().size());
        assertFalse(elementModel.getObservers().contains(shotObserverModel));
    }

    @Test
    public void notifyObservers(){
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ShotObserverModel shotObserverModel = Mockito.mock(ShotObserverModel.class);
        elementModel.addObserver(shotObserverModel);
        elementModel.notifyObservers(shotModel);
        Mockito.verify(shotObserverModel, Mockito.times(1)).update(shotModel);
    }

    @Test
    public void collideWith(){
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        Mockito.when(shotModel.getX()).thenReturn(1);
        Mockito.when(shotModel.getY()).thenReturn(2);
        assertTrue(elementModel.collideWith(shotModel));
    }

    @Test
    public void collideWith2(){
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        Mockito.when(shotModel.getX()).thenReturn(3);
        Mockito.when(shotModel.getY()).thenReturn(2);
        assertFalse(elementModel.collideWith(shotModel));
    }

    @Test
    public void collideWith3(){
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        Mockito.when(shotModel.getX()).thenReturn(0);
        Mockito.when(shotModel.getY()).thenReturn(1);
        assertFalse(elementModel.collideWith(shotModel));
    }

    @Test
    public void collideWith4(){
        ElementModel elementModel = new ProtectionModel(new PositionModel(1, 2), 3);
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        Mockito.when(shotModel.getX()).thenReturn(2);
        Mockito.when(shotModel.getY()).thenReturn(3);
        assertTrue(elementModel.collideWith(shotModel));
    }

    @Test
    public void collideWith5(){
        ElementModel elementModel = new ProtectionModel(new PositionModel(1, 2), 3);
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        Mockito.when(shotModel.getX()).thenReturn(4);
        Mockito.when(shotModel.getY()).thenReturn(3);
        assertTrue(elementModel.collideWith(shotModel));
    }

    @Test
    public void collideWith6(){
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        Mockito.when(shotModel.getX()).thenReturn(0);
        Mockito.when(shotModel.getY()).thenReturn(0);
        assertFalse(elementModel.collideWith(shotModel));
    }

    @Test
    public void collideWith7(){
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                ElementModel elementModel = new ProtectionModel(new PositionModel(1, 2), 3);
                ShotModel shotModel = Mockito.mock(ShotModel.class);
                Mockito.when(shotModel.getX()).thenReturn(i);
                Mockito.when(shotModel.getY()).thenReturn(j);
                if(i >= 1 && i <= 4 && j >= 2 && j <= 3){
                    System.out.println(shotModel.getX() + " " + shotModel.getY());
                    assertTrue(elementModel.collideWith(shotModel));
                } else {
                    assertFalse(elementModel.collideWith(shotModel));
                }
            }
        }
    }

    @Test
    public void moveLeft(){
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        int oldX = elementModel.getX();
        elementModel.move(0);
        assertEquals(oldX - 1, elementModel.getX());
    }

    @Test
    public void moveRight(){
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        int oldX = elementModel.getX();
        elementModel.move(1);
        assertEquals(oldX + 1, elementModel.getX());
    }

    @Test
    public void moveDown(){
        ElementModel elementModel = new AlienModel(new PositionModel(1, 2), "P", "FFFFFF");
        int oldY = elementModel.getY();
        elementModel.move(2);
        assertEquals(oldY + 1, elementModel.getY());
    }
}
