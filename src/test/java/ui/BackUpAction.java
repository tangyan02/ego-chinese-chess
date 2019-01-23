package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackUpAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MoveRecord moveRecord = Start.stack.pop();
        if (moveRecord != null) {
            Start.gameMap.undoMove(moveRecord.from, moveRecord.x, moveRecord.y, moveRecord.to);
        }
        MapDrawer.drawMap(Start.gameMap);
    }

}
