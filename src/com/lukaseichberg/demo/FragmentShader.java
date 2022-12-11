package com.lukaseichberg.demo;

import com.lukaseichberg.shader.Shader;
import com.lukaseichberg.structs.Uniform;
import com.lukaseichberg.structs.Vertex;

public class FragmentShader extends Shader {

	@Override
	public Vertex main(Vertex in, Uniform uniform) {
		Vertex out = new Vertex(0, 1, 0);
		out.pos = in.pos.clone();
		out.vec3[0] = in.vec3[0].clone();
		return out;
	}

}
