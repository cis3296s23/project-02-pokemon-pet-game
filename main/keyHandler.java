package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {

    public boolean leftPressed;
    public boolean rightPressed;
    public boolean jumpPressed;
    public boolean speakPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        // Move to the left
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        // Move to the right
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        // Jump
        if (keyCode == KeyEvent.VK_SPACE) {
            jumpPressed = true;
        }
        // Make noise
        if (keyCode == KeyEvent.VK_S) {
            speakPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        // Move to the right
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        // Jump
        if (keyCode == KeyEvent.VK_SPACE) {
            jumpPressed = false;
        }
        // Make noise
        if (keyCode == KeyEvent.VK_S) {
            speakPressed = false;
        }
    }

}
