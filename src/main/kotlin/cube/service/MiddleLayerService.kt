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

fun Cube.placeMiddleLayerPieces(){
    if (middleLayerIsCorrectlyPlaced()){
        return
    }
    while (!middleLayerIsCorrectlyPlaced()){
        orientCorrectlyPlacedEdgePieces()
        if (middleLayerIsCorrectlyPlaced()){
            return
        }
        while (yellowFaceOnlyHasYellowEdgePieces()){
            moveAnEdgePieceToYellowFace()
        }
        placeMiddleLayerEdgePieces()
    }
}

fun Cube.orientCorrectlyPlacedEdgePieces(){
    if (colorValues[5] == RED && colorValues[28] == BLUE){
        orientEdgePiece(RED,BLUE)
    }
    if (colorValues[34] == GREEN && colorValues[50] == RED){
        orientEdgePiece(GREEN,RED)
    }
    if (colorValues[48] == ORANGE && colorValues[16] == GREEN){
        orientEdgePiece(ORANGE,GREEN)
    }
    if (colorValues[10] == BLUE && colorValues[3] == ORANGE){
        orientEdgePiece(BLUE,ORANGE)
    }
}

fun Cube.middleLayerIsCorrectlyPlaced(): Boolean {
    return colorValues[5] == BLUE && colorValues[28] == RED && colorValues[34] == RED &&
            colorValues[50] == GREEN && colorValues[48] == GREEN && colorValues[16] == ORANGE &&
            colorValues[10] == ORANGE && colorValues[3] == BLUE
}

fun Cube.yellowFaceOnlyHasYellowEdgePieces(): Boolean{
    return (colorValues[1] == YELLOW || colorValues[37] == YELLOW) &&
            (colorValues[32] == YELLOW || colorValues[39] == YELLOW) &&
            (colorValues[12] == YELLOW || colorValues[41] == YELLOW) &&
            (colorValues[52] == YELLOW || colorValues[43] == YELLOW)
}

fun Cube.moveAnEdgePieceToYellowFace(){
    when {
        !(colorValues[5] == BLUE && colorValues[28] == RED) -> moveEdgePieceUp(RED,BLUE)
        !(colorValues[34] == RED && colorValues[50] == GREEN) -> moveEdgePieceUp(GREEN,RED)
        !(colorValues[48] == GREEN && colorValues[16] == ORANGE) -> moveEdgePieceUp(ORANGE,GREEN)
        !(colorValues[10] == ORANGE && colorValues[3] == BLUE) -> moveEdgePieceUp(BLUE,ORANGE)
    }
}

fun Cube.placeMiddleLayerEdgePieces(){
    while(!yellowFaceOnlyHasYellowEdgePieces()){
        when {
            colorValues[1] == BLUE && colorValues[4] == BLUE && colorValues[37] != YELLOW -> placeAlignedMiddleLayerPiece(BLUE)
            colorValues[32] == RED && colorValues[31] == RED && colorValues[39] != YELLOW -> placeAlignedMiddleLayerPiece(RED)
            colorValues[49] == GREEN && colorValues[52] == GREEN && colorValues[43] != YELLOW -> placeAlignedMiddleLayerPiece(GREEN)
            colorValues[13] == ORANGE && colorValues[12] == ORANGE && colorValues[41] != YELLOW -> placeAlignedMiddleLayerPiece(ORANGE)
            else -> turn(YELLOW,CLOCKWISE)
        }
    }
}

fun Cube.placeAlignedMiddleLayerPiece(color: Color){
    val secondColor: Color
    val against: Boolean

    when (color) {
        BLUE -> {
            if (colorValues[37] == ORANGE) {
                secondColor = ORANGE
                against = true
            }
            else {
                secondColor = RED
                against = false
            }
        }
        RED -> {
            if (colorValues[39] == BLUE) {
                secondColor = BLUE
                against = true
            }
            else {
                secondColor = GREEN
                against = false
            }
        }
        GREEN -> {
            if (colorValues[43] == RED) {
                secondColor = RED
                against = true
            }
            else {
                secondColor = ORANGE
                against = false
            }
        }
        ORANGE -> {
            if (colorValues[41] == GREEN) {
                secondColor = GREEN
                against = true
            }
            else {
                secondColor = BLUE
                against = false
            }
        }
        else -> throw IllegalStateException()
    }

    when (against) {
        true -> {
            turn(YELLOW,COUNTER_CLOCKWISE)
            turn(secondColor,COUNTER_CLOCKWISE)
            turn(YELLOW,COUNTER_CLOCKWISE)
            turn(secondColor,CLOCKWISE)
            turn(YELLOW,CLOCKWISE)
            turn(color,CLOCKWISE)
            turn(YELLOW,CLOCKWISE)
            turn(color,COUNTER_CLOCKWISE)
        }
        false -> {
            turn(YELLOW,CLOCKWISE)
            turn(secondColor,CLOCKWISE)
            turn(YELLOW,CLOCKWISE)
            turn(secondColor,COUNTER_CLOCKWISE)
            turn(YELLOW,COUNTER_CLOCKWISE)
            turn(color,COUNTER_CLOCKWISE)
            turn(YELLOW,COUNTER_CLOCKWISE)
            turn(color,CLOCKWISE)
        }
    }
}

fun Cube.moveEdgePieceUp(color1: Color, color2: Color){
    turn(color1,CLOCKWISE)
    turn(YELLOW,CLOCKWISE)
    turn(color1,COUNTER_CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color2,COUNTER_CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color2,CLOCKWISE)
}

fun Cube.orientEdgePiece(color1: Color, color2: Color){
    turn(color1,CLOCKWISE)
    turn(YELLOW,CLOCKWISE)
    turn(color1,COUNTER_CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color2,COUNTER_CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color2,CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color1,CLOCKWISE)
    turn(YELLOW,CLOCKWISE)
    turn(color1,COUNTER_CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color2,COUNTER_CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color2,CLOCKWISE)
}