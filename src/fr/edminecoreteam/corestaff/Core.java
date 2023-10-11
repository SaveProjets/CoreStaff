package fr.edminecoreteam.corestaff;

import fr.edminecoreteam.corestaff.commands.CommandChat;
import fr.edminecoreteam.corestaff.commands.CommandFreeze;
import fr.edminecoreteam.corestaff.commands.CommandHistory;
import fr.edminecoreteam.corestaff.commands.CommandVanish;
import fr.edminecoreteam.corestaff.edorm.MySQL;
import fr.edminecoreteam.corestaff.edorm.SQLState;
import fr.edminecoreteam.corestaff.listeners.*;
import fr.edminecoreteam.corestaff.listeners.PluginMessage.ReceivedPluginMessage;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Core extends JavaPlugin {

    private static Core instance;

    public MySQL database;
    private SQLState sqlState;

    public ArrayList<UUID> modList = new ArrayList<>();

    public ArrayList<UUID> vanishList = new ArrayList<>();
    public HashMap<UUID, PlayerManager> players = new HashMap<>();
    public Map<UUID, Location> freezedPlayers = new HashMap<>();
    public boolean chatEnabled = true;


    public String staffPrefix = "§d§lSTAFF §8§l» ";


    /*
     * Activation du plugin.
     */
    @Override
    public void onEnable() {

        MySQLConnect();
        loadListeners();
        loadCommands();
        loadPluginChannel();

        System.out.println("Core Staff Enabled.");
    }



    @Override
    public void onDisable() {

        MySQLDisconnect();

        System.out.println("Core Staff Disabled.");
    }

    private void loadListeners() {
        Core.instance = this;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new ModInteractItems(), this);
        pm.registerEvents(new ModCancels(), this);
        pm.registerEvents(new QuitEvent(), this);
        pm.registerEvents(new ChatEvent(), this);
    }


    private void loadCommands(){
        getCommand("vanish").setExecutor(new CommandVanish());
        getCommand("chat").setExecutor(new CommandChat());
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("history").setExecutor(new CommandHistory());
    }

    private void loadPluginChannel(){
        ReceivedPluginMessage pluginCListener = new ReceivedPluginMessage();
        getServer().getMessenger().registerIncomingPluginChannel((Plugin) this, "ModActivate", (PluginMessageListener) pluginCListener);
        getServer().getMessenger().registerIncomingPluginChannel(this, "ModIsActivate", pluginCListener);
    }

    public boolean isFreeze(Player p){
        return freezedPlayers.containsKey(p.getUniqueId());
    }

    /*
     * Méthode de connexion au serveur SQL.
     *
     * "jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9"
     */
    public void MySQLConnect()
    {
        instance = this;

        (this.database = new MySQL(instance, "jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9")).connexion();


    }

    /*
     * Méthode de déconnexion au serveur SQL.
     */
    private void MySQLDisconnect()
    {
        database.deconnexion();
    }

    /*
     * Méthode de statut de l'instance MySQL
     * State List: DISCONECTED 0, CONECTED 1.
     */
    public void setSQLState(SQLState sqlState)
    {
        this.sqlState = sqlState;
    }
    public boolean isSQLState(SQLState sqlState)
    {
        return this.sqlState == sqlState;
    }

    public static Core getInstance() { return Core.instance; }


}
