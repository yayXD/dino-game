package world.ucode.objectsG;

import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy{
    private BufferedImage cactusImage;
    private int posX;
    private int posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    private boolean isScoreGot = false;

    public Cactus(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        cactusImage = Resource.getResourceImage("data/cactus1.png");
        posX = 200;
        posY = 65;
        rect = new Rectangle();
    }

    public void update(float speed) {
        posX -= (int)speed;
        rect.x = posX;
        rect.y = posY;
        rect.width = cactusImage.getWidth();
        rect.height = cactusImage.getHeight();
    }

    @Override
    public Rectangle getBound() {
        return rect;
    }

    @Override
    public void draw(Graphics graf) {
        graf.drawImage(cactusImage, posX, posY, null);
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public void setCactusImage(BufferedImage image) {
        cactusImage = image;
        if(cactusImage.getWidth() == 49) {
            posY = 75;
        }
    }

    @Override
    public boolean isOutOfScreen() {
        return (posX + cactusImage.getWidth() < 0);
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
        return 1;
    }
}
