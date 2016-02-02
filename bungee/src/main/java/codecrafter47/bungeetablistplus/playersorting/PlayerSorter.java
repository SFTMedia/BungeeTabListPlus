/*
 * BungeeTabListPlus - a BungeeCord plugin to customize the tablist
 *
 * Copyright (C) 2014 - 2015 Florian Stober
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package codecrafter47.bungeetablistplus.playersorting;

import codecrafter47.bungeetablistplus.api.bungee.IPlayer;
import codecrafter47.bungeetablistplus.api.bungee.tablist.TabListContext;

import java.util.Collections;
import java.util.List;

public class PlayerSorter {
    private final List<SortingRule> rules;

    public PlayerSorter(List<SortingRule> rules) {
        this.rules = rules;
    }

    public void sort(TabListContext context, List<IPlayer> players) {
        Collections.sort(players, (p1, p2) -> {
            for (SortingRule rule : rules) {
                int i = rule.compare(context, p1, p2);
                if (i != 0) {
                    return i;
                }
            }
            if (players.indexOf(p2) > players.indexOf(p1)) {
                return -1;
            }
            return 1;
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSorter that = (PlayerSorter) o;

        return rules.equals(that.rules);
    }

    @Override
    public int hashCode() {
        return rules.hashCode();
    }
}
