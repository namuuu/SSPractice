package fr.namu.pr.enumpr;

public enum Ranks {
    DEFAULT("default", "§7", "§f[§7Rookie§f]"),
    GOLD("gold", "§eG §7» ", "§f[§eGold§f]")
    ;

    private String name;
    private String prefix;
    private String fullName;

    Ranks(String name, String prefix, String fullName) {
        this.name = name;
        this.prefix = prefix;
        this.fullName = fullName;
    }


    public String getName() {
        return name;
    }
    public String getPrefix() {
        return prefix;
    }
    public String getFullName() {
        return fullName;
    }
}
