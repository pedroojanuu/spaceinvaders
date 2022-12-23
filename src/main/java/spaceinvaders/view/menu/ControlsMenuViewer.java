package spaceinvaders.view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.menu.ControlsMenuModel;

public class ControlsMenuViewer implements MenuViewer {
    private static ControlsMenuViewer instance = null;
    private ControlsMenuModel model;
    private ControlsMenuViewer(ControlsMenuModel model){
        this.model = model;
    }
    public static ControlsMenuViewer getInstance(ControlsMenuModel model){
        if(instance == null){
            instance = new ControlsMenuViewer(model);
        }
        return instance;
    }
    public static ControlsMenuViewer getInstance(){
        return instance;
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(6, 8, "Controls");
        graphics.putString(6, 12, "Move left: A or Left Arrow");
        graphics.putString(6, 13, "Move right: D or Right Arrow");
        graphics.putString(6, 14, "Shoot: Space or Up Arrow");
        graphics.putString(6, 15, "Back: Q or ESC key");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC300"));
        graphics.putString(6, 20, "> Exit");
    }

    public static void reset(){
        instance = null;
    }
}
