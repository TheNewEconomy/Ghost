package net.tnemc.ghost.core.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
public class GhostAccount {

  Map<String, BigDecimal> holdings = new HashMap<>();

  private String name;
  private UUID id;

  public GhostAccount(String name) {
    this.name = name;
  }

  public GhostAccount(UUID id) {
    this.id = id;
  }

  public Map<String, BigDecimal> getHoldings() {
    return holdings;
  }

  public void setHoldings(Map<String, BigDecimal> holdings) {
    this.holdings = holdings;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setHoldings(String world, String currency, BigDecimal holdings) {
    this.holdings.put(world + ":" + currency, holdings);
  }

  public BigDecimal getHoldings(String world, String currency) {
    return this.holdings.getOrDefault(world + ":" + currency, BigDecimal.ZERO);
  }
}