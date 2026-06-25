package jp.amaterasu_hyouka.core.command;

import jp.amaterasu_hyouka.core.Permission.ServerPermission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public abstract class PlayerCommandExecutor implements CommandExecutor {

    private final ServerPermission permission;

    protected PlayerCommandExecutor(ServerPermission permission) {
        this.permission = permission;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandContext ctx = new CommandContext(sender, command, label, args);

        if (!ctx.isPlayer()) {
            ctx.reply(ChatColor.DARK_RED + "このコマンドはプレイヤーのみ実行できます");
            return true;
        }

        if (hasPermission(permission) && !ctx.isOp()) {
            ctx.reply(ChatColor.DARK_RED + "権限がありません");
            return true;
        }

        try {
            return execute(ctx);
        } catch (IllegalArgumentException e) {
            ctx.reply(ChatColor.DARK_RED + e.getMessage());
            ctx.replyUsage();
            return true;
        } catch (Exception e) {
            ctx.reply(ChatColor.DARK_RED + "コマンド実行中にエラーが発生しました");
            Bukkit.getLogger().log(Level.SEVERE, ">" + e);
            return true;
        }
    }

    protected abstract boolean execute(CommandContext ctx);

    public boolean hasPermission(ServerPermission permission) {
        return permission != null;
    }
}
