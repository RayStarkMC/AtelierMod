package raystark.atelier.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class SampleTileProduct extends AbstractTileProduct {
    private int tick = 0;

    @Override
    public void updateEntity() {
        /*if(!worldObj.isRemote) {
            @SuppressWarnings("unchecked") List<EntityPlayer> playerList = worldObj.playerEntities;
            for(EntityPlayer player : playerList)
            if (++tick % 20 == 0)
                player.addChatComponentMessage(new ChatComponentText(String.valueOf(getStatus().getQuality())));
        }*/
    }
}
