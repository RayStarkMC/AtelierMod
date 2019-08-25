package raystark.atelier.api.util;

import java.util.Objects;

/**
 * メタデータを持つ要素を表すクラス。
 *
 * <p>要素とint型で表されるメタデータを持ちます。要素にはnullをとることが出来、メタデータにはint型の範囲で制限がありません。
 *
 * @param <E> 要素の型
 */
public final class ElementWithMetadata<E> {
    private final E element;
    private final int metadata;

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

    /**
     * 要素を返します。
     *
     * @return 要素
     */
    public E getElement() {
        return element;
    }

    /**
     * メタデータを返します。
     *
     * @return メタデータ
     */
    public int getMetadata() {
        return metadata;
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