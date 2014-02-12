package cs355.solution.controller;

import cs355.solution.model.Shape;
import cs355.solution.view.DrawableShape;
import java.awt.Color;
import java.awt.Point;

public class CS355State {

    public enum SelectedButtonType {

        LINE, RECTANGLE, SQUARE, ELLIPSE, CIRCLE, TRIANGLE, SELECT, ROTATE, NONE
    }
    private static CS355State instance = null;
    private SelectedButtonType selectedButton;
    private Point mouseDown;
    private Color color;
    private DrawableShape curShape;
    private Shape selectedShape;

    private CS355State() {
        selectedButton = SelectedButtonType.NONE;
        color = Color.WHITE;
        mouseDown = null;
        curShape = null;
        selectedShape = null;
    }

    public static CS355State getInst() {
        if (instance == null) {
            instance = new CS355State();
        }
        return instance;
    }

    public SelectedButtonType getSelectedButtonType() {
        return selectedButton;
    }

    public void setSelectedButtonType(SelectedButtonType selectedButton) {
        this.selectedButton = selectedButton;
        switch (selectedButton) {
            case LINE:
            case RECTANGLE:
            case SQUARE:
            case ELLIPSE:
            case CIRCLE:
            case TRIANGLE:
                selectedShape = null;
                break;
        }
    }

    public Point getMouseDown() {
        return mouseDown;
    }

    public void setMouseDown(Point mouseDown) {
        this.mouseDown = mouseDown;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DrawableShape getCurShape() {
        return curShape;
    }

    public void setCurShape(DrawableShape curShape) {
        this.curShape = curShape;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }
}
