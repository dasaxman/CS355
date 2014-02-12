package cs355.solution.model;

import java.awt.Color;
import java.awt.Point;

public class Line extends Shape {
    private Point startPoint;
    private Point endPoint;
    private final Integer SELECTION_DISTANCE = 4;

    public Line(Point startPoint, Point endPoint, Color color) {
        type = ShapeType.LINE;
        this.color = color;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void setCenter(Point center) {
        Point oldCenter = new Point ((endPoint.x + startPoint.x) / 2, (endPoint.y - startPoint.y) / 2);
        Point movementVector = new Point(center.x - oldCenter.x, center.y - oldCenter.y);
        startPoint = new Point(startPoint.x + movementVector.x, startPoint.y + movementVector.y);
        endPoint = new Point(endPoint.x + movementVector.x, endPoint.y + movementVector.y);
    }

    @Override
    public Point getCenter() {
        return new Point ((endPoint.x + startPoint.x) / 2, (endPoint.y - startPoint.y) / 2);
    }
    
    

    @Override
    public boolean pointIsInsideShape(Point p) {
        Point normalVector = new Point(-(endPoint.y - startPoint.y), endPoint.x - startPoint.x);
        double normalizer = Math.sqrt(Math.pow(normalVector.x, 2) + Math.pow(normalVector.y, 2));
        double d = ((startPoint.x * normalVector.x) + (startPoint.y * normalVector.y)) / normalizer;
        double distance = Math.abs(((p.x * normalVector.x) / normalizer) + ((p.y * normalVector.y) / normalizer) - d);
        return distance < SELECTION_DISTANCE;
    }
    
    
}
