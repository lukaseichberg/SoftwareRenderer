package com.lukaseichberg.demo;

import com.lukaseichberg.shader.Shader;
import com.lukaseichberg.structs.Uniform;
import com.lukaseichberg.structs.Vertex;

public class VertexShader extends Shader {

	@Override
	public Vertex main(Vertex in, Uniform uniform) {
		return in.clone();
	}

}
