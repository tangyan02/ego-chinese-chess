package ui;

import cn.tangyancode.ego.chineseChess.core.GameMap;
import util.MapProvider;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Start {

    static JFrame frame = new JFrame("ego chinese ai");
    static GameMap gameMap = MapProvider.getGameMap("default.txt");

    public static void main(String[] args) throws InterruptedException {

        frame.setBounds(100, 100, 600, 600);
        frame.setLayout(null);

        JButton selfMoveButton = new JButton();
        selfMoveButton.setText("己方行动");
        selfMoveButton.setBounds(10, 500, 150, 40);
        frame.getContentPane().add(selfMoveButton);
        selfMoveButton.addActionListener(new SelfMoveAction());

        JButton OpponentMoveButton = new JButton();
        OpponentMoveButton.setText("对方行动");
        OpponentMoveButton.setBounds(200, 500, 150, 40);
        frame.getContentPane().add(OpponentMoveButton);
        OpponentMoveButton.addActionListener(new OpponentMoveAction());


        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Thread.sleep(1000);
        MapDrawer.drawMap(gameMap);
    }


}
