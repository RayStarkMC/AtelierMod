package raystark.atelier.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import raystark.atelier.api.tile.AbstractTileProduct;

import java.util.List;

public class SampleTileProduct extends AbstractTileProduct {
    int tick = 0;

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote) {
            for(EntityPlayer player : (List<EntityPlayer>) worldObj.playerEntities)
            if (++tick % 20 == 0)
                player.addChatComponentMessage(new ChatComponentText(String.valueOf(getStatus().getQuality())));
        }
    }
}
