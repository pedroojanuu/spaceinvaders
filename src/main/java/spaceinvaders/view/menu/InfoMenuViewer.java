package spaceinvaders.view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.menu.InfoMenuModel;

public class InfoMenuViewer implements MenuViewer {
    private static InfoMenuViewer instance = null;
    private InfoMenuModel model;
    private InfoMenuViewer(InfoMenuModel model){
        this.model = model;
    }
    public static InfoMenuViewer getInstance(InfoMenuModel model){
        if(instance == null){
            instance = new InfoMenuViewer(model);
        }
        return instance;
    }
    public static InfoMenuViewer getInstance(){
        return instance;
    }
    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(6, 8, "Info");
        graphics.putString(6, 12, "This Game was created by a group of");
        graphics.putString(6, 13, "3 students who dream of becoming");
        graphics.putString(6, 14, "excellent computer engineers");
        graphics.putString(6, 16, "We hope you enjoy our game!");
        graphics.putString(6, 17, "Help us by giving us a good grade!");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC300"));
        graphics.putString(6, 20, "> Exit");
    }

    public static void reset() {
        instance = null;
    }
}
