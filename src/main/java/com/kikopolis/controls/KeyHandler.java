package com.kikopolis.controls;

import com.google.inject.Inject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyHandler implements KeyListener {
    private final Map<Integer, Boolean> pressed = new HashMap<>();
    
    @Inject
    public KeyHandler() {}
    
    public List<Integer> getPressed() {
        // get a list of all the keys that have true as value
        return List.copyOf(pressed.keySet().stream().filter(pressed::get).toList());
    }
    
    public boolean isPressed(final int keyCode) {
        return pressed.getOrDefault(keyCode, false);
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
        // do nothing
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        pressed.put(e.getKeyCode(), true);
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
        pressed.put(e.getKeyCode(), false);
    }
}
