package fr.nkri.core;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderPortal extends JavaPlugin {

    @Getter
    private static EnderPortal INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
    }
}
