package raystark.atelier.api.alchemy.status;

/**
 * データソース付きのステータスの抽象クラスです。
 *
 * <p>この抽象クラスはアイテムスタックから生成されるProductStatusが継承する事を目的に作られています。
 *
 * @param <T>
 */
public abstract class ProductStatusWithDataSource<T> extends SimpleProductStatus{

    private T dataSource;

    protected ProductStatusWithDataSource(T dataSource) {
        super();
        if(dataSource == null) throw new NullPointerException("dataSource must not be null.");
        this.dataSource = dataSource;
    }

    protected T getDataSource() {
        return dataSource;
    }
}
