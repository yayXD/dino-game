package world.ucode.userinterface;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GWindow extends JFrame {
    private GScreen gScreen;

    public GWindow() {
        super("Game TRex from Google Chrome browser is made in Java language(in China)");
        setSize(600, 175);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        gScreen = new GScreen();
        add(gScreen);
        addKeyListener(gScreen);
    }

    public void startG() {
        gScreen.startG();
    }
}

