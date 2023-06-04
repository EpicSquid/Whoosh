package dev.epicsquid.whoosh.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.entity.EntityTeleportEvent;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TeleportUtils {

	// Based on the old school Ender IO Travel Staff teleport code
	public static boolean shortTeleport(Level level, Player player, InteractionHand hand) {
		Vec3 targetVec = player.position().add(0, player.getEyeHeight(), 0);
		Vec3 lookVec = player.getLookAngle();
		BlockPos target = null;
		for (double i = 7; i >= 2; i -= 0.5) {
			Vec3 v3d = targetVec.add(lookVec.multiply(i, i, i));
			target = new BlockPos(Math.round(v3d.x), Math.round(v3d.y), Math.round(v3d.z));
			if (canTeleportTo(level, target.below())) {
				break;
			} else {
				target = null;
			}
		}
		if (target != null) {
			if (!player.getLevel().isClientSide) {
				Vec3 teleportVec = checkTeleport(player, target);
				if (teleportVec == null) {
					return false;
				}
				player.teleportTo(teleportVec.x(), teleportVec.y(), teleportVec.z());
			}
			player.fallDistance = 0;
			player.swing(hand, true);
			player.playNotifySound(SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1F, 1F);
			return true;
		}
		return false;
	}

	public static boolean canTeleportTo(BlockGetter level, BlockPos target) {
		return !level.getBlockState(target.immutable().above(1)).canOcclude()
						&& !level.getBlockState(target.immutable().above(2)).canOcclude()
						&& target.getY() >= level.getMinBuildHeight();
	}

	@Nullable
	private static Vec3 checkTeleport(Player player, BlockPos target) {
		EntityTeleportEvent event = new EntityTeleportEvent(player, target.getX() + 0.5, target.getY(), target.getZ() + 0.5);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return null;
		}
		return new Vec3(event.getTargetX(), event.getTargetY(), event.getTargetZ());
	}
}
