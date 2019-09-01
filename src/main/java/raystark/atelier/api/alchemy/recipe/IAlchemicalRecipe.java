package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.status.IMaterialStatus;

import java.util.List;

/**
 * 錬金レシピを表すインターフェース.
 *
 * <p>レシピは材料としてアイテムそのものを指定できるほか、アイテムの属すカテゴリを指定することが出来ます。
 * これにより同じ完成品を違う材料によって作成することが出来ます。
 *
 * <p>レシピに使う材料から抽出したステータスを
 *
 * @param <S> アイテムスタックの型
 *
 * @author RayStark
 */
public interface IAlchemicalRecipe<S> {

    /**
     * このレシピで作成するアイテムを返します。
     *
     * @return このレシピで作成するアイテム
     */
    IAlchemicalProduct getProduct();

    /**
     * このレシピの要求物の不変リストを返します。
     *
     * @return このレシピの要求物の不変リスト
     */
    List<IRequirement<S>> getRequirements();

    /**
     * このレシピの効果予測の不変リストを返します。
     *
     * @return 効果予測のリスト
     */
    List<IEffectEstimated> getEffectsEstimated();

    /**
     * ステータスからこのレシピの完成品のアイテムスタックを生成します。
     *
     * <p>このメソッドでは引数のステータスを元に品質値、効果、潜在能力を決定して完成品に付与します。
     *
     * @param status このレシピに適応する材料のステータス。
     * @return 完成品のアイテムスタック
     */
    S createProduct(IMaterialStatus status);
}