package fr.edminecoreteam.corestaff;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.edminecoreteam.corestaff.commands.CommandMod;
import fr.edminecoreteam.corestaff.edorm.MySQL;
import fr.edminecoreteam.corestaff.edorm.SQLState;
import fr.edminecoreteam.corestaff.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Core extends JavaPlugin implements PluginMessageListener {

    private static Core instance;

    public MySQL database;
    private SQLState sqlState;

    public ArrayList<UUID> modList = new ArrayList<>();
    public HashMap<UUID, PlayerManager> players = new HashMap<>();


    /*
     * Activation du plugin.
     */
    @Override
    public void onEnable() {

        MySQLConnect();
        loadListeners();
        loadCommands();

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

    }

    private void loadCommands(){
        getCommand("moderation").setExecutor(new CommandMod());

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


    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {
        if (!channel.equals("BungeeCord"))
        {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel"))
        {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }


    public static Core getInstance() {  return Core.instance; }



}
