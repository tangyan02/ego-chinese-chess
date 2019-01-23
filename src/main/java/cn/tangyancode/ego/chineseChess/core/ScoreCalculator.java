package cn.tangyancode.ego.chineseChess.core;

import cn.tangyancode.ego.chineseChess.entity.Relation;
import cn.tangyancode.ego.chineseChess.entity.Troop;
import cn.tangyancode.ego.chineseChess.entity.Unit;

import java.util.HashSet;

public class ScoreCalculator {

    public static int getScore(GameMap gameMap, Relation relation) {
        int sum = 0;
        HashSet<Unit> units = gameMap.getUnits();
        for (Unit unit : units) {
            if (unit.relation == relation) {
                sum += unit.troop.getValue();
                sum += getExtraScore(gameMap, unit);
            }
            if (unit.relation != relation) {
                sum -= unit.troop.getValue();
                sum -= getExtraScore(gameMap, unit);
            }
        }
        return sum;
    }

    private static int getExtraScore(GameMap gameMap, Unit unit) {
        if (unit.troop == Troop.CHE) {
            int sigma = Math.min(3, unit.moveTimes) * 30;
            int delta = 5 * -getDistanceFromJiang(gameMap, unit, unit.relation.getOther());
            return sigma + delta;
        }
        if (unit.troop == Troop.MA) {
            int sigma = Math.min(3, unit.moveTimes) * 5;
            int delta = 10 * -getDistanceFromJiang(gameMap, unit, unit.relation.getOther());
            return delta + sigma;
        }
        if (unit.troop == Troop.BING) {
            int delta = 2 * -getDistanceFromJiang(gameMap, unit, unit.relation.getOther());
            int sigma = Math.min(2, unit.moveTimes) * 40;
            return sigma + delta;
        }
        if (unit.troop == Troop.PAO) {
            //棋子数量决定炮的衰减
            return -(32 - gameMap.getUnits().size()) * 5;
        }
        if (unit.troop == Troop.SHI || unit.troop == Troop.XIANG) {
            int sigma = Math.min(1, unit.moveTimes) * 10;
            int delta = 2 * (2 - getDistanceFromJiang(gameMap, unit, unit.relation));
            int la = 0;
            //如果没有一对，则效果减半
            if (gameMap.getRelationTroopCount().get(unit.relation).get(unit.troop) < 2) {
                la = -unit.troop.getValue() / 2 + sigma * 10;
            }
            return la + sigma + delta;
        }
        return 0;
    }

    private static int getDistanceFromJiang(GameMap gameMap, Unit unit, Relation relation) {
        return Math.abs(gameMap.getJIANGPoint().get(relation).x - unit.x) +
                Math.abs(gameMap.getJIANGPoint().get(relation).y - unit.y);
    }

}
