package jp.amaterasu_hyouka.core.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public record CommandContext(CommandSender sender, Command command, String label, String[] args) {
    @Override
    public String[] args() {
        return hasAdmin() ? Arrays.copyOf(args, args.length - 1) : args;
    }

    public boolean hasAdmin() {
        return args.length > 0 && args[args.length - 1].equals("admin");
    }

    public int argCount() {
        return args().length;
    }

    public boolean isCommandRangeInvalid(int min, int max) {
        return min > max || min < 0 || argCount() < min || argCount() > max;
    }

    public boolean isArgOutOfRange(int index) {
        return index < 0 || index >= args().length;
    }

    public int intArg(int index) {
        if (isArgOutOfRange(index)) throw new IllegalArgumentException("引数[" + index + "] が範囲外です");
        try {
            return Integer.parseInt(args()[index]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("引数[" + index + "] が数値ではありません");
        }
    }

    public double doubleArg(int index) {
        if (isArgOutOfRange(index)) throw new IllegalArgumentException("引数[" + index + "] が範囲外です");
        try {
            return Double.parseDouble(args()[index]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("引数[" + index + "] が数値ではありません");
        }
    }

    public long longArg(int index) {
        if (isArgOutOfRange(index)) throw new IllegalArgumentException("引数[" + index + "] が範囲外です");
        try {
            return Long.parseLong(args()[index]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("引数[" + index + "] が数値ではありません");
        }
    }

    public String stringArg(int index) {
        if (isArgOutOfRange(index)) throw new IllegalArgumentException("引数[" + index + "] が範囲外です");
        return args()[index];
    }

    public boolean booleanArg(int index) {
        if (isArgOutOfRange(index)) throw new IllegalArgumentException("引数[" + index + "] が範囲外です");

        String value = args()[index];
        if ("true".equalsIgnoreCase(value)) return true;
        if ("false".equalsIgnoreCase(value)) return false;

        throw new IllegalArgumentException("引数[" + index + "] が真偽値ではありません (true/false)");
    }

    public boolean isCommandArg(int index, String command) {
        if (isArgOutOfRange(index)) return false;
        return stringArg(index).equalsIgnoreCase(command);
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public Player player() {
        return (Player) sender;
    }

    public boolean isOp() {
        Player player = player();
        return player != null && player().isOp();
    }

    public void reply(String message) {
        sender.sendMessage(message);
    }
    public void reply(Component component) {
        sender.sendMessage(component);
    }

    public void replyUsage() {
        String usage = command.getUsage();
        if (usage.isEmpty()) {
            return;
        }
        sender.sendMessage(usage);
    }
}
