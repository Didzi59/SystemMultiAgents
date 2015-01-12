package image;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageGetter {
	
	public static final Image IMAGE_SHARK = new ImageGetter().getImageShark();
	public static final Image IMAGE_FISH = new ImageGetter().getImageFish();
	private BufferedImage imageShark; 
	private BufferedImage imageFish; 
	
	private ImageGetter() {
		this.imageShark = null;
		try {                
			imageShark = ImageIO.read(this.getClass().getResource("shark.png"));
			imageFish = ImageIO.read(this.getClass().getResource("fish.png"));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Marche pas!");
		}
	}

	private Image getImageShark() {
		return this.imageShark;
	}
	
	private Image getImageFish() {
		return this.imageFish;
	}
}
