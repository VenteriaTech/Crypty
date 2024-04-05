package cz.venteria.wallet.commands;

import cz.venteria.Main;
import cz.venteria.apis.Command;
import cz.venteria.wallet.Wallet;
import cz.venteria.wallet.enums.CryptoType;
import cz.venteria.wallet.enums.Status;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.List;
import java.util.logging.Level;

public class WalletCommand extends Command {

    private Main plugin;
    public WalletCommand(String command, String[] aliases, String description) {
        super("wallet", new String[]{"crypty"}, "Main command for Crypty plugin");
        this.plugin = Main.getInstance();

    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().log(Level.WARNING, "Crypty >> You cannot use this command from console!");
            return;
        }


        // /wallet open <private key>
        if (args[0].equalsIgnoreCase("open") && args.length == 2) {
            if (plugin.getWalletAPI().openWallet(player, args[1]) == Status.SUCCESS) {
                player.sendMessage("Otevřel si úspěšně peněženku...");
                return;
            }
            player.sendMessage("Neplatný klíč k peněžence!");

            return;
        }

        // /wallet close
        if (args[0].equalsIgnoreCase("close") && args.length == 1) {
            if (plugin.getWalletAPI().closeWallet(player) == Status.SUCCESS) {
                player.sendMessage("Zavřel si úspěšně peněženku...");
                return;
            }
            player.sendMessage("Nebyla nalezena otevřená peněženka!");
            return;
        }

        // /wallet price <crypto>
        if (args[0].equalsIgnoreCase("price") && args.length == 2) {
            Wallet playerWallet = plugin.getWalletAPI().getPlayerWallet(player);
            player.sendMessage("Cena crypta: " + playerWallet.getPrice(CryptoType.valueOf(args[1].toUpperCase())) + " "+ CryptoType.valueOf(args[1].toUpperCase()));
            return;
        }

        // /wallet send <public key> <crypto> <amount>
        if (args[0].equalsIgnoreCase("send") && args.length == 4) {


            return;
        }


        // /wallet buy <crypto> <amount>
        if (args[0].equalsIgnoreCase("buy") && args.length == 3) {


            return;
        }


        // /wallet sell <crypto> <amount>
        if (args[0].equalsIgnoreCase("sell") && args.length == 3) {


            return;
        }

        // /wallet help
        if (args[0].equalsIgnoreCase("help") && args.length == 1) {


            return;
        }


    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
