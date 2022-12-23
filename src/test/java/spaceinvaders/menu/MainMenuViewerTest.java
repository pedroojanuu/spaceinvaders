package spaceinvaders.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.MainMenuModel;
import spaceinvaders.view.menu.MainMenuViewer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainMenuViewerTest {
    GameModel gameModel;
    MainMenuModel model;
    MainMenuViewer viewer;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        MainMenuModel.reset();
        MainMenuViewer.reset();
        model = MainMenuModel.getInstance(gameModel);
        viewer = MainMenuViewer.getInstance(model);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(viewer, MainMenuViewer.getInstance(model));
    }

    @Test
    public void getInstanceTest2(){
        MainMenuModel modelMock = Mockito.mock(MainMenuModel.class);
        assertEquals(viewer, MainMenuViewer.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(viewer, MainMenuViewer.getInstance());
    }

    @Test
    public void draw(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        viewer.draw(graphicsMock);
        List<String> spaceInvaders = new ArrayList<>();
        spaceInvaders.add(" ### ###   #    ###  #### ");
        spaceInvaders.add("#    #  # # #  #   # #     ");
        spaceInvaders.add(" ##  ###  # #  #     #### ");
        spaceInvaders.add("   # #   ##### #   # #     ");
        spaceInvaders.add("###  #   #   #  ###  #### ");
        spaceInvaders.add("");
        spaceInvaders.add("### #   # #   #  #   ##    #### ###   ### ");
        spaceInvaders.add(" #  ##  # #   # # #  #  #  #    #  # #    ");
        spaceInvaders.add(" #  # # #  # #  # #  #   # #### ###   ##  ");
        spaceInvaders.add(" #  #  ##  # # ##### #  #  #    # #     # ");
        spaceInvaders.add("### #   #   #  #   # ##    #### #  # ###  ");
        for(int i = 0; i < spaceInvaders.size(); i++){
            Mockito.verify(graphicsMock, Mockito.atLeast(1)).putString(1, 1 + i, spaceInvaders.get(i));
        }

        for(int i = 0; i < model.getCommands().size(); i++){
            if(model.getCommands().get(i) != null){
                if(i == model.getSelectedCommandInt()){
                    Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 18 + i, "> " + model.getCommands().get(i).getTitle());
                }else{
                    Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 18 + i, model.getCommands().get(i).getTitle());
                }
            }
        }

        Mockito.verify(graphicsMock, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#FFC300"));
        Mockito.verify(graphicsMock, Mockito.atLeast(4)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        Mockito.verify(graphicsMock, Mockito.times(4)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    @Test
    public void draw2(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        viewer.draw(graphicsMock);
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 18, "> Start Game");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 19, "Start In Level");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 20, "HighScores");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 21, "Options");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 22, "Exit");
    }

    @Test
    public void draw3(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        model.downSelectedCommand();
        viewer.draw(graphicsMock);
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 18, "Start Game");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 19, "> Start In Level");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 20, "HighScores");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 21, "Options");
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(5, 22, "Exit");
    }

    @Test
    public void resetTest(){
        MainMenuViewer.reset();
        assertEquals(null, MainMenuViewer.getInstance());
    }
}
