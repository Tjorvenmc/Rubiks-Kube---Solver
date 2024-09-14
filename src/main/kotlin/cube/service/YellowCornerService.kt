package cube.service

import cube.Cube.Companion.BLUE
import cube.Cube.Companion.CLOCKWISE
import cube.Cube.Companion.COUNTER_CLOCKWISE
import cube.Cube.Companion.GREEN
import cube.Cube.Companion.ORANGE
import cube.Cube.Companion.RED
import cube.Cube.Companion.WHITE
import cube.Cube.Companion.YELLOW
import cube.Cube
import cube.enums.Color

fun Cube.positionYellowCorners(){
    if (allYellowCornersAreInCorrectPosition()){
        return
    }

    while (true) {
        val cornerPair = when {
            yellowCornerIsInCorrectPosition(36, 29, 2) -> Pair(RED, ORANGE)
            yellowCornerIsInCorrectPosition(42, 35, 53) -> Pair(GREEN, BLUE)
            yellowCornerIsInCorrectPosition(44, 15, 51) -> Pair(ORANGE, RED)
            yellowCornerIsInCorrectPosition(38, 9, 0) -> Pair(BLUE, GREEN)
            else -> null
        }

        cornerPair?.let { (firstColor, secondColor) ->
            while (!allYellowCornersAreInCorrectPosition()) {
                rotateYellowCorners(firstColor, secondColor)
            }
            return
        }

        rotateYellowCorners(RED,ORANGE)
    }
}

fun Cube.allYellowCornersAreInCorrectPosition(): Boolean{
    return yellowCornerIsInCorrectPosition(36,29,2) &&
            yellowCornerIsInCorrectPosition(42,35,53) &&
            yellowCornerIsInCorrectPosition(44,15,51) &&
            yellowCornerIsInCorrectPosition(38,9,0)
}

fun Cube.yellowCornerIsInCorrectPosition(index1: Int, index2: Int, index3: Int): Boolean {
    val colors = setOf(colorValues[index1],colorValues[index2],colorValues[index3])
    val indexes = setOf(index1,index2,index3)
    return when (indexes) {
        setOf(36,29,2) -> colors == setOf(YELLOW,RED,BLUE)
        setOf(42,35,53) -> colors == setOf(YELLOW,RED,GREEN)
        setOf(44,15,51) -> colors == setOf(YELLOW,ORANGE,GREEN)
        setOf(38,9,0) -> colors == setOf(YELLOW,ORANGE,BLUE)
        else -> throw IllegalStateException()
    }
}

fun Cube.rotateYellowCorners(color1: Color, color2: Color){
    turn(YELLOW, CLOCKWISE)
    turn(color1,CLOCKWISE)
    turn(YELLOW,COUNTER_CLOCKWISE)
    turn(color2, COUNTER_CLOCKWISE)
    turn(YELLOW,CLOCKWISE)
    turn(color1, COUNTER_CLOCKWISE)
    turn(YELLOW, COUNTER_CLOCKWISE)
    turn(color2, CLOCKWISE)
}

fun Cube.orientYellowCorners(){
    if (this.isSolved()){
        return
    }

    while (true){

        while (colorValues[36] != YELLOW){
            turn(BLUE,CLOCKWISE)
            turn(WHITE,CLOCKWISE)
            turn(BLUE, COUNTER_CLOCKWISE)
            turn(WHITE, COUNTER_CLOCKWISE)
        }

        turn(YELLOW,CLOCKWISE)

        if (topLayerIsSolved()){
            while (!this.isSolved()) {
                turn(YELLOW, CLOCKWISE)
            }

            return
        }
    }
}

fun Cube.topLayerIsSolved(): Boolean {
    return colorValues[0] == colorValues[1] &&
            colorValues[1] == colorValues[2] &&
            colorValues[29] == colorValues[32] &&
            colorValues[32] == colorValues[35] &&
            colorValues[51] == colorValues[52] &&
            colorValues[52] == colorValues[53] &&
            colorValues[9] == colorValues[12] &&
            colorValues[12] == colorValues[15]
}
