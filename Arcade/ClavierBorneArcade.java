package Arcade;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ClavierBorneArcade implements KeyListener {
    // Boolean variables for directional keys
    public boolean gauche, gaucheTape, droite, droiteTape, haut, hautTape, bas, basTape;
    
    // Boolean variables for other keys
    public boolean a, d, e, k, l, m, o, q, s, z;
    public boolean f, g, h, r, t, y;
    
    // Tape variables for key presses
    public boolean aTape, dTape, eTape, kTape, lTape, mTape, oTape, qTape, sTape, zTape;
    public boolean fTape, gTape, hTape, rTape, tTape, yTape;

    /**
     * Method to reset all keyboard states
     */
    public void reinitialisation(){
        gauche = gaucheTape = droite = droiteTape = false;
        haut = hautTape = bas = basTape = false;

        a = d = e = k = l = m = o = q = s = z = false;
        aTape = dTape = eTape = kTape = lTape = mTape = oTape = qTape = sTape = zTape = false;
        f = g = h = r = t = y = false;
        fTape = gTape = hTape = rTape = tTape = yTape = false;
    }

    /**
     * KeyReleased method to handle key release events
     */
    @Override
    public void keyReleased ( KeyEvent key ) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_F:
                f = false;
                fTape = true;
                break;
            case KeyEvent.VK_G:
                g = false;
                gTape = true;
                break;
            case KeyEvent.VK_H:
                h = false;
                hTape = true;
                break;
            case KeyEvent.VK_R:
                r = false;
                rTape = true;
                break;
            case KeyEvent.VK_T:
                t = false;
                tTape = true;
                break;
            case KeyEvent.VK_Y:
                y = false;
                yTape = true;
                break;
            case KeyEvent.VK_LEFT:
                gauche = false;
                gaucheTape = true;
                break;
            case KeyEvent.VK_RIGHT:
                droite = false;
                droiteTape = true;
                break;
            case KeyEvent.VK_UP:
                haut = false;
                hautTape = true;
                break;
            case KeyEvent.VK_DOWN:
                bas = false;
                basTape = true;
                break;
            case KeyEvent.VK_A:
                a = false;
                aTape = true;
                break;
            case KeyEvent.VK_D:
                d = false;
                dTape = true;
                break;
            case KeyEvent.VK_E:
                e = false;
                eTape = true;
                break;
            case KeyEvent.VK_K:
                k = false;
                kTape = true;
                break;
            case KeyEvent.VK_L:
                l = false;
                lTape = true;
                break;
            case KeyEvent.VK_M:
                m = false;
                mTape = true;
                break;
            case KeyEvent.VK_O:
                o = false;
                oTape = true;
                break;
            case KeyEvent.VK_Q:
                q = false;
                qTape = true;
                break;
            case KeyEvent.VK_S:
                s = false;
                sTape = true;
                break;
            case KeyEvent.VK_Z:
                z = false;
                zTape = true;
                break;
        }
    }

    /**
     * KeyTyped method (not used in this implementation)
     */
    @Override
    public void keyTyped ( KeyEvent k ) {
    }

    /**
     * KeyPressed method to handle key press events
     */
    @Override
    public void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_F:
                f = true;
                break;
            case KeyEvent.VK_G:
                g = true;
                break;
            case KeyEvent.VK_H:
                h = true;
                break;
            case KeyEvent.VK_R:
                r = true;
                break;
            case KeyEvent.VK_T:
                t = true;
                break;
            case KeyEvent.VK_Y:
                y = true;
                break;
            case KeyEvent.VK_LEFT:
                gauche = true;
                break;
            case KeyEvent.VK_RIGHT:
                droite = true;
                break;
            case KeyEvent.VK_UP:
                haut = true;
                break;
            case KeyEvent.VK_DOWN:
                bas = true;
                break;
            case KeyEvent.VK_A:
                a = true;
                break;
            case KeyEvent.VK_D:
                d = true;
                break;
            case KeyEvent.VK_E:
                e = true;
                break;
            case KeyEvent.VK_K:
                k = true;
                break;
            case KeyEvent.VK_L:
                l = true;
                break;
            case KeyEvent.VK_M:
                m = true;
                break;
            case KeyEvent.VK_O:
                o = true;
                break;
            case KeyEvent.VK_Q:
                q = true;
                break;
            case KeyEvent.VK_S:
                s = true;
                break;
            case KeyEvent.VK_Z:
                z = true;
                break;
            default:
                System.out.println(KeyEvent.getKeyText(key.getKeyCode()));
        }
    }
}