package ui;

import cn.tangyancode.ego.chineseChess.core.Config;
import cn.tangyancode.ego.chineseChess.core.GameMap;
import cn.tangyancode.ego.chineseChess.entity.Relation;
import cn.tangyancode.ego.chineseChess.entity.Unit;

import java.awt.*;

public class MapDrawer {

    public static void drawMap(GameMap gameMap) {
        drawBackGround();
        gameMap.getUnits().forEach(
                MapDrawer::drawCube
        );
    }

    private static void drawBackGround() {
        Graphics g = Start.frame.getGraphics();
        g.setColor(Color.gray);
        int size = 40;
        int baseX = 20;
        int baseY = 50;
        g.fillRect(baseX, baseY, Config.WIDTH * size, Config.HEIGHT * size);

    }

    private static void drawCube(Unit unit) {
        if (unit == null) {
            return;
        }
        Graphics g = Start.frame.getGraphics();
        if (unit.relation == Relation.SELF) {
            g.setColor(Color.YELLOW);
        }
        if (unit.relation == Relation.OPPONENT) {
            g.setColor(Color.black);
        }
        int size = 40;
        int baseX = 10 + size / 2;
        int baseY = 50 + size / 2;
        g.drawString(unit.troop.getChineseValue(), baseX + unit.y * size, baseY + unit.x * size);
    }

}
