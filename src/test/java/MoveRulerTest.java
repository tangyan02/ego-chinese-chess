
import cn.tangyancode.ego.chineseChess.core.GameMap;
import cn.tangyancode.ego.chineseChess.core.MoveRuler;
import cn.tangyancode.ego.chineseChess.entity.Point;
import org.junit.Test;
import util.MapProvider;

import java.util.List;

public class MoveRulerTest {

    @Test
    public void TestMove() {
        GameMap gameMap = MapProvider.getGameMap("inputMoveRuler_CHE.TXT");
        List<Point> points = MoveRuler.getMovePoint(gameMap, gameMap.getUnit(8, 6));
        System.out.println(points);
    }

}
