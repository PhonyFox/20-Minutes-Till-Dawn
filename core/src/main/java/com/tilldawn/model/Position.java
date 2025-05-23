package com.tilldawn.model;

public class Position {
    private float x;
    private float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void add(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
