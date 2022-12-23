package spaceinvaders.model.menu;

import spaceinvaders.controller.Controller;
import spaceinvaders.model.RunStateModel;
import spaceinvaders.view.RunStateViewer;
import spaceinvaders.view.menu.MenuStateViewer;

public class MenuStateModel implements RunStateModel {
    private MenuModel model;
    private Controller controller;
    private MenuStateViewer viewer;
    public MenuStateModel(MenuModel model, Controller controller){
        this.model = model;
        this.controller = controller;
        this.viewer = new MenuStateViewer(model.getViewer());
    }
    @Override
    public void run(){}
    @Override
    public RunStateViewer getViewer() {
        return viewer;
    }
    public void setViewer(MenuStateViewer viewer){
        this.viewer = viewer;
    }

    @Override
    public Controller getController() {
        return controller;
    }

    public MenuModel getModel(){
        return model;
    }


}
