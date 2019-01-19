package ui;

import cn.tangyancode.ego.chineseChess.core.ChessGame;
import cn.tangyancode.ego.chineseChess.entity.PlayResult;
import cn.tangyancode.ego.chineseChess.entity.Relation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OpponentMoveAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ChessGame chessGame = new ChessGame(new ArrayList<>(Start.gameMap.getUnits()));
        PlayResult playResult = chessGame.play(Relation.OPPONENT);
        Start.gameMap.move(playResult.move);
        MapDrawer.drawMap(Start.gameMap);
    }

}
