package view;

import controllers.GameController;
import controllers.ViewController;
import game.Game;

import javax.swing.*;

public class Window extends JFrame implements MenuObserver {
    private MenuPanel menuPanel;

    public Window() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Start with the MenuPanel
        menuPanel = new MenuPanel();
        menuPanel.addObserver(this);

        add(menuPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void startGame() {
        // Replace the MenuPanel with GamePanel
        getContentPane().removeAll();
        GamePanel gameView = ViewController.getInstance().getGamePanel();
        add(gameView);
        revalidate();
        repaint();
//        pack();
        Game game = GameController.getInstance().getGame();
        game.addObserver(gameView);
        gameView.requestFocus();
        new Thread(new Runnable() {
            public void run() {
                game.run();
            }
        }).start();
    }
}
