package test;

/**
 * Created by chin on 5/17/16.
 */
public enum Color {

    //RED, GREEN, BLANK, YELLOW

    RED("红色",1),GREE("绿色",2),BLANK("白色",3),YELLOW("黄色",4);

    private String name;
    private int index;

    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {


        for (Color c: Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }
}
