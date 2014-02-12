package cs355.solution.model;

import java.awt.Color;
import java.awt.Point;

public class Square extends Shape {
    private Integer size;

    public Square(Point center, Integer size, Color color) {
        type = ShapeType.SQUARE;
        super.center = center;
        this.size = size;
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    
    @Override
    public boolean pointIsInsideShape(Point worldCoord) {
        Point objCoord = convertToObjCoords(worldCoord);
        return (objCoord.x > -size / 2 && objCoord.x < size / 2 && objCoord.y > -size / 2 && objCoord.y < size / 2);
    }
}
