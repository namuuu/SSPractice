package fr.namu.pr.enumpr;

import org.bukkit.Material;

public enum Kit {

    BUILD_UHC("BuildUHC", Material.LAVA_BUCKET, MapType.CLASSICO, true, true, true),
    NODEBUFF("NoDebuff", Material.POTION, MapType.CLASSICO, true, true, true),
    SUMO("Sumo", Material.FENCE, MapType.SUMO, true, false, true),

    ;

    private String kitName;
    private Material mat;
    private MapType mt;

    private Boolean ranked;
    private Boolean tvt;
    private Boolean ffa;

    Kit(String kitName, Material mat, MapType mt, Boolean ranked, Boolean tvt, Boolean ffa) {
        this.kitName = kitName;
        this.mat = mat;
        this.mt = mt;
        this.ranked = ranked;
        this.tvt = tvt;
        this.ffa = ffa;
    }

    public String getKitName() {
        return kitName;
    }

    public Material getMat() {
        return mat;
    }

    public Boolean getRanked() {
        return ranked;
    }
    public void setRanked(Boolean ranked) {
        this.ranked = ranked;
    }

    public Boolean getTvt() {
        return tvt;
    }
    public void setTvt(Boolean tvt) {
        this.tvt = tvt;
    }

    public Boolean getFfa() {
        return ffa;
    }
    public void setFfa(Boolean ffa) {
        this.ffa = ffa;
    }

    public MapType getMt() {
        return mt;
    }
}
