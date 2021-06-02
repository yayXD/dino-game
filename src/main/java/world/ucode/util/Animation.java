package world.ucode.util;

import java.awt.image.BufferedImageFilter;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private List<BufferedImage> framesStandart;
    private List<BufferedImage> framesDown;
    private List<BufferedImage> frameEnd;
    private List<BufferedImage> framePteror;
    private int frameIndex = 0;
    private int deltaTime;
    private long previousTime;

    public boolean isDown = false;

    public Animation(int deltaTime) {
        this.deltaTime = deltaTime;
        framesStandart = new ArrayList<BufferedImage>();
        framesDown = new ArrayList<BufferedImage>();
        frameEnd = new ArrayList<BufferedImage>();
        framePteror = new ArrayList<BufferedImage>();
    }

    public void updatePteror() {
        if (System.currentTimeMillis() - previousTime > deltaTime) {
            frameIndex++;
            if (frameIndex >= framePteror.size()) {
                frameIndex = 0;
            }
            previousTime = System.currentTimeMillis();
        }
    }

    public void update(boolean downArrowReleased, boolean isAlive) {
        if(isAlive == false) {
            frameIndex = 0;
            isDown = false;
        } else if(downArrowReleased == true) {
            isDown = false;
            if (System.currentTimeMillis() - previousTime > deltaTime) {
                frameIndex++;
                if (frameIndex >= framesStandart.size()) {
                    frameIndex = 0;
                }
                previousTime = System.currentTimeMillis();
            }
        } else {
            isDown = true;
            if (System.currentTimeMillis() - previousTime > deltaTime) {
                frameIndex++;
                if (frameIndex >= framesDown.size()) {
                    frameIndex = 0;
                }
                previousTime = System.currentTimeMillis();
            }
        }
    }

    public void addFrameStandart(BufferedImage frame) {
        framesStandart.add(frame);
    }

    public BufferedImage getFrameStandart() {
        if(framesStandart.size() > 0) {
            return framesStandart.get(frameIndex);
        }

        return null;
    }

    public void addFrameDown(BufferedImage frame) {
        framesDown.add(frame);
    }

    public BufferedImage getFrameDown() {
        if(framesDown.size() > 0) {
            return framesDown.get(frameIndex);
        }

        return null;
    }

    public void addFrameEnd(BufferedImage frame) {
        frameEnd.add(frame);
    }

    public BufferedImage getFrameEnd() {
        if(frameEnd.size() > 0) {
            return frameEnd.get(frameIndex);
        }

        return null;
    }

    public void addFramePteror(BufferedImage frame) {
        framePteror.add(frame);
    }

    public BufferedImage getFramePteror() {
        if(framePteror.size() > 0) {
            return framePteror.get(frameIndex);
        }

        return null;
    }
}
