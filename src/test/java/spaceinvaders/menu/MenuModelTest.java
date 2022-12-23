package spaceinvaders.menu;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.MainMenuModel;
import spaceinvaders.model.menu.MenuModel;
import spaceinvaders.view.menu.MenuViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuModelTest {
    @Test
    public void getViewerTest(){
        MenuViewer viewer = Mockito.mock(MenuViewer.class);
        GameModel gameModel = Mockito.mock(GameModel.class);
        MenuModel model = MainMenuModel.getInstance(gameModel);
        model.setViewer(viewer);
        assertEquals(viewer, model.getViewer());
    }
}
