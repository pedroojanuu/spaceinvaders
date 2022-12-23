package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.menu.MenuModel;
import spaceinvaders.model.menu.MenuStateModel;
import spaceinvaders.view.menu.MenuStateViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuStateModelTest {
    MenuStateModel menuStateModelUnderTest;
    MenuModel menuModel;
    Controller controller;
    @BeforeEach
    public void helper(){
        menuModel = Mockito.mock(MenuModel.class);
        controller = Mockito.mock(Controller.class);
        menuStateModelUnderTest = new MenuStateModel(menuModel, controller);
    }

    @Test
    public void getViewer() {
        MenuStateViewer viewer = Mockito.mock(MenuStateViewer.class);
        menuStateModelUnderTest.setViewer(viewer);
        assertEquals(menuStateModelUnderTest.getViewer(), viewer);
    }

    @Test
    public void getController() {
        assertEquals(menuStateModelUnderTest.getController(), controller);
    }

    @Test
    public void getModel() {
        assertEquals(menuStateModelUnderTest.getModel(), menuModel);
    }

    @Test
    public void run() {
        menuStateModelUnderTest.run();
    }
}
