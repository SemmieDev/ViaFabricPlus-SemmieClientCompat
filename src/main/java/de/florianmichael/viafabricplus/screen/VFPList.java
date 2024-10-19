/*
 * This file is part of ViaFabricPlus - https://github.com/FlorianMichael/ViaFabricPlus
 * Copyright (C) 2021-2024 FlorianMichael/EnZaXD <florian.michael07@gmail.com> and RK_01/RaphiMC
 * Copyright (C) 2023-2024 contributors
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

package de.florianmichael.viafabricplus.screen;

import de.florianmichael.viafabricplus.screen.base.PerServerVersionScreen;
import de.florianmichael.viafabricplus.screen.base.ProtocolSelectionScreen;
import de.florianmichael.viafabricplus.settings.impl.GeneralSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

/**
 * Wrapper class for {@link AlwaysSelectedEntryListWidget} including the following features:
 * <ul>
 *     <li>Changing the constructor arguments to be more readable and customizable</li>
 *     <li>Adds {@link #initScrollAmount(double)} to save the scroll state after closing the screen, requires static tracking by the implementation</li>
 *     <li>Removes the selection box</li>
 * </ul>
 *
 * @see ProtocolSelectionScreen
 * @see PerServerVersionScreen
 */
public class VFPList extends AlwaysSelectedEntryListWidget<VFPListEntry> {

    public VFPList(MinecraftClient minecraftClient, int width, int height, int top, int bottom, int entryHeight) {
        super(minecraftClient, width, height - top - bottom, top, entryHeight);
    }

    public void initScrollAmount(final double amount) {
        // Needs calling last in init to have data loaded before setting scroll amount
        if (GeneralSettings.global().saveScrollPositionInSlotScreens.getValue()) {
            this.setScrollAmount(amount);
        }
    }

    @Override
    public void setScrollAmountOnly(double amount) {
        super.setScrollAmountOnly(amount);
        updateSlotAmount(getScrollAmount()); // Ensure value is clamped
    }

    @Override
    protected void drawSelectionHighlight(DrawContext context, int y, int entryWidth, int entryHeight, int borderColor, int fillColor) {
        // Remove selection box
    }

    protected void updateSlotAmount(final double amount) {
        // To be overridden
    }

}
