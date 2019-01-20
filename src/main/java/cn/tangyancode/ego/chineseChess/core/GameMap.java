package cn.tangyancode.ego.chineseChess.core;

import cn.tangyancode.ego.chineseChess.entity.Move;
import cn.tangyancode.ego.chineseChess.entity.Unit;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.List;

public class GameMap {

    private HashSet<Unit> units;

    private Unit[][] map = new Unit[Config.HEIGHT][Config.WIDTH];

    public GameMap(List<Unit> units) {
        this.units = new HashSet<>(units);
        units.forEach(unit -> map[unit.x][unit.y] = unit);
    }

    public void move(Move move) {
        move(move.unit, move.x, move.y);
    }

    public void move(Unit unit, int x, int y) {
        if (map[x][y] != null) {
            units.remove(map[x][y]);
        }
        map[unit.x][unit.y] = null;
        map[x][y] = unit;
        unit.x = x;
        unit.y = y;
    }

    public void undoMove(Unit unit, int x, int y, Unit last) {
        map[unit.x][unit.y] = last;
        if (last != null) {
            units.add(last);
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

}
