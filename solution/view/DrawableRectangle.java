package cs355.solution.view;

import cs355.solution.model.Rectangle;
import cs355.solution.model.Shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class DrawableRectangle extends DrawableShape {

    private Point upperLeftCorner;
    private Integer width;
    private Integer height;

    public DrawableRectangle(Point anchor, Color color) {
        this.anchor = anchor;
        this.color = color;
        upperLeftCorner = new Point();
    }

    public DrawableRectangle(Shape shape) {
        if (!(shape instanceof Rectangle)) {
            throw new IllegalArgumentException("DrawableRectangle can only be constructed from a Rectangle");
        }
        Rectangle rec = (Rectangle) shape;
        center = rec.getCenter();
        width = rec.getWidth();
        height = rec.getHeight();
        upperLeftCorner = new Point(center.x - width / 2, center.y - height / 2);
        color = rec.getColor();
        rotationAngle = shape.getRotationAngle();
    }

    @Override
    public void drawShape(Graphics2D graphics2D, boolean selected) {
        graphics2D.setColor(color);
        graphics2D.fillRect(upperLeftCorner.x, upperLeftCorner.y, width, height);
        
        if (selected) {
            graphics2D.setColor(color.darker());
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + width - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y + height - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + width - 2, upperLeftCorner.y + height - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + width / 2 - 2, upperLeftCorner.y - ROTATE_HANDLE_LENGTH, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.drawLine(upperLeftCorner.x + width / 2, upperLeftCorner.y, upperLeftCorner.x + width / 2, upperLeftCorner.y - ROTATE_HANDLE_LENGTH);
            graphics2D.setStroke(new BasicStroke(HIGHLIGHT_WIDTH));
            graphics2D.drawRect(upperLeftCorner.x, upperLeftCorner.y, width, height);
        }
    }

    @Override
    public void addPoint(Point p) {
        Integer difX = p.x - anchor.x;
        Integer difY = p.y - anchor.y;

        width = Math.abs(difX);
        height = Math.abs(difY);

        upperLeftCorner.x = (difX < 0) ? p.x : anchor.x;
        upperLeftCorner.y = (difY < 0) ? p.y : anchor.y;

        center = new Point(upperLeftCorner.x + width / 2, upperLeftCorner.y + height / 2);
    }

    @Override
    public Shape generateModelObject() {
        if (height == 0 && width == 0) {
            return null;
        }
        return new Rectangle(center, width, height, color);
    }

    @Override
    public Point getRotationHandle() {
        return getRotationHandleByHalfHeight(height / 2);
    }
}
