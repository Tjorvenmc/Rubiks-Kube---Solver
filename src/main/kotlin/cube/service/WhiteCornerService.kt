package cube.service

import cube.Cube
import cube.Cube.Companion.BLUE
import cube.Cube.Companion.CLOCKWISE
import cube.Cube.Companion.COUNTER_CLOCKWISE
import cube.Cube.Companion.GREEN
import cube.Cube.Companion.ORANGE
import cube.Cube.Companion.RED
import cube.Cube.Companion.WHITE
import cube.Cube.Companion.YELLOW
import cube.enums.Color
import cube.enums.WhiteCorner

fun Cube.placeWhiteCorners(){
    if (allWhiteCornersAreCorrectlyPlaced()){
        return
    }

    while (!allWhiteCornersAreCorrectlyPlaced()){
        placeWhiteCornersFromYellowFace()
        moveIncorrectlyPlacedWhiteCorners()
    }
}

fun Cube.allWhiteCornersAreCorrectlyPlaced(): Boolean {
    return cornerIsCorrectlyPlaced(WhiteCorner.BLUE_RED) &&
            cornerIsCorrectlyPlaced(WhiteCorner.RED_GREEN) &&
            cornerIsCorrectlyPlaced(WhiteCorner.GREEN_ORANGE) &&
            cornerIsCorrectlyPlaced(WhiteCorner.ORANGE_BLUE)
}

fun Cube.cornerIsCorrectlyPlaced(corner: WhiteCorner): Boolean {
    return when (corner) {
        WhiteCorner.BLUE_RED -> colorValues[20] == WHITE && colorValues[8] == BLUE && colorValues[27] == RED
        WhiteCorner.RED_GREEN -> colorValues[26] == WHITE && colorValues[33] == RED && colorValues[47] == GREEN
        WhiteCorner.GREEN_ORANGE -> colorValues[24] == WHITE && colorValues[45] == GREEN && colorValues[17] == ORANGE
        WhiteCorner.ORANGE_BLUE -> colorValues[18] == WHITE && colorValues[11] == ORANGE && colorValues[6] == BLUE
    }
}

fun Cube.yellowFaceHasWhiteCornerPieces(): Boolean {
    return cornerIsWhite(36,29,2) ||
            cornerIsWhite(35,42,53) ||
            cornerIsWhite(44,15,51) ||
            cornerIsWhite(0,9,38)
}

fun Cube.identifyWhiteCorner(index1: Int, index2: Int, index3: Int): WhiteCorner? {
    val colors = setOf(colorValues[index1], colorValues[index2], colorValues[index3])
   return when (colors) {
       setOf(BLUE,RED,WHITE) -> WhiteCorner.BLUE_RED
       setOf(RED, GREEN,WHITE) -> WhiteCorner.RED_GREEN
       setOf(GREEN, ORANGE,WHITE) -> WhiteCorner.GREEN_ORANGE
       setOf(ORANGE,BLUE,WHITE) -> WhiteCorner.ORANGE_BLUE
       else -> null
   }
}

fun Cube.placeWhiteCornersFromYellowFace() {
    while (yellowFaceHasWhiteCornerPieces()){
        if (identifyWhiteCorner(2,29,36) == WhiteCorner.BLUE_RED){
            while (!cornerIsCorrectlyPlaced(WhiteCorner.BLUE_RED)){
                downAndOver(RED)
            }
        }

        if (identifyWhiteCorner(35,53,42) == WhiteCorner.RED_GREEN){
            while (!cornerIsCorrectlyPlaced(WhiteCorner.RED_GREEN)){
                downAndOver(GREEN)
            }
        }

        if (identifyWhiteCorner(51,15,44) == WhiteCorner.GREEN_ORANGE){
            while (!cornerIsCorrectlyPlaced(WhiteCorner.GREEN_ORANGE)){
                downAndOver(ORANGE)
            }
        }

        if (identifyWhiteCorner(0,9,38) == WhiteCorner.ORANGE_BLUE){
            while (!cornerIsCorrectlyPlaced(WhiteCorner.ORANGE_BLUE)){
                downAndOver(BLUE)
            }
        }

        turn(YELLOW,CLOCKWISE)
    }
}

fun Cube.moveIncorrectlyPlacedWhiteCorners() {
    if (!cornerIsCorrectlyPlaced(WhiteCorner.BLUE_RED) && cornerIsWhite(8,27,20)){
        moveWhiteCornerUp(WhiteCorner.BLUE_RED)
    }
    if (!cornerIsCorrectlyPlaced(WhiteCorner.RED_GREEN) && cornerIsWhite(33,47,26)){
        moveWhiteCornerUp(WhiteCorner.RED_GREEN)
    }
    if (!cornerIsCorrectlyPlaced(WhiteCorner.GREEN_ORANGE) && cornerIsWhite(45,17,24)){
        moveWhiteCornerUp(WhiteCorner.GREEN_ORANGE)
    }
    if (!cornerIsCorrectlyPlaced(WhiteCorner.ORANGE_BLUE) && cornerIsWhite(11,6,18)){
        moveWhiteCornerUp(WhiteCorner.ORANGE_BLUE)
    }
}

fun Cube.moveWhiteCornerUp(corner: WhiteCorner){
    val cornerIndex1:Int
    val cornerIndex2: Int
    val cornerIndex3: Int
    val color: Color

    when (corner) {
        WhiteCorner.BLUE_RED -> {
            cornerIndex1 = 8
            cornerIndex2 = 20
            cornerIndex3 = 27
            color = RED
        }
        WhiteCorner.RED_GREEN -> {
            cornerIndex1 = 47
            cornerIndex2 = 26
            cornerIndex3 = 33
            color = GREEN
        }
        WhiteCorner.GREEN_ORANGE -> {
            cornerIndex1 = 17
            cornerIndex2 = 24
            cornerIndex3 = 45
            color = ORANGE
        }
        WhiteCorner.ORANGE_BLUE -> {
            cornerIndex1 = 6
            cornerIndex2 = 11
            cornerIndex3 = 18
            color = BLUE
        }
    }

    while (cornerIsWhite(cornerIndex1,cornerIndex2,cornerIndex3)){
        turn(color,CLOCKWISE)
        turn(YELLOW,CLOCKWISE)
        turn(color,COUNTER_CLOCKWISE)
    }
}

fun Cube.cornerIsWhite(index1: Int, index2: Int, index3: Int): Boolean {
    return colorValues[index1] == WHITE ||
            colorValues[index2] == WHITE ||
            colorValues[index3] == WHITE
}

fun Cube.downAndOver(color: Color){
    turn(color,CLOCKWISE)
    turn(YELLOW,CLOCKWISE)
    turn(color,COUNTER_CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
}
