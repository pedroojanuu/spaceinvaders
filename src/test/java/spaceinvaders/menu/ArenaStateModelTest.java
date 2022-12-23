package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.ArenaController;
import spaceinvaders.model.ArenaModel;
import spaceinvaders.model.ArenaStateModel;
import spaceinvaders.view.ArenaStateViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaStateModelTest {
    ArenaModel arenaModelMock;
    ArenaStateModel arenaStateModel;
    @BeforeEach
    public void helper() {
        arenaModelMock = Mockito.mock(ArenaModel.class);
        arenaStateModel = new ArenaStateModel(arenaModelMock);
    }

    @Test
    public void runTest(){
        arenaStateModel.run();
        Mockito.verify(arenaModelMock, Mockito.times(1)).run();
    }

    @Test
    public void getViewer(){
        ArenaStateViewer arenaStateViewer = Mockito.mock(ArenaStateViewer.class);
        arenaStateModel.setViewer(arenaStateViewer);
        assertEquals(arenaStateViewer, arenaStateModel.getViewer());
    }

    @Test
    public void getController(){
        ArenaController arenaController = (ArenaController) arenaStateModel.getController();
        assertEquals(arenaModelMock, arenaController.getModel());
    }
}
