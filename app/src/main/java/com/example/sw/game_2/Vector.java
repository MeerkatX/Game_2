package com.example.sw.game_2;

/**
 * Created by sw
 * time 2016/1/22.${time}
 */
public class Vector {
    public float x;
    public float y;

    public Vector() {

    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    public String toString() {
        return "Vector{" + "x+" + "y" + "=" + "}";
    }

    public Vector add(Vector vector) {
        x += vector.x;
        y += vector.y;
        return this;
    }

    public Vector sub(Vector vector) {
        x -= vector.x;
        y -= vector.y;
        return this;
    }

    public Vector mult(float n) {
        x *= n;
        y *= n;
        return this;
    }

    public Vector div(float n) {
        if (n != 0) {
            x /= n;
            y /= n;
        }
        return this;
    }

    public void normalize() {
        div(mag());
    }

    public float mag() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void Limit(float max) {
        if (max * max <= mag() * mag()) {
            normalize();
            mult(max);
        }

    }
}
