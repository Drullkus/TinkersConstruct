package tconstruct.tools.network;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import io.netty.buffer.ByteBuf;
import tconstruct.common.network.AbstractPacketThreadsafe;
import tconstruct.tools.inventory.ContainerPartBuilder;

public class PartCrafterSelectionPacket extends AbstractPacketThreadsafe {

  public ItemStack pattern;

  public PartCrafterSelectionPacket() {
  }

  public PartCrafterSelectionPacket(ItemStack pattern) {
    this.pattern = pattern;
  }

  @Override
  public void handleClientSafe(NetHandlerPlayClient netHandler) {
    // never
  }

  @Override
  public void handleServerSafe(NetHandlerPlayServer netHandler) {
    Container container = netHandler.playerEntity.openContainer;
    if(container instanceof ContainerPartBuilder) {
      ((ContainerPartBuilder) container).setPattern(pattern);
    }
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    pattern = ByteBufUtils.readItemStack(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeItemStack(buf, pattern);
  }
}
