package cs355.solution.model;

import java.awt.Color;
import java.awt.Point;

public class Ellipse extends Shape {

    private Integer height;
    private Integer width;

    public Ellipse(Point center, Integer height, Integer width, Color color) {
        type = ShapeType.ELLIPSE;
        super.center = center;
        super.color = color;
        
        this.height = height;
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
    
    @Override
    public boolean pointIsInsideShape(Point worldCoord) {
        double majorAxis = width/2;
        double minorAxis = height/2;
        Point objCoord = convertToObjCoords(worldCoord);
        
        if (objCoord.x > -majorAxis && objCoord.x < majorAxis && objCoord.y > -minorAxis && objCoord.y < minorAxis){
            if ((Math.pow(objCoord.x / majorAxis, 2) + Math.pow(objCoord.y / minorAxis, 2)) < 1){
                return true;
            }
        }
        return false;
    }
}
