package cs355.solution.controller;

import cs355.CS355Frame;
import cs355.GUIFunctions;
import cs355.solution.model.Model;
import cs355.solution.view.DrawableShape;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CS355MouseMotionListener implements MouseMotionListener {

    private final Model model;

    public CS355MouseMotionListener(Model model) {
        this.model = model;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        CS355State state = CS355State.getInst();
        CS355State.SelectedButtonType button = state.getSelectedButtonType();
        switch (button) {
            case LINE:
            case RECTANGLE:
            case SQUARE:
            case ELLIPSE:
            case CIRCLE:
                state.getCurShape().addPoint(e.getPoint());
                GUIFunctions.refresh();
                break;
            case SELECT:
                if (state.getSelectedShape() != null) {
                    Point oldPoint = state.getMouseDown();
                    Point newPoint = e.getPoint();
                    Point movementVector = new Point(newPoint.x - oldPoint.x, newPoint.y - oldPoint.y);
                    model.moveShape(state.getSelectedShape(), movementVector);
                    state.setMouseDown(newPoint);
                }
                break;
            case ROTATE:
                Point oldPoint = state.getMouseDown();
                Point newPoint = e.getPoint();
                Point center = state.getSelectedShape().getCenter();
                Point v1 = new Point(oldPoint.x - center.x, oldPoint.y - center.y);
                Point v2 = new Point(newPoint.x - center.x, newPoint.y - center.y);
                double angle = Math.atan2(v1.x * v2.y - v1.y * v2.x, v1.x * v2.x + v1.y * v2.y);
                model.rotateShape(state.getSelectedShape(), angle);
                state.setMouseDown(newPoint);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        CS355State state = CS355State.getInst();
        CS355State.SelectedButtonType button = state.getSelectedButtonType();
        switch (button) {
            case SELECT:
                if (state.getSelectedShape() != null) {
                    DrawableShape shape = DrawableShape.getDrawableShape(state.getSelectedShape());
                    Point handle = shape.getRotationHandle();
                    if (handle == null)
                        return;
                    double distance = Math.sqrt(Math.pow(handle.x - e.getPoint().x, 2) + Math.pow(handle.y - e.getPoint().y, 2));
                    if (distance <= DrawableShape.SELECTION_BOX_WIDTH) {
                        CS355Frame.inst().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    } else {
                        CS355Frame.inst().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                }
        }
    }

}
