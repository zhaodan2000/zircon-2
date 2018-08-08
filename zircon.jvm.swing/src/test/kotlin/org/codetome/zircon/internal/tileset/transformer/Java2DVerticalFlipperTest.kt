package org.codetome.zircon.internal.tileset.transformer

import org.codetome.zircon.api.interop.Modifiers
import org.codetome.zircon.api.interop.Tiles
import org.codetome.zircon.internal.tileset.impl.DefaultTileTexture
import org.junit.Before
import org.junit.Test
import java.awt.image.BufferedImage

class Java2DVerticalFlipperTest {

    lateinit var target: Java2DVerticalFlipper

    @Before
    fun setUp() {
        target = Java2DVerticalFlipper()
    }

    @Test
    fun shouldProperlyRun() {
        val image = BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB)
        target.transform(DefaultTileTexture(WIDTH, HEIGHT, image), CHAR)

        // TODO: check vertical flip?
    }

    companion object {
        val WIDTH = 10
        val HEIGHT = 10
        val CHAR = Tiles.newBuilder()
                .modifiers(Modifiers.verticalFlip())
                .build()
    }
}
