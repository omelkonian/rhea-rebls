Stream.just(1)
  .loop(entry => (entry.multiply(2) mergeSort entry.multiply(3))
                         mergeSort entry.multiply(5))
  .distinct.print

class IntStream(stream: Stream[Int]) {
  def multiply(constant: Int): Stream[Int] = stream.map(i => i * constant)
  def mergeSort(other: Stream[Int]): Stream[Int] = ...
}
implicit def enrichStream(st: Stream[Int]): IntStream = new IntStream(st)

