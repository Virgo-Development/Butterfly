package uk.virgodevelopment;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.menus.MenuHandler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uk.virgodevelopment.command.ColorCommands;
import uk.virgodevelopment.listener.ChatListener;

import java.util.Collections;

@Getter
public class ButterflyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new MenuHandler(this);

        Collections.singletonList(
                new ChatListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        final CommandHandler commandHandler = new BukkitCommandHandler("color");

        commandHandler.registerCommand(new ColorCommands());
    }
}
