package spaceinvaders.view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.menu.StartInLevelMenuModel;

public class StartInLevelMenuViewer implements MenuViewer {
    private static StartInLevelMenuViewer instance = null;
    private StartInLevelMenuModel model;
    private StartInLevelMenuViewer(StartInLevelMenuModel model){
        this.model = model;
    }
    public static StartInLevelMenuViewer getInstance(StartInLevelMenuModel model){
        if(instance == null){
            instance = new StartInLevelMenuViewer(model);
        }
        return instance;
    }
    public static StartInLevelMenuViewer getInstance(){
        return instance;
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(6, 10,"Insert level: ");
        graphics.putString(6, 14, model.getLevel());
    }

    public static void reset(){
        instance = null;
    }
}
