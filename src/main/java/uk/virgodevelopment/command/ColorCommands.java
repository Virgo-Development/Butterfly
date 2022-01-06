package uk.virgodevelopment.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import uk.virgodevelopment.menu.ColorMenu;

public class ColorCommands {

    @Command(label = "color")
    public void color(BukkitCommandExecutor executor) {
        new ColorMenu(executor.getPlayer()).updateMenu();
    }
}
