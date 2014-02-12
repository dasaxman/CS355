package cs355.solution.view;

import cs355.solution.model.Shape;
import cs355.solution.model.Square;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class DrawableSquare extends DrawableShape {

    private Point upperLeftCorner;
    private Integer size;

    public DrawableSquare(Point anchor, Color color) {
        this.anchor = anchor;
        this.color = color;
        upperLeftCorner = new Point();
    }

    public DrawableSquare(Shape shape) {
        if (!(shape instanceof Square)) {
            throw new IllegalArgumentException("DrawableRectangle can only be constructed from a Square");
        }
        Square square = (Square) shape;

        center = square.getCenter();
        size = square.getSize();
        upperLeftCorner = new Point(center.x - size / 2, center.y - size / 2);
        color = square.getColor();
        rotationAngle = shape.getRotationAngle();
    }

    @Override
    public void drawShape(Graphics2D graphics2D, boolean selected) {
        graphics2D.setColor(color);
        graphics2D.fillRect(upperLeftCorner.x, upperLeftCorner.y, size, size);
        if (selected) {
            graphics2D.setColor(color.darker());
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + size - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y + size - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + size - 2, upperLeftCorner.y + size - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + size / 2 - 2, upperLeftCorner.y - ROTATE_HANDLE_LENGTH, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.drawLine(upperLeftCorner.x + size / 2, upperLeftCorner.y, upperLeftCorner.x + size / 2, upperLeftCorner.y - ROTATE_HANDLE_LENGTH);
            graphics2D.setStroke(new BasicStroke(HIGHLIGHT_WIDTH));
            graphics2D.drawRect(upperLeftCorner.x, upperLeftCorner.y, size, size);
        }
    }

    @Override
    public void addPoint(Point p) {
        Integer difX = p.x - anchor.x;
        Integer difY = p.y - anchor.y;

        size = Math.max(Math.abs(difX), Math.abs(difY));

        upperLeftCorner.x = (difX < 0) ? anchor.x - size : anchor.x;
        upperLeftCorner.y = (difY < 0) ? anchor.y - size : anchor.y;

        center = new Point(upperLeftCorner.x + size / 2, upperLeftCorner.y + size / 2);
    }

    @Override
    public Shape generateModelObject() {
        if (size == 0) {
            return null;
        }
        return new Square(center, size, color);
    }

    @Override
    public Point getRotationHandle() {
        return getRotationHandleByHalfHeight(size / 2);
    }
}
