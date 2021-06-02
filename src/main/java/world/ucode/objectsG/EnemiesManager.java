package world.ucode.objectsG;

import world.ucode.userinterface.GScreen;
import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemiesManager {
    private List<Enemy> enemiesList;
    private Random random;

    private BufferedImage imageCactus1;
    private BufferedImage imageCactus2;
    private MainCharacter mainCharacter;
    private GScreen gameScreen;

    public EnemiesManager(MainCharacter mainCharacter, GScreen gameScreen) {
        this.mainCharacter = mainCharacter;
        this.gameScreen = gameScreen;
        enemiesList = new ArrayList<Enemy>();
        imageCactus1 = Resource.getResourceImage("data/cactus1.png");
        imageCactus2 = Resource.getResourceImage("data/cactus2.png");

        random = new Random();
        enemiesList.add(getRandomEnemy());
    }

    public void update(float speed) {
        for(Enemy e : enemiesList) {
            e.update(speed);
            if(e.isOver() && !e.isScoreGot()) {
                if(e.isWatEnemy() == 1) {
                    gameScreen.plusScore(10);
                    gameScreen.countSpeed(10);
                    e.setisScoreGot(true);
                }
            }
            if(e.getBound().intersects(mainCharacter.getBound())) {
                mainCharacter.setAlive(false);
            }
        }
        Enemy firstEnemy = enemiesList.get(0);
        if(firstEnemy.isOutOfScreen()) {
            enemiesList.remove(firstEnemy);
            enemiesList.add(getRandomEnemy());
        }
    }

    public void draw(Graphics graf) {
        for(Enemy e : enemiesList) {
            e.draw(graf);
        }
    }

    public void reset() {
        enemiesList.clear();
        enemiesList.add(getRandomEnemy());
    }

    private Cactus getRandomCactus() {
        Cactus cactus = new Cactus(mainCharacter);
        cactus.setX(600);
        if(random.nextBoolean()) {
            cactus.setCactusImage(imageCactus1);
        } else {
            cactus.setCactusImage(imageCactus2);
        }
        return cactus;
    }

    private Pterodactyl getPterodactyl() {
        Pterodactyl pteror = new Pterodactyl(mainCharacter);
        pteror.setX(600);

        return pteror;
    }

    private Enemy getRandomEnemy() {
        if(random.nextBoolean()) {
            return getRandomCactus();
        } else {
            return getPterodactyl();
        }
    }
}
