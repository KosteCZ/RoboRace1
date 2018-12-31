package cz.koscak.jan.gui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Images {
	
	public static Map<String, BufferedImage> mapOfImages = new HashMap<String, BufferedImage>();
	
	static {
		loadImages();
	}
	
	public static void loadImages() {
		
		mapOfImages.put("background", getImage("background"));
		mapOfImages.put("robot", getImage("robot1"));
		mapOfImages.put("belt", getImage("beltRL1"));
		mapOfImages.put("hole", getImage("hole1"));
		mapOfImages.put("fastbelt", getImage("fastbeltRL1"));
		//mapOfImages.put("wall", getImage("wallT4"));
		mapOfImages.put("wall", getImage("wall"));
		mapOfImages.put("rotateR7", getImage("rotateR7"));
		mapOfImages.put("rotateL7", getImage("rotateL7"));
		mapOfImages.put("target1", getImage("target1"));
		mapOfImages.put("repair", getImage("repair2"));
		mapOfImages.put("fullRepair", getImage("fullRepair1"));
		mapOfImages.put("laser1", getImage("laser1"));
		mapOfImages.put("laser2", getImage("laser2"));
		mapOfImages.put("laser3", getImage("laser3"));
		mapOfImages.put("laserBeam1", getImage("laserBeam1"));
		mapOfImages.put("laserBeam2", getImage("laserBeam2"));
		mapOfImages.put("laserBeam3", getImage("laserBeam3"));
		mapOfImages.put("start", getImage("start5"));
		
		createImageInAll4Directions("robot");
		createImageInAll4Directions("wall");		
		createImageInAll4Directions("laser1");		
		createImageInAll4Directions("laser2");		
		createImageInAll4Directions("laser3");		
		createImageInAll4Directions("laserBeam1");
		createImageInAll4Directions("laserBeam2");
		createImageInAll4Directions("laserBeam3");
		
		mapOfImages.put("background1", getImage("background1"));
		mapOfImages.put("robot1", getImage("robot1"));
		mapOfImages.put("beltRL1", getImage("beltRL1"));
		mapOfImages.put("hole1", getImage("hole1"));
		mapOfImages.put("fastbeltRL1", getImage("fastbeltRL1"));

		mapOfImages.put("wallT1", getImage("wallT1"));
		mapOfImages.put("wallT2", getImage("wallT2"));
		mapOfImages.put("wallT3", getImage("wallT3"));
		mapOfImages.put("wallT4", getImage("wallT4"));
		
	}
	
	public static void createImageInAll4Directions(String name) {
		
		mapOfImages.put(name + "L", Images.rotateImageByDegrees(mapOfImages.get(name), 90.0, null));
		mapOfImages.put(name + "T", Images.rotateImageByDegrees(mapOfImages.get(name), 180.0, null));
		mapOfImages.put(name + "R", Images.rotateImageByDegrees(mapOfImages.get(name), 270.0, null));
		mapOfImages.put(name + "D", Images.rotateImageByDegrees(mapOfImages.get(name), 0.0, null));
		
	}
	
    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle, ImageObserver imageObserver) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, imageObserver);
        g2d.dispose();

        return rotated;
    }
	
	public static BufferedImage getPreloadedImage(String name) {
		
		return mapOfImages.get(name);
		
	}
	
	public static BufferedImage getImage(String name) {
		
		BufferedImage image = null;
		
		try {
			
			InputStream in = new FileInputStream(new File("img/" + name + ".png"));
			
			image = ImageIO.read(in);
			
		} catch (IOException e) { 
			System.err.println("Error: " + e);
		}
		
		return image;
		
	}

}
