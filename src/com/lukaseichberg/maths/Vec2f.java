package com.lukaseichberg.maths;

public class Vec2f {
	
	public float x, y;
	
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2f lerp(Vec2f v, float value) {
		return new Vec2f(
			Maths.lerp(x, v.x, value),
			Maths.lerp(y, v.y, value)
		);
	}
	
	public Vec2f add(Vec2f v) {
		return new Vec2f(
			x + v.x,
			y + v.y
		);
	}
	
	public void _add(Vec2f v) {
		x += v.x;
		y += v.y;
	}
	
	public Vec2f sub(Vec2f v) {
		return new Vec2f(
			x - v.x,
			y - v.y
		);
	}
	
	public void _sub(Vec2f v) {
		x -= v.x;
		y -= v.y;
	}

	public Vec2f mul(float scalar) {
		return new Vec2f(
			x * scalar,
			y * scalar
		);
	}
	
	public void _mul(float scalar) {
		x *= scalar;
		y *= scalar;
	}

	public Vec2f div(float scalar) {
		return new Vec2f(
			x / scalar,
			y / scalar
		);
	}
	
	public void _div(float scalar) {
		x /= scalar;
		y /= scalar;
	}

	public float mag() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float dot(Vec2f v) {
		return x * v.x + y * v.y;
	}

	public Vec2f normal() {
		return div(mag());
	}

	public void normalize() {
		_div(mag());
	}
	
	public void swap(Vec2f v) {
		Vec2f tmpV = new Vec2f(x, y);
		x = v.x;
		y = v.y;
		v.x = tmpV.x;
		v.y = tmpV.y;
	}
	
	public Vec2f clone() {
		return new Vec2f(x, y);
	}
	
	public String toString() {
		return "Vec2("+x+", "+y+")";
	}

}
