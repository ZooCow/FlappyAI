import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GamePanel extends JPanel implements Runnable {

    private Game game;
    public AI player;

    public GamePanel() {
        game = new Game();
        new Thread(this).start();
        player = new AI();
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);


        g2D.setColor(Color.BLACK);

        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g2D.drawString("(" + game.getBirdX() + "," + game.getBirdY()+")", game.getBirdX()+25, game.getBirdY()+50);

        g2D.drawString("N (" + game.getPipeFrontX() + "," + game.getNorthPipeBottom() + ")", game.getPipeFrontX() - 5, game.getNorthPipeBottom() - 5);
        g2D.drawString("N (" + game.getPipeRearX() + "," + game.getNorthPipeBottom() + ")", game.getPipeRearX() + 5, game.getNorthPipeBottom() - 5);

        g2D.drawString("S (" + game.getPipeFrontX() + "," + game.getSouthPipeTop() + ")", game.getPipeFrontX() - 50, game.getSouthPipeTop() + 5);
        g2D.drawString("S (" + game.getPipeRearX() + "," + game.getSouthPipeTop() + ")", game.getPipeFrontX() + 50, game.getSouthPipeTop() + 5);

        if(false) {
            g2D.drawString("100", 75, 100);
            g2D.drawString("200", 75, 200);
            g2D.drawString("250", 75, 250);
            g2D.drawString("300", 75, 300);
            g2D.drawString("350", 75, 350);
            g2D.drawLine(100, 100, 300, 100);
            g2D.drawLine(100, 200, 300, 200);
            g2D.drawLine(100, 250, 300, 250);
            g2D.drawLine(100, 300, 300, 300);
            g2D.drawLine(100, 350, 300, 350);
            g2D.drawLine(100, 400, 300, 400);
        }

        if (!game.started) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press SPACE to start", 150, 240);
        } else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            g2D.drawString("Score: " + Integer.toString(game.score), 5, 445);
            g2D.drawString("Fitness: " + Integer.toString(game.fitness), 5, 465);
        }

        if (game.gameover) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
        }

    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(20);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
