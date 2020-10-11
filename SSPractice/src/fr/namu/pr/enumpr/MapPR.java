package fr.namu.pr.enumpr;

public enum MapPR {

    TEST("Map de Test", MapTypePR.CLASSIC, 0, 0, -7, 0, 0, 7),

    SUMO_QUARTZ("Quartz", MapTypePR.SUMO, 5, 0, 0, 0, 0, 0),
    ;

    private String name;
    private MapTypePR maptype;

    private int x1;
    private int y1;
    private int z1;

    private int x2;
    private int y2;
    private int z2;

    MapPR(String name, MapTypePR maptype, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;

        this.maptype = maptype;

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

    public MapTypePR getMaptype() {
        return maptype;
    }
}
