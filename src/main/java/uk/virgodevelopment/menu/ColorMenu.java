package uk.virgodevelopment.menu;

import io.github.nosequel.menus.MenuHandler;
import io.github.nosequel.menus.button.Button;
import io.github.nosequel.menus.button.ButtonBuilder;
import io.github.nosequel.menus.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import uk.virgodevelopment.ButterflyPlugin;
import uk.virgodevelopment.util.CC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ColorMenu extends Menu {

    public ColorMenu(Player player) {
        super(player, CC.translate("&eChoose your color"), 18);
    }

    @Override
    public List<Button> getButtons() {
        final List<Button> buttons = new ArrayList<>();

        for (int i = 0; i < colors.length; i++) {
            final ChatColor color = colors[i];
            final Function<ClickType, Boolean> action = type -> {

                if (!this.getPlayer().hasPermission("color." + color.name().toLowerCase())) {
                    this.getPlayer().sendMessage(CC.translate("&cNo Permission."));
                    return true;
                }

                if (this.getPlayer().hasMetadata("color")) {
                    this.getPlayer().removeMetadata("color", ButterflyPlugin.getPlugin(ButterflyPlugin.class));
                }

                this.getPlayer().setMetadata("color", new FixedMetadataValue(ButterflyPlugin.getPlugin(ButterflyPlugin.class), color.name()));

                this.getPlayer().closeInventory();
                return true;
            };

            buttons.add(new ButtonBuilder(i, new ItemStack(Material.WOOL, 1, this.findDyeColor(color).getWoolData()))
                    .displayName(color + color.name())
                    .lore(
                            CC.translate("&7Chat colors change the message"),
                            CC.translate("&7of your messages in chat."),
                            CC.translate(""),
                            CC.translate("&7Preview: &f" + this.getPlayer().getName() + color + " Example"),
                            CC.translate(""),
                            CC.translate("&eClick to select this color."),
                            CC.translate("&7&o(( &f&oLeft Click &7to equip this color!&7&o ))")
                    ).action(action));
        }

        return buttons;
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        MenuHandler.get().getMenus().remove(event.getPlayer());
    }

    private final ChatColor[] colors = new ChatColor[]{
            ChatColor.DARK_RED,
            ChatColor.RED,
            ChatColor.GOLD,
            ChatColor.YELLOW,
            ChatColor.DARK_GREEN,
            ChatColor.GREEN,
            ChatColor.AQUA,
            ChatColor.DARK_AQUA,
            ChatColor.DARK_BLUE,
            ChatColor.BLUE,
            ChatColor.LIGHT_PURPLE,
            ChatColor.DARK_PURPLE,
            ChatColor.WHITE,
            ChatColor.GRAY,
            ChatColor.DARK_GRAY,
            ChatColor.BLACK
    };

    private final Map<ChatColor, DyeColor> colorMap = new HashMap<ChatColor, DyeColor>() {{
        put(ChatColor.BLUE, DyeColor.BLUE);
        put(ChatColor.AQUA, DyeColor.CYAN);
        put(ChatColor.BLACK, DyeColor.BLACK);
        put(ChatColor.DARK_AQUA, DyeColor.CYAN);
        put(ChatColor.DARK_BLUE, DyeColor.BLUE);
        put(ChatColor.DARK_GREEN, DyeColor.GREEN);
        put(ChatColor.GREEN, DyeColor.LIME);
        put(ChatColor.DARK_PURPLE, DyeColor.PURPLE);
        put(ChatColor.LIGHT_PURPLE, DyeColor.PINK);
        put(ChatColor.RED, DyeColor.RED);
        put(ChatColor.DARK_RED, DyeColor.RED);
        put(ChatColor.YELLOW, DyeColor.YELLOW);
        put(ChatColor.WHITE, DyeColor.WHITE);
        put(ChatColor.GOLD, DyeColor.ORANGE);
        put(ChatColor.GRAY, DyeColor.GRAY);
        put(ChatColor.DARK_GRAY, DyeColor.GRAY);
    }};

    private DyeColor findDyeColor(ChatColor color) {
        return this.colorMap.getOrDefault(color, DyeColor.WHITE);
    }
}