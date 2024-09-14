package cube.service

import cube.Cube.Companion.BLUE
import cube.Cube.Companion.CLOCKWISE
import cube.Cube.Companion.COUNTER_CLOCKWISE
import cube.Cube.Companion.GREEN
import cube.Cube.Companion.ORANGE
import cube.Cube.Companion.RED
import cube.Cube.Companion.WHITE
import cube.Cube
import cube.data.DataHelper.Companion.whiteCenterPieceOnNonWhiteFaceIndexes
import cube.data.DataHelper.Companion.whiteCenterPieceSideFaceColors
import cube.enums.Color
import cube.enums.Direction

val whiteCenterPieceIndexes = listOf(19, 21, 23, 25)

fun Cube.identifyWhiteCenterPiecesOnWhiteFace(): Set<Int> {
    assert(this.isSolvable())
    return whiteCenterPieceIndexes.filter { index ->
        colorValues[index] == WHITE
    }.toSet()
}

fun Cube.placeWhiteCenterPiecesOnWhiteFace() {
    val whiteCenterPiecesOnWhiteFace = this.identifyWhiteCenterPiecesOnWhiteFace()
    when (whiteCenterPiecesOnWhiteFace.size){
        4 -> return
        1 -> {
            while (colorValues[25] != WHITE) {
                this.turn(WHITE, CLOCKWISE)}}
        2 -> {
            when {
                whiteCenterPiecesOnWhiteFace.contains(21) && whiteCenterPiecesOnWhiteFace.contains(23) -> {
                    this.turn(WHITE, CLOCKWISE)
                    this.placeWhiteCenterPiecesOnWhiteFace()
                }
                whiteCenterPiecesOnWhiteFace.contains(19) && whiteCenterPiecesOnWhiteFace.contains(25) -> {
                    this.turn(BLUE, COUNTER_CLOCKWISE)
                    this.turn(WHITE, COUNTER_CLOCKWISE)
                    this.turn(BLUE, CLOCKWISE)
                    this.turn(WHITE, CLOCKWISE)
                }
                else -> {
                    while (colorValues[23] != WHITE && colorValues[25] != WHITE) {
                        this.turn(WHITE, CLOCKWISE)
                    }
                }
            }
        }
        3 -> {
            while (colorValues[21] != WHITE) {
                this.turn(WHITE, CLOCKWISE)
            }
        }
    }
    assert(this.isSolvable())
}

fun Cube.findWhiteCenterPieceOnNonWhiteFace(): List<Pair<Color, Direction>> {
    val possibleLocations = listOf(3, 7, 1, 5, 16, 12, 10, 14, 28, 30, 34, 32, 48, 46, 50, 52, 41, 43, 37, 39)
    possibleLocations.forEach { index ->
        if (colorValues[index] == WHITE) {
            return whiteCenterPieceOnNonWhiteFaceIndexes[index]!!
        }
    }
    assert(this.isSolvable())
    return listOf()
}

fun Cube.fillWhiteCross() {
    while (identifyWhiteCenterPiecesOnWhiteFace().size < 4) {
        findWhiteCenterPieceOnNonWhiteFace().forEach { (color, direction) ->
            turn(color, direction)
        }
    }
    assert(this.isSolvable())
}

fun Cube.findCorrectlyPlacedWhiteCenterPieces(): Set<Int> {
    val correctlyPlacedPieces = mutableSetOf<Int>()
    whiteCenterPieceSideFaceColors.forEach { values ->
        if (colorValues[values.first] == values.second) {
            correctlyPlacedPieces.add(values.third)
        }
    }
    assert(this.isSolvable())
    return correctlyPlacedPieces.toSet()
}

fun Cube.swapNeighbouringWhiteCenterPieces(color: Color) {
    turn(color, COUNTER_CLOCKWISE)
    turn(WHITE, COUNTER_CLOCKWISE)
    turn(color, CLOCKWISE)
    turn(WHITE, CLOCKWISE)
    turn(color, COUNTER_CLOCKWISE)

    assert(this.isSolvable())
}

fun Cube.swapOpposingWhiteCenterPieces(color: Color) {
    turn(color, COUNTER_CLOCKWISE)
    turn(WHITE, CLOCKWISE)
    turn(WHITE, CLOCKWISE)
    turn(color, CLOCKWISE)
    turn(WHITE, CLOCKWISE)
    turn(WHITE, CLOCKWISE)
    turn(color, COUNTER_CLOCKWISE)

    assert(this.isSolvable())
}

fun Cube.orientWhiteCenterPieces() {
    var correctlyPlacedPieces = findCorrectlyPlacedWhiteCenterPieces()

    while (correctlyPlacedPieces.size < 2) {
        turn(WHITE, CLOCKWISE)
        correctlyPlacedPieces = findCorrectlyPlacedWhiteCenterPieces()
    }

    if (correctlyPlacedPieces.size == 4) {
        return
    }

    when {
        correctlyPlacedPieces.contains(19) && correctlyPlacedPieces.contains(23) -> swapNeighbouringWhiteCenterPieces(GREEN)
        correctlyPlacedPieces.contains(23) && correctlyPlacedPieces.contains(25) -> swapNeighbouringWhiteCenterPieces(ORANGE)
        correctlyPlacedPieces.contains(21) && correctlyPlacedPieces.contains(25) -> swapNeighbouringWhiteCenterPieces(BLUE)
        correctlyPlacedPieces.contains(19) && correctlyPlacedPieces.contains(21) -> swapNeighbouringWhiteCenterPieces(RED)
        correctlyPlacedPieces.contains(19) && correctlyPlacedPieces.contains(25) -> swapOpposingWhiteCenterPieces(ORANGE)
        correctlyPlacedPieces.contains(21) && correctlyPlacedPieces.contains(23) -> swapOpposingWhiteCenterPieces(BLUE)
    }
    assert(this.isSolvable())
}