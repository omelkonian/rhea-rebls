Stream.just(1, 2, 3)
          .map(x -> x + 1)
          .subscribe(System.out::println);
