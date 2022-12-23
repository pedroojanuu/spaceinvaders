package spaceinvaders;

import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.Controller;
import spaceinvaders.controller.RunStateController;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunStateControllerTest {
    Controller controller;
    RunStateController runStateController;
    @BeforeEach
    public void helper(){
        controller = Mockito.mock(Controller.class);
        runStateController = new RunStateController(controller);
    }

    @Test
    public void processKeyTest(){
        KeyStroke key = Mockito.mock(KeyStroke.class);
        runStateController.processKey(key);
        Mockito.verify(controller, Mockito.times(1)).processKey(key);
    }

    @Test
    public void getControllerTest(){
        assertEquals(runStateController.getController(), controller);
    }
}
