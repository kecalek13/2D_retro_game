package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    // CAMERA/SCREEN X, Y ON THE MAP
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        // Set camera/screen X, Y
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // Set player's solid area
        solidArea = new Rectangle(8, 16, 32, 32);

        // Call functions
        setDefaultValues();
        getPlayerImage();
    }

    // GETS PLAYER IMAGES
    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    // SET PLAYER'S DEFAULT VALUES
    public void setDefaultValues() {
        WorldX = gp.tileSize * 23;
        WorldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    // Update method called in GamePanel's run method 60 times per second
    public void update() {
        // If key W or S or A or D is pressed call this
        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            // PLAYER DIRECTION CHANGE
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cCheck.checkTile(this);

            // IF COLLISION IS FALSE PLAYER CAN MOVE
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        WorldY -= speed;
                        break;
                    case "down":
                        WorldY += speed;
                        break;
                    case "left":
                        WorldX -= speed;
                        break;
                    case "right":
                        WorldX += speed;
                        break;
                }

            }

            // MAP RENDERING
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    // Player drawing function
    public void draw(Graphics2D g2) {
        // Player
        BufferedImage image = null;

        // Different player's positions
        switch (direction) {
            case "up":                  // Up position
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":                // Down position
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":                // Left position
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":               // Right position
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        // Draw player
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
