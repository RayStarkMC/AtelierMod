package raystark.atelier.api.util;

import java.util.Objects;

/**
 * メタデータを持つ要素を表すクラス。
 *
 * <p>要素とint型で表されるメタデータを持ちます。要素にはnullをとることが出来、メタデータにはint型の範囲で制限がありません。
 * このクラスでは要素とメタデータは公開されています。いずれもfinal変数として宣言されており、要素が不変クラスの場合このクラスは不変です。
 * 要素が可変クラスの場合、このクラスは不変であることを保証しません。
 *
 * @param <E> 要素の型
 */
public final class ElementWithMetadata<E> {
    /**
     * メタデータの付与される要素
     */
    public final E element;

    /**
     * メタデータ
     */
    public final int metadata;

    /**
     * メタデータ付き要素を生成します。
     *
     * 要素としてnullを利用することが出来ます。
     *
     * @param element nullを許容する要素
     * @param metadata メタデータ
     */
    public ElementWithMetadata(E element, int metadata){
        this.element = element;
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementWithMetadata<?> that = (ElementWithMetadata<?>) o;
        return metadata == that.metadata &&
                Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, metadata);
    }
}