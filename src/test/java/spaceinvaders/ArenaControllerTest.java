package spaceinvaders;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.ArenaController;
import spaceinvaders.model.ArenaModel;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.ShipModel;
import spaceinvaders.model.menu.ExitCommand;
import spaceinvaders.view.ArenaViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaControllerTest {
    private ArenaModel model;
    private ArenaController controller;

    @BeforeEach
    public void helper() {
        GameModel gameModel = new GameModel();
        this.model = new ArenaModel(gameModel);
        this.controller = new ArenaController(model);
    }

    @Test
    public void processKey1(){
        int expected = model.getShip().getX()-1;
        controller.processKey(new KeyStroke('a', false, false));
        assertEquals(expected, model.getShip().getX());
    }

    @Test
    public void processKey2(){
        int expected = model.getShip().getX()+1;
        controller.processKey(new KeyStroke('d', false, false));
        assertEquals(expected, model.getShip().getX());
    }

    @Test
    public void processKey3(){
        controller.processKey(new KeyStroke(' ', false, false));
        assertEquals(1, model.getShots().size());
    }

    @Test
    public void processKey4(){
        int expected = model.getShip().getX()-1;
        controller.processKey(new KeyStroke(KeyType.ArrowLeft));
        assertEquals(expected, model.getShip().getX());
    }

    @Test
    public void processKey5(){
        int expected = model.getShip().getX()+1;
        controller.processKey(new KeyStroke(KeyType.ArrowRight));
        assertEquals(expected, model.getShip().getX());
    }

    @Test
    public void processKey6(){
        controller.processKey(new KeyStroke(KeyType.ArrowUp));
        assertEquals(1, model.getShots().size());
    }

    @Test
    public void processKey7() {
        ArenaModel arenaMock = Mockito.mock(ArenaModel.class);
        Mockito.when(arenaMock.getShip()).thenReturn(new ShipModel());
        this.controller = new ArenaController(arenaMock);
        ExitCommand exitCommandMock = Mockito.mock(ExitCommand.class);
        Mockito.when(arenaMock.getExitCommand()).thenReturn(exitCommandMock);
        controller.processKey(new KeyStroke('q', false, false));
        Mockito.verify(exitCommandMock, Mockito.times(1)).execute();
    }

    @Test
    public void processKey8() {
        ArenaModel arenaMock = Mockito.mock(ArenaModel.class);
        Mockito.when(arenaMock.getShip()).thenReturn(new ShipModel());
        this.controller = new ArenaController(arenaMock);
        ExitCommand exitCommandMock = Mockito.mock(ExitCommand.class);
        Mockito.when(arenaMock.getExitCommand()).thenReturn(exitCommandMock);
        controller.processKey(new KeyStroke(KeyType.Escape));
        Mockito.verify(exitCommandMock, Mockito.times(1)).execute();
    }
}
