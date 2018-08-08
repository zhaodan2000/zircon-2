package org.codetome.zircon.examples;

import org.codetome.zircon.TerminalUtils;
import org.codetome.zircon.api.data.Size;
import org.codetome.zircon.api.graphics.Layer;
import org.codetome.zircon.api.interop.Screens;
import org.codetome.zircon.api.interop.Sizes;
import org.codetome.zircon.api.resource.CP437TilesetResource;
import org.codetome.zircon.api.resource.REXPaintResource;
import org.codetome.zircon.api.screen.Screen;
import org.codetome.zircon.api.grid.TileGrid;

import java.io.InputStream;
import java.util.List;

public class RexTesterExample {
    private static final int TERMINAL_WIDTH = 70;
    private static final int TERMINAL_HEIGHT = 40;
    private static final Size SIZE = Sizes.create(TERMINAL_WIDTH, TERMINAL_HEIGHT);
    private static final InputStream RESOURCE = RexTesterExample.class.getResourceAsStream("/rex_files/Buildings.xp");

    public static void main(String[] args) {
        REXPaintResource rex = REXPaintResource.loadREXFile(RESOURCE);
        final TileGrid tileGrid = TerminalUtils.fetchTerminalBuilder(args)
                .font(CP437TilesetResource.REX_PAINT_16X16)
                .initialTerminalSize(SIZE)
                .build();
        final Screen screen = Screens.createScreenFor(tileGrid);
        screen.setCursorVisibility(false);
        List<Layer> layers = rex.toLayerList();

        for (Layer layer: layers) {
            screen.pushLayer(layer);
        }

        screen.display();
    }
}
