package cube.service

import cube.Cube.Companion.BLUE
import cube.Cube.Companion.CLOCKWISE
import cube.Cube.Companion.COUNTER_CLOCKWISE
import cube.Cube.Companion.GREEN
import cube.Cube.Companion.ORANGE
import cube.Cube.Companion.RED
import cube.Cube.Companion.YELLOW
import cube.Cube
import cube.enums.Color
import cube.enums.YellowCenterPiecePairs

fun Cube.makeYellowCross(){
    if (yellowCrossIsPlaced()){
        return
    }

    if (yellowFaceHasNoYellowCenterPieces()){
        downAndOver()
        turn(YELLOW,CLOCKWISE)
        turn(YELLOW,CLOCKWISE)
    }

    if (yellowFaceHasTwoNeighbouringYellowCenterPieces()){
        while (!(colorValues[41] == YELLOW && colorValues[43] == YELLOW)){
            turn(YELLOW, CLOCKWISE)
        }
        downAndOver()
    }

    makeYellowCrossFromYellowLine()
    assert(yellowCrossIsPlaced())
}

fun Cube.orientYellowCross(){
    var pairs = identifyCorrectlyOrientedCenterPiecePairs()

    while(pairs == YellowCenterPiecePairs.NONE){
        turn(YELLOW, CLOCKWISE)
        pairs = identifyCorrectlyOrientedCenterPiecePairs()
    }

    when (pairs){
        YellowCenterPiecePairs.ALL -> return
        YellowCenterPiecePairs.BLUE_RED -> swapCenterPieces(BLUE)
        YellowCenterPiecePairs.RED_GREEN -> swapCenterPieces(RED)
        YellowCenterPiecePairs.GREEN_ORANGE -> swapCenterPieces(GREEN)
        YellowCenterPiecePairs.ORANGE_BLUE -> swapCenterPieces(ORANGE)
        YellowCenterPiecePairs.BLUE_GREEN -> {
            swapCenterPieces(GREEN)
            turn(YELLOW,CLOCKWISE)
            swapCenterPieces(ORANGE)
        }
        YellowCenterPiecePairs.RED_ORANGE -> {
            swapCenterPieces(ORANGE)
            turn(YELLOW, CLOCKWISE)
            swapCenterPieces(BLUE)
        }
        else -> throw IllegalStateException()
    }
}

fun Cube.downAndOver(){
    turn(BLUE, CLOCKWISE)
    turn(RED, CLOCKWISE)
    turn(YELLOW, CLOCKWISE)
    turn(RED, COUNTER_CLOCKWISE)
    turn(YELLOW, COUNTER_CLOCKWISE)
    turn(BLUE, COUNTER_CLOCKWISE)
}

fun Cube.yellowCrossIsPlaced(): Boolean{
    return colorValues[37] == YELLOW && colorValues[39] == YELLOW && colorValues[41] == YELLOW && colorValues[43] == YELLOW
}

fun Cube.yellowFaceHasNoYellowCenterPieces(): Boolean{
    return colorValues[37] != YELLOW && colorValues[39] != YELLOW && colorValues[41] != YELLOW && colorValues[43] != YELLOW
}

fun Cube.yellowFaceHasTwoNeighbouringYellowCenterPieces(): Boolean{
   return (colorValues[39] == YELLOW && colorValues[37] == YELLOW &&
       !(colorValues[41] == YELLOW || colorValues[43] == YELLOW)) ||

       (colorValues[37] == YELLOW && colorValues[41] == YELLOW &&
       !(colorValues[39] == YELLOW || colorValues[43] == YELLOW)) ||

       (colorValues[43] == YELLOW && colorValues[39] == YELLOW &&
       !(colorValues[37] == YELLOW || colorValues[41] == YELLOW)) ||

       (colorValues[41] == YELLOW && colorValues[43] == YELLOW &&
       !(colorValues[37] == YELLOW || colorValues[39] == YELLOW))
}

fun Cube.makeYellowCrossFromYellowLine(){
    if (colorValues[37] == YELLOW && colorValues[43] == YELLOW &&
        colorValues[39] != YELLOW && colorValues[41] != YELLOW){
        turn(YELLOW,CLOCKWISE)
    }
    downAndOver()
}

fun Cube.swapCenterPieces(color: Color){
    turn(color, CLOCKWISE)
    turn(YELLOW, CLOCKWISE)
    turn(color, COUNTER_CLOCKWISE)
    turn(YELLOW, CLOCKWISE)
    turn(color, CLOCKWISE)
    turn(YELLOW, CLOCKWISE)
    turn(YELLOW, CLOCKWISE)
    turn(color, COUNTER_CLOCKWISE)
    turn(YELLOW, CLOCKWISE)
}

fun Cube.identifyCorrectlyOrientedCenterPiecePairs(): YellowCenterPiecePairs {
    return when {
        colorValues[1] == BLUE && colorValues[32] == RED && colorValues[52] == GREEN && colorValues[12] == ORANGE -> YellowCenterPiecePairs.ALL
        colorValues[1] == BLUE && colorValues[32] == RED -> YellowCenterPiecePairs.BLUE_RED
        colorValues[32] == RED && colorValues[52] == GREEN -> YellowCenterPiecePairs.RED_GREEN
        colorValues[52] == GREEN && colorValues[12] == Color.ORANGE -> YellowCenterPiecePairs.GREEN_ORANGE
        colorValues[12] == ORANGE && colorValues[1] == BLUE -> YellowCenterPiecePairs.ORANGE_BLUE
        colorValues[1] == BLUE && colorValues[52] == GREEN -> YellowCenterPiecePairs.BLUE_GREEN
        colorValues[32] == RED && colorValues[12] == ORANGE -> YellowCenterPiecePairs.RED_ORANGE
        else -> YellowCenterPiecePairs.NONE
    }
}