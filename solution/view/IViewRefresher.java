package cs355.solution.view;

import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.solution.controller.CS355State;
import cs355.solution.model.Model;
import cs355.solution.model.Shape;
import cs355.solution.model.listener.EventType;
import cs355.solution.model.listener.ModelListener;
import java.awt.Graphics2D;
import java.util.List;

public class IViewRefresher implements ViewRefresher, ModelListener {

    private Model model;

    public IViewRefresher(Model model) {
        this.model = model;
        model.addListener(this);
    }

    public IViewRefresher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        List<Shape> shapes = model.getShapes();

        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            DrawableShape dShape = DrawableShape.getDrawableShape(shape);
            dShape.draw(g2d, shape.equals(CS355State.getInst().getSelectedShape()));
        }
        if (CS355State.getInst().getCurShape() != null) {
            CS355State.getInst().getCurShape().draw(g2d, false);
        }
    }

    @Override
    public void listen(EventType type) {
        switch (type) {
            case SHAPE_ADDED:
            case SHAPE_COLOR_CHANGED:
            case SHAPE_MOVED:
                GUIFunctions.refresh();
                break;
        }
    }

}
