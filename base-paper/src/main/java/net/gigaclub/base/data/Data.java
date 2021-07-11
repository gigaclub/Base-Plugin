package net.gigaclub.base.data;

import net.gigaclub.base.odoo.Odoo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Data {

    private Odoo odoo;

    public Data(String hostname, String database, String username, String password) {
        this.odoo = new Odoo(hostname, database, username, password);
    }

    public int getPlayer(String playerUUID) {
        List players = this.odoo.search(
                "gc.user",
                Arrays.asList(
                        Arrays.asList(
                                Arrays.asList("mc_uuid", "=", playerUUID)
                        )
                ),
                new HashMap() {{ put("limit", 1); }}
        );
        if (players.size() > 0) {
            return (int) players.get(0);
        }
        return 0;
    }

    public boolean checkIfPlayerExists(String playerUUID) {
        return this.odoo.search_count(
            "gc.user",
            Arrays.asList(
                    Arrays.asList(
                        Arrays.asList("mc_uuid", "=", playerUUID)
                    )
            )
        ) > 0;
    }

    public boolean createPlayer(String name, String playerUUID) {
        return this.odoo.create(
            "gc.user",
            Arrays.asList(
                    new HashMap() {{ put("name", name); put("mc_uuid", playerUUID); }}
            )
        ) > 0;
    }

    public boolean checkName(String name, String playerUUID) {
        return this.odoo.search_count(
            "gc.user",
            Arrays.asList(
                Arrays.asList(
                    Arrays.asList("name", "=", name), Arrays.asList("mc_uuid", "=", playerUUID)
                )
            )
        ) > 0;
    }

    public void updateName(String name, String playerUUID) {
        this.odoo.write(
            "gc.user",
            Arrays.asList(
                    Arrays.asList(this.getPlayer(playerUUID)),
                    new HashMap() {{ put("name", name); }}
            )
        );
    }

    public void updateStatus(String playerUUID, String status) {
        this.odoo.write(
            "gc.user",
            Arrays.asList(
                    Arrays.asList(this.getPlayer(playerUUID)),
                    new HashMap() {{ put("state", status); }}
            )
        );
    }

}
