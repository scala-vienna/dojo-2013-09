package dojo

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DeepThoughtSuite extends FunSuite {

  test("no moves means the game is still running") {
    val moves = Nil
    val gameIsFinished = isGameFinished(moves)
    assert(gameIsFinished === false)
  }

  test("game still running after one move") {
    val moves = Seq((0,0)-> "X")
    val gameIsFinished = isGameFinished(moves)
    assert(gameIsFinished === false)
  }

  test("game is finished after 3 in a row") {
    val moves = Seq((0,0)-> "X", (1,0)-> "X", (2,0)-> "X")
    val gameIsFinished = isGameFinished(moves)
    assert(gameIsFinished === true)
  }

  test("game is not finished after 2 Os  and an X in a row") {
    val moves = Seq((0,0)-> "O", (1,0)-> "O", (2,0)-> "X")
    val gameIsFinished = isGameFinished(moves)
    assert(gameIsFinished === false)
  }

  test("game is not finished when not all Os are in the same row") {
    val moves = Seq((1,1)-> "O", (1,2)-> "O", (2,1)-> "O")
    val gameIsFinished = isGameFinished(moves)
    assert(gameIsFinished === false)
  }

  def isGameFinished(moves: Seq[((Int, Int), String)]): Boolean = {
    val os = moves.filter(m => m._2 == "O")
    val xs = moves.filter(m => m._2 == "X")
    val allXsInOneRow = xs.groupBy(x => x._1._2).size == 1
    val allOsInOneRow = os.groupBy(x => x._1._1).size == 1
    val pieces = moves.map(m => m._2)
    val onlyOneKindOfPiece = pieces.toSet.size == 1
    allOsInOneRow || allXsInOneRow
  }

  test("after nine moves the game is over") {
    val moves = Seq((0,0)-> "X", (0,0)-> "X", (0,0)-> "X", (0,0)-> "X", (0,0)-> "X", (0,0)-> "X", (0,0)-> "X", (0,0)-> "X", (0,0)-> "X")
    val gameIsFinished = isGameFinished(moves)
    assert(gameIsFinished === true)
  }



}
