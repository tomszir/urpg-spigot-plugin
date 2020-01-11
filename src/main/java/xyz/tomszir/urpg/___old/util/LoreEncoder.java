package xyz.tomszir.urpg.___old.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bukkit.ChatColor;

import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

// https://www.spigotmc.org/threads/how-to-hide-item-lore-how-to-bind-data-to-itemstack.196008/
public class LoreEncoder {

    public static String encode(String message) {
        StringBuilder result = new StringBuilder();

        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        String hex = Hex.encodeHexString(bytes);

        for (char c : hex.toCharArray())
            result.append(ChatColor.COLOR_CHAR).append(c);

        return result.toString();
    }

    public static String decode(String message) {
        char[] chars = message.toCharArray();
        char[] hexChars = new char[chars.length / 2];

        IntStream.range(0, chars.length)
            .filter(value -> value % 2 != 0)
            .forEach(value -> hexChars[value / 2] = chars[value]);

        try {
            return new String(Hex.decodeHex(hexChars), StandardCharsets.UTF_8);
        } catch (DecoderException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong while decoding the text!", e);
        }
    }
}
