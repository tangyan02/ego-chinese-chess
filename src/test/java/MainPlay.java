
import cn.tangyancode.ego.chineseChess.core.ChessGame;
import cn.tangyancode.ego.chineseChess.core.GameMap;
import cn.tangyancode.ego.chineseChess.entity.PlayResult;
import cn.tangyancode.ego.chineseChess.entity.Relation;
import cn.tangyancode.ego.chineseChess.entity.Unit;
import cn.tangyancode.ego.chineseChess.helper.MapDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MainPlay {

    boolean update = true;


    @Test
    public void play() throws IOException {
        String path = "src/test/resources/input.txt";
        List<Unit> units = null;
        try {
            units = MapDriver.readUnits(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChessGame chessGame = new ChessGame(units);
        PlayResult result = chessGame.play(Relation.SELF);
        System.out.println(result);
        if (update) {
            GameMap gameMap = new GameMap(units);
            gameMap.move(result.move.unit, result.move.x, result.move.y);
            MapDriver.writeUnits(path, gameMap);
        }
    }

}
