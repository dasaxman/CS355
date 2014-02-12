package cs355.solution.view;

import cs355.solution.model.Ellipse;
import cs355.solution.model.Shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class DrawableEllipse extends DrawableShape {

    private Point upperLeftCorner;
    private Integer width;
    private Integer height;

    public DrawableEllipse(Point anchor, Color color) {
        super.anchor = anchor;
        super.color = color;
        upperLeftCorner = new Point();
    }

    public DrawableEllipse(Shape shape) {
        if (!(shape instanceof Ellipse)) {
            throw new IllegalArgumentException("DrawableEllipse can only be constructed from a Ellipse");
        }
        Ellipse ellipse = (Ellipse) shape;

        center = ellipse.getCenter();
        width = ellipse.getWidth();
        height = ellipse.getHeight();
        upperLeftCorner = new Point(center.x - (width / 2), center.y - (height / 2));
        color = ellipse.getColor();
        rotationAngle = shape.getRotationAngle();
    }

    @Override
    public void drawShape(Graphics2D graphics2D, boolean selected) {
        graphics2D.setColor(color);
        graphics2D.fillOval(upperLeftCorner.x, upperLeftCorner.y, width, height);
        if (selected) {
            graphics2D.setColor(color.darker());
            graphics2D.drawRect(upperLeftCorner.x, upperLeftCorner.y, width, height);
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + width - 2, upperLeftCorner.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x - 2, upperLeftCorner.y + height - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + width - 2, upperLeftCorner.y + height - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(upperLeftCorner.x + width / 2 - 2, upperLeftCorner.y - ROTATE_HANDLE_LENGTH, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.drawLine(upperLeftCorner.x + width / 2, upperLeftCorner.y, upperLeftCorner.x + width / 2, upperLeftCorner.y - ROTATE_HANDLE_LENGTH);
            graphics2D.setStroke(new BasicStroke(HIGHLIGHT_WIDTH));
            graphics2D.drawOval(upperLeftCorner.x, upperLeftCorner.y, width, height);
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

        return new Ellipse(new Point(upperLeftCorner.x + (width / 2), upperLeftCorner.y + (height / 2)), height, width, color);
    }

    @Override
    public Point getRotationHandle() {
        return getRotationHandleByHalfHeight(height / 2);
    }
}
