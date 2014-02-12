/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs355.solution.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Riley
 */
public abstract class Shape {
    
    public enum ShapeType { LINE, RECTANGLE, SQUARE, ELLIPSE, CIRCLE, TRIANGLE}
    
    protected Color color;
    protected ShapeType type;
    protected Point center;
    protected double rotationAngle = 0;

    //Protected methods
    protected Point convertToObjCoords(Point worldCoord) {
        Point objCoord = new Point();
        
        AffineTransform transform = new AffineTransform();
        transform.rotate(-rotationAngle);
        transform.translate(-center.x, -center.y);
        transform.transform(worldCoord, objCoord);
        
        return objCoord;
    }
    
    //Getters and Setters
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }
    
    public ShapeType getShapeType() {
        return type;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    } 
    
    //Abstract methods
    public abstract boolean pointIsInsideShape(Point p);
}
