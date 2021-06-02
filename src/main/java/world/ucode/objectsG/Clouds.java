package world.ucode.objectsG;

import world.ucode.util.Resource;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Clouds {
    private BufferedImage cloudImage;
    private List<Cloud> listClouds;

    public Clouds() {
        cloudImage = Resource.getResourceImage("data/cloud.png");
        listClouds = new ArrayList<Cloud>();

        Cloud cloud1 = new Cloud();
        cloud1.posX = 100;
        cloud1.posY = 50;
        listClouds.add(cloud1);
        Cloud cloud2 = new Cloud();
        cloud2.posX = 200;
        cloud2.posY = 20;
        listClouds.add(cloud2);
        Cloud cloud3 = new Cloud();
        cloud3.posX = 300;
        cloud3.posY = 40;
        listClouds.add(cloud3);
        Cloud cloud4 = new Cloud();
        cloud4.posX = 400;
        cloud4.posY = 30;
        listClouds.add(cloud4);
        Cloud cloud5 = new Cloud();
        cloud5.posX = 500;
        cloud5.posY = 10;
        listClouds.add(cloud5);
        Cloud cloud6 = new Cloud();
        cloud6.posX = 600;
        cloud6.posY = 25;
        listClouds.add(cloud6);
    }

    public void update(float speed) {
        for(Cloud cloud : listClouds) {
            cloud.posX -= (int)speed / 2;
        }
        Cloud firstCloud = listClouds.get(0);
        Random random = new Random();
        if(firstCloud.posX + cloudImage.getWidth() < 0) {
            firstCloud.posX = 600;
            firstCloud.posY = random.nextInt((50 - 10) + 1) + 10;
            listClouds.remove(firstCloud);
            listClouds.add(firstCloud);
        }
    }

    public void draw(Graphics graf) {
        for(Cloud cloud : listClouds) {
            graf.drawImage(cloudImage, (int)cloud.posX, (int)cloud.posY, null);
        }
    }

    private class Cloud {
        float posX;
        float posY;
    }
}
