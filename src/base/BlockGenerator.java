package base;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockGenerator {


    private final static int WHITE_BLOCK = 0;
    private final static int YELLOW_BLOCK = 1;
    private final static int PINK_BLOCK = 2;
    private final static int BLUE_BLOCK = 3;
    private final static int RED_BLOCK = 4;
    private final static int GREEN_BLOCK = 5;
    private final static int CIAN_BLOCK = 6;
    private final static int ORANGE_BLOCK = 7;
    private final static int GRAY_BLOCK = 8;
    private final static int GOLD_BLOCK = 9;
    private final static int NULL_BLOCK = 10;
    private final static int MAX_BLOCK_HORIZONTAL = 13;
    private final static int MAX_BLOCK_VERTICAL = 6;

    private BufferedImage[] blockList;
    private BufferedImage blockGameMatrix[][];


    public BlockGenerator(BufferedImage[] blockList) {
        this.blockList = blockList;
        this.blockGameMatrix = new BufferedImage[MAX_BLOCK_HORIZONTAL][MAX_BLOCK_VERTICAL];
    }

    public BufferedImage[][] getStage1() {
        for (int i = 0; i < blockGameMatrix.length; i++) {
            for (int j = 0; j < blockGameMatrix[i].length; j++) {
                switch (j) {
                    case 0:
                        this.blockGameMatrix[i][j] = this.blockList[GRAY_BLOCK];
                        break;
                    case 1:
                        this.blockGameMatrix[i][j] = this.blockList[RED_BLOCK];
                        break;
                    case 2:
                        this.blockGameMatrix[i][j] = this.blockList[YELLOW_BLOCK];
                        break;
                    case 3:
                        this.blockGameMatrix[i][j] = this.blockList[BLUE_BLOCK];
                        break;
                    case 4:
                        this.blockGameMatrix[i][j] = this.blockList[PINK_BLOCK];
                        break;
                    case 5:
                        this.blockGameMatrix[i][j] = this.blockList[GREEN_BLOCK];
                        break;
                    default:
//                        this.blockGameMatrix[i][j] = this.blockList[NULL_BLOCK];
                        break;
                }
            }
        }
        return this.blockGameMatrix;
    }

}
