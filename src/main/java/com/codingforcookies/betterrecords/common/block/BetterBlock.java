package com.codingforcookies.betterrecords.common.block;

import com.codingforcookies.betterrecords.common.BetterRecords;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BetterBlock<T extends TileEntity> extends BlockContainer {

    public BetterBlock(Material material, String name) {
        super(material);
        setUnlocalizedName(name);
        this.setCreativeTab(BetterRecords.recordsTab);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        GameRegistry.registerBlock(this, name);
        return super.setUnlocalizedName("betterrecords:" + name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return 2;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public abstract T createNewTileEntity(World world, int meta);
}
