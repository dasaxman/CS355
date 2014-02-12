package cs355.solution.view;

import cs355.solution.model.Circle;
import cs355.solution.model.Shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class DrawableCircle extends DrawableShape {

    private Point upperLeftCorner;
    private Integer size;

    public DrawableCircle(Point anchor, Color color) {
        super.anchor = anchor;
        super.color = color;
        upperLeftCorner = new Point();
    }

    public DrawableCircle(Shape shape) {
        if (!(shape instanceof Circle)) {
            throw new IllegalArgumentException("DrawableCircle can only be constructed from a Square");
        }
        Circle circle = (Circle) shape;
        Integer radius = circle.getRadius();
        
        center = circle.getCenter();
        upperLeftCorner = new Point(center.x - radius, center.y - radius);
        size = radius * 2;
        color = circle.getColor();
    }
    
    @Override
    public void drawShape(Graphics2D graphics2D, boolean selected) {
        graphics2D.setColor(color);
        graphics2D.fillOval(upperLeftCorner.x, upperLeftCorner.y, size, size);
        
        if (selected) {
            graphics2D.setColor(color.darker());
            graphics2D.drawRect(upperLeftCorner.x, upperLeftCorner.y, size, size);
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + size - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y + size - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + size - 2, upperLeftCorner.y + size - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.setStroke(new BasicStroke(HIGHLIGHT_WIDTH));
            graphics2D.drawOval(upperLeftCorner.x, upperLeftCorner.y, size, size);
        }
    }

    @Override
    public void addPoint(Point p) {
        center = new Point((p.x + anchor.x) / 2, (p.y + anchor.y) / 2);
        
        size = (int) Math.sqrt(Math.pow(p.x - anchor.x, 2) + Math.pow(p.y - anchor.y, 2));

        upperLeftCorner = new Point(center.x - size / 2, center.y - size / 2);
    }

    @Override
    public Shape generateModelObject() {
        if (size == 0) {
            return null;
        }
        return new Circle(new Point(upperLeftCorner.x + (size / 2), upperLeftCorner.y + (size / 2)), size / 2, color);
    }

    @Override
    public Point getRotationHandle() {
        return null;
    }

}
