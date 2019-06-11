package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.ClassicalElement;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.registry.IMaterialRegistry;
import raystark.atelier.common.block.AtelierBlocks;
import raystark.atelier.common.block.SampleBlock;
import raystark.atelier.common.item.AtelierItems;

import javax.swing.text.html.Option;
import java.util.*;

public class MaterialRegistry<I, B> implements IMaterialRegistry<I, B> {
    private static final int META_ANY = -1;
    private static final IMaterialStatus DEFAULT_STATUS = new IMaterialStatus() {
        @Override
        public int getElementValue(ClassicalElement elementType) {
            return 0;
        }

        @Override
        public List<IPotentialAbility> getPotentialAbilityList() {
            @SuppressWarnings("unchecked") List<IPotentialAbility> ret = Collections.EMPTY_LIST;
            return ret;
        }

        @Override
        public int getQuality() {
            return Quality.MIN_VALUE;
        }
    };

    public static void init(IMaterialRegistry<Item, Block> registry) {
        registry.registerDefaultBlockStatus(AtelierBlocks.sampleBlock, new IMaterialStatus() {
            @Override
            public int getElementValue(ClassicalElement elementType) {
                return 10;
            }

            @Override
            public List<IPotentialAbility> getPotentialAbilityList() {
                @SuppressWarnings("unchecked") List<IPotentialAbility> ret = Collections.EMPTY_LIST;
                return ret;
            }

            @Override
            public int getQuality() {
                return 40;
            }
        });
    }

    private Map<IMaterialKey<I>, IMaterialStatus> itemMaterials;
    private Map<IMaterialKey<B>, IMaterialStatus> blockMaterials;

    public MaterialRegistry() {
        itemMaterials = new HashMap<>();
        blockMaterials = new HashMap<>();
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultItemStatus(I materialItem, int damage, IMaterialStatus status) {
        return Optional.ofNullable(itemMaterials.put(new MaterialKey<>(materialItem, damage), status));
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultItemStatus(I materialItem, IMaterialStatus status) {
        return registerDefaultItemStatus(materialItem, META_ANY, status);
    }

    @Override
    public IMaterialStatus getDefaultItemStatus(I materialItem, int damage) {
        return Optional.ofNullable(itemMaterials.get(new MaterialKey<>(materialItem, damage))).orElse(DEFAULT_STATUS);
    }

    @Override
    public IMaterialStatus getDefaultItemStatus(I materialItem) {
        return getDefaultItemStatus(materialItem, META_ANY);
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultBlockStatus(B materialBlock, int metadata, IMaterialStatus status) {
        return Optional.ofNullable(blockMaterials.put(new MaterialKey<>(materialBlock, metadata), status));
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultBlockStatus(B materialBlock, IMaterialStatus status) {
        return registerDefaultBlockStatus(materialBlock, META_ANY, status);
    }

    @Override
    public IMaterialStatus getDefaultBlockStatus(B materialBlock, int metadata) {
        System.out.println(metadata);
        System.out.println(blockMaterials.get(new MaterialKey<>(materialBlock, metadata)) == null ? "null" : "exit");
        return Optional.ofNullable(blockMaterials.get(new MaterialKey<>(materialBlock, metadata))).orElse(DEFAULT_STATUS);
    }

    @Override
    public IMaterialStatus getDefaultBlockStatus(B materialBlock) {
        return getDefaultBlockStatus(materialBlock, META_ANY);
    }

    private static class MaterialKey<E> implements IMaterialKey<E> {
        private E element;
        private int metadata;

        private MaterialKey(E element, int metadata) {
            this.element = element;
            this.metadata = metadata;
        }

        @Override
        public E getElement() {
            return element;
        }

        @Override
        public int getMetadata() {
            return metadata;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MaterialKey<?> that = (MaterialKey<?>) o;
            if(!Objects.equals(element, that.element)) return false;
            return metadata == META_ANY || that.metadata == META_ANY || metadata == that.metadata;
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, metadata);
        }
    }
}
