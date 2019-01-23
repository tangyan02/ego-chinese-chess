package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackUpAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Start.stack.isEmpty()) {
            MoveRecord moveRecord = Start.stack.pop();
            Start.gameMap.undoMove(moveRecord.from, moveRecord.x, moveRecord.y, moveRecord.to);
        }
        MapDrawer.drawMap(Start.gameMap);
    }

}
