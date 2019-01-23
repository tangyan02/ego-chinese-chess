package ui;

import cn.tangyancode.ego.chineseChess.core.GameMap;
import cn.tangyancode.ego.chineseChess.entity.Unit;
import util.MapProvider;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

public class Start {

    static JFrame frame = new JFrame("ego chinese ai");
    static GameMap gameMap = MapProvider.getGameMap("default.txt");
    static Unit prepareUnit = null;
    static Stack<MoveRecord> stack = new Stack<>();

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


        JButton BackUpButton = new JButton();
        BackUpButton.setText("上一步");
        BackUpButton.setBounds(410, 500, 150, 40);
        frame.getContentPane().add(BackUpButton);
        BackUpButton.addActionListener(new BackUpAction());

        frame.addMouseListener(new FrameMouseAction());


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
