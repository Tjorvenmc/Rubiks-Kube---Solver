package cube

import cube.data.DataHelper.Companion.centerPieceColorList
import cube.data.DataHelper.Companion.centerPieceIndexList
import cube.data.DataHelper.Companion.colorRotationListMap
import cube.data.DataHelper.Companion.cornerPieceColorList
import cube.data.DataHelper.Companion.cornerPieceIndexList
import cube.data.DataHelper.Companion.moveToTurn
import cube.data.DataHelper.Companion.scrambles
import cube.enums.Color
import cube.enums.Direction
import cube.service.*

class Cube {
    val colorValues: MutableList<Color> = mutableListOf()
    var turnsMade: MutableList<Pair<Color, Direction>> = mutableListOf()

    fun resetCube() {
        colorValues.clear()
        turnsMade.clear()
        for (color in Color.entries) {
            repeat(9) {
                colorValues.add(color)
            }
        }
    }

    fun isSolved(): Boolean {
        val solvedValues: MutableList<Color> = mutableListOf()
        for (color in Color.entries) {
            repeat(9) {
                solvedValues.add(color)
            }
        }
        return colorValues == solvedValues
    }

    fun turn(color: Color, direction: Direction) {
        val colorIndexes = colorRotationListMap[color]!!
        colorIndexes.forEach { indexList ->
            val temp = colorValues[indexList[0]]
            when {
                direction == Direction.CLOCKWISE -> {
                    colorValues[indexList[0]] = colorValues[indexList[3]]
                    colorValues[indexList[3]] = colorValues[indexList[2]]
                    colorValues[indexList[2]] = colorValues[indexList[1]]
                    colorValues[indexList[1]] = temp
                }
                direction == Direction.COUNTER_CLOCKWISE -> {
                    colorValues[indexList[0]] = colorValues[indexList[1]]
                    colorValues[indexList[1]] = colorValues[indexList[2]]
                    colorValues[indexList[2]] = colorValues[indexList[3]]
                    colorValues[indexList[3]] = temp
                }
            }
        }
        turnsMade.add(Pair(color, direction))
    }

    fun solve() {
        this.placeWhiteCenterPiecesOnWhiteFace()
        this.fillWhiteCross()
        this.orientWhiteCenterPieces()
        this.placeWhiteCorners()
        this.placeMiddleLayerPieces()
        this.makeYellowCross()
        this.orientYellowCross()
        this.positionYellowCorners()
        this.orientYellowCorners()
    }

    fun optimizeTurnsMade() {
        val optimizedMovesMade: MutableList<Pair<Color, Direction>> = turnsMade.toMutableList()
        var turn1: Pair<Color, Direction>
        var turn2: Pair<Color, Direction>
        var turn3: Pair<Color, Direction>
        var turn4: Pair<Color, Direction>

        repeat(2) {
            // Swaps two turns in the same direction with a double turn
            var i = 0
            while (i < optimizedMovesMade.size - 1) {
                turn1 = optimizedMovesMade[i]
                turn2 = optimizedMovesMade[i + 1]

                if (turn1 == turn2 && turn1.second != DOUBLE_TURN) {
                    optimizedMovesMade[i] = Pair(turn1.first, DOUBLE_TURN)
                    optimizedMovesMade.removeAt(i + 1) // Remove the second turn
                } else {
                    i++
                }
            }

            // Removes a turn immediately followed by a turn in the opposite direction
            i = 0
            while (i < optimizedMovesMade.size - 1) {
                turn1 = optimizedMovesMade[i]
                turn2 = optimizedMovesMade[i + 1]

                if (turn1.first == turn2.first && turn1.second != turn2.second && turn1.second != DOUBLE_TURN && turn2.second != DOUBLE_TURN) {
                    optimizedMovesMade.removeAt(i + 1) // Remove turn2
                    optimizedMovesMade.removeAt(i)     // Remove turn1
                } else {
                    i++
                }
            }

            // Removes two turns one way, followed by two turns the other way
            var j = optimizedMovesMade.size - 1
            while (j >= 3) {
                turn1 = optimizedMovesMade[j - 3]
                turn2 = optimizedMovesMade[j - 2]
                turn3 = optimizedMovesMade[j - 1]
                turn4 = optimizedMovesMade[j]

                if (turn1 == turn2 && turn3 == turn4 && turn1.second != turn3.second && turn1.second != DOUBLE_TURN && turn3.second != DOUBLE_TURN) {
                    optimizedMovesMade.removeAt(j)      // Remove turn4
                    optimizedMovesMade.removeAt(j - 1)  // Remove turn3
                    optimizedMovesMade.removeAt(j - 2)  // Remove turn2
                    optimizedMovesMade.removeAt(j - 3)  // Remove turn1
                }
                j--
            }

            // Removes three consecutive turns in the same direction
            j = optimizedMovesMade.size - 1
            while (j >= 2) {
                turn1 = optimizedMovesMade[j - 2]
                turn2 = optimizedMovesMade[j - 1]
                turn3 = optimizedMovesMade[j]

                if (turn1 == turn2 && turn2 == turn3) {
                    optimizedMovesMade[j - 2] = Pair(turn1.first, if (turn1.second == COUNTER_CLOCKWISE) CLOCKWISE else COUNTER_CLOCKWISE)
                    optimizedMovesMade.removeAt(j)     // Remove turn3
                    optimizedMovesMade.removeAt(j - 1) // Remove turn2
                }
                j--
            }

            // Removes four identical consecutive turns
            j = optimizedMovesMade.size - 1
            while (j >= 3) {
                turn1 = optimizedMovesMade[j - 3]
                turn2 = optimizedMovesMade[j - 2]
                turn3 = optimizedMovesMade[j - 1]
                turn4 = optimizedMovesMade[j]

                if (turn1 == turn2 && turn1 == turn3 && turn1 == turn4) {
                    optimizedMovesMade.removeAt(j)     // Remove turn4
                    optimizedMovesMade.removeAt(j - 1) // Remove turn3
                    optimizedMovesMade.removeAt(j - 2) // Remove turn2
                    optimizedMovesMade.removeAt(j - 3) // Remove turn1
                }
                j--
            }

            // Handle double turns before or after a turn of the same color
            i = 0
            while (i < optimizedMovesMade.size - 1) {
                turn1 = optimizedMovesMade[i]
                turn2 = optimizedMovesMade[i + 1]

                if ((turn1.first == turn2.first && turn1.second == DOUBLE_TURN && turn2.second == COUNTER_CLOCKWISE)
                    || (turn1.first == turn2.first && turn2.second == DOUBLE_TURN && turn1.second == COUNTER_CLOCKWISE)) {
                    optimizedMovesMade[i] = Pair(turn1.first, CLOCKWISE)
                    optimizedMovesMade.removeAt(i + 1)
                } else if ((turn1.first == turn2.first && turn1.second == DOUBLE_TURN && turn2.second == CLOCKWISE)
                    || (turn1.first == turn2.first && turn2.second == DOUBLE_TURN && turn1.second == CLOCKWISE)) {
                    optimizedMovesMade[i] = Pair(turn1.first, COUNTER_CLOCKWISE)
                    optimizedMovesMade.removeAt(i + 1)
                } else {
                    i++
                }
            }
        }

        turnsMade = optimizedMovesMade
    }


    fun scramble(scrambleIndex: Int){
        val moves: List<String> = scrambles[scrambleIndex].split(" ")
        moves.forEach { move ->
            moveToTurn(move)
        }
    }

    fun isSolvable(): Boolean {
        colorValues.groupBy { it }.values.forEach { colorGroup ->
            if (colorGroup.size != 9) {
                println("ERROR IN CUBE $colorGroup has wrong amount: ${colorGroup.size} for color ${colorGroup.first()}")
                return false
            }
        }

        cornerPieceIndexList.map { indexList ->
            val actualCubeCornerColors = setOf(
                colorValues[indexList[0]],
                colorValues[indexList[1]],
                colorValues[indexList[2]],
            )
            actualCubeCornerColors
        }.forEach { cubeCornerPieceSet ->
            if (cubeCornerPieceSet.size < 3) {
            println("INSOLVABLE CUBE! Corner has more than one of the same color!")
            }

            if (!cornerPieceColorList.contains(cubeCornerPieceSet)) {
                println("INSOLVABLE CUBE! Incorrect corner-piece: $cubeCornerPieceSet")
                return false
            }
        }

        centerPieceIndexList.map { indexList ->
            setOf(
                colorValues[indexList[0]],
                colorValues[indexList[1]],
            )
        }.forEach { centerPieceSet ->
            if (!centerPieceColorList.contains(centerPieceSet)) {
                println("ERROR IN CUBE! Incorrect center-piece: $centerPieceSet ")
                return false
            }
        }
        return true
    }

    companion object{
        val YELLOW = Color.YELLOW
        val BLUE = Color.BLUE
        val GREEN = Color.GREEN
        val ORANGE = Color.ORANGE
        val WHITE = Color.WHITE
        val RED = Color.RED
        val CLOCKWISE = Direction.CLOCKWISE
        val COUNTER_CLOCKWISE = Direction.COUNTER_CLOCKWISE
        val DOUBLE_TURN = Direction.DOUBLE_TURN
    }
}