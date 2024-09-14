package cube.data

import cube.Cube
import cube.Cube.Companion.BLUE
import cube.Cube.Companion.CLOCKWISE
import cube.Cube.Companion.COUNTER_CLOCKWISE
import cube.Cube.Companion.ORANGE
import cube.Cube.Companion.RED
import cube.Cube.Companion.GREEN
import cube.Cube.Companion.WHITE
import cube.Cube.Companion.YELLOW
import cube.enums.Color
import cube.enums.Direction
import java.io.FileNotFoundException
import java.io.InputStream

class DataHelper {
    companion object {
        val colorRotationListMap: MutableMap<Color, List<List<Int>>> = mutableMapOf()
        var cornerPieceColorList: List<Set<Color>>
        var cornerPieceIndexList: List<List<Int>>
        var centerPieceColorList: List<Set<Color>>
        var centerPieceIndexList: List<List<Int>>
        val scrambles: List<String> by lazy {
            val inputStream = this::class.java.getResourceAsStream("/scrambles")
                ?: throw IllegalArgumentException("Resource not found: /scrambles")
            inputStream.bufferedReader().readLines()
        }

        val colorNameMap = mapOf(
            Color.BLUE to "blue",
            Color.ORANGE to "orange",
            Color.WHITE to "white",
            Color.RED to "red",
            Color.YELLOW to "yellow",
            Color.GREEN to "green"
        )

        val whiteCenterPieceOnNonWhiteFaceIndexes = mapOf(
            3 to listOf(
                Pair(Color.ORANGE, Direction.CLOCKWISE),
                Pair(Color.WHITE, Direction.COUNTER_CLOCKWISE)
            ),
            7 to listOf
                (Pair(Color.BLUE, Direction.CLOCKWISE)),
            1 to listOf(
                Pair(Color.YELLOW, Direction.CLOCKWISE),
                Pair(Color.ORANGE, Direction.COUNTER_CLOCKWISE),
                Pair(Color.WHITE, Direction.COUNTER_CLOCKWISE),
                Pair(Color.GREEN, Direction.CLOCKWISE)
            ),
            5 to listOf(
                Pair(Color.WHITE, Direction.CLOCKWISE),
                Pair(Color.WHITE, Direction.CLOCKWISE),
                Pair(Color.RED, Direction.COUNTER_CLOCKWISE),
                Pair(Color.WHITE, Direction.CLOCKWISE)
            ),
            16 to listOf(
                Pair(Color.WHITE, Direction.COUNTER_CLOCKWISE),
                Pair(Color.GREEN, Direction.CLOCKWISE)
            ),
            12 to listOf(
                Pair(Color.ORANGE, Direction.COUNTER_CLOCKWISE)
            ),
            10 to listOf(
                Pair(Color.ORANGE, Direction.COUNTER_CLOCKWISE)
            ),
            14 to listOf(
                Pair(Color.ORANGE, Direction.CLOCKWISE)
            ),
            28 to listOf(
                Pair(Color.WHITE, Direction.CLOCKWISE),
                Pair(Color.BLUE, Direction.CLOCKWISE),
                Pair(Color.WHITE, Direction.CLOCKWISE),
                Pair(Color.WHITE, Direction.CLOCKWISE)
            ),
            30 to listOf(
                Pair(Color.RED, Direction.CLOCKWISE)
            ),
            34 to listOf(
                Pair(Color.WHITE, Direction.COUNTER_CLOCKWISE),
                Pair(Color.GREEN, Direction.COUNTER_CLOCKWISE)
            ),
            32 to listOf(
                Pair(Color.YELLOW, Direction.CLOCKWISE)
            ),
            48 to listOf(
                Pair(Color.ORANGE, Direction.COUNTER_CLOCKWISE),
                Pair(Color.WHITE, Direction.COUNTER_CLOCKWISE)
            ),
            46 to listOf(
                Pair(Color.GREEN, Direction.COUNTER_CLOCKWISE)
            ),
            50 to listOf(
                Pair(Color.WHITE, Direction.CLOCKWISE),
                Pair(Color.WHITE, Direction.CLOCKWISE),
                Pair(Color.RED, Direction.CLOCKWISE),
                Pair(Color.WHITE, Direction.CLOCKWISE)
            ),
            52 to listOf(
                Pair(Color.YELLOW, Direction.COUNTER_CLOCKWISE),
                Pair(Color.ORANGE, Direction.COUNTER_CLOCKWISE),
                Pair(Color.WHITE, Direction.COUNTER_CLOCKWISE),
                Pair(Color.GREEN, Direction.CLOCKWISE)
            ),
            41 to listOf(
                Pair(Color.ORANGE, Direction.CLOCKWISE),
                Pair(Color.ORANGE, Direction.CLOCKWISE),
                Pair(Color.WHITE, Direction.COUNTER_CLOCKWISE)
            ),
            43 to listOf(
                Pair(Color.YELLOW, Direction.COUNTER_CLOCKWISE)
            ),
            37 to listOf(
                Pair(Color.YELLOW, Direction.CLOCKWISE)
            ),
            39 to listOf(
                Pair(Color.YELLOW, Direction.CLOCKWISE)
            )
        )

        val whiteCenterPieceSideFaceColors = listOf(
            Triple(7, Color.BLUE,19),
            Triple(30, Color.RED,23),
            Triple(46, Color.GREEN,25),
            Triple(14, Color.ORANGE,21)
        )

        init {
            for (color in Color.entries) {
                colorRotationListMap[color] = createRotationLists(color)
            }
            cornerPieceColorList = createPieceColorList("/pieces/corner-pieces-colors")
            cornerPieceIndexList = createPieceIndexList("/pieces/corner-pieces-indexes")
            centerPieceColorList = createPieceColorList("/pieces/center-pieces-colors")
            centerPieceIndexList = createPieceIndexList("/pieces/center-pieces-indexes")
        }

        private fun createPieceColorList(path: String): List<Set<Color>> {
            val inputStream: InputStream = this::class.java.getResourceAsStream(path) ?: throw IllegalArgumentException("Resource not found: $path")
            return inputStream.bufferedReader().readLines().map { line ->
                line.split(",").mapNotNull { colorString ->
                    when (colorString.isNotEmpty()) {
                        true -> Color.valueOf(colorString)
                        false -> null
                    }
                }.toSet()
            }
        }

        private fun createPieceIndexList(path: String): List<List<Int>> {
            val inputStream: InputStream = this::class.java.getResourceAsStream(path)
                ?: throw IllegalArgumentException("Resource not found: $path")
            return inputStream.bufferedReader().readLines().map { line ->
                line.split(",").map {
                    it.trim().toInt()
                }
            }
        }

        fun Cube.moveToTurn(move:String){
            when (move){
                "U" -> turn(BLUE,CLOCKWISE)
                "U'" -> turn(BLUE,COUNTER_CLOCKWISE)
                "U2" -> {
                    turn(BLUE,CLOCKWISE)
                    turn(BLUE,CLOCKWISE)
                }
                "L" -> turn(ORANGE,CLOCKWISE)
                "L'" -> turn(ORANGE, COUNTER_CLOCKWISE)
                "L2" -> {
                    turn(ORANGE,CLOCKWISE)
                    turn(ORANGE,CLOCKWISE)
                }
                "F" -> turn(WHITE,CLOCKWISE)
                "F'" -> turn(WHITE, COUNTER_CLOCKWISE)
                "F2" -> {
                    turn(WHITE,CLOCKWISE)
                    turn(WHITE,CLOCKWISE)
                }
                "R" -> turn(RED,CLOCKWISE)
                "R'" -> turn(RED,COUNTER_CLOCKWISE)
                "R2" -> {
                    turn(RED,CLOCKWISE)
                    turn(RED,CLOCKWISE)
                }
                "B" -> turn(YELLOW,CLOCKWISE)
                "B'" -> turn(YELLOW,COUNTER_CLOCKWISE)
                "B2" -> {
                    turn(YELLOW,CLOCKWISE)
                    turn(YELLOW,CLOCKWISE)
                }
                "D" -> turn(GREEN,CLOCKWISE)
                "D'" -> turn(GREEN,CLOCKWISE)
                "D2" -> {
                    turn(GREEN,CLOCKWISE)
                    turn(GREEN,CLOCKWISE)
                }
            }
        }

        private fun createRotationLists(color: Color): List<List<Int>> {
            val pathColor = colorNameMap[color]
            val resourcePath = "/rotations/$pathColor-rotation" // Adjusted for resources
            val inputStream: InputStream = this::class.java.getResourceAsStream(resourcePath) ?: throw FileNotFoundException("Resource not found: $resourcePath")
            return inputStream.bufferedReader().use { reader ->
                reader.readLines().map { line ->
                    line.split(",").map { it.trim().toInt() }
                }
            }
        }
    }
}