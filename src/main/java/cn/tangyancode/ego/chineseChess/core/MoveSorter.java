package cn.tangyancode.ego.chineseChess.core;

import cn.tangyancode.ego.chineseChess.entity.MoveStep;
import cn.tangyancode.ego.chineseChess.entity.Unit;

import java.util.List;

public class MoveSorter {

    public static void sort(List<MoveStep> moveSteps, GameMap gameMap) {
        moveSteps.sort(
                (o1, o2) -> {
                    int value1 = 0;
                    Unit targetUnit1 = gameMap.getUnit(o1.x, o2.y);
                    if (targetUnit1 != null) {
                        value1 = targetUnit1.troop.getValue();
                    }

                    int value2 = 0;
                    Unit targetUnit2 = gameMap.getUnit(o1.x, o2.y);
                    if (targetUnit2 != null) {
                        value2 = targetUnit2.troop.getValue();
                    }
                    return value2 - value1;
                }
        );
    }
}
