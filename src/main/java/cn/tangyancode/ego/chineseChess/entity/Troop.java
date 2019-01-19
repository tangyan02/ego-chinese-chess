package cn.tangyancode.ego.chineseChess.entity;

public enum Troop {

    CHE(1000, 'c', "车"),
    MA(500, 'm' ,"马"),
    XIANG(300, 'x',"相"),
    SHI(300, 's',"士"),
    JIANG(100000, 'j',"将"),
    PAO(500, 'p',"炮"),
    BING(100, 'b',"兵");

    private int value;

    private char letter;

    private String chineseValue;

    Troop(int value, char letter, String chineseValue) {
        this.value = value;
        this.letter = letter;
        this.chineseValue = chineseValue;
    }

    public int getValue() {
        return value;
    }

    public char getLetter() {
        return letter;
    }

    public String getChineseValue() {
        return chineseValue;
    }
}
