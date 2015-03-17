/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.jamk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author h3090
 */
public class EventTesting extends JFrame {
    
    private int x=0, y=0, mx=0, my=0;
    private String key = "";
    
    public EventTesting() {
        super("Event Testing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        DrawPanel drawPanel = new DrawPanel();
        getContentPane().add(drawPanel);
        drawPanel.addMouseListener(new MousePressedTracker());
        drawPanel.addMouseMotionListener(new MouseMovementTracker());
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyDispatcher());
    }
    
    public static void main(String[] args) {
        new EventTesting().setVisible(true);
    }
    
    class DrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.black);
            g.setColor(Color.white);
            g.drawString("Mouse pressed = " + x + ", " + y, 20, 20);
            g.drawString("Mouse moved = " + mx + ", " + my, 20, 40);
            g.drawString("Key pressed = " + key, 20, 60);
        }
    }
    
    class MousePressedTracker extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            repaint();
        }
    }
    
    class MouseMovementTracker extends MouseMotionAdapter {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
            repaint();
        }
    }
    
    class KeyDispatcher implements KeyEventDispatcher {
        
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if(e.getID() == KeyEvent.KEY_PRESSED) {
                int code = e.getKeyCode();
                key = KeyEvent.getKeyText(code);
                repaint();
            }
            return false;
        }
        
    }
    
}
