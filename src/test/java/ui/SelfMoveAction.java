package ui;

import cn.tangyancode.ego.chineseChess.core.ChessGame;
import cn.tangyancode.ego.chineseChess.entity.PlayResult;
import cn.tangyancode.ego.chineseChess.entity.Relation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelfMoveAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ChessGame chessGame = new ChessGame(new ArrayList<>(Start.gameMap.getUnits()));
        PlayResult playResult = chessGame.play(Relation.SELF);
        System.out.println(playResult);
        Start.stack.push(
                new MoveRecord(
                        playResult.move.unit,
                        Start.gameMap.getUnit(playResult.move.x, playResult.move.y),
                        playResult.move.unit.x,
                        playResult.move.unit.y)
        );
        Start.gameMap.move(playResult.move.unit, playResult.move.x, playResult.move.y);
        MapDrawer.drawMap(Start.gameMap);
    }

}
