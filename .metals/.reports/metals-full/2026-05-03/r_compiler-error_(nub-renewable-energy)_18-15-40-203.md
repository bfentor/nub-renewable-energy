file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
### scala.reflect.internal.FatalError: 
  ThisType(method getData) for sym which is not a class
     while compiling: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -classpath <WORKSPACE>/.bloop/nub-renewable-energy/bloop-bsp-clients-classes/classes-Metals-FmYaqjniSqO3ZnBjN__iIQ==:<HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.11.2/semanticdb-javac-0.11.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/2.3.0/scala-parser-combinators_2.13-2.3.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/requests_2.13/0.8.0/requests_2.13-0.8.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/github/cdimascio/dotenv-java/3.2.0/dotenv-java-3.2.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/geny_2.13/1.0.0/geny_2.13-1.0.0.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: TypeTree
       tree position: line 10 of file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
            tree tpe: <error>
              symbol: <none>
   symbol definition: <none> (a NoSymbol)
      symbol package: <none>
       symbol owners: 
           call site: <none> in <none>

== Source file context for tree position ==

     7   private val apiKey = env.get("FINGRID_PRIMARY")
     8 
     9   def getData(startTime: String, endTime: String, id: String): String = {
    10     val url = s"https://data.fingrid.fi/api/datasets/$id/data?startTime=$startTime&endTime=$_CURSOR_endTime&format=csv&locale=en&sortBy=startTime&sortOrder=desc"
    11     val headers = Map("x-api-key" -> apiKey)
    12 
    13 

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.12
Classpath:
<WORKSPACE>/.bloop/nub-renewable-energy/bloop-bsp-clients-classes/classes-Metals-FmYaqjniSqO3ZnBjN__iIQ== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.11.2/semanticdb-javac-0.11.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/2.3.0/scala-parser-combinators_2.13-2.3.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/requests_2.13/0.8.0/requests_2.13-0.8.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/github/cdimascio/dotenv-java/3.2.0/dotenv-java-3.2.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/geny_2.13/1.0.0/geny_2.13-1.0.0.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb


action parameters:
offset: 361
uri: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
text:
```scala
import requests._
import io.github.cdimascio.dotenv.Dotenv
import AnalysisEngine._

object Main extends App {
  private val env = Dotenv.load()
  private val apiKey = env.get("FINGRID_PRIMARY")

  def getData(startTime: String, endTime: String, id: String): String = {
    val url = s"https://data.fingrid.fi/api/datasets/$id/data?startTime=$startTime&endTime=$@@endTime&format=csv&locale=en&sortBy=startTime&sortOrder=desc"
    val headers = Map("x-api-key" -> apiKey)


    val response = requests.get(url, headers = headers)
    response.text()
  }

  // Wind data example. Date not yet implemented
  println(getData("2026-05-03", "246"))
  
  
}
```



#### Error stacktrace:

```
scala.reflect.internal.Reporting.abort(Reporting.scala:70)
	scala.reflect.internal.Reporting.abort$(Reporting.scala:66)
	scala.reflect.internal.SymbolTable.abort(SymbolTable.scala:28)
	scala.reflect.internal.Types$ThisType.<init>(Types.scala:1394)
	scala.reflect.internal.Types$UniqueThisType.<init>(Types.scala:1414)
	scala.reflect.internal.Types$ThisType$.apply(Types.scala:1418)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:75)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:60)
	scala.collection.immutable.List.collect(List.scala:267)
	scala.meta.internal.pc.AutoImportsProvider.autoImports(AutoImportsProvider.scala:60)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$autoImports$1(ScalaPresentationCompiler.scala:384)
```
#### Short summary: 

scala.reflect.internal.FatalError: 
  ThisType(method getData) for sym which is not a class
     while compiling: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -classpath <WORKSPACE>/.bloop/nub-renewable-energy/bloop-bsp-clients-classes/classes-Metals-FmYaqjniSqO3ZnBjN__iIQ==:<HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.11.2/semanticdb-javac-0.11.2.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/2.3.0/scala-parser-combinators_2.13-2.3.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/requests_2.13/0.8.0/requests_2.13-0.8.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/io/github/cdimascio/dotenv-java/3.2.0/dotenv-java-3.2.0.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/geny_2.13/1.0.0/geny_2.13-1.0.0.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: TypeTree
       tree position: line 10 of file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/Main.scala
            tree tpe: <error>
              symbol: <none>
   symbol definition: <none> (a NoSymbol)
      symbol package: <none>
       symbol owners: 
           call site: <none> in <none>

== Source file context for tree position ==

     7   private val apiKey = env.get("FINGRID_PRIMARY")
     8 
     9   def getData(startTime: String, endTime: String, id: String): String = {
    10     val url = s"https://data.fingrid.fi/api/datasets/$id/data?startTime=$startTime&endTime=$_CURSOR_endTime&format=csv&locale=en&sortBy=startTime&sortOrder=desc"
    11     val headers = Map("x-api-key" -> apiKey)
    12 
    13 