package com.codingforcookies.betterrecords.src.items;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.codingforcookies.betterrecords.src.BetterUtils;
import com.codingforcookies.betterrecords.src.betterenums.ConnectionHelper;
import com.codingforcookies.betterrecords.src.betterenums.IRecordWire;
import com.codingforcookies.betterrecords.src.client.BetterEventHandler;
import com.codingforcookies.betterrecords.src.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLazer extends BlockContainer {
	public BlockLazer() {
		super(Material.iron);
		setBlockBounds(0.25F, 0F, 0.25F, 0.75F, 0.75F, 0.74F);
	}
	
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te == null || !(te instanceof IRecordWire))
			return 0;
		
		BetterUtils.markBlockDirty(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		
        return (((IRecordWire)te).getConnections().size() > 0 ? 5 : 0);
    }
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		world.markBlockForUpdate(x, y, z);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te != null && te instanceof TileEntityLazer) {
			((TileEntityLazer)te).pitch = entityLiving.rotationPitch;
			((TileEntityLazer)te).yaw = entityLiving.rotationYaw;
		}
		
		if(world.isRemote && !ClientProxy.tutorials.get("lazer")) {
			BetterEventHandler.tutorialText = "Connect this to a record player using wire to add a stream of color to your songs!";
			BetterEventHandler.tutorialTime = System.currentTimeMillis() + 10000;
			ClientProxy.tutorials.put("lazer", true);
		}
	}
	
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		if(world.isRemote)
			return super.removedByPlayer(world,player, x, y, z, willHarvest);
		
		TileEntity te = world.getTileEntity(x, y, z);
		if(te != null && te instanceof IRecordWire)
			ConnectionHelper.clearConnections(world, (IRecordWire)te);
		
		return super.removedByPlayer(world,player, x, y, z, willHarvest);
	}
	
	@SideOnly(Side.CLIENT)
    public int getRenderType() {
		return -1;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityLazer();
	}
}