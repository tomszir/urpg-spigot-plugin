package xyz.tomszir.urpg.___old.items;

import org.json.JSONObject;
import xyz.tomszir.urpg.___old.util.LoreEncoder;

import java.util.List;

public class ItemData {

    private JSONObject data;

    public ItemData(JSONObject data) {
        this.data = data;
    }

    public static ItemData fromEncodedLore(List<String> lore) {
        String encodedLine = lore.get(lore.size() - 1).split(" ")[1];
        JSONObject json = new JSONObject(LoreEncoder.decode(encodedLine));

        return new ItemData(json);
    }

    public String getIdentifier() {
        return data.getString("Identifier");
    }

    public String getNickname() {
        return data.getString("Nickname");
    }

    public Boolean isSoulbound() {
        return data.getBoolean("IsSoulbound");
    }

    public String toString() {
        return data.toString();
    }
}
