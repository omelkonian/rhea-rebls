val g = FlowGraph { implicit b =>
  import FlowGraphImplicits._
  val in = Source(1 to 10)
  val out = Sink.ignore 
  val bcast = Broadcast[Int]
  val merge = Merge[Int] 
  val f1, f2, f3, f4 = Flow[Int].map(_ + 10) 
  in ~> f1 ~> bcast ~> f2 ~> merge ~> f3 ~> out
                    bcast ~> f4 ~> merge
}