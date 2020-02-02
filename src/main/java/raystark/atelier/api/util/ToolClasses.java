package raystark.atelier.api.util;

/**
 * ツールの種類の文字列表現を表す列挙です。
 *
 * @author RayStark
 */
public enum ToolClasses {
    /**
     * つるはしの文字列表現。
     */
    PICKAXE("pickaxe"),

    /**
     * ショベルの文字列表現。
     */
    SHOVEL("shovel"),

    /**
     * 斧の文字列表現。
     */
    AXE("axe");

    private String toolClass;

    ToolClasses(String toolClass) {
        this.toolClass = toolClass;
    }

    /**
     * このツールの文字列表現を返します。
     * @return 文字列表現
     */
    public String value() { return toolClass; }
}
