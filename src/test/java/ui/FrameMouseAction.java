package ui;

import cn.tangyancode.ego.chineseChess.entity.Move;
import cn.tangyancode.ego.chineseChess.entity.Point;
import cn.tangyancode.ego.chineseChess.helper.MapDriver;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FrameMouseAction implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        Graphics graphics = Start.frame.getGraphics();
        graphics.setColor(Color.red);
        int size = 40;

        int boundx = 20;
        int boundy = 50;

        int y = (e.getX() - boundx) / size;
        int x = (e.getY() - boundy) / size;

        if (Start.prepareUnit == null) {
            if (Start.gameMap.getUnit(x, y) != null) {
                Start.prepareUnit = Start.gameMap.getUnit(x, y);
                graphics.drawRoundRect(y * size + boundx, x * size + boundy, size, size, size, size);
            }
        } else {
            if (Start.prepareUnit.x != x || Start.prepareUnit.y != y) {
                Start.stack.push(new MoveRecord(Start.prepareUnit, Start.gameMap.getUnit(x, y), Start.prepareUnit.x, Start.prepareUnit.y));
                Start.gameMap.move(Start.prepareUnit, x, y);
            }
            MapDrawer.drawMap(Start.gameMap);
            Start.prepareUnit = null;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
