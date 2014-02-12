package cs355.solution.model;

import java.awt.Color;
import java.awt.Point;

public class Rectangle extends Shape {
    private Integer width;
    private Integer height;

    public Rectangle(Point center, Integer width, Integer height, Color color) {
        type = ShapeType.RECTANGLE;
        super.center = center;
        super.color = color;
        
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
    
    @Override
    public boolean pointIsInsideShape(Point worldCoord) {
        Point objCoord = convertToObjCoords(worldCoord);
        
        return (objCoord.x > -width / 2d && objCoord.x < width / 2d && objCoord.y > -height / 2d && objCoord.y < height / 2d);
    }
}
