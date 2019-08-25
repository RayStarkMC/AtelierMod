package raystark.atelier.common.registry;

import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.api.registry.IMaterialRegistry;
import raystark.atelier.api.util.ElementWithMetadata;

import java.util.*;

import static raystark.atelier.api.alchemy.status.IMaterialStatus.DEFAULT_STATUS;

public abstract class AbstractMaterialRegistry<I, B> implements IMaterialRegistry<I, B> {
    private static final int META_ANY = -1;

    private Map<ElementWithMetadata<I>, IMaterialStatus> itemMaterials;
    private Map<ElementWithMetadata<B>, IMaterialStatus> blockMaterials;

    protected AbstractMaterialRegistry() {
        itemMaterials = new HashMap<>();
        blockMaterials = new HashMap<>();
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultItemStatus(I materialItem, int damage, IMaterialStatus status) {
        return Optional.ofNullable(itemMaterials.put(new ElementWithMetadata<>(materialItem, damage), status));
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultItemStatus(I materialItem, IMaterialStatus status) {
        return registerDefaultItemStatus(materialItem, META_ANY, status);
    }

    @Override
    public IMaterialStatus getDefaultItemStatus(I materialItem, int damage) {
        return Optional.ofNullable(itemMaterials.get(new ElementWithMetadata<>(materialItem, damage))).orElse(DEFAULT_STATUS);
    }

    @Override
    public IMaterialStatus getDefaultItemStatus(I materialItem) {
        return getDefaultItemStatus(materialItem, META_ANY);
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultBlockStatus(B materialBlock, int metadata, IMaterialStatus status) {
        return Optional.ofNullable(blockMaterials.put(new ElementWithMetadata<>(materialBlock, metadata), status));
    }

    @Override
    public Optional<IMaterialStatus> registerDefaultBlockStatus(B materialBlock, IMaterialStatus status) {
        return registerDefaultBlockStatus(materialBlock, META_ANY, status);
    }

    @Override
    public IMaterialStatus getDefaultBlockStatus(B materialBlock, int metadata) {
        return Optional.ofNullable(blockMaterials.get(new ElementWithMetadata<>(materialBlock, metadata))).orElse(DEFAULT_STATUS);
    }

    @Override
    public IMaterialStatus getDefaultBlockStatus(B materialBlock) {
        return getDefaultBlockStatus(materialBlock, META_ANY);
    }
}