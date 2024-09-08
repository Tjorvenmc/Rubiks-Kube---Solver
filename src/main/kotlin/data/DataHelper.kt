package data

import enums.Color
import java.io.File

class DataHelper {
    companion object {
        lateinit var colorRotationListMap: MutableMap<Color, List<List<Int>>>
        lateinit var cornerPieceColorList: List<Set<Color>>
        lateinit var cornerPieceIndexList: List<List<Int>>
        lateinit var centerPieceColorList: List<Set<Color>>
        lateinit var centerPieceIndexList: List<List<Int>>
    }

    private val colorNameMap = mapOf(
        Color.BLUE to "blue",
        Color.ORANGE to "orange",
        Color.WHITE to "white",
        Color.RED to "red",
        Color.YELLOW to "yellow",
        Color.GREEN to "green"
    )

    init {
        for (color in Color.entries) {
            colorRotationListMap[color] = createRotationLists(color)
        }
        cornerPieceColorList = createPieceColorList("resources/pieces/corner-pieces-colors")
        cornerPieceIndexList = createPieceIndexList("resources/pieces/corner-pieces-indexes")
        centerPieceColorList = createPieceColorList("resources/pieces/center-pieces-colors")
        centerPieceIndexList = createPieceIndexList("resources/pieces/center-pieces-indexes")
    }

    private fun createRotationLists(color: Color): List<List<Int>> {
        return File("resources/rotations/" + colorNameMap[color] + "-rotation").readLines().map { line ->
            line.split(",").map {
                it.trim().toInt()
            }
        }
    }

    private fun createPieceColorList(path: String): List<Set<Color>> {
        return File(path).readLines().map { line ->
                line.split(",").map { colorString ->
                    Color.valueOf(colorString.trim().uppercase())
                }.toSet()
            }
    }

    private fun createPieceIndexList(path: String): List<List<Int>>  {
        return File(path).readLines().map { line ->
            line.split(",").map {
                it.toInt()
            }
        }
    }
}