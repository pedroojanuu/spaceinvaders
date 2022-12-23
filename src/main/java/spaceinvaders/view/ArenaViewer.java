package spaceinvaders.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.ArenaModel;
import spaceinvaders.model.ElementModel;
import spaceinvaders.model.ShotModel;

public class ArenaViewer implements Viewer{
    private ArenaModel model;

    public ArenaViewer(ArenaModel model) {
        this.model = model;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        for (ElementModel element : model.getElements()) {
            element.getViewer().draw(graphics);
        }
        for (ShotModel shot : model.getShots())
            shot.getViewer().draw(graphics);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        drawLives(graphics);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(38, 1,"Score: "+ model.getScore());

        if(model.getYouWon()) {
            graphics.putString(22, 13, "Level " + model.getLevel());
        }
    }
    public void drawLives(TextGraphics graphics) {
        graphics.putString(1, 1, "Lives: ");
        graphics.putString(8,1,"&");
        int lives = model.getShip().getLives();
        switch (lives) {
            case 1:
                graphics.setForegroundColor(TextColor.Factory.fromString("#660000"));
                graphics.putString(9,1,"&");
                graphics.putString(10,1,"&");
                break;
            case 2:
                graphics.putString(9,1,"&");
                graphics.setForegroundColor(TextColor.Factory.fromString("#660000"));
                graphics.putString(10,1,"&");
                break;
            case 3:
                graphics.putString(9,1,"&");
                graphics.putString(10,1,"&");
        }
    }
}
