package com.codingforcookies.betterrecords.src.packets;

import java.util.EnumMap;

import net.minecraft.nbt.NBTTagCompound;

import com.codingforcookies.betterrecords.src.betterenums.RecordConnection;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	public static EnumMap<Side, FMLEmbeddedChannel> channels;
	
	public static void sendWireConnectionFromClient(RecordConnection connection) {
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeOutbound(new PacketWireConnection(connection));
	}
	
	public static void sendURLWriteFromClient(int x, int y, int z, String name, String url, String localName, int size, int color, String author) {
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeOutbound(new PacketURLWriter(x, y, z, name, url, localName, size, color, author));
	}

	public static void sendURLWriteFromClient(int x, int y, int z, String name, String url, String localName, int size) {
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeOutbound(new PacketURLWriter(x, y, z, name, url, localName, size));
	}
	
	public static void sendRecordPlayToAllFromServer(int x, int y, int z, int dimension, float playRadius, NBTTagCompound nbt, boolean repeat) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(new PacketRecordPlayerPlay(x, y, z, playRadius, dimension, nbt, repeat));
	}
	
	public static void sendRecordPlayToAllFromServer(int x, int y, int z, int dimension, float playRadius, String name, String url, String local, boolean repeat) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(new PacketRecordPlayerPlay(x, y, z, playRadius, dimension, name, url, local, repeat));
	}

	public static void sendSoundStopToAllFromServer(int x, int y, int z, int dimension) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(new PacketSoundStop(x, y, z, dimension));
	}
	
	public static void sendRadioPlayToAllFromServer(int x, int y, int z, int dimension, float playRadius, String local, String url) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(new PacketRadioPlay(x, y, z, playRadius, dimension, local, url));
	}
}