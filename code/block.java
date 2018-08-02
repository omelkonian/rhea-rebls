Stream<Int> s1 = Stream.nat();
BlockingStream<Int> s2 = s1.toBlocking();
List<Int> list = s2.toList();
System.out.print(list);