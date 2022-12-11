package com.lukaseichberg.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.lukaseichberg.buffer.ColorBuffer;
import com.lukaseichberg.buffer.DepthBuffer;
import com.lukaseichberg.maths.Vec3f;

public class Display {
	
	public JFrame frame;
	private BufferedImage image;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private int[] pixels;
	
	private int width, height, scale;
	private ColorBuffer colorBuffer;
	private DepthBuffer depthBuffer;
	
	public Canvas canvas;
	
	public Display(int width, int height, int scale, String title) {
		this.width 	= width;
		this.height = height;
		this.scale 	= scale;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		colorBuffer = new ColorBuffer(width, height);
		depthBuffer = new DepthBuffer(width, height);
		
		pixels = (((DataBufferInt) image.getRaster().getDataBuffer()).getData());
		
		Dimension size = new Dimension(width * scale, height * scale);
		
		canvas = new Canvas();
		canvas.setPreferredSize(size);
		canvas.setMinimumSize(size);
		canvas.setMaximumSize(size);
		canvas.createBufferStrategy(1);
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		bufferStrategy = canvas.getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
	}
	
	public void update() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Vec3f color = colorBuffer.get(x, y).clone();
				
				//	Clip values 0 - 1 
				color.x = color.x > 1 ? 1 : color.x < 0 ? 0 : color.x;
				color.y = color.y > 1 ? 1 : color.y < 0 ? 0 : color.y;
				color.z = color.z > 1 ? 1 : color.z < 0 ? 0 : color.z;
				
				//	Color Vec to Int
				int r = (int) (color.x * 0xFF);
				int g = (int) (color.y * 0xFF);
				int b = (int) (color.z * 0xFF);
				int c = r << 16 | g << 8 | b;
				pixels[y * width + x] = c;
			}
		}
		graphics.drawImage(image, 0, 0, width * scale, height * scale, null);
		bufferStrategy.show();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getScale() {
		return scale;
	}
	
	public ColorBuffer getColorBuffer() {
		return colorBuffer;
	}
	
	public DepthBuffer getDepthBuffer() {
		return depthBuffer;
	}

}
