package cs355.solution.controller;

import java.awt.Point;

public class ControllerUtils {
    public static boolean validatePoint(Point start, Point end) {
        return (end.x - start.x > 0) && (end.y - start.y > 0);
    } 
}
