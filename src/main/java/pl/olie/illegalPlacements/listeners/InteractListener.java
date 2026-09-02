package pl.olie.illegalPlacements.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.olie.illegalPlacements.Utils;

public class InteractListener implements Listener {
    private final Utils utils;
    public InteractListener(Utils utils){
        this.utils = utils;
    }
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getHand() != null) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || item.getType().isAir()) return;

        Material itemType = item.getType();
        if (!utils.isIllegalBlock(itemType)) return;

        Block clicked = event.getClickedBlock();
        if (clicked == null) return;

        BlockFace face = event.getBlockFace();
        if (face != BlockFace.UP && face != BlockFace.DOWN && face != BlockFace.NORTH && face != BlockFace.SOUTH && face != BlockFace.EAST && face != BlockFace.WEST) {
            return;
        }

        Block placeBlock = clicked.getRelative(face);

        if (!placeBlock.getType().isAir()) return;

        Material clickedType = clicked.getType();
        if (!clickedType.isSolid() && !utils.isIllegalBlock(clickedType)) return;

        boolean isDouble = utils.isDoubleBlock(itemType);

        if (isDouble) {
            Block above = placeBlock.getRelative(face);
            if (!above.getType().isAir()) return;
        }

        event.setCancelled(true);
        placeBlock.setType(itemType, false);

        if (isDouble) {
            Block top = placeBlock.getRelative(BlockFace.UP);
            top.setType(itemType, false);

            if (placeBlock.getBlockData() instanceof Bisected) {
                Bisected bottom = (Bisected) placeBlock.getBlockData();
                bottom.setHalf(Bisected.Half.BOTTOM);
                placeBlock.setBlockData(bottom, false);
            }
            if (top.getBlockData() instanceof Bisected) {
                Bisected topData = (Bisected) top.getBlockData();
                topData.setHalf(Bisected.Half.TOP);
                top.setBlockData(topData, false);
            }
        }

        player.swingMainHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            item.setAmount(item.getAmount() - 1);
        }
    }
}
