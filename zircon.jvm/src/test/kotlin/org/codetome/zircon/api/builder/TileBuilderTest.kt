package org.codetome.zircon.api.builder

import org.assertj.core.api.Assertions.assertThat
import org.codetome.zircon.api.builder.data.TileBuilder
import org.codetome.zircon.api.builder.graphics.StyleSetBuilder
import org.codetome.zircon.api.color.TextColor
import org.codetome.zircon.api.data.Tile
import org.codetome.zircon.api.interop.Modifiers
import org.junit.Test

class TileBuilderTest {

    @Test
    fun shouldBuildProperTextCharacter() {
        val result = TileBuilder.newBuilder()
                .backgroundColor(BG_COLOR)
                .foregroundColor(FG_COLOR)
                .character(CHAR)
                .tags(TAGS)
                .modifiers(MODIFIER)
                .build()

        assertThat(result).isEqualTo(
                Tile.create(
                        character = CHAR,
                        style = StyleSetBuilder.newBuilder()
                                .foregroundColor(FG_COLOR)
                                .backgroundColor(BG_COLOR)
                                .modifiers(MODIFIER)
                                .build()))
    }

    companion object {
        val BG_COLOR = TextColor.fromString("#aabbcc")
        val FG_COLOR = TextColor.fromString("#ccbbaa")
        val TAGS = setOf("TAG1", "TAG2")
        val CHAR = 'a'
        val MODIFIER = Modifiers.crossedOut()
    }
}
