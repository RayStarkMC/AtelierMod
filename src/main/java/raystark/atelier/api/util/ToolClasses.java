package raystark.atelier.api.util;

/**
 * ツールの種類の文字列表現を表す列挙
 */
public enum ToolClasses {
    PICKAXE("pickaxe"),
    SHOVEL("shovel"),
    AXE("axe");

    private String toolClass;

    ToolClasses(String toolClass) {
        this.toolClass = toolClass;
    }

    /**
     * このツールの文字列表現を返す。
     * @return 文字列表現
     */
    public String value() { return toolClass; }
}
