package fr.namu.pr.enumpr;

public enum MapPR {

    TEST("Map de Test", 0, 0, -7, 0, 0, 7),
    ;

    private String name;

    private int x1;
    private int y1;
    private int z1;

    private int x2;
    private int y2;
    private int z2;



    MapPR(String name, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;

        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getZ1() {
        return z1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getZ2() {
        return z2;
    }
}
