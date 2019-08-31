package raystark.atelier.common.alchemy.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.recipe.AbstractSpecifiedRequirement;
import raystark.atelier.api.alchemy.recipe.ISpecifiedRequirement;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.Objects;

/**
 * AbstractSpecifiedRequirementの1.7.10実装
 *
 * <p>この実装ではアイテムスタックを要求物に指定します。
 * 要求物の名前にはアイテムスタックの表示名が利用されます。
 */
public final class SpecifiedRequirement extends AbstractSpecifiedRequirement<ItemStack> {

    /**
     * アイテムが指定された要求物を生成します。
     *
     * @param specified 要求物
     * @throws NullPointerException specifiedがnullの場合
     */
    public SpecifiedRequirement(ItemStack specified) {
        super(specified);
    }

    public static ISpecifiedRequirement<ItemStack> requireItem(Item item, int metadata) {
        if(AtelierModUtil.isIllegalItemMeta(metadata))
            throw new IllegalArgumentException("[0 <= metadata], metadata: " + metadata);
        Objects.requireNonNull(item, "item must not be null.");
        return new SpecifiedRequirement(new ItemStack(item, 0, metadata));
    }

    public static ISpecifiedRequirement<ItemStack> requireBlock(Block block, int metadata) {
        if(AtelierModUtil.isIllegalItemMeta(metadata))
            throw new IllegalArgumentException("[0 <= metadata <= 15], metadata: " + metadata);
        Objects.requireNonNull(block, "block must not be null.");
        return new SpecifiedRequirement(new ItemStack(block, 1, metadata));
    }

    /**
     * 引数に渡されたアイテムスタックが材料として利用できるか調べます。
     *
     * <p>この実装ではアイテムクラス、及びダメージ値を比較します。アイテムスタックのNBTの違いは無視されます。
     *
     * @param input アイテムスタック
     * @return
     */
    @Override
    public boolean isApplicableAsMaterial(ItemStack input) {
        Objects.requireNonNull(input, "input must not be null.");
        return input.getItem() == specified.getItem() && input.getItemDamage() == specified.getItemDamage();
    }

    /**
     * この要求物の表示名を返します。
     *
     * <p>このメソッドはアイテムスタックの表示名を返します。
     *
     * @return 表示名
     */
    @Override
    public String getName() {
        return specified.getDisplayName();
    }
}
