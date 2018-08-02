Stream.just(1)
  .loop((entry: Stream[Int]) =>
    (entry.multiply(2) mergeSort entry.multiply(3))
      mergeSort
    entry.multiply(5) : Stream[Int])
  .distinct
  .print

class IntStream(stream: Stream[Int]) {
  def multiply(constant: Int): Stream[Int] =
    stream.map(i => i * constant)

  def mergeSort(other: Stream[Int]): Stream[Int] = {
    val queue = new PriorityQueue[Int]()
    Stream.zip(stream, other, (x, y) => {
      val min: Int = Math.min(x, y)
      val max: Int = Math.max(x, y)
      queue.add(max)
      if (min < queue.peek())
        min
      else {
        queue.add(min)
        queue.poll() }})
    .concatWith(Stream.from(queue))
  }
}
implicit def enrichStream(st: Stream[Int]): IntStream =
  new IntStream(st)

