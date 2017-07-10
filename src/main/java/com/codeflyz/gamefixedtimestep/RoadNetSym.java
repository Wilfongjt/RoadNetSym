package com.codeflyz.gamefixedtimestep;

import com.codeflyz.gamefixedtimestep.roads.GamePanel;
import com.codeflyz.gamefixedtimestep.roads.GameState;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 
 * @author jameswilfong
 *
 * Link is a set of indices to next and previous objects. Genaric linking.
 *
 * LinkList is a list of Links.
 *
 * Coordinate is a an x, y pair: x, y
 *
 * Feature is a list of Coordinates: idx, visible, color, interpolation
 *
 * MBR is a Feature containing a minimum Coordinate and a maximum Coordinate
 *
 * Road-Segment is a Feature containing the starting and end Coordinates: color,
 * speed maximum
 *
 * Road is a list of Road-segments : color, mbr, index of link indices :
 *
 * Road-Network is a list of roadlists: color, mbr, list of road to road links
 *
 * Driver is a set of floating point numbers representing the psychological
 * state of the driver: speedPsyc
 *
 * Car is a Feature: RoadNetwork, RoadList, RoadSegment, Driver, width, height,
 * xVelocity, yVelocity, speed, 
 *  
 *
 *
 *
 *
 *
 *
 *
 */
public class RoadNetSym extends JFrame implements ActionListener {
    private GameState gameState = (GameState)new GameState().setBackround(getBackground()); 
    private GamePanel gamePanel = new GamePanel(gameState);
    private JButton startButton = new JButton("Start");
    private JButton quitButton = new JButton("Quit");
    private JButton pauseButton = new JButton("Pause");
    private boolean running = false;
    private boolean paused = false;
    private int fps = 60;  // frames per second
    //private int frameCount = 0;
    
    
    
    public RoadNetSym() {
        super("Network Viewer");

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        p.add(startButton);
        p.add(pauseButton);
        p.add(quitButton);
        cp.add(gamePanel, BorderLayout.CENTER);
        cp.add(p, BorderLayout.SOUTH);
        setSize(500, 500);

        startButton.addActionListener(this);
        quitButton.addActionListener(this);
        pauseButton.addActionListener(this);
    }

    public static void main(String[] args) {
        RoadNetSym glt = new RoadNetSym();
        glt.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == startButton) {
            running = !running;
            if (running) {
                startButton.setText("Stop");
                runGameLoop();
            } else {
                startButton.setText("Start");
            }
        } else if (s == pauseButton) {
            paused = !paused;
            if (paused) {
                pauseButton.setText("Unpause");
            } else {
                pauseButton.setText("Pause");
            }
        } else if (s == quitButton) {
            System.exit(0);
        }
    }

    //Starts a new thread and runs the game loop in it.
    public void runGameLoop() {
        Thread loop = new Thread() {
            public void run() {
                gameLoop();
            }
        };
        loop.start();
    }

    //Only run this in another Thread!
    private void gameLoop() {
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;//5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused) {
                //Do as many game updates as we need to, potentially playing catchup.
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    updateGame();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                double interpolation = Math.min(1.0f,  ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
                //System.out.println("interpolation: " + interpolation);
                //jball.setInterpolation(interpolation);
                drawGame(interpolation);

                lastRenderTime = now;

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime) {
                    System.out.println("NEW SECOND " + thisSecond + " " + gameState.getFrameCount());
                    fps = gameState.getFrameCount();

                    gameState.setFrameCount(0);
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }

                    now = System.nanoTime();
                }
            }
        }
    }

    private void updateGame() {
        gamePanel.update();
    }

    private void drawGame(double interpolation) {
        gamePanel.setInterpolation(interpolation);
        gamePanel.repaint();
    }


}
