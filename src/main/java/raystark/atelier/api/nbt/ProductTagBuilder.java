package raystark.atelier.api.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.Quality;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;
import static raystark.atelier.api.nbt.AtelierTagKey.ATELIER;
import static raystark.atelier.api.nbt.AtelierTagKey.ATELIER_QUALITY;
import static raystark.atelier.api.nbt.AtelierTagKey.ATELIER_EFFECT;
import static raystark.atelier.api.nbt.AtelierTagKey.ATELIER_POTENTIAL;
import static raystark.atelier.api.util.function.Functions.f;

/**
 * 錬金術製品のステータスを保持するNBTTagCompoundのビルダーです。
 *
 * <p>ビルダーは2つの状態を持ち、生成された時点では「構築前」状態です。buildメソッドが呼び出されると「構築後」状態に遷移します。
 * 「構築後」状態でbuildメソッドが呼ばれるとIllegalStateExceptionがスローされるため、ビルダーは再利用することが出来ません。
 *
 * <p>ビルダーは1つのNBTTagCompoundを編集します。編集内容はbuildメソッドによりNBTTagCompoundを取得する直前に反映されます。
 * ビルダーのステータス編集用の各メソッドの内setQualityメソッドは必須で、これが呼ばれずにbuildメソッドが呼ばれた場合は
 * IllegalStateExceptionをスローします。
 *
 * <p>このクラスはコンストラクターを公開せず、代わりにスタティックファクトリメソッドを2つ提供します。
 *
 * <p>このクラスはスレッドセーフではありません。
 *
 * @author RayStark
 * @see ProductTagReader
 */
public class ProductTagBuilder {
    private boolean isBuilt;

    private final Supplier<NBTTagCompound> tagSupplier;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private OptionalInt quality;
    private final List<IEffect> effects;
    private final List<IPotentialAbility> abilities;

    private ProductTagBuilder(Supplier<NBTTagCompound> tagSupplier) {
        isBuilt = false;
        this.tagSupplier = tagSupplier;
        quality = OptionalInt.empty();
        effects = new ArrayList<>();
        abilities = new ArrayList<>();
    }

    /**
     * tagCompoundを編集するビルダーを生成します。
     *
     * @param tagCompound 編集するtagCompound
     * @return ビルダー
     */
    public static ProductTagBuilder builder(NBTTagCompound tagCompound) {
        requireNonNull(tagCompound, "tagSupplier must not be null");
        return new ProductTagBuilder(() -> tagCompound);
    }

    /**
     * 新たにNBTTagCompoundを生成して編集するビルダーを生成します。
     *
     * <p>このクラスの実装ではNBTTagCompoundの生成はbuildメソッド実行時まで遅延されます。
     *
     * @return ビルダー
     */
    public static ProductTagBuilder builder() {
        return new ProductTagBuilder(NBTTagCompound::new);
    }

    /**
     * 製品の品質値をqualityに設定します。
     *
     * <p>品質値が既に設定されていた場合このメソッドはIllegalStateExceptionをスローします。
     * 品質値が不正値の場合このメソッドはIllegalArgumentExceptionをスローします。
     *
     * @param quality 品質値
     * @return ビルダー
     * @see Quality
     */
    public ProductTagBuilder setQuality(int quality) {
        if(this.quality.isPresent()) throw new IllegalStateException("quality has already been set.");
        if(quality < Quality.MIN_VALUE) throw new IllegalArgumentException("quality must be positive.");
        this.quality = OptionalInt.of(quality);
        return this;
    }

    /**
     * 製品のステータスに指定したeffectを追加します。
     *
     * <p>このメソッドにnullを渡した場合はNullPointerExceptionをスローします。
     *
     * @param effect 効果
     * @return ビルダー
     */
    public ProductTagBuilder addEffect(IEffect effect) {
        effects.add(requireNonNull(effect, "effect must not be null."));
        return this;
    }

    /**
     * 製品のステータスにeffectsが含む全てのeffectを追加します。
     *
     * <p>effectsにnullを渡した場合、またeffectsがnull値を含む場合NullPointerExceptionをスローします。
     *
     * @param effects 効果のコレクション
     * @return ビルダー
     */
    public ProductTagBuilder addAllEffects(Collection<? extends IEffect> effects) {
        requireNonNull(effects, "effects must not be null.");
        this.effects.addAll(effects);
        return this;
    }

    /**
     * 製品のステータスに指定したabilityを追加します。
     *
     * <p>このメソッドにnullを渡した場合はNullPointerExceptionをスローします。
     *
     * @param ability 潜在能力
     * @return ビルダー
     */
    public ProductTagBuilder addPotentialAbility(IPotentialAbility ability) {
        abilities.add(requireNonNull(ability, "effect must not be null."));
        return this;
    }

    /**
     * 製品のステータスにabilitiesが含む全てのabilityを追加します。
     *
     * <p>abilitiesにnullを渡した場合、またabilitiesがnull値を含む場合NullPointerExceptionをスローします。
     *
     * @param abilities 潜在能力のコレクション
     * @return ビルダー
     */
    public ProductTagBuilder addAllPotentials(Collection<? extends IPotentialAbility> abilities) {
        requireNonNull(abilities, "abilities must not be null.");
        this.abilities.addAll(abilities);
        return this;
    }

    /**
     * 製品のステータスを保持するNBTTagCompoundをビルドします。
     *
     * <p>このメソッドで返されるNBTTagCompoundには品質値、効果のリスト、潜在能力のリストを保持するタグが付与されています。
     * それらのタグはProductTagReaderで読み取ることが出来ます。
     *
     * <p>このメソッドが呼ばれるまでビルダーは「構築前」状態であり、実行後に「構築後」状態に遷移します。
     *
     * <p>構築後状態でこのメソッドが呼ばれた場合、
     * @return 編集されたNBTTagCompound
     * @see ProductTagReader
     */
    public NBTTagCompound build() {
        if(isBuilt) throw new IllegalStateException("ProductTag is already built.");
        isBuilt = true;
        int quality = this.quality.orElseThrow(() -> new IllegalStateException("quality is not set."));

        NBTTagCompound tagCompound = this.tagSupplier.get();

        NBTTagCompound tagAtelier = new NBTTagCompound();
        NBTTagList tagEffects = new NBTTagList();
        NBTTagList tagPotentials = new NBTTagList();

        tagAtelier.setInteger(ATELIER_QUALITY.name(), quality);
        tagAtelier.setTag(ATELIER_EFFECT.name(), tagEffects);
        tagAtelier.setTag(ATELIER_POTENTIAL.name(), tagPotentials);

        tagCompound.setTag(ATELIER.name(), tagAtelier);

        effects.forEach(f(IEffect::getName).then(NBTTagString::new).consume(tagEffects::appendTag));
        abilities.forEach(f(IPotentialAbility::getName).then(NBTTagString::new).consume(tagPotentials::appendTag));
        return tagCompound;
    }
}
