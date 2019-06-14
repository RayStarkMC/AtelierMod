package raystark.atelier.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.effect.IEffectInstantHeal;
import raystark.atelier.common.registry.EffectRegistry;

import java.util.List;
import java.util.Optional;

import static raystark.atelier.common.util.AtelierModUtil.addEffect;
import static raystark.atelier.common.util.AtelierModUtil.applyDefaultTag;

public class BandAid extends ItemProductBase {

    public BandAid() {
        super("BandAid", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List p_150895_3_) {
        p_150895_3_.add(addEffect(applyDefaultTag(new ItemStack(item, 1, 0)), EffectRegistry.EFFECT_WEAK_HEAL));
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
