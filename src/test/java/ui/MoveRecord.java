package ui;

import cn.tangyancode.ego.chineseChess.entity.Unit;

public class MoveRecord {

    public Unit from;
    public Unit to;
    public int x;
    public int y;

    public MoveRecord(Unit from, Unit to, int x, int y) {
        this.from = from;
        this.to = to;
        this.x = x;
        this.y = y;
    }
}
