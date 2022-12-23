package spaceinvaders.view.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.view.RunStateViewer;

public class MenuStateViewer implements RunStateViewer {
    private MenuViewer menuViewer;

    public MenuStateViewer(MenuViewer menuViewer){
        this.menuViewer = menuViewer;
    }
    @Override
    public void draw(TextGraphics graphics){
        menuViewer.draw(graphics);
    }
}
