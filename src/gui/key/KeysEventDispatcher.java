/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.key;

import java.awt.KeyEventDispatcher;
import java.util.ArrayList;
import key.Key;

/**
 *
 * @author armin
 */
public class KeysEventDispatcher extends ArrayList<Key> implements KeyEventDispatcher {

    public KeysEventDispatcher() {
    }

    public void add(int keyCode, key.KeyEvent.Mode mode) {
        add(new Key(keyCode, mode));
    }

    @Override
    public boolean dispatchKeyEvent(java.awt.event.KeyEvent e) {
        for (Object object : this) {
            Key key = (Key) object;
            if (key.getCode() == e.getKeyCode()) {
                key.addEvent(e);
            }
        }
        return true;
    }
}
