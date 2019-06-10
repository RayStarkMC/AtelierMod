package raystark.atelier.common.util;

/**
 * NBTの型とidの対応の列挙
 */
public enum NBTType {
    END((byte)0),
    BYTE((byte)1),
    SHORT((byte)2),
    INT((byte)3),
    LONG((byte)4),
    FLOAT((byte)5),
    DOUBLE((byte)6),
    BYTE_ARRAY((byte)7),
    STRING((byte)8),
    LIST((byte)9),
    COMPOUND((byte)10),
    INT_ARRAY((byte)11);

    private byte id;

    NBTType(byte id)  {
        this.id = id;
    }

    /**
     * この型に対応するidを返す
     * @return id
     */
    public byte getID() {
        byte i = 0;
        return id;
    }
}