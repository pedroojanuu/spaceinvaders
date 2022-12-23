package spaceinvaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.RunStateModel;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameModelTest {
    GameModel gameModel;
    @BeforeEach
    public void helper() {
        gameModel = new GameModel();
    }

    @Test
    public void getState() {
        RunStateModel state = Mockito.mock(RunStateModel.class);
        gameModel.setState(state);
        assertEquals(state, gameModel.getState());
    }

    @Test
    public void runTest() throws IOException {
        RunStateModel state = Mockito.mock(RunStateModel.class);
        gameModel.setState(state);
        gameModel.run();
        Mockito.verify(state).run();
    }
}
