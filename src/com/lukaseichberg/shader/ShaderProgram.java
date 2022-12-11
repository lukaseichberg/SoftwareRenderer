package com.lukaseichberg.shader;

import com.lukaseichberg.structs.Uniform;
import com.lukaseichberg.structs.Vertex;

public class ShaderProgram {

	private Shader vertexShader;
	private Shader fragmentShader;
	private Uniform uniform;
	
	public ShaderProgram(Shader vs, Shader fs/*, Uniform uniform*/) {
		vertexShader = vs;
		fragmentShader = fs;
		
		uniform = new Uniform();
	}
	
	public Vertex processVertex(Vertex v) {
		return vertexShader.main(v, uniform);
	}
	
	public Vertex processFragment(Vertex v) {
		return fragmentShader.main(v, uniform);
	}
}
