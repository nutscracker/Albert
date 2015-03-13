package org.system.macros

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.language.postfixOps
import scala.reflect.macros.whitebox

/**
 * Created by evgeniikorniichuk on 12/03/15.
 */
class TestMacroAnnotation extends StaticAnnotation {

  def macroTransform(annottees: Any*) = macro TestMacroAnnotation.impl

}

object TestMacroAnnotation {
  def impl(c: whitebox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._

    def modifiedClass1(с: whitebox.Context, classDecl: ClassDef, compDeclOpt: Option[ModuleDef]) = {
      val q"$mods class $tpname[..$tparams] $ctorMods(...$paramss) extends { ..$earlydefns } with ..$parents { ..$stats }" = classDecl

      q"""$mods class $tpname[..$tparams] $ctorMods(...$paramss) extends { ..$earlydefns } with ..$parents {
            ..$stats
            def hello():String = "hello"
          }"""
    }

    val result = annottees map (_ tree) match {
      case (classDecl: ClassDef) :: Nil => modifiedClass1(c, classDecl, None)
      case (classDecl: ClassDef) :: (compDecl: ModuleDef) :: Nil => modifiedClass1(c, classDecl, Some(compDecl))
    }
    c.Expr[Any](result)
  }

}