package world.ucode.userinterface;

import org.w3c.dom.ls.LSOutput;
import world.ucode.objectsG.*;
import world.ucode.util.Resource;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class GScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    public static final float GROUNDY = 110;

    public float gravity = 0.1f;

    private MainCharacter mainCharacter;
    private Thread thr;
    private Land land;
    private Clouds clouds;
    private EnemiesManager enemiesManager;
    private int score;
    private int hightScore;

    private boolean downArrowReleased = true;

    private int GState = GAME_FIRST_STATE;
    private float speed = 2f;

    private BufferedImage imageGameOverScreen;
    private BufferedImage imageGameOverButton;
    private AudioClip scoreUpSound;
    private AudioClip endGameSound;
    private AudioClip jumpSound;

    public GScreen() {
        thr = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        land = new Land(this);
        clouds = new Clouds();
        enemiesManager = new EnemiesManager(mainCharacter, this);
        imageGameOverScreen = Resource.getResourceImage("data/gameover_text.png");
        imageGameOverButton = Resource.getResourceImage("data/replay_button.png");
        try {
            scoreUpSound = Applet.newAudioClip(new URL("file", "", "data/scoreup.wav"));
            endGameSound = Applet.newAudioClip(new URL("file", "", "data/dead.wav"));
            jumpSound = Applet.newAudioClip(new URL("file", "", "data/jump.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void startG() {
        thr.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                update();
                repaint();
                Thread.sleep(10);
            } catch(InterruptedException err) {
                err.printStackTrace();
            }
        }
    }

    public void update() {
        if(GState == GAME_PLAY_STATE) {
            clouds.update(speed);
            land.update(speed);
            enemiesManager.update(speed);
            mainCharacter.update(gravity, downArrowReleased);
            if(!mainCharacter.getAlive()) {
                GState = GAME_OVER_STATE;
                endGameSound.play();
            }
        }
    }

    public void countSpeed(int score) {
        speed += (float)score / 30;
    }

    public void plusScore(int score) {
        this.score += score;
        scoreUpSound.play();
    }

    @Override
    public void paint(Graphics graf) {
        graf.setColor(Color.decode("#f7f7f7"));
        graf.fillRect(0, 0, getWidth(), getHeight());

        if(GState == GAME_FIRST_STATE) {
            mainCharacter.draw(graf);
            graf.drawString("Press SPACE to start the game", 100, 85);
        } else if(GState == GAME_PLAY_STATE) {
            clouds.draw(graf);
            land.draw(graf);
            enemiesManager.draw(graf);
            mainCharacter.draw(graf);
            graf.drawString("HI: " + String.valueOf(hightScore) + " " + String.valueOf(score), 500, 20);
        } else if(GState == GAME_OVER_STATE) {
            clouds.draw(graf);
            land.draw(graf);
            enemiesManager.draw(graf);
            mainCharacter.draw(graf);
            graf.drawString("HI: " + String.valueOf(hightScore) + " " + String.valueOf(score), 500, 20);
            graf.drawImage(imageGameOverScreen, 200, 50, null);
            graf.drawImage(imageGameOverButton, 280, 80, null);
        }
    }

    private void resetGame() {
        mainCharacter.setAlive(true);
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        enemiesManager.reset();
        if(hightScore < score) {
            hightScore = score;
        }
        score = 0;
        speed = 2;
        if(downArrowReleased == false) {
            gravity = gravity / 10;
        }
        downArrowReleased = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !mainCharacter.getDown()) {
            if(GState == GAME_FIRST_STATE) {
                GState = GAME_PLAY_STATE;
            } else if(GState == GAME_PLAY_STATE) {
                if(mainCharacter.getY() + 5 == GROUNDY - mainCharacter.getBound().getHeight()) {
                    mainCharacter.jump();
                    jumpSound.play();
                }
            } else if(GState == GAME_OVER_STATE) {
                GState = GAME_PLAY_STATE;
                resetGame();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && downArrowReleased == true) {
            if(GState == GAME_PLAY_STATE) {
                gravity = gravity * 10;
                if(mainCharacter.getY() + 5 == GROUNDY - mainCharacter.getBound().getHeight()) {
                    mainCharacter.setY(mainCharacter.getY() + 17);
                }
            }
            downArrowReleased = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if((GState == GAME_PLAY_STATE && downArrowReleased == false) || (GState == GAME_OVER_STATE && downArrowReleased == false)) {
                gravity = gravity / 10;
            }
            downArrowReleased = true;
        }
    }
}
