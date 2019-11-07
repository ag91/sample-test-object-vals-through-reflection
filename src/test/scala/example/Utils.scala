package example

import scala.reflect._
import scala.reflect.runtime.universe._

object Utils {

  def containsAllValsInObject[O:ClassTag, T:ClassTag](all: List[O], obj: T): Boolean = {
    val ru = scala.reflect.runtime.universe
    val rootMirror = ru.runtimeMirror(getClass.getClassLoader)
    val classMirror = rootMirror.reflect(obj)
    val membersOfA = classMirror.symbol.typeSignature.members
    val accessors = membersOfA.collect{case x if x.isStatic && !x.isMethod => x}
    val companionSymbol = classMirror.symbol.companion
    val fieldSymbols =
      accessors.map(a => classMirror.symbol.typeSignature.member(ru.TermName(a.name.toString)))
    val fields = fieldSymbols.map(fs => classMirror.reflectField(fs.asTerm).get)
    val allValsInObject: List[O] = fields.foldLeft(List():List[O]){case (acc:List[O], e:O) => acc ++ List(e.asInstanceOf[O]); case (acc, _) => acc}
    all.toSet.subsetOf(allValsInObject.toSet) && allValsInObject.toSet.subsetOf(all.toSet)
  }
}
