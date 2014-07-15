/**
 * Created with IntelliJ IDEA.
 * User: croughton
 * Date: 5/11/13
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */
 val occ = List(('a',2),('b',2))

val perms = for{
  o <- occ
  i <- (o._2 to 1 by -1)
} yield (o._1,i)

perms.groupBy(_._1)


