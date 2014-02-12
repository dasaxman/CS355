package cs355.solution.model;

import java.awt.Color;
import java.awt.Point;

public class Triangle extends Shape {

    private final int VERTEX_COUNT = 3;
    private Point[] vertices = new Point[VERTEX_COUNT];

    public Triangle(Point v1, Point v2, Point v3, Color color) {
        type = ShapeType.TRIANGLE;
        super.color = color;
        
        center = new Point((v1.x + v2.x + v3.x)/VERTEX_COUNT, (v1.y + v2.y + v3.y)/VERTEX_COUNT);
        
        vertices[0] = convertToObjCoords(v1);
        vertices[1] = convertToObjCoords(v2);
        vertices[2] = convertToObjCoords(v3);
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public boolean pointIsInsideShape(Point worldCoord) {
        Point objCoord = convertToObjCoords(worldCoord);
        Point p1 = vertices[0];
        Point p2 = vertices[1];
        Point p3 = vertices[2];
        float alpha = (float)((p2.y - p3.y) * (objCoord.x - p3.x) + (p3.x - p2.x) * (objCoord.y - p3.y))
                / (float)((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        float beta = (float)((p3.y - p1.y) * (objCoord.x - p3.x) + (p1.x - p3.x) * (objCoord.y - p3.y))
                / (float)((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        float gamma = 1.0f - alpha - beta;
        return (alpha > 0) && (beta > 0) && (gamma > 0);
    }
}
