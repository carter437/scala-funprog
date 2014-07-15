package recfun

import collection.immutable.Stack
import util.{Failure, Success, Try}

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def genRow(prevRow : List[Int]) : List[Int] = {
       prevRow match {
         case Nil => List(1)
         case head :: Nil => List(1,1)
         case _ => (1 :: prevRow.sliding(2,1).toList.map(it=>it.head + it.tail.head)) :+ 1
       }
    }

    def genTriangle(numOfRows : Int) : List[List[Int]] = {
        numOfRows match {
          case 0 => List(genRow(Nil))
          case _ => {
            val tri = genTriangle(numOfRows - 1)
            tri :+ genRow(tri.last)
          }
        }
    }
    genTriangle(r)(r)(c)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    type ParenHandler = PartialFunction[Char,Stack[Char] => Stack[Char]]
    val openParen : ParenHandler =  { case  '(' =>  { s => s.push('(')}}
    val closeParen: ParenHandler =  { case  ')' => { s => s.pop}}
    val other     : ParenHandler =  { case _ => {s => s}}

    val handler = (openParen orElse closeParen orElse other)

    def balanceHlpr(chars: List[Char], stack : Stack[Char] = Stack()) : Stack[Char] ={
        chars match {
          case head :: Nil => handler(head)(stack)
          case head :: tail => {
            balanceHlpr(tail,handler(head)(stack))
          }
          case Nil => stack
      }
    }

    Try(balanceHlpr(chars)) match {
      case Success(s) => s.isEmpty
      case Failure(e) => false
    }
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    money match {
      case 0 => 1
      case remaining if remaining < 0 => 0
      case _ => coins match {
        case Nil => 0
        case _ => countChange(money,coins.tail) + countChange(money-coins.head,coins)
      }
    }
  }
}
