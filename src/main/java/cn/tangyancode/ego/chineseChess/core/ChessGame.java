package cn.tangyancode.ego.chineseChess.core;

import cn.tangyancode.ego.chineseChess.entity.*;

import java.util.List;

public class ChessGame {

    private GameMap gameMap;

    private int count = 0;

    private long startTime;

    private int currentLevel;

    private Move move = new Move();

    public ChessGame(List<Unit> units) {
        gameMap = new GameMap(units);
    }

    public PlayResult play(Relation relation) {
        Move result = null;
        for (int level = 2; level <= Config.level; level += 2) {
            currentLevel = level;
            int alpha = Integer.MIN_VALUE / 2;
            int beta = Integer.MAX_VALUE / 2;
            startTime = System.currentTimeMillis();
            count = 0;
            Integer value = dfs(level, relation, alpha, beta, false);
            if (value == null) {
                break;
            }
            result = new Move(move.unit, move.x, move.y, move.value);
            System.out.println("level:" + level + " " + move + " count:" + count);
        }
        return new PlayResult(result, count);
    }

    private Integer dfs(int level, Relation relation, int alpha, int beta, boolean check) {
        if (System.currentTimeMillis() - startTime > Config.timeLimit) {
            return null;
        }
        count++;
        if (level == 0 || check) {
            return ScoreCalculator.getScore(gameMap, relation);
        }
        int currentMax = Integer.MIN_VALUE;
        List<MoveStep> moves = MoveRuler.getMoves(gameMap, relation);
        MoveSorter.sort(moves, gameMap);
        for (MoveStep moveStep : moves) {
            Unit unit = moveStep.unit;
            int fromX = unit.x;
            int fromY = unit.y;
            int toX = moveStep.x;
            int toY = moveStep.y;
            Unit targetUnit = gameMap.getUnit(toX, toY);
            //己方单位不攻击
            if (targetUnit != null) {
                if (targetUnit.relation == unit.relation) {
                    continue;
                }
            }
            //将军判断
            if (targetUnit != null && targetUnit.troop == Troop.JIANG) {
                check = true;
            }
            gameMap.move(unit, toX, toY);
            Integer result = dfs(level - 1, relation.getOther(), -beta, -alpha, check);
            gameMap.undoMove(unit, fromX, fromY, targetUnit);
            if (result == null) {
                return null;
            }
            check = false;
            int value = -result;
            if (value > currentMax) {
                currentMax = value;
                if (level == currentLevel) {
                    move.set(unit, toX, toY, value);
                }
                if (value > alpha) {
                    alpha = value;
                    if (value > beta) {
                        return value;
                    }
                }
            }
        }
        return currentMax;
    }


}
