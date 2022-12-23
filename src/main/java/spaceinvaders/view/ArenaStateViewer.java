package spaceinvaders.view;

import com.googlecode.lanterna.graphics.TextGraphics;

public class ArenaStateViewer implements RunStateViewer {
    private ArenaViewer arenaViewer;

    public ArenaStateViewer(ArenaViewer arenaViewer){
        this.arenaViewer = arenaViewer;
    }

    @Override
    public void draw(TextGraphics graphics){
        arenaViewer.draw(graphics);
    }
}
