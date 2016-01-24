package com.builtbroken.militarybasedecor.worldwar1.content.block;

import com.builtbroken.militarybasedecor.MilitaryBaseDecor;
import com.builtbroken.militarybasedecor.managers.ConfigManager;
import com.builtbroken.militarybasedecor.vanilla.VanillaModule;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockConcertinaWire extends Block {

    public BlockConcertinaWire() {
        super(Material.iron);
        this.setHardness(3.0F);
        this.setResistance(10.0F);
        this.setCreativeTab(MilitaryBaseDecor.CREATIVE_TAB_1);
        this.setBlockName("concertina_wire");
        this.setBlockTextureName(MilitaryBaseDecor.DOMAIN + ":" + "concertina_wire");
        this.setBlockUnbreakable();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int int4, float float1, float float2, float float3) {

        if (world.isRemote) {
            return true;
        } else {
            ItemStack itemstack = player.inventory.getCurrentItem();

            if (itemstack == null) {
                return true;
            } else {
                if (itemstack.getItem() == VanillaModule.wireCutters)
                {
                    world.playSoundEffect(x, y ,z, "militarybasedecor:wirecutters", 2.0F, 1.0F);
                    itemstack.damageItem(1, player);
                    world.setBlockToAir(x, y, z);
                    player.inventory.addItemStackToInventory(new ItemStack(this.getItem(world, x, y, z)));
                    player.inventoryContainer.detectAndSendChanges();
                    if (ConfigManager.WIRECUTTERS_CHAT) {
                        player.addChatMessage(new ChatComponentText(player.getDisplayName() + " cut a Concertina Wire"));
                    }
                }
                return true;
            }
        }
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity player) {
        if (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
            player.setInWeb();
            player.attackEntityFrom(DamageSource.cactus, 1.0F);
        }
        if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {

        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public int getRenderType() {
        return 1;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
}