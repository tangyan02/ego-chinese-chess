package cn.tangyancode.ego.chineseChess.core;

import cn.tangyancode.ego.chineseChess.entity.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GameMap {

    private HashSet<Unit> units;

    private Unit[][] map = new Unit[Config.HEIGHT][Config.WIDTH];

    private Map<Relation, Point> JIANGPoint = new HashMap<>();

    private Map<Relation, Map<Troop, Integer>> relationTroopCount = new HashMap<>();

    {
        relationTroopCount.put(Relation.SELF, new HashMap<>());
        relationTroopCount.put(Relation.OPPONENT, new HashMap<>());
    }

    public GameMap(List<Unit> units) {
        this.units = new HashSet<>(units);
        units.forEach(unit -> {
            map[unit.x][unit.y] = unit;

            //将的记录
            if (unit.troop == Troop.JIANG) {
                JIANGPoint.put(unit.relation, new Point(unit.x, unit.y));
            }

            //数量记录
            if (!relationTroopCount.get(unit.relation).containsKey(unit.troop)) {
                relationTroopCount.get(unit.relation).put(unit.troop, 0);
            }
            relationTroopCount.get(unit.relation).put(unit.troop, relationTroopCount.get(unit.relation).get(unit.troop) + 1);
        });
    }

    public void move(Unit unit, int x, int y) {
        unit.moveTimes++;
        if (map[x][y] != null) {
            units.remove(map[x][y]);
            relationTroopCount.get(unit.relation).put(
                    unit.troop,
                    relationTroopCount.get(unit.relation).get(unit.troop) - 1
            );
        }
        map[unit.x][unit.y] = null;
        map[x][y] = unit;
        unit.x = x;
        unit.y = y;
    }

    public void undoMove(Unit unit, int x, int y, Unit last) {
        unit.moveTimes--;
        map[unit.x][unit.y] = last;
        if (last != null) {
            units.add(last);
            relationTroopCount.get(last.relation).put(
                    last.troop,
                    relationTroopCount.get(last.relation).get(last.troop) + 1
            );
        }
        map[x][y] = unit;
        unit.x = x;
        unit.y = y;
    }

    public HashSet<Unit> getUnits() {
        return new HashSet<>(units);
    }

    public Unit getUnit(int x, int y) {
        return map[x][y];
    }

    public boolean moveAble(int x, int y) {
        if (!inMap(x, y)) {
            return false;
        }
        if (map[x][y] != null) {
            return false;
        }
        return true;
    }

    public boolean attackAble(int x, int y) {
        if (!inMap(x, y)) {
            return false;
        }
        if (map[x][y] != null) {
            return true;
        }
        return false;
    }

    public boolean moveOrAttackAble(int x, int y) {
        if (!inMap(x, y)) {
            return false;
        }
        return true;
    }

    public boolean inMap(int x, int y) {
        if (x < 0 || x >= Config.HEIGHT)
            return false;
        if (y < 0 || y >= Config.WIDTH)
            return false;
        return true;
    }

    public Map<Relation, Point> getJIANGPoint() {
        return JIANGPoint;
    }

    public Map<Relation, Map<Troop, Integer>> getRelationTroopCount() {
        return relationTroopCount;
    }
}
