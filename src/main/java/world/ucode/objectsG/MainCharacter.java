package world.ucode.objectsG;

import world.ucode.util.Animation;
import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

import static world.ucode.userinterface.GScreen.GROUNDY;

public class MainCharacter {
    private float x = 0;
    private float y = 0;
    private float speedY = 1;
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true;

    public MainCharacter() {
        characterRun = new Animation(200);
        characterRun.addFrameStandart(Resource.getResourceImage("data/main-character1.png"));
        characterRun.addFrameStandart(Resource.getResourceImage("data/main-character2.png"));
        characterRun.addFrameDown(Resource.getResourceImage("data/main-character5.png"));
        characterRun.addFrameDown(Resource.getResourceImage("data/main-character6.png"));
        characterRun.addFrameEnd(Resource.getResourceImage("data/main-character4.png"));
        rect = new Rectangle();
    }

    public void update(float gravity, boolean downArrowReleased) {
        characterRun.update(downArrowReleased, isAlive);
        if(characterRun.isDown == false) {
            if (y >= GROUNDY - characterRun.getFrameStandart().getHeight()) {
                speedY = 0;
                y = GROUNDY - characterRun.getFrameStandart().getHeight();
            } else {
                speedY += gravity;
                y += speedY;
            }
            rect.x = (int)x;
            rect.y = (int)y;
            rect.width = characterRun.getFrameStandart().getWidth() - 5;
            rect.height = characterRun.getFrameStandart().getHeight() - 5;
        } else if(characterRun.isDown == true) {
            if (y >= GROUNDY - characterRun.getFrameDown().getHeight()) {
                speedY = 0;
                y = GROUNDY - characterRun.getFrameDown().getHeight();
            } else {
                speedY += gravity;
                y += speedY;
            }
            rect.x = (int)x;
            rect.y = (int)y;
            rect.width = characterRun.getFrameDown().getWidth() - 5;
            rect.height = characterRun.getFrameDown().getHeight() - 5;
        }
    }

    public Rectangle getBound() {
        return rect;
    }

    public void draw(Graphics graf) {
        graf.setColor(Color.BLACK);
        //graf.drawRect((int)x, (int)y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());

        if(isAlive == false) {
            graf.drawImage(characterRun.getFrameEnd(), (int)x, (int)y, null);
        } else if(characterRun.isDown == false || this.getY() + 5 != GROUNDY - this.getBound().getHeight()) {
            graf.drawImage(characterRun.getFrameStandart(), (int)x, (int)y, null);
        } else if(characterRun.isDown == true) {
            graf.drawImage(characterRun.getFrameDown(), (int)x, (int)y, null);
        }
    }

    public void jump() {
        speedY = -4;
        y += speedY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public boolean getDown() {
        return this.characterRun.isDown;
    }
}
