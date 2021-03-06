package org.hexworks.zircon.internal.game.impl

import org.hexworks.zircon.api.data.BlockTileType.BOTTOM
import org.hexworks.zircon.api.data.BlockTileType.CONTENT
import org.hexworks.zircon.api.data.BlockTileType.TOP
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.extensions.toTileComposite
import org.hexworks.zircon.api.graphics.TileComposite
import org.hexworks.zircon.internal.game.ProjectionStrategy
import org.hexworks.zircon.internal.util.AnyGameAreaState
import org.hexworks.zircon.internal.util.RenderSequence
import org.hexworks.zircon.platform.util.SystemUtils

class TopDownProjectionStrategy : ProjectionStrategy {

    fun createRenderingSequence(position: Position3D): RenderSequence {
        return sequence {
            var currPos = position
            while (currPos.z >= 0) {
                SIDES.forEach { type ->
                    yield(currPos to type)
                }
                currPos = currPos.withRelativeZ(-1)
            }
        }
    }

    override fun projectGameArea(gameAreaState: AnyGameAreaState): Sequence<TileComposite> {
        val (blocks, _, visibleSize, visibleOffset) = gameAreaState
        val size = visibleSize.to2DSize()
        val remainingPositions = size.fetchPositions().toMutableSet()
        val lastZ = visibleOffset.z
        var currentPos = visibleOffset.withZ(visibleSize.zLength - 1 + lastZ)
        val renderSequence = createRenderingSequence(currentPos).iterator()
        return sequence {
            while (currentPos.z >= lastZ && remainingPositions.isNotEmpty() && renderSequence.hasNext()) {
                val tiles = mutableMapOf<Position, Tile>()
                val posIter = remainingPositions.iterator()
                val (topLeftCorner, nextSide) = renderSequence.next()
                currentPos = topLeftCorner
                while (posIter.hasNext()) {
                    val pos = posIter.next()
                    val tile = blocks[topLeftCorner.withRelativeX(pos.x).withRelativeY(pos.y)]
                            ?.getTileByType(nextSide) ?: Tile.empty()
                    if (tile.isNotEmpty) {
                        tiles[pos] = tile
                    }
                    if (tile.isOpaque) {
                        posIter.remove()
                    }
                }
                if (tiles.isNotEmpty()) {
                    yield(tiles.toTileComposite(size))
                }
            }
        }
    }

    companion object {
        private val SIDES = listOf(TOP, CONTENT, BOTTOM)
    }
}