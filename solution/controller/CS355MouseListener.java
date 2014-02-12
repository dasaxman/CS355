package cs355.solution.controller;

import cs355.CS355Frame;
import cs355.GUIFunctions;
import cs355.solution.model.*;
import cs355.solution.view.DrawableCircle;
import cs355.solution.view.DrawableEllipse;
import cs355.solution.view.DrawableLine;
import cs355.solution.view.DrawableRectangle;
import cs355.solution.view.DrawableShape;
import cs355.solution.view.DrawableSquare;
import cs355.solution.view.DrawableTriangle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class CS355MouseListener implements MouseListener {

    private final Model model;

    public CS355MouseListener(Model model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        CS355State state = CS355State.getInst();
        Color color = state.getColor();

        switch (state.getSelectedButtonType()) {
            case LINE:
                state.setCurShape(new DrawableLine(e.getPoint(), color));
                break;
            case RECTANGLE:
                state.setCurShape(new DrawableRectangle(e.getPoint(), color));
                break;
            case SQUARE:
                state.setCurShape(new DrawableSquare(e.getPoint(), color));
                break;
            case ELLIPSE:
                state.setCurShape(new DrawableEllipse(e.getPoint(), color));
                break;
            case CIRCLE:
                state.setCurShape(new DrawableCircle(e.getPoint(), color));
                break;
            case TRIANGLE:
                if (!(state.getCurShape() instanceof DrawableTriangle) || ((DrawableTriangle) state.getCurShape()).isDone()) {
                    state.setCurShape(new DrawableTriangle(state.getColor()));
                }
                break;
            case SELECT:
                selectShape(e.getPoint());
                break;
        }
        CS355State.getInst().setMouseDown(e.getPoint());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        CS355State state = CS355State.getInst();
        DrawableShape curShape = state.getCurShape();
        boolean shapeFinished = false;

        switch (state.getSelectedButtonType()) {
            case LINE:
                DrawableLine line = (DrawableLine) curShape;
                line.addPoint(e.getPoint());
                shapeFinished = true;
                break;
            case RECTANGLE:
                DrawableRectangle rec = (DrawableRectangle) curShape;
                rec.addPoint(e.getPoint());
                shapeFinished = true;
                break;
            case SQUARE:
                DrawableSquare square = (DrawableSquare) curShape;
                square.addPoint(e.getPoint());
                shapeFinished = true;
                break;
            case ELLIPSE:
                DrawableEllipse ellipse = (DrawableEllipse) curShape;
                ellipse.addPoint(e.getPoint());
                shapeFinished = true;
                break;
            case CIRCLE:
                DrawableCircle circle = (DrawableCircle) curShape;
                circle.addPoint(e.getPoint());
                shapeFinished = true;
                break;
            case TRIANGLE:
                DrawableTriangle tri = (DrawableTriangle) curShape;
                tri.addPoint(e.getPoint());
                GUIFunctions.refresh();
                if (tri.isDone()) {
                    shapeFinished = true;
                }
                break;
            case ROTATE:
                state.setSelectedButtonType(CS355State.SelectedButtonType.SELECT);
                break;
        }
        CS355State.getInst().setMouseDown(null);
        CS355Frame.inst().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        if (shapeFinished) {
            model.addShape(curShape.generateModelObject());
            CS355State.getInst().setCurShape(null);
        }
    }

    private void selectShape(Point p) {
        CS355State state = CS355State.getInst();
        List<Shape> shapes = model.getShapes();
        // First check if we clicked on a handle
        if (state.getSelectedShape() != null) {
            DrawableShape dshape = DrawableShape.getDrawableShape(state.getSelectedShape());
            Point handle = dshape.getRotationHandle();
            if (handle != null) {
                double distance = Math.sqrt(Math.pow(handle.x - p.x, 2) + Math.pow(handle.y - p.y, 2));
                if (distance <= DrawableShape.SELECTION_BOX_WIDTH) {
                    state.setSelectedButtonType(CS355State.SelectedButtonType.ROTATE);
                    state.setMouseDown(p);
                    return;
                }
            }
        }
        // If not, then select the shape
        state.setSelectedShape(model.getClickedOnShape(p));
        GUIFunctions.refresh();
        if (state.getSelectedShape() != null)
            GUIFunctions.changeSelectedColor(state.getSelectedShape().getColor());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
