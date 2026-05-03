error id: file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/AnalysisEngine.scala:[38,41) in virtfile:file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/AnalysisEngine.scala(... lass 
    def testFunc( ...)
file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/file:<HOME>/Documents/University/Year%25202/Semester%25202/Introduction%2520to%2520Functional%2520Programming/Project/nub-renewable-energy/src/main/scala/AnalysisEngine.scala
file://<HOME>/Documents/University/Year%202/Semester%202/Introduction%20to%20Functional%20Programming/Project/nub-renewable-energy/src/main/scala/AnalysisEngine.scala:3: error: expected identifier; obtained def


Current stack trace:
java.base/java.lang.Thread.getStackTrace(Thread.java:2220)
scala.meta.internal.mtags.ScalaToplevelMtags.failMessage(ScalaToplevelMtags.scala:1250)
scala.meta.internal.mtags.ScalaToplevelMtags.$anonfun$reportError$1(ScalaToplevelMtags.scala:1236)
scala.meta.internal.metals.StdReporter.$anonfun$create$1(ReportContext.scala:148)
scala.util.Try$.apply(Try.scala:217)
scala.meta.internal.metals.StdReporter.create(ReportContext.scala:143)
scala.meta.pc.reports.Reporter.create(Reporter.java:10)
scala.meta.internal.mtags.ScalaToplevelMtags.reportError(ScalaToplevelMtags.scala:1233)
scala.meta.internal.mtags.ScalaToplevelMtags.newIdentifier(ScalaToplevelMtags.scala:1107)
scala.meta.internal.mtags.ScalaToplevelMtags.emitMember(ScalaToplevelMtags.scala:788)
scala.meta.internal.mtags.ScalaToplevelMtags.loop(ScalaToplevelMtags.scala:263)
scala.meta.internal.mtags.ScalaToplevelMtags.indexRoot(ScalaToplevelMtags.scala:96)
scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:83)
scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:560)
scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:691)
scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:688)
scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:688)
scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:940)
scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
java.base/java.lang.Thread.run(Thread.java:1516)

    def testFunc(string: String): String = {
    ^
#### Short summary: 

expected identifier; obtained def