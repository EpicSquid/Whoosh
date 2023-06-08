//package dev.epicsquid.whoosh.utils
//
//import net.minecraft.core.BlockPos
//import net.minecraft.sounds.SoundEvents
//import net.minecraft.sounds.SoundSource
//import net.minecraft.world.InteractionHand
//import net.minecraft.world.entity.player.Player
//import net.minecraft.world.level.BlockGetter
//import net.minecraft.world.level.Level
//import net.minecraft.world.phys.Vec3
//import net.minecraftforge.common.MinecraftForge
//import net.minecraftforge.event.entity.EntityTeleportEvent
//
//object TeleportUtils {
//    // Based on the old school Ender IO Travel Staff teleport code
//    fun shortTeleport(level: Level, player: Player, hand: InteractionHand?): Boolean {
//        val targetVec = player.position().add(0.0, player.eyeHeight.toDouble(), 0.0)
//        val lookVec = player.lookAngle
//        var target: BlockPos? = null
//        var i = 7.0
//        while (i >= 2) {
//            val v3d = targetVec.add(lookVec.multiply(i, i, i))
//            target = BlockPos(Math.round(v3d.x).toDouble(), Math.round(v3d.y).toDouble(), Math.round(v3d.z).toDouble())
//            target = if (canTeleportTo(level, target.below())) {
//                break
//            } else {
//                null
//            }
//            i -= 0.5
//        }
//        if (target != null) {
//            if (!player.getLevel().isClientSide) {
//                val teleportVec = checkTeleport(player, target) ?: return false
//                player.teleportTo(teleportVec.x(), teleportVec.y(), teleportVec.z())
//            }
//            player.fallDistance = 0f
//            player.swing(hand, true)
//            player.playNotifySound(SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1f, 1f)
//            return true
//        }
//        return false
//    }
//
//    fun canTeleportTo(level: BlockGetter, target: BlockPos): Boolean {
//        return (!level.getBlockState(target.immutable().above(1)).canOcclude()
//                && !level.getBlockState(target.immutable().above(2)).canOcclude() && target.y >= level.minBuildHeight)
//    }
//
//    private fun checkTeleport(player: Player, target: BlockPos): Vec3? {
//        val event = EntityTeleportEvent(player, target.x + 0.5, target.y.toDouble(), target.z + 0.5)
//        return if (MinecraftForge.EVENT_BUS.post(event)) {
//            null
//        } else Vec3(event.targetX, event.targetY, event.targetZ)
//    }
//}
