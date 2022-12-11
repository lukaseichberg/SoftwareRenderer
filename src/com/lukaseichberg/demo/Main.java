package com.lukaseichberg.demo;

import com.lukaseichberg.buffer.ColorBuffer;
import com.lukaseichberg.buffer.DepthBuffer;
import com.lukaseichberg.display.Display;
import com.lukaseichberg.maths.Vec3f;
import com.lukaseichberg.maths.Vec4f;
import com.lukaseichberg.renderer.Renderer;
import com.lukaseichberg.shader.ShaderProgram;
import com.lukaseichberg.structs.Vertex;

public class Main {

	public static void main(String[] args) {
		int width	= 320;
		int height	= 240;
		int scale	= 4;
		
		Display display = new Display(width, height, scale, "Software Renderer -  Demo");

		// Get buffers of the shown window
		ColorBuffer colorBuffer = display.getColorBuffer();
		DepthBuffer depthBuffer = display.getDepthBuffer();

		// Set the buffers the Renderer should render to
		Renderer.setColorBuffer(colorBuffer);
		Renderer.setDepthBuffer(depthBuffer);
		
		// Create vertex and fragment shaders and a ShaderProgram
		VertexShader vertexShader = new VertexShader();
		FragmentShader fragmentShader = new FragmentShader();
		ShaderProgram shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
		
		// Tell the Renderer which ShaderProgram to use while rendering
		Renderer.bindShaderProgram(shaderProgram);

		// Create 3 vertices for a triangle.
		// Parameters specify how many Vec2s, Vec3s and Vec4s a vertex holds.
		// In this example we want to set a color for each vertex
		// so we need 1 Vec3
		Vertex v0 = new Vertex(0, 1, 0);
		Vertex v1 = new Vertex(0, 1, 0);
		Vertex v2 = new Vertex(0, 1, 0);
		
		// Set 3D position for the vertices
		v0.pos = new Vec4f(0, 0.5f, 1f, 1);
		v1.pos = new Vec4f(0.5f, -0.5f, 1f, 1);
		v2.pos = new Vec4f(-0.5f, -0.5f, 1f, 1);
		
		// Set a Vec3 for the color of the vertex 
		v0.vec3[0] = new Vec3f(1, 0, 0);	// red
		v1.vec3[0] = new Vec3f(0, 1, 0);	// green
		v2.vec3[0] = new Vec3f(0, 0, 1);	// blue
		
		boolean running = true;
		while (running) {
			// Clear the color and depth buffer
			colorBuffer.fill(new Vec3f(0, 0, 0));
			depthBuffer.fill(Float.MAX_VALUE);
			
			// Render a triangle using the currently bound ShaderProgram
			Renderer.renderTriangle(v0, v1, v2);
			
			// Update pixels on screen
			display.update();
		}
	}

}
