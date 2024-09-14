import cube.*
import cube.enums.Color
import cube.enums.Direction
import cube.service.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CubeTest {

    @Test
    fun `cube should solve all scrambles`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0
        var mostTurns = 0
        var leastTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            cube.scramble(counter)
            cube.solve()
            assert(cube.isSolved())
            println("Solved cube ${counter + 1} times.")

            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size
            if (cube.turnsMade.size >= mostTurns) mostTurns = cube.turnsMade.size
            if (cube.turnsMade.size <= leastTurns || leastTurns == 0){
                leastTurns = cube.turnsMade.size
            }
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for placing white center pieces before optimizing: $average")
        cube.optimizeTurnsMade()
        println("Average turns for  placing white center pieces after optimizing: $averageOptimized")

        println("Most effiecent solve used $leastTurns turns")
        println("Least efficient solve used $mostTurns turns")
    }

    @Test
    fun `turning cube should write colorValues correctly`(){
        val cube = Cube()

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.BLUE, Direction.CLOCKWISE)
        assertEquals(Color.WHITE,cube.colorValues[9])
        assertEquals(Color.WHITE,cube.colorValues[10])
        assertEquals(Color.WHITE,cube.colorValues[11])
        assertEquals(Color.RED,cube.colorValues[18])
        assertEquals(Color.RED,cube.colorValues[19])
        assertEquals(Color.RED,cube.colorValues[20])
        assertEquals(Color.YELLOW,cube.colorValues[27])
        assertEquals(Color.YELLOW,cube.colorValues[28])
        assertEquals(Color.YELLOW,cube.colorValues[29])
        assertEquals(Color.ORANGE,cube.colorValues[36])
        assertEquals(Color.ORANGE,cube.colorValues[37])
        assertEquals(Color.ORANGE,cube.colorValues[38])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.BLUE, Direction.COUNTER_CLOCKWISE)
        assertEquals(Color.YELLOW,cube.colorValues[9])
        assertEquals(Color.YELLOW,cube.colorValues[10])
        assertEquals(Color.YELLOW,cube.colorValues[11])
        assertEquals(Color.ORANGE,cube.colorValues[18])
        assertEquals(Color.ORANGE,cube.colorValues[19])
        assertEquals(Color.ORANGE,cube.colorValues[20])
        assertEquals(Color.WHITE,cube.colorValues[27])
        assertEquals(Color.WHITE,cube.colorValues[28])
        assertEquals(Color.WHITE,cube.colorValues[29])
        assertEquals(Color.RED,cube.colorValues[36])
        assertEquals(Color.RED,cube.colorValues[37])
        assertEquals(Color.RED,cube.colorValues[38])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.WHITE, Direction.CLOCKWISE)
        assertEquals(Color.ORANGE,cube.colorValues[6])
        assertEquals(Color.ORANGE,cube.colorValues[7])
        assertEquals(Color.ORANGE,cube.colorValues[8])
        assertEquals(Color.BLUE,cube.colorValues[27])
        assertEquals(Color.BLUE,cube.colorValues[30])
        assertEquals(Color.BLUE,cube.colorValues[33])
        assertEquals(Color.RED,cube.colorValues[47])
        assertEquals(Color.RED,cube.colorValues[46])
        assertEquals(Color.RED,cube.colorValues[45])
        assertEquals(Color.GREEN,cube.colorValues[17])
        assertEquals(Color.GREEN,cube.colorValues[14])
        assertEquals(Color.GREEN,cube.colorValues[11])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.WHITE, Direction.COUNTER_CLOCKWISE)
        assertEquals(Color.RED,cube.colorValues[6])
        assertEquals(Color.RED,cube.colorValues[7])
        assertEquals(Color.RED,cube.colorValues[8])
        assertEquals(Color.GREEN,cube.colorValues[27])
        assertEquals(Color.GREEN,cube.colorValues[30])
        assertEquals(Color.GREEN,cube.colorValues[33])
        assertEquals(Color.ORANGE,cube.colorValues[47])
        assertEquals(Color.ORANGE,cube.colorValues[46])
        assertEquals(Color.ORANGE,cube.colorValues[45])
        assertEquals(Color.BLUE,cube.colorValues[17])
        assertEquals(Color.BLUE,cube.colorValues[14])
        assertEquals(Color.BLUE,cube.colorValues[11])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.ORANGE, Direction.CLOCKWISE)
        assertEquals(Color.YELLOW,cube.colorValues[0])
        assertEquals(Color.YELLOW,cube.colorValues[3])
        assertEquals(Color.YELLOW,cube.colorValues[6])
        assertEquals(Color.BLUE,cube.colorValues[18])
        assertEquals(Color.BLUE,cube.colorValues[21])
        assertEquals(Color.BLUE,cube.colorValues[24])
        assertEquals(Color.WHITE,cube.colorValues[45])
        assertEquals(Color.WHITE,cube.colorValues[48])
        assertEquals(Color.WHITE,cube.colorValues[51])
        assertEquals(Color.GREEN,cube.colorValues[38])
        assertEquals(Color.GREEN,cube.colorValues[41])
        assertEquals(Color.GREEN,cube.colorValues[44])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.ORANGE, Direction.COUNTER_CLOCKWISE)
        assertEquals(Color.WHITE,cube.colorValues[0])
        assertEquals(Color.WHITE,cube.colorValues[3])
        assertEquals(Color.WHITE,cube.colorValues[6])
        assertEquals(Color.GREEN,cube.colorValues[18])
        assertEquals(Color.GREEN,cube.colorValues[21])
        assertEquals(Color.GREEN,cube.colorValues[24])
        assertEquals(Color.YELLOW,cube.colorValues[45])
        assertEquals(Color.YELLOW,cube.colorValues[48])
        assertEquals(Color.YELLOW,cube.colorValues[51])
        assertEquals(Color.BLUE,cube.colorValues[38])
        assertEquals(Color.BLUE,cube.colorValues[41])
        assertEquals(Color.BLUE,cube.colorValues[44])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.GREEN, Direction.CLOCKWISE)
        assertEquals(Color.YELLOW,cube.colorValues[15])
        assertEquals(Color.YELLOW,cube.colorValues[16])
        assertEquals(Color.YELLOW,cube.colorValues[17])
        assertEquals(Color.ORANGE,cube.colorValues[24])
        assertEquals(Color.ORANGE,cube.colorValues[25])
        assertEquals(Color.ORANGE,cube.colorValues[26])
        assertEquals(Color.WHITE,cube.colorValues[33])
        assertEquals(Color.WHITE,cube.colorValues[34])
        assertEquals(Color.WHITE,cube.colorValues[35])
        assertEquals(Color.RED,cube.colorValues[42])
        assertEquals(Color.RED,cube.colorValues[43])
        assertEquals(Color.RED,cube.colorValues[44])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.GREEN, Direction.COUNTER_CLOCKWISE)
        assertEquals(Color.WHITE,cube.colorValues[15])
        assertEquals(Color.WHITE,cube.colorValues[16])
        assertEquals(Color.WHITE,cube.colorValues[17])
        assertEquals(Color.RED,cube.colorValues[24])
        assertEquals(Color.RED,cube.colorValues[25])
        assertEquals(Color.RED,cube.colorValues[26])
        assertEquals(Color.YELLOW,cube.colorValues[33])
        assertEquals(Color.YELLOW,cube.colorValues[34])
        assertEquals(Color.YELLOW,cube.colorValues[35])
        assertEquals(Color.ORANGE,cube.colorValues[42])
        assertEquals(Color.ORANGE,cube.colorValues[43])
        assertEquals(Color.ORANGE,cube.colorValues[44])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.RED, Direction.CLOCKWISE)
        assertEquals(Color.WHITE,cube.colorValues[2])
        assertEquals(Color.WHITE,cube.colorValues[5])
        assertEquals(Color.WHITE,cube.colorValues[8])
        assertEquals(Color.GREEN,cube.colorValues[20])
        assertEquals(Color.GREEN,cube.colorValues[23])
        assertEquals(Color.GREEN,cube.colorValues[26])
        assertEquals(Color.YELLOW,cube.colorValues[47])
        assertEquals(Color.YELLOW,cube.colorValues[50])
        assertEquals(Color.YELLOW,cube.colorValues[53])
        assertEquals(Color.BLUE,cube.colorValues[36])
        assertEquals(Color.BLUE,cube.colorValues[39])
        assertEquals(Color.BLUE,cube.colorValues[42])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.RED, Direction.COUNTER_CLOCKWISE)
        assertEquals(Color.YELLOW,cube.colorValues[2])
        assertEquals(Color.YELLOW,cube.colorValues[5])
        assertEquals(Color.YELLOW,cube.colorValues[8])
        assertEquals(Color.BLUE,cube.colorValues[20])
        assertEquals(Color.BLUE,cube.colorValues[23])
        assertEquals(Color.BLUE,cube.colorValues[26])
        assertEquals(Color.WHITE,cube.colorValues[47])
        assertEquals(Color.WHITE,cube.colorValues[50])
        assertEquals(Color.WHITE,cube.colorValues[53])
        assertEquals(Color.GREEN,cube.colorValues[36])
        assertEquals(Color.GREEN,cube.colorValues[39])
        assertEquals(Color.GREEN,cube.colorValues[42])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.YELLOW, Direction.CLOCKWISE)
        assertEquals(Color.RED,cube.colorValues[0])
        assertEquals(Color.RED,cube.colorValues[1])
        assertEquals(Color.RED,cube.colorValues[2])
        assertEquals(Color.GREEN,cube.colorValues[29])
        assertEquals(Color.GREEN,cube.colorValues[32])
        assertEquals(Color.GREEN,cube.colorValues[35])
        assertEquals(Color.ORANGE,cube.colorValues[51])
        assertEquals(Color.ORANGE,cube.colorValues[52])
        assertEquals(Color.ORANGE,cube.colorValues[53])
        assertEquals(Color.BLUE,cube.colorValues[9])
        assertEquals(Color.BLUE,cube.colorValues[12])
        assertEquals(Color.BLUE,cube.colorValues[15])

        cube.resetCube()
        assertTrue(cube.isSolved())
        cube.turn(Color.YELLOW, Direction.COUNTER_CLOCKWISE)
        assertEquals(Color.ORANGE,cube.colorValues[0])
        assertEquals(Color.ORANGE,cube.colorValues[1])
        assertEquals(Color.ORANGE,cube.colorValues[2])
        assertEquals(Color.BLUE,cube.colorValues[29])
        assertEquals(Color.BLUE,cube.colorValues[32])
        assertEquals(Color.BLUE,cube.colorValues[35])
        assertEquals(Color.RED,cube.colorValues[51])
        assertEquals(Color.RED,cube.colorValues[52])
        assertEquals(Color.RED,cube.colorValues[53])
        assertEquals(Color.GREEN,cube.colorValues[9])
        assertEquals(Color.GREEN,cube.colorValues[12])
        assertEquals(Color.GREEN,cube.colorValues[15])

        repeat(100){ counter ->
            cube.resetCube()
            cube.scramble(counter)
            assertTrue(cube.isSolvable())
        }
    }

    @Test
    fun `result of optimizing turns should solve cube`(){

    }

    @Test
    fun `should solve place white center pieces on white face`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()

            assertTrue(cube.isSolvable())
            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Placed white center-pieces already on white face ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for placing white center pieces before optimizing: $average")
        cube.optimizeTurnsMade()
        println("Average turns for  placing white center pieces after optimizing: $averageOptimized")
    }

    @Test
    fun `should fill white cross`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.turnsMade.clear()
            cube.fillWhiteCross()

            assertEquals(Color.WHITE,cube.colorValues[19])
            assertEquals(Color.WHITE,cube.colorValues[21])
            assertEquals(Color.WHITE,cube.colorValues[22])
            assertEquals(Color.WHITE,cube.colorValues[23])
            assertEquals(Color.WHITE,cube.colorValues[25])

            assertTrue(cube.isSolvable())
            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Solved filled white- ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for filling white cross before optimizing: $average")
        println("Average turns for filling white cross after optimizing: $averageOptimized")
    }

    @Test
    fun `should orient white cross`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.fillWhiteCross()
            cube.turnsMade.clear()
            cube.orientWhiteCenterPieces()

            assertEquals(Color.WHITE,cube.colorValues[19])
            assertEquals(Color.WHITE,cube.colorValues[21])
            assertEquals(Color.WHITE,cube.colorValues[22])
            assertEquals(Color.WHITE,cube.colorValues[23])
            assertEquals(Color.WHITE,cube.colorValues[25])

            assertEquals(Color.BLUE,cube.colorValues[7])
            assertEquals(Color.RED,cube.colorValues[30])
            assertEquals(Color.GREEN,cube.colorValues[46])
            assertEquals(Color.ORANGE,cube.colorValues[14])

            assertTrue(cube.isSolvable())
            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Oriented white cross ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for orienting white cross before optimizing: $average")
        println("Average turns for orienting white cross after optimizing: $averageOptimized")
    }

    @Test
    fun `should solve white-corners`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.fillWhiteCross()
            cube.orientWhiteCenterPieces()
            cube.turnsMade.clear()
            cube.placeWhiteCorners()

            assertEquals(Color.WHITE,cube.colorValues[19])
            assertEquals(Color.WHITE,cube.colorValues[21])
            assertEquals(Color.WHITE,cube.colorValues[22])
            assertEquals(Color.WHITE,cube.colorValues[23])
            assertEquals(Color.WHITE,cube.colorValues[25])
            assertEquals(Color.WHITE,cube.colorValues[18])
            assertEquals(Color.WHITE,cube.colorValues[20])
            assertEquals(Color.WHITE,cube.colorValues[26])
            assertEquals(Color.WHITE,cube.colorValues[24])
            assertEquals(Color.BLUE,cube.colorValues[7])
            assertEquals(Color.BLUE,cube.colorValues[8])
            assertEquals(Color.BLUE,cube.colorValues[6])
            assertEquals(Color.RED,cube.colorValues[30])
            assertEquals(Color.RED,cube.colorValues[33])
            assertEquals(Color.RED,cube.colorValues[27])
            assertEquals(Color.GREEN,cube.colorValues[46])
            assertEquals(Color.GREEN,cube.colorValues[45])
            assertEquals(Color.GREEN,cube.colorValues[47])
            assertEquals(Color.ORANGE,cube.colorValues[14])
            assertEquals(Color.ORANGE,cube.colorValues[11])
            assertEquals(Color.ORANGE,cube.colorValues[17])

            assertTrue(cube.isSolvable())
            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Solved white-corners ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for solving white corners before optimizing: $average")
        println("Average turns for solving white corners after optimizing: $averageOptimized")
    }

    @Test
    fun `should solve middle layer`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.fillWhiteCross()
            cube.orientWhiteCenterPieces()
            cube.placeWhiteCorners()
            cube.turnsMade.clear()
            cube.placeMiddleLayerPieces()

            assertEquals(Color.WHITE,cube.colorValues[19])
            assertEquals(Color.WHITE,cube.colorValues[21])
            assertEquals(Color.WHITE,cube.colorValues[22])
            assertEquals(Color.WHITE,cube.colorValues[23])
            assertEquals(Color.WHITE,cube.colorValues[25])
            assertEquals(Color.WHITE,cube.colorValues[18])
            assertEquals(Color.WHITE,cube.colorValues[20])
            assertEquals(Color.WHITE,cube.colorValues[26])
            assertEquals(Color.WHITE,cube.colorValues[24])
            assertEquals(Color.BLUE,cube.colorValues[7])
            assertEquals(Color.BLUE,cube.colorValues[8])
            assertEquals(Color.BLUE,cube.colorValues[6])
            assertEquals(Color.RED,cube.colorValues[30])
            assertEquals(Color.RED,cube.colorValues[33])
            assertEquals(Color.RED,cube.colorValues[27])
            assertEquals(Color.GREEN,cube.colorValues[46])
            assertEquals(Color.GREEN,cube.colorValues[45])
            assertEquals(Color.GREEN,cube.colorValues[47])
            assertEquals(Color.ORANGE,cube.colorValues[14])
            assertEquals(Color.ORANGE,cube.colorValues[11])
            assertEquals(Color.ORANGE,cube.colorValues[17])

            assertEquals(Color.BLUE,cube.colorValues[3])
            assertEquals(Color.BLUE,cube.colorValues[4])
            assertEquals(Color.BLUE,cube.colorValues[5])
            assertEquals(Color.RED,cube.colorValues[28])
            assertEquals(Color.RED,cube.colorValues[31])
            assertEquals(Color.RED,cube.colorValues[34])
            assertEquals(Color.GREEN,cube.colorValues[48])
            assertEquals(Color.GREEN,cube.colorValues[49])
            assertEquals(Color.GREEN,cube.colorValues[50])
            assertEquals(Color.ORANGE,cube.colorValues[10])
            assertEquals(Color.ORANGE,cube.colorValues[13])
            assertEquals(Color.ORANGE,cube.colorValues[16])

            assertTrue(cube.isSolvable())
            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Solved white-corners ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for solving white corners before optimizing: $average")
        println("Average turns for solving white corners after optimizing: $averageOptimized")
    }

    @Test
    fun `should place yellow cross`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.fillWhiteCross()
            cube.orientWhiteCenterPieces()
            cube.placeWhiteCorners()
            cube.placeMiddleLayerPieces()
            cube.turnsMade.clear()
            cube.makeYellowCross()

            assertEquals(Color.WHITE,cube.colorValues[19])
            assertEquals(Color.WHITE,cube.colorValues[21])
            assertEquals(Color.WHITE,cube.colorValues[22])
            assertEquals(Color.WHITE,cube.colorValues[23])
            assertEquals(Color.WHITE,cube.colorValues[25])
            assertEquals(Color.WHITE,cube.colorValues[18])
            assertEquals(Color.WHITE,cube.colorValues[20])
            assertEquals(Color.WHITE,cube.colorValues[26])
            assertEquals(Color.WHITE,cube.colorValues[24])
            assertEquals(Color.BLUE,cube.colorValues[7])
            assertEquals(Color.BLUE,cube.colorValues[8])
            assertEquals(Color.BLUE,cube.colorValues[6])
            assertEquals(Color.RED,cube.colorValues[30])
            assertEquals(Color.RED,cube.colorValues[33])
            assertEquals(Color.RED,cube.colorValues[27])
            assertEquals(Color.GREEN,cube.colorValues[46])
            assertEquals(Color.GREEN,cube.colorValues[45])
            assertEquals(Color.GREEN,cube.colorValues[47])
            assertEquals(Color.ORANGE,cube.colorValues[14])
            assertEquals(Color.ORANGE,cube.colorValues[11])
            assertEquals(Color.ORANGE,cube.colorValues[17])
            assertEquals(Color.BLUE,cube.colorValues[3])
            assertEquals(Color.BLUE,cube.colorValues[4])
            assertEquals(Color.BLUE,cube.colorValues[5])
            assertEquals(Color.RED,cube.colorValues[28])
            assertEquals(Color.RED,cube.colorValues[31])
            assertEquals(Color.RED,cube.colorValues[34])
            assertEquals(Color.GREEN,cube.colorValues[48])
            assertEquals(Color.GREEN,cube.colorValues[49])
            assertEquals(Color.GREEN,cube.colorValues[50])
            assertEquals(Color.ORANGE,cube.colorValues[10])
            assertEquals(Color.ORANGE,cube.colorValues[13])
            assertEquals(Color.ORANGE,cube.colorValues[16])
            assertEquals(Color.YELLOW,cube.colorValues[37])
            assertEquals(Color.YELLOW,cube.colorValues[39])
            assertEquals(Color.YELLOW,cube.colorValues[41])
            assertEquals(Color.YELLOW,cube.colorValues[43])

            assertTrue(cube.isSolvable())
            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Placed yellow cross ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for placing yellow cross before optimizing: $average")
        println("Average turns for placing yellow cross after optimizing: $averageOptimized")
    }

    @Test
    fun `should orient yellow cross`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.fillWhiteCross()
            cube.orientWhiteCenterPieces()
            cube.placeWhiteCorners()
            cube.placeMiddleLayerPieces()
            cube.makeYellowCross()
            cube.turnsMade.clear()
            cube.orientYellowCross()

            assertEquals(Color.WHITE,cube.colorValues[19])
            assertEquals(Color.WHITE,cube.colorValues[21])
            assertEquals(Color.WHITE,cube.colorValues[22])
            assertEquals(Color.WHITE,cube.colorValues[23])
            assertEquals(Color.WHITE,cube.colorValues[25])
            assertEquals(Color.WHITE,cube.colorValues[18])
            assertEquals(Color.WHITE,cube.colorValues[20])
            assertEquals(Color.WHITE,cube.colorValues[26])
            assertEquals(Color.WHITE,cube.colorValues[24])
            assertEquals(Color.BLUE,cube.colorValues[7])
            assertEquals(Color.BLUE,cube.colorValues[8])
            assertEquals(Color.BLUE,cube.colorValues[6])
            assertEquals(Color.RED,cube.colorValues[30])
            assertEquals(Color.RED,cube.colorValues[33])
            assertEquals(Color.RED,cube.colorValues[27])
            assertEquals(Color.GREEN,cube.colorValues[46])
            assertEquals(Color.GREEN,cube.colorValues[45])
            assertEquals(Color.GREEN,cube.colorValues[47])
            assertEquals(Color.ORANGE,cube.colorValues[14])
            assertEquals(Color.ORANGE,cube.colorValues[11])
            assertEquals(Color.ORANGE,cube.colorValues[17])
            assertEquals(Color.BLUE,cube.colorValues[3])
            assertEquals(Color.BLUE,cube.colorValues[4])
            assertEquals(Color.BLUE,cube.colorValues[5])
            assertEquals(Color.RED,cube.colorValues[28])
            assertEquals(Color.RED,cube.colorValues[31])
            assertEquals(Color.RED,cube.colorValues[34])
            assertEquals(Color.GREEN,cube.colorValues[48])
            assertEquals(Color.GREEN,cube.colorValues[49])
            assertEquals(Color.GREEN,cube.colorValues[50])
            assertEquals(Color.ORANGE,cube.colorValues[10])
            assertEquals(Color.ORANGE,cube.colorValues[13])
            assertEquals(Color.ORANGE,cube.colorValues[16])
            assertEquals(Color.YELLOW,cube.colorValues[37])
            assertEquals(Color.YELLOW,cube.colorValues[39])
            assertEquals(Color.YELLOW,cube.colorValues[41])
            assertEquals(Color.YELLOW,cube.colorValues[43])
            assertEquals(Color.BLUE,cube.colorValues[1])
            assertEquals(Color.RED,cube.colorValues[32])
            assertEquals(Color.GREEN,cube.colorValues[52])
            assertEquals(Color.ORANGE,cube.colorValues[12])

            assertTrue(cube.isSolvable())
            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Oriented yellow cross ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for orienting yellow cross before optimizing: $average")
        println("Average turns for orienting yellow cross after optimizing: $averageOptimized")
    }

    @Test
    fun `should position yellow corners`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.fillWhiteCross()
            cube.orientWhiteCenterPieces()
            cube.placeWhiteCorners()
            cube.placeMiddleLayerPieces()
            cube.makeYellowCross()
            cube.orientYellowCross()
            cube.turnsMade.clear()
            cube.positionYellowCorners()

            assertEquals(Color.BLUE,cube.colorValues[1])
            assertEquals(Color.BLUE,cube.colorValues[3])
            assertEquals(Color.BLUE,cube.colorValues[4])
            assertEquals(Color.BLUE,cube.colorValues[5])
            assertEquals(Color.BLUE,cube.colorValues[6])
            assertEquals(Color.BLUE,cube.colorValues[7])
            assertEquals(Color.BLUE,cube.colorValues[8])

            assertEquals(Color.ORANGE,cube.colorValues[10])
            assertEquals(Color.ORANGE,cube.colorValues[11])
            assertEquals(Color.ORANGE,cube.colorValues[12])
            assertEquals(Color.ORANGE,cube.colorValues[13])
            assertEquals(Color.ORANGE,cube.colorValues[14])
            assertEquals(Color.ORANGE,cube.colorValues[16])
            assertEquals(Color.ORANGE,cube.colorValues[17])

            assertEquals(Color.WHITE,cube.colorValues[18])
            assertEquals(Color.WHITE,cube.colorValues[19])
            assertEquals(Color.WHITE,cube.colorValues[20])
            assertEquals(Color.WHITE,cube.colorValues[21])
            assertEquals(Color.WHITE,cube.colorValues[22])
            assertEquals(Color.WHITE,cube.colorValues[23])
            assertEquals(Color.WHITE,cube.colorValues[24])
            assertEquals(Color.WHITE,cube.colorValues[25])
            assertEquals(Color.WHITE,cube.colorValues[26])

            assertEquals(Color.GREEN,cube.colorValues[45])
            assertEquals(Color.GREEN,cube.colorValues[46])
            assertEquals(Color.GREEN,cube.colorValues[47])
            assertEquals(Color.GREEN,cube.colorValues[48])
            assertEquals(Color.GREEN,cube.colorValues[49])
            assertEquals(Color.GREEN,cube.colorValues[50])
            assertEquals(Color.GREEN,cube.colorValues[52])

            assertEquals(Color.RED,cube.colorValues[27])
            assertEquals(Color.RED,cube.colorValues[28])
            assertEquals(Color.RED,cube.colorValues[30])
            assertEquals(Color.RED,cube.colorValues[31])
            assertEquals(Color.RED,cube.colorValues[32])
            assertEquals(Color.RED,cube.colorValues[33])
            assertEquals(Color.RED,cube.colorValues[34])

            assertEquals(Color.YELLOW,cube.colorValues[37])
            assertEquals(Color.YELLOW,cube.colorValues[39])
            assertEquals(Color.YELLOW,cube.colorValues[40])
            assertEquals(Color.YELLOW,cube.colorValues[41])
            assertEquals(Color.YELLOW,cube.colorValues[43])

            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Positioned yellow corners ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for positioning yellow corners before optimizing: $average")
        println("Average turns for positioning yellow corners after optimizing: $averageOptimized")
    }

    @Test
    fun `should orient yellow corners`(){
        val cube = Cube()

        var turns = 0
        var optimizedTurns = 0

        repeat(100) { counter ->
            cube.resetCube()
            assertTrue(cube.isSolved())
            cube.scramble(counter)
            cube.placeWhiteCenterPiecesOnWhiteFace()
            cube.fillWhiteCross()
            cube.orientWhiteCenterPieces()
            cube.placeWhiteCorners()
            cube.placeMiddleLayerPieces()
            cube.makeYellowCross()
            cube.orientYellowCross()
            cube.positionYellowCorners()
            cube.turnsMade.clear()
            cube.orientYellowCorners()

            assertTrue(cube.isSolved())

            turns += cube.turnsMade.size
            cube.optimizeTurnsMade()
            optimizedTurns += cube.turnsMade.size

            println("Oriented yellow corners ${counter + 1} times!")
        }

        val average = turns/100
        val averageOptimized = optimizedTurns/100
        println("Average turns for orienting yellow corners before optimizing: $average")
        println("Average turns for placing yellow corners after optimizing: $averageOptimized")
    }
}
