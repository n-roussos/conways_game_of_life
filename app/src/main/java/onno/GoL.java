package onno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class GoL {
    private static Logger LOGGER = LoggerFactory.getLogger(GoL.class);

    // five different seeds
    final int[][] line = new int[][]{{3, 3}, {3, 4}, {3, 5}};
    final int[][] glider = new int[][]{{1, 2}, {2, 3}, {3, 1}, {3, 2}, {3, 3}};
    final int[][] toadAndGlider = new int[][]{{41, 43}, {41, 44}, {41, 45}, {42, 42},
            {42, 43}, {42, 44}, {36, 35}, {37, 36}, {38, 34}, {38, 35}, {38, 36}};
    final int[][] pentadecathlon = new int[][]{{35, 30}, {36, 30}, {37, 29}, {37, 31},
            {38, 30}, {39, 30}, {40, 30}, {41, 30}, {42, 29}, {42, 31}, {43, 30}, {44, 30}};
    final int[][] grandParent = new int[][]{{53, 30}, {53, 31}, {52, 29}, {51, 29},
            {50, 29}};
    final int[][] Rpentomino = new int[][]{{30, 50}, {30, 51}, {30, 52}, {31, 50},
            {29, 51}};
    final int[][] gliderGun = new int[][]{
            {1, 5}, {1, 6}, {2, 5}, {2, 6}, {35, 3}, {35, 4}, {36, 3}, {36, 4},// two blocks
            {11, 5}, {11, 6}, {11, 7}, {12, 4}, {12, 8}, {13, 3}, {13, 9}, {14, 3}, {14, 9}, {15, 6},
            {16, 4}, {16, 8}, {17, 5}, {17, 6}, {17, 7}, {18, 6},// left side of gun
            {21, 3}, {21, 4}, {21, 5}, {22, 3}, {22, 4}, {22, 5}, {23, 2}, {23, 6}, {25, 1}, {25, 2},
            {25, 6}, {25, 7}};      // right side of gun
    final int[][] infiniteGrowth1 = new int[][]{{101, 101}, {101, 102}, {101, 103},
            {101, 105}, {102, 101}, {103, 104}, {103, 105}, {104, 102}, {104, 103}, {104, 105},
            {105, 101}, {105, 103}, {105, 105}};
    final int[][] infiniteGrowth2 = new int[][]{{31, 48}, {31, 46}, {32, 46}, {33, 44},
            {34, 44}, {35, 44}, {34, 42}, {35, 42}, {36, 42}, {35, 41}};


    private final GameOfLife game;
    private final Container parent;
    private final Canvas canvas;

    public static void main(String[] args) throws InterruptedException {
        new GoL();
    }

    public GoL() throws InterruptedException {
        game = new GameOfLife(glider);

        // Set-up the UI
        canvas = new MyCanvas();
        parent = new MyFrame();
        parent.add(canvas);
        parent.setVisible(true);

        // Playing the game
        while (true) {
//            game.start();
            game.tick();

            canvas.repaint();
            Thread.sleep(100);
        }
    }

    // The ui classes
    public class MyFrame extends Frame {
        static final int width = 600; // width of frame
        static final int height = 600; // height of frame

        public MyFrame() {
            super("Conway's Game Of Life");
            setVisible(false);
            setLayout(null);
            setSize(width + 20, height + 40);
            enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        }

        public void processWindowEvent(WindowEvent evt) {
            if (evt.getID() == WindowEvent.WINDOW_CLOSING) {
                setVisible(false);
                System.exit(1);
            }
        }
    }

    public class MyCanvas extends Canvas {
        private final int width = 19000; // width of grid
        private final int height = 11000; // height of grid          

        public MyCanvas() {
            setLocation(0, 32);
            setSize(10 * width + 10, 10 * height + 10);
            setBackground(Color.yellow);
            // Start and stop the game with the mouse
            addMouseListener(new MouseInputAdapter() {
                public void mouseClicked(MouseEvent e) {
                    // center the canvas in the frame around the click 
                    int x = (MyFrame.width / 2 - e.getX() > 0 ? 0 : MyFrame.width / 2 - e.getX());
                    int y = (MyFrame.height / 2 - e.getY() > 0 ? 0 : MyFrame.height / 2 - e.getY());
                    setLocation(x, 32 + y);
                }

                public void mouseExited(MouseEvent e) {
                    game.stop();
                }

                public void mouseEntered(MouseEvent e) {
                    game.start();
                }
            });
        }

        public void paint(Graphics g) {
            game.draw(g);
        }
    }
}


