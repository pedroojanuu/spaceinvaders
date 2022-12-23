package spaceinvaders.view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.menu.MainMenuModel;

import java.util.ArrayList;
import java.util.List;

public class MainMenuViewer implements MenuViewer {
    private MainMenuModel model;
    private static MenuViewer instance = null;
    private MainMenuViewer(MainMenuModel model){
        this.model = model;
    }
    public static MainMenuViewer getInstance(MainMenuModel model){
        if(instance == null){
            instance = new MainMenuViewer(model);
        }
        return (MainMenuViewer) instance;
    }
    public static MenuViewer getInstance(){
        return instance;
    }
    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC300"));
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
            graphics.putString(1, 1 + i, spaceInvaders.get(i));
        }
        for(int i = 0; i < model.getCommands().size(); i++){
            if(model.getCommands().get(i) != null){
                if(i == model.getSelectedCommandInt()){
                    graphics.setForegroundColor(TextColor.Factory.fromString("#FFC300"));
                    graphics.putString(5, 18 + i, "> " + model.getCommands().get(i).getTitle());
                }else{
                    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    graphics.putString(5, 18 + i, model.getCommands().get(i).getTitle());
                }
            }
        }
    }
    public static void reset(){
        instance = null;
    }
}
