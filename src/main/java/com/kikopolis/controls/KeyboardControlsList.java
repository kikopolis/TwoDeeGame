package com.kikopolis.controls;

import java.awt.event.KeyEvent;
import java.util.List;

public abstract class KeyboardControlsList {
    public static final List<Integer> MOVE_UP = List.of(KeyEvent.VK_UP, KeyEvent.VK_W);
    public static final List<Integer> MOVE_DOWN = List.of(KeyEvent.VK_DOWN, KeyEvent.VK_S);
    public static final List<Integer> MOVE_LEFT = List.of(KeyEvent.VK_LEFT, KeyEvent.VK_A);
    public static final List<Integer> MOVE_RIGHT = List.of(KeyEvent.VK_RIGHT, KeyEvent.VK_D);
}
