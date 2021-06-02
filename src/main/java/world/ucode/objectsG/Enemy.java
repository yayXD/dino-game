package world.ucode.objectsG;

import java.awt.*;

public abstract class Enemy {
    public abstract Rectangle getBound();
    public abstract void draw(Graphics graf);
    public abstract void update(float speed);
    public abstract boolean isOutOfScreen();
    public abstract boolean isOver();
    public abstract boolean isScoreGot();
    public abstract void setisScoreGot(boolean isScoreGot);
    public abstract int isWatEnemy(); // 1 - Cactus ||  2 - Pterodactyl
}
