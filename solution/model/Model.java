package cs355.solution.model;

import cs355.solution.model.listener.EventType;
import cs355.solution.model.listener.ModelListener;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Shape> SHAPES = new ArrayList<>();
    private final List<ModelListener> LISTENERS = new ArrayList<>();

    public List<Shape> getShapes() {
        return SHAPES;
    }
    
    public void addShape(Shape shape) {
        if (shape == null)
            return;
        SHAPES.add(shape);
        notifyListeners(EventType.SHAPE_ADDED);
    }
    
    public void changeShapeColor(Shape shape, Color c) {
        shape.setColor(c);
        notifyListeners(EventType.SHAPE_COLOR_CHANGED);
    }
    
    public void moveShape(Shape shape, Point p) {
        Point center = shape.getCenter();
        shape.setCenter(new Point(center.x + p.x, center.y + p.y));
        notifyListeners(EventType.SHAPE_MOVED);
    }
    
    public Shape getClickedOnShape(Point p) {
        Shape shape = null;
        for (int i = 0; i < SHAPES.size(); i++) {
            Shape temp = SHAPES.get(i);
            if (temp.pointIsInsideShape(p)) {
                shape = temp;
            }
        }
        return shape;
    }
    
    public void rotateShape(Shape shape, double angle) {
        double rotationAngle = shape.getRotationAngle();
        shape.setRotationAngle(rotationAngle + angle);
        notifyListeners(EventType.SHAPE_MOVED);
    }
    
    public void addListener(ModelListener listener) {
        LISTENERS.add(listener);
    }
    
    private void notifyListeners(EventType eventType) {
        for (ModelListener listener : LISTENERS) {
            listener.listen(eventType);
        }
    }

}
