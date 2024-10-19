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

package de.florianmichael.viafabricplus.screen.base;

import de.florianmichael.viafabricplus.ViaFabricPlus;
import de.florianmichael.viafabricplus.screen.VFPScreen;
import de.florianmichael.viafabricplus.screen.classic4j.BetaCraftScreen;
import de.florianmichael.viafabricplus.screen.classic4j.ClassiCubeLoginScreen;
import de.florianmichael.viafabricplus.screen.classic4j.ClassiCubeServerListScreen;
import de.florianmichael.viafabricplus.screen.realms.BedrockRealmsScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ServerListScreen extends VFPScreen {

    public static final ServerListScreen INSTANCE = new ServerListScreen();

    public ServerListScreen() {
        super(Text.translatable("screen.viafabricplus.server_list"), true);
    }

    @Override
    protected void init() {
        super.init();
        this.setupDefaultSubtitle();

        // ClassiCube
        final boolean loggedIn = ViaFabricPlus.global().getSaveManager().getAccountsSave().getClassicubeAccount() != null;

        final ButtonWidget.Builder classiCubeBuilder = ButtonWidget.builder(ClassiCubeServerListScreen.INSTANCE.getTitle(), button -> {
            if (!loggedIn) {
                ClassiCubeLoginScreen.INSTANCE.open(this);
                return;
            }
            ClassiCubeServerListScreen.INSTANCE.open(this);
        }).position(this.width / 2 - 100, this.height / 2 - 25).size(200, 20);
        if (!loggedIn) {
            classiCubeBuilder.tooltip(Tooltip.of(Text.translatable("classicube.viafabricplus.warning")));
        }
        this.addDrawableChild(classiCubeBuilder.build());

        final ButtonWidget.Builder betaCraftBuilder = ButtonWidget.builder(BetaCraftScreen.INSTANCE.getTitle(), button -> {
            BetaCraftScreen.INSTANCE.open(this);
        }).position(this.width / 2 - 100, this.height / 2 - 25 + 20 + 3).size(200, 20);
        if (BetaCraftScreen.SERVER_LIST == null) {
            betaCraftBuilder.tooltip(Tooltip.of(Text.translatable("betacraft.viafabricplus.warning")));
        }
        this.addDrawableChild(betaCraftBuilder.build());

        final ButtonWidget.Builder bedrockRealmsBuilder = ButtonWidget.builder(BedrockRealmsScreen.INSTANCE.getTitle(), button -> {
            BedrockRealmsScreen.INSTANCE.open(this);
        }).position(this.width / 2 - 100, this.height / 2 - 25 + 40 + 6).size(200, 20);
        final boolean missingAccount = ViaFabricPlus.global().getSaveManager().getAccountsSave().getBedrockAccount() == null; // Only check for presence, later validate
        if (missingAccount) {
            bedrockRealmsBuilder.tooltip(Tooltip.of(Text.translatable("bedrock_realms.viafabricplus.warning")));
        }

        final ButtonWidget bedrockRealmsButton = bedrockRealmsBuilder.build();
        this.addDrawableChild(bedrockRealmsButton);
        if (missingAccount) {
            bedrockRealmsButton.active = false;
        }
    }

}
