package cs355.solution.view;

import cs355.solution.model.Shape;
import cs355.solution.model.Triangle;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class DrawableTriangle extends DrawableShape {

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;
    private int numVertices;

    public DrawableTriangle(Color color) {
        this.color = color;
        center = new Point(); //This is just so that a null pointer doesn't get thrown until all 2 points are down
        numVertices = 0;
    }

    public DrawableTriangle(Shape shape) {
        if (!(shape instanceof Triangle)) {
            throw new IllegalArgumentException("DrawableTriangle can only be constructed from a Triangle");
        }
        Triangle tri = (Triangle) shape;
        Point[] vs = tri.getVertices();
        center = tri.getCenter();
        this.vertex1 = new Point(vs[0].x + center.x, vs[0].y + center.y);
        this.vertex2 = new Point(vs[1].x + center.x, vs[1].y + center.y);
        this.vertex3 = new Point(vs[2].x + center.x, vs[2].y + center.y);
        this.color = tri.getColor();
        numVertices = 3;
        super.rotationAngle = shape.getRotationAngle();
    }

    @Override
    public void drawShape(Graphics2D graphics2D, boolean selected) {
        graphics2D.setColor(color);
        if (numVertices == 3) {
            int xPts[] = {vertex1.x, vertex2.x, vertex3.x};
            int yPts[] = {vertex1.y, vertex2.y, vertex3.y};
            graphics2D.fillPolygon(xPts, yPts, xPts.length);
            if (selected) {
                graphics2D.setColor(color.darker());
                graphics2D.fillRect(xPts[0] - 2, yPts[0] - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
                graphics2D.fillRect(xPts[1] - 2, yPts[1] - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
                graphics2D.fillRect(xPts[2] - 2, yPts[2] - 2, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
                Point handleVector = new Point(xPts[0] - center.x, yPts[0] - center.y);
                Double handleVectorLength = Math.sqrt(Math.pow(handleVector.x, 2) + Math.pow(handleVector.y, 2));
                handleVector = new Point((int) (handleVector.x / handleVectorLength * ROTATE_HANDLE_LENGTH),
                        (int) (handleVector.y / handleVectorLength * ROTATE_HANDLE_LENGTH));
                graphics2D.fillRect(xPts[0] + handleVector.x -2 , yPts[0] + handleVector.y, SELECTION_BOX_WIDTH, SELECTION_BOX_WIDTH);
                graphics2D.drawLine(xPts[0], yPts[0], xPts[0] + handleVector.x, yPts[0] + handleVector.y);
                graphics2D.setStroke(new BasicStroke(HIGHLIGHT_WIDTH));
                graphics2D.drawPolygon(xPts, yPts, xPts.length);
            }
        }
    }

    @Override
    public void addPoint(Point p) {
        if (numVertices >= 3) {
            throw new IllegalAccessError("DrawableTriangle already has 3 vertices");
        }
        switch (numVertices) {
            case 0:
                vertex1 = p;
                break;
            case 1:
                vertex2 = p;
                break;
            case 2:
                vertex3 = p;
                center = new Point((vertex1.x + vertex2.x + vertex3.x) / 3, (vertex1.y + vertex2.y + vertex3.y) / 3);
                break;
        }
        numVertices++;
    }

    @Override
    public Shape generateModelObject() {
        if (numVertices < 3) {
            return null;
        }
        return new Triangle(vertex1, vertex2, vertex3, color);
    }

    public boolean isDone() {
        return numVertices == 3;
    }

    @Override
    public Point getRotationHandle() {
        Point handleVector = new Point(vertex1.x - center.x, vertex1.y - center.y);
        Double handleVectorLength = Math.sqrt(Math.pow(handleVector.x, 2) + Math.pow(handleVector.y, 2));
        handleVector = new Point((int) (handleVector.x / handleVectorLength * ROTATE_HANDLE_LENGTH),
                (int) (handleVector.y / handleVectorLength * ROTATE_HANDLE_LENGTH));
        Point p = new Point(vertex1.x + handleVector.x - 2, vertex1.y + handleVector.y);
        super.transform = new AffineTransform();
        transform.translate(center.x, center.y);
        transform.rotate(rotationAngle);
        transform.translate(-center.x, -center.y);
        transform.transform(p, p);
        return p;
    }
}
