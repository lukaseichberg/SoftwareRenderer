package com.lukaseichberg.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lukaseichberg.buffer.FrameBufferInterface;
import com.lukaseichberg.maths.Vec3f;

public class Texture implements FrameBufferInterface<Vec3f> {
	
	private BufferedImage img;
	
	public Texture(String fileName) {
		File file = new File(fileName);
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void set(int x, int y, Vec3f color) {	// TODO: improve performance
		int r = (int) (color.x * 0xFF);
		int g = (int) (color.y * 0xFF);
		int b = (int) (color.z * 0xFF);
		int rgb = r << 16 | g << 8 | b;
		img.setRGB(x, y, rgb);
	}

	@Override
	public Vec3f get(int x, int y) {	// TODO: improve performance
		int color = img.getRGB(x, y);
		float r = (float) ((color >> 16) & 0xFF) / 0xFF;
		float g = (float) ((color >> 8) & 0xFF) / 0xFF;
		float b = (float) (color & 0xFF) / 0xFF;
		return new Vec3f(r, g, b);
	}
	
	public int getWidth() {
		return img.getWidth();
	}
	
	public int getHeight() {
		return img.getHeight();
	}

}
