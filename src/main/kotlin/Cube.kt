import data.DataHelper.Companion.colorRotationListMap
import data.DataHelper.Companion.cornerPieceColorList
import data.DataHelper.Companion.cornerPieceIndexList
import enums.Color
import enums.Direction

class Cube {
    private val colorValues: MutableList<Color> = mutableListOf()
    private val turnsMade: MutableList<Pair<Color, Direction>> = mutableListOf()

    private fun setColorValuesToSolved(){
        for (color in Color.entries) {
            repeat(8) {
                colorValues.add(color)
            }
        }
    }

    fun turn(color: Color, direction: Direction){
        val colorIndexes = colorRotationListMap[color]!!
        colorIndexes.forEach { indexList ->
            val temp = colorValues[indexList[0]]
            if (direction == Direction.CLOCKWISE){
                colorValues[indexList[0]] = colorValues[indexList[3]]
                colorValues[indexList[3]] = colorValues[indexList[2]]
                colorValues[indexList[2]] = colorValues[indexList[1]]
                colorValues[indexList[1]] = temp
            } else if (direction == Direction.COUNTER_CLOCKWISE){
                colorValues[indexList[0]] = colorValues[indexList[1]]
                colorValues[indexList[1]] = colorValues[indexList[2]]
                colorValues[indexList[2]] = colorValues[indexList[3]]
                colorValues[indexList[3]] = temp
            } else {
            }
        }
        turnsMade.add(Pair(color, direction))
    }

    fun solve(){

    }

    fun optimiseTurnsMade(){

    }

    fun isSolvable(): Boolean{
        //check for correct number of colors
        colorValues.groupBy { it }.values.forEach { colorGroup ->
            if (colorGroup.size != 9) {
                return false
            }
        }

        //check individual pieces match those of an actual cube
        cornerPieceIndexList.map { indexList ->
            setOf(
                colorValues[indexList[0]],
                colorValues[indexList[1]],
                colorValues[indexList[2]],
            )}.forEach { cubeCornerPieceSet ->
            if (!cornerPieceColorList.contains(cubeCornerPieceSet)){
                return false
            }
        }

        return true
    }
}