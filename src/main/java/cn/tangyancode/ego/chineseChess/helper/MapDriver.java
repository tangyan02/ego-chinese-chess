package cn.tangyancode.ego.chineseChess.helper;

import cn.tangyancode.ego.chineseChess.core.Config;
import cn.tangyancode.ego.chineseChess.core.GameMap;
import cn.tangyancode.ego.chineseChess.entity.Relation;
import cn.tangyancode.ego.chineseChess.entity.Troop;
import cn.tangyancode.ego.chineseChess.entity.Unit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MapDriver {

    public static List<Unit> readUnits(String path) throws IOException {
        File file = new File(path);
        List<Unit> units = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        for (int i = 0; i < Config.HEIGHT; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < Config.WIDTH; j++) {
                char letter = line.charAt(j);
                if (letter == '.') {
                    continue;
                }
                Unit unit = new Unit();
                unit.x = i;
                unit.y = j;
                unit.relation = Character.isUpperCase(letter) ? Relation.SELF : Relation.OPPONENT;
                for (Troop troop : Troop.values()) {
                    if (troop.getLetter() == Character.toLowerCase(letter)) {
                        unit.troop = troop;
                    }
                }
                if (unit.troop == null) {
                    throw new IllegalArgumentException("troop not support");
                }
                units.add(unit);
            }
        }
        return units;
    }

    public static void writeUnits(String path, GameMap gameMap) throws IOException {
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < Config.HEIGHT; i++) {
            for (int j = 0; j < Config.WIDTH; j++) {
                Unit unit = gameMap.getUnit(i, j);
                if (unit == null) {
                    fileWriter.write('.');
                    continue;
                }
                char letter = unit.troop.getLetter();
                if (unit.relation == Relation.SELF) {
                    letter = Character.toUpperCase(letter);
                }
                fileWriter.write(letter);
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public static void printToConsole(GameMap gameMap) {
        for (int i = 0; i < Config.HEIGHT; i++) {
            for (int j = 0; j < Config.WIDTH; j++) {
                Unit unit = gameMap.getUnit(i, j);
                if (unit == null) {
                    System.out.print('.');
                    continue;
                }
                char letter = unit.troop.getLetter();
                if (unit.relation == Relation.SELF) {
                    letter = Character.toUpperCase(letter);
                }
                System.out.print(letter);
            }
            System.out.println();
        }
        System.out.println();
    }

}
