package org.codetome.zircon.internal.tileset.transformer

import org.codetome.zircon.api.builder.data.TileBuilder
import org.codetome.zircon.api.interop.Modifiers
import org.codetome.zircon.internal.tileset.impl.DefaultTileTexture
import org.junit.Before
import org.junit.Test
import java.awt.image.BufferedImage

class Java2DBorderTransformerTest {

    lateinit var target: Java2DBorderTransformer

    @Before
    fun setUp() {
        target = Java2DBorderTransformer()
    }

    @Test
    fun shouldProperlyRun() {
        val image = BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB)
        target.transform(DefaultTileTexture(WIDTH, HEIGHT, image), CHAR)

        // TODO: check border?
    }

    companion object {
        val WIDTH = 10
        val HEIGHT = 10
        val CHAR = TileBuilder.newBuilder()
                .modifiers(Modifiers.border())
                .build()
    }
}
