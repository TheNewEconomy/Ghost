package net.tnemc.ghost.core;

import net.tnemc.commands.core.CommandsHandler;
import net.tnemc.core.Reserve;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

/*
 * Ghost Server Plugin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Ghost extends JavaPlugin implements TabCompleter {

  CommandsHandler handler;

  @Override
  public void onEnable() {
    System.out.println("Enabling Ghost....");

    System.out.println("Enabling TNCH...");
    handler = new CommandsHandler(this, YamlConfiguration.loadConfiguration(new InputStreamReader(getResource("commands.yml")))).withTranslator((message)->{
      if(message.equalsIgnoreCase("Messages.Command.Developer")) {
        return Optional.of(ChatColor.RED + "Translated Message Test: You must be a developer for this command.");
      }
      return Optional.empty();
    });

    handler.addExecutor("hello_exe", ((commandSender, command, s, strings) ->{
      commandSender.sendMessage(ChatColor.RED + "Kappa");
      return true;
    }));
    handler.addExecutor("world_exe", ((commandSender, command, s, strings) ->{
      commandSender.sendMessage(ChatColor.RED + "Kappa 2");
      return true;
    }));

    handler.load();

    final boolean reserve = Bukkit.getPluginManager().isPluginEnabled("Reserve");
    System.out.println("Is Reserve Enabled? " + ((reserve)? "Yes" : "No"));

    if(reserve) {
      Reserve.instance().registerProvider(new GhostProvider(this));
    }

  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {

    System.out.println("Ghost.onCommand(" + label + ")");
    return handler.handle(sender, command, label, arguments);
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] arguments) {
    System.out.println("Tab Complete");
    return handler.tab(sender, command, alias, arguments);
  }
}