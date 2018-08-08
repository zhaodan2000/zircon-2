package org.codetome.zircon.api.grid

import org.codetome.zircon.api.animation.AnimationHandler
import org.codetome.zircon.api.behavior.*
import org.codetome.zircon.internal.behavior.ShutdownHook

/**
 * This is the main grid interface, at the lowest level supported. You can write your own
 * implementation of this if you want to have a custom grid handling algorithm
 * but you should probably use one of the existing implementations.
 *
 * This interface abstracts a grid at a more fundamental level, expressing methods for not
 * only printing titles but also changing colors, moving the cursor to new positions,
 * enable special modifiers and get notified when the grid's size has changed.
 *
 * If you want to write an application that has a very precise control of the grid,
 * this is the interface you should be programming against.
 */
interface TileGrid
    : AnimationHandler, Closeable, Clearable, DrawSurface, InputEmitter, Layerable, ShutdownHook, Styleable, TilesetOverride, TypingSupport {

    fun widthInPixels() = tileset().width * getBoundableSize().xLength

    fun heightInPixels() = tileset().height * getBoundableSize().yLength

}
