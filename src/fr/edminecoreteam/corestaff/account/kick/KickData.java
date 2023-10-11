package fr.edminecoreteam.corestaff.account.kick;

import fr.edminecoreteam.corestaff.edorm.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KickData {
    protected static String table;
    private static String playerKick;
    private static String playerMod;

    public KickData(String playerKick){
        this.playerKick = playerKick;
    }

    public KickData(String playerKick, String playerMod){
        this.playerKick = playerKick;
        this.playerMod = playerMod;
    }

    static {
        KickData.table = "ed_kick";
    }

    public List<String> getKick(){
        List<String> kickList = new ArrayList<String>();
        try{
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT DISTINCT p1.player_name, p2.player_name, reason, kick_date FROM " + table + " k JOIN ed_accounts p1 ON k.player_uuid = p1.player_uuid JOIN ed_accounts p2 ON kick_by_uuid = p2.player_uuid WHERE k.player_uuid = ?");
            ps.setString(1, playerKick.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                kickList.add(rs.getString("reason") + " (le " + rs.getString("kick_date") + " par " + rs.getString("p2.player_name") + " !");
            }
            kickList.add("fin");
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return kickList;
    }
}
