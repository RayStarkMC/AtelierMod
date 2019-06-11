package raystark.atelier.common.registry;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.ClassicalElement;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.registry.IMaterialRegistry;

import java.util.*;

import static raystark.atelier.api.alchemy.status.IMaterialStatus.DEFAULT_STATUS;

public abstract class AbstractMaterialRegistry<I, B> implements IMaterialRegistry<I, B> {
    private static final int META_ANY = -1;

    private Map<IMaterialKey<I>, IMaterialStatus> itemMaterials;
    private Map<IMaterialKey<B>, IMaterialStatus> blockMaterials;

    protected AbstractMaterialRegistry() {
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
