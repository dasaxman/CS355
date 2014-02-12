package cs355.solution.view;

import cs355.solution.model.Shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public abstract class DrawableShape {
    
    public static final Integer SELECTION_BOX_WIDTH = 4;
    
    protected final Integer HIGHLIGHT_WIDTH = 2;
    protected final Integer ROTATE_HANDLE_LENGTH = 20;
    
    protected Point anchor;
    protected Point center;
    protected Color color;
    protected AffineTransform transform;
    protected Double rotationAngle = 0d;
    
    public void draw(Graphics2D graphics2D, boolean selected) {
        AffineTransform originalTransform = graphics2D.getTransform();
        
        transform = new AffineTransform();
        transform.translate(center.x , center.y);
        transform.rotate(rotationAngle);
        transform.translate(-center.x , -center.y);
        graphics2D.setTransform(transform);
        
        drawShape(graphics2D, selected);
        
        graphics2D.setTransform(originalTransform);        
    }
    
    public abstract void addPoint(Point p);
    
    public abstract Shape generateModelObject();
    
    public abstract Point getRotationHandle();
    
    public static DrawableShape getDrawableShape(Shape shape) {
        switch (shape.getShapeType()) {
            case LINE :
                return new DrawableLine(shape);
            case RECTANGLE:
                return new DrawableRectangle(shape);
            case SQUARE :
                return new DrawableSquare(shape);
            case ELLIPSE:
                return new DrawableEllipse(shape);
            case CIRCLE:
                return new DrawableCircle(shape);
            default:
                return new DrawableTriangle(shape);
        }
    }
    
    protected Point getRotationHandleByHalfHeight(Integer halfHeight) {
        Point old = new Point(center.x, center.y - halfHeight - ROTATE_HANDLE_LENGTH);
        Point newP = new Point();
        
        transform = new AffineTransform();
        transform.translate(center.x, center.y);
        transform.rotate(rotationAngle);
        transform.translate(-center.x, -center.y);
        transform.transform(old, newP);
        
        return newP;
    }
    
    protected abstract void drawShape(Graphics2D graphics2D, boolean selected);
}
