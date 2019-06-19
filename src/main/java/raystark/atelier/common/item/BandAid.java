package raystark.atelier.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.effect.IEffectInstantHeal;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.common.AtelierMod;
import raystark.atelier.api.alchemy.status.SimpleMaterialStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BandAid extends ItemProductBase {

    public BandAid() {
        super("BandAid", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List p_150895_3_) {
        ItemStack stack = AtelierMod.getInstance().recipe.createProduct(Quality.MIN_VALUE, new SimpleMaterialStatus.ElementContainer(0, 0, 0, 0), Collections.EMPTY_LIST);
        ItemStack stack2 = AtelierMod.getInstance().recipe.createProduct(50, new SimpleMaterialStatus.ElementContainer(0, 80, 0, 0), Collections.EMPTY_LIST);
        stack.stackSize = 1;
        stack2.stackSize = 1;
        p_150895_3_.add(stack);
        p_150895_3_.add(stack2);
        // p_150895_3_.add(addEffect(applyDefaultTag(new ItemStack(item, 1, 0)), EffectRegistry.EFFECT_WEAK_HEAL));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(itemStack == null || !(itemStack.getItem() instanceof BandAid)) {
            return itemStack;
        }

        healEntity(itemStack, world, entityPlayer);
        return itemStack;
    }

    private void healEntity(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(!world.isRemote) {
            Optional<IEffectInstantHeal> optionalHeal = getStatus(itemStack).getEffectList().stream()
                    .filter(e -> e instanceof IEffectInstantHeal)
                    .findFirst()
                    .map(e -> (IEffectInstantHeal) e);

            optionalHeal.ifPresent(e -> entityPlayer.heal(e.getAmountOfHeal()));
            itemStack.stackSize--;
        }
    }
}
