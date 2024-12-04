package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JPanel implements MenuObservable {
    private List<MenuObserver> observers = new ArrayList<>();
    private Image backgroundImage;

    public MenuPanel() {
        setLayout(new BorderLayout());
        JButton startButton = new JButton("Click to Start");
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 120));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers();
            }
        });
        add(startButton, BorderLayout.CENTER);
        setPreferredSize(new Dimension(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT));

        backgroundImage = new ImageIcon("resources/menu/main.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void addObserver(MenuObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MenuObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (MenuObserver observer : observers) {
            observer.startGame();
        }
    }
}
