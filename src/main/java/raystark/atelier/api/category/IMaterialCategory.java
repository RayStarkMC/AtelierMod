package raystark.atelier.api.category;

/**
 * 錬金素材のカテゴリを表すインターフェース。
 *
 * <p>カテゴリはアイテムの分類を表します。錬金術では材料としてアイテムの代わりにカテゴリを要求することが出来ます。
 * カテゴリが要求された場合は、そのカテゴリに属すアイテムであればどのアイテムでも材料として扱えます。
 *
 * アイテムのカテゴリ情報はレジストリに保存され、このインターフェースはレジストリのキーとして使われます。
 *
 * @author RayStark
 */
public interface IMaterialCategory {

    /**
     * カテゴリ名を返します。
     *
     * @return カテゴリ名
     */
    String getCategoryName();
}


