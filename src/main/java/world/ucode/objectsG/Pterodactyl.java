package world.ucode.objectsG;

import world.ucode.util.Animation;
import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pterodactyl extends Enemy{
    private int posX;
    private int posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    private Animation pterorFly;
    private boolean isScoreGot = false;

    public Pterodactyl(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        pterorFly = new Animation(200);
        pterorFly.addFramePteror(Resource.getResourceImage("data/pteror.jpg"));
        pterorFly.addFramePteror(Resource.getResourceImage("data/pteror1.jpg"));
        Random random = new Random();
        posX = 200;
        posY = random.nextInt((80 - 10) + 1) + 10;;
        rect = new Rectangle();
    }

    public void update(float speed) {
        pterorFly.updatePteror();
        posX -= (int)speed;
        rect.x = posX;
        rect.y = posY;
        rect.width = pterorFly.getFramePteror().getWidth();
        rect.height = pterorFly.getFramePteror().getHeight();
    }

    @Override
    public Rectangle getBound() {
        return rect;
    }

    @Override
    public void draw(Graphics graf) {
        graf.drawImage(pterorFly.getFramePteror(), posX, posY, null);
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    @Override
    public boolean isOutOfScreen() {
        return (posX + pterorFly.getFramePteror().getWidth() < 0);
    }

    @Override
    public boolean isOver() {
        return (mainCharacter.getX() > posX);
    }

    @Override
    public boolean isScoreGot() {
        return isScoreGot;
    }

    @Override
    public void setisScoreGot(boolean isScoreGot) {
        this.isScoreGot = isScoreGot;
    }

    @Override
    public int isWatEnemy() {
        return 2;
    }
}
