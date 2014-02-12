package cs355.solution.model;

import java.awt.Color;
import java.awt.Point;

public class Circle extends Shape {
    private Integer radius;

    public Circle(Point center, Integer radius, Color color) {
        super.type = ShapeType.CIRCLE;
        super.center = center;
        super.color = color;
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    @Override
    public boolean pointIsInsideShape(Point worldCoord) {
        Point objCoord = convertToObjCoords(worldCoord);
        
        if (objCoord.x > -radius && objCoord.x < radius && objCoord.y > -radius && objCoord.y < radius){
            if (Math.pow(objCoord.x, 2) + Math.pow(objCoord.y, 2) < Math.pow(radius, 2)){
                return true;
            }
        }
        return false;
    }
    
    
}
