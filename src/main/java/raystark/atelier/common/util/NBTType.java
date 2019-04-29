package raystark.atelier.common.util;

/**
 * NBTの型とidの対応の列挙
 */
public enum NBTType {
    END(0),
    BYTE(1),
    SHORT(2),
    INT(3),
    LONG(4),
    FLOAT(5),
    DOUBLE(6),
    BYTE_ARRAY(7),
    STRING(8),
    LIST(9),
    COMPOUND(10),
    INT_ARRAY(11);

    private int id;

    NBTType(int id)  {
        this.id = id;
    }

    /**
     * この型に対応するidを返す
     * @return id
     */
    public int getID() {
        return id;
    }
}
