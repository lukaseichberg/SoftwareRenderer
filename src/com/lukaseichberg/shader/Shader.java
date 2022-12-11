package com.lukaseichberg.shader;

import com.lukaseichberg.buffer.FrameBufferInterface;
import com.lukaseichberg.maths.Vec2f;
import com.lukaseichberg.maths.Vec3f;

public abstract class Shader implements ShaderInterface {
	
	public Vec3f sample(FrameBufferInterface<Vec3f> texture, Vec2f uv) {
		int x = (int) (uv.x * texture.getWidth());
		int y = (int) (uv.y * texture.getHeight());
		x = Math.floorMod(x, texture.getWidth());
		y = Math.floorMod(y, texture.getHeight());
		return texture.get(x, y);
	}

}
