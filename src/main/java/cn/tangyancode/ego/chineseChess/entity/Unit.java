package cn.tangyancode.ego.chineseChess.entity;

public class Unit {

    public Integer x;
    public Integer y;
    public Troop troop;
    public Relation relation;
    public int moveTimes = 0;

    @Override
    public String toString() {
        return "Unit{" +
                "x=" + x +
                ", y=" + y +
                ", troop=" + troop +
                ", relation=" + relation +
                '}';
    }
}
