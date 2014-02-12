package cs355.solution.view;

import cs355.solution.model.Line;
import cs355.solution.model.Shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class DrawableLine extends DrawableShape {

    private Point start;
    private Point end;
    private final int LINE_WIDTH = 1;

    public DrawableLine(Point start, Color color) {
        this.start = start;
        this.color = color;
        end = null;
    }

    public DrawableLine(Shape shape) {
        if (!(shape instanceof Line)) {
            throw new IllegalArgumentException("DrawableLine can only be constructed from a Line");
        }
        Line line = (Line) shape;
        start = line.getStartPoint();
        end = line.getEndPoint();
        color = line.getColor();
        center = new Point ((end.x + start.x) / 2, (end.y - start.y) / 2);
    }

    @Override
    public void addPoint(Point p) {
        center = new Point ((p.x + start.x) / 2, (p.y - start.y) / 2);
        end = p;
    }

    @Override
    public void drawShape(Graphics2D graphics2D, boolean selected) {
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(LINE_WIDTH));
        if (selected) {
            graphics2D.setColor(color.darker());
            graphics2D.fillRect(start.x - 2, start.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
            graphics2D.fillRect(end.x - 2, end.y - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
        }
        graphics2D.drawLine(start.x, start.y, end.x, end.y);
    }

    @Override
    public Shape generateModelObject() {
        return new Line(start, end, color);
    }

    @Override
    public Point getRotationHandle() {
        return null;
    }
}
