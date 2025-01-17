package com.denizenscript.denizen.nms.v1_18.impl.network.packets;

import com.denizenscript.denizen.nms.interfaces.packets.PacketOutEntityMetadata;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.SynchedEntityData;

public class PacketOutEntityMetadataImpl implements PacketOutEntityMetadata {

    private ClientboundSetEntityDataPacket internal;

    public PacketOutEntityMetadataImpl(ClientboundSetEntityDataPacket internal) {
        this.internal = internal;
    }

    @Override
    public int getEntityId() {
        return internal.getId();
    }

    @Override
    public boolean checkForGlow() {
        for (SynchedEntityData.DataItem<?> data : internal.getUnpackedData()) {
            if (data.getAccessor().getId() == 0) {
                // TODO: strip out the 0x40 "Glowing" metadata rather than cancelling entirely?
                return true;
            }
        }
        return false;
    }
}
