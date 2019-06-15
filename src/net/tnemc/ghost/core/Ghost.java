package net.tnemc.ghost.core;

import net.tnemc.core.Reserve;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
public class Ghost extends JavaPlugin {

  @Override
  public void onEnable() {
    System.out.println("Enabling Ghost....");

    final boolean reserve = Bukkit.getPluginManager().isPluginEnabled("Reserve");
    System.out.println("Is Reserve Enabled? " + ((reserve)? "Yes" : "No"));

    if(reserve) {
      Reserve.instance().registerProvider(new GhostProvider(this));
    }

  }
}