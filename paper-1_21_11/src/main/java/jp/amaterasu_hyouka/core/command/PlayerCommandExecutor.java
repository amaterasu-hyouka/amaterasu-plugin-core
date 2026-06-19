package jp.amaterasu_hyouka.core.command;

import jp.amaterasu_hyouka.core.Permission.ServerPermission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public abstract class PlayerCommandExecutor implements CommandExecutor {

    private final ServerPermission permission;

    protected PlayerCommandExecutor(ServerPermission permission) {
        this.permission = permission;
    }

    @Override
    public final boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        CommandContext ctx = new CommandContext(sender, command, label, args);

        if (!ctx.isPlayer()) {
            ctx.reply(Component.text("このコマンドはプレイヤーのみ実行できます", NamedTextColor.DARK_RED));
            return true;
        }

        if (hasPermission(permission) && !ctx.isOp()) {
            ctx.reply(Component.text("権限がありません", NamedTextColor.DARK_RED));
            return true;
        }

        try {
            return execute(ctx);
        } catch (IllegalArgumentException e) {
            ctx.reply(Component.text(e.getMessage(), NamedTextColor.DARK_RED));
            ctx.replyUsage();
            return true;
        } catch (Exception e) {
            ctx.reply(Component.text("コマンド実行中にエラーが発生しました", NamedTextColor.DARK_RED));
            Bukkit.getServer().getLogger().log(Level.SEVERE, "> ", e);
            return true;
        }
    }

    protected abstract boolean execute(CommandContext ctx);

    public boolean hasPermission(ServerPermission permission) {
        return permission != null;
    }
}
