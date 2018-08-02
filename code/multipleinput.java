Stream<Int> s = Stream.range(1, 10);
Stream.zip(s, s, (x, y) -> x + y);
