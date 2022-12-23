package spaceinvaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.AlienGroupModel;
import spaceinvaders.model.AlienModel;
import spaceinvaders.model.ArenaModel;

import java.util.List;

public class AlienGroupModelTest {
    private ArenaModel arenaModel;
    private AlienGroupModel alienGroupModel;
    @BeforeEach
    public void helper() {
        this.arenaModel = Mockito.mock(ArenaModel.class);
        this.alienGroupModel = new AlienGroupModel(arenaModel);
    }
    @Test
    public void createAliensTest() {
        Assertions.assertEquals(50, alienGroupModel.getAliens().size());
    }
    @Test
    public void addAlienTest() {
        List<AlienModel> expected = alienGroupModel.getAliens();
        AlienModel mockAlien = Mockito.mock(AlienModel.class);
        expected.add(mockAlien);
        alienGroupModel.addAlien(mockAlien);
        Assertions.assertEquals(expected, alienGroupModel.getAliens());
    }
    @Test
    public void getWidthTest() {
        Assertions.assertEquals(0, alienGroupModel.getWidth());
    }
    @Test
    public void getHeightTest() {
        Assertions.assertEquals(0, alienGroupModel.getHeight());
    }
    @Test
    public void isTangibleTest() {
        Assertions.assertFalse(alienGroupModel.isTangible());
    }
    @Test
    public void canIMoveTest() {
        Assertions.assertTrue(alienGroupModel.canIMove(false));
        Assertions.assertTrue(alienGroupModel.canIMove(true));
        Assertions.assertTrue(alienGroupModel.getAliens().get(0).canIMove(false));
        Assertions.assertTrue(alienGroupModel.getAliens().get(0).canIMove(true));
    }
    @Test
    public void move0Test() {
        int expected = alienGroupModel.getX()-1;
        alienGroupModel.move(0);
        Assertions.assertEquals(expected, alienGroupModel.getX());
    }
    @Test
    public void move1Test() {
        AlienModel first = alienGroupModel.getAliens().get(0);
        int expected2 = first.getX()+1;
        int expected = alienGroupModel.getX()+1;
        alienGroupModel.move(1);
        Assertions.assertEquals(expected, alienGroupModel.getX());
        Assertions.assertEquals(expected2, alienGroupModel.getAliens().get(0).getX());

    }
    @Test
    public void move2Test() {
        int expected = alienGroupModel.getY()+1;
        alienGroupModel.move(2);
        Assertions.assertEquals(expected, alienGroupModel.getY());
    }

    @Test
    public void damageTest() {
        alienGroupModel.damage();
        Assertions.assertTrue(true);

    }
    @Test
    public void fireTest() {

    }
    @Test
    public void collideWithTest() {

    }
}
