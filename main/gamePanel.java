package main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class gamePanel extends JPanel {

    final int orignalTileSize = 16;
    final int scale = 4;

    final int tileSize = orignalTileSize * scale; // 64 x 64 per tile
    // 16:9 Width to height
    final int maxScreenCol = 16;
    final int maxScreenRow = 9;
    // 1024 x 576
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public gamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true); // May improve performance
    }
}
