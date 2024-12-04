package controllers;

import java.awt.Point;
import java.awt.event.*;

/**
 * Controller for the mouse
 */
public class MouseController implements MouseListener, MouseMotionListener {
    /**
     * The position of the mouse
     */
    private Point mousePosition;
    /**
     * Whether the mouse has been clicked
     */
    private boolean mouseClicked;
    /**
     * Whether the mouse is pressed
     */
    private boolean mousePressed;

    /**
     * Constructor for the MouseController
     */
    public MouseController() {
        mousePosition = new Point(0, 0);
    }

    /**
     * Get the position of the mouse
     * @return the position of the mouse
     */
    public void clearMouseClicked() {
        mouseClicked = false;
    }
    /**
     * Get the position of the mouse
     * @return the position of the mouse
     */
    public Point getMouseposition() {
        return mousePosition ;
    }
    /**
     * Get whether the mouse is pressed
     * @return whether the mouse is pressed
     */
    public boolean isMousePressed() {
        return mousePressed;
    }
    /**
     * Get whether the mouse has been clicked
     * @return whether the mouse has been clicked
     */
    public boolean isMouseclicked() {
        return mouseClicked;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    //    System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
    }
    /**
     * Invoked when a mouse button has been pressed on a component.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }
    /**
     * Invoked when a mouse button has been released on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked= true;
        mousePressed = false;
    }
    /**
     * Invoked when the mouse enters a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {}
    /**
     * Invoked when the mouse exits a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {}
    /**
     * Invoked when a mouse button is pressed on a component and then dragged.
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = e.getPoint();
    }
    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint();
    }
}