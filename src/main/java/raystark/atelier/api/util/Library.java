package raystark.atelier.api.util;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public final class Library {
    private Library() {throw new UnsupportedOperationException();}


    /**
     * optと同一要素を持つStreamを返します。
     *
     * @param opt Optionalのインスタンス
     * @param <T> Optionalの要素型
     * @return optと同一要素を持つStreamStream
     * @throws NullPointerException optがnullの場合
     */
    public static <T> Stream<T> asStream(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<T> opt) {
        return requireNonNull(opt).map(Stream::of).orElseGet(Stream::empty);
    }
}
