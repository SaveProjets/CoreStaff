package fr.edminecoreteam.corestaff.edorm;

import fr.edminecoreteam.corestaff.Core;
import org.bukkit.scheduler.BukkitRunnable;

public class SQLTasks extends BukkitRunnable
{
    public int timer;
    private Core api;
    public MySQL database;

    public SQLTasks(Core api, MySQL database) {
        this.database = database;
        this.api = api;
        this.timer = 10;
    }

    @Override
    public void run() {
        if (timer == 0)
        {
            if (database.isOnline())
            {
                api.setSQLState(SQLState.CONNECTED);
                this.timer = 10;
            }
            if (!database.isOnline())
            {
                api.setSQLState(SQLState.DISCONNECTED);
                api.MySQLConnect();
                cancel();
            }
        }
        --timer;
    }
}
