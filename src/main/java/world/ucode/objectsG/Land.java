package world.ucode.objectsG;

import world.ucode.userinterface.GScreen;
import world.ucode.util.Resource;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static world.ucode.userinterface.GScreen.GROUNDY;

public class Land {
    private List<ImageLand> listImage;
    private BufferedImage imageLand1;
    private BufferedImage imageLand2;
    private BufferedImage imageLand3;
    private Random random;

    public Land(GScreen game) {
        random = new Random();
        imageLand1 = Resource.getResourceImage("data/land1.png");
        imageLand2 = Resource.getResourceImage("data/land2.png");
        imageLand3 = Resource.getResourceImage("data/land3.png");
        listImage = new ArrayList<ImageLand>();
        int numberOfLandTitle = 600 / imageLand1.getWidth() + 2;
        for(int q = 0; q < numberOfLandTitle; q++) {
            ImageLand imageLand = new ImageLand();
            imageLand.posX = (int)(q * imageLand1.getWidth());
            imageLand.image = getImageLand();
            listImage.add(imageLand);
        }
    }

    public void update(float speed) {
        for(ImageLand imageLand : listImage) {
            imageLand.posX -= (int)speed;
        }
        ImageLand firstElement = listImage.get(0);
        if(listImage.get(0).posX + imageLand1.getWidth() < 0) {
            firstElement.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
            listImage.add(firstElement);
            listImage.remove(0);
        }
    }

    public void draw(Graphics graf) {
        for(ImageLand imageLand : listImage) {
            graf.drawImage(imageLand.image, imageLand.posX, (int)GROUNDY - 20, null);
        }
    }

    private BufferedImage getImageLand() {
        int q = random.nextInt(10);
        if(q == 0) {
            return imageLand1;
        } else if(q == 1) {
            return imageLand3;
        }
        return imageLand2;
    }

    private class ImageLand {
        int posX;
        BufferedImage image;
    }
}
