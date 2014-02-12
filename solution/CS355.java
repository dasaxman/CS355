/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.solution.controller.CS355MouseListener;
import cs355.solution.controller.CS355MouseMotionListener;
import cs355.solution.controller.ICS355Controller;
import cs355.solution.model.Model;
import cs355.solution.view.IViewRefresher;

/**
 *
 * @author [your name here]
 */
public class CS355 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	// Fill in the parameters below with your controller, view, 
        //   mouse listener, and mouse motion listener
        Model model = new Model();
        ViewRefresher view = new IViewRefresher(model);
        CS355Controller controller = new ICS355Controller(model);
        CS355MouseListener mouseListener = new CS355MouseListener(model);
        CS355MouseMotionListener mouseMotionListener = new CS355MouseMotionListener(model);
        GUIFunctions.createCS355Frame(controller, view, mouseListener, mouseMotionListener);

        GUIFunctions.refresh();
    }
}
