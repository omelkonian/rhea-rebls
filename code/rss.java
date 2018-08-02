Publisher<Integer> pub = new Publisher {
	void subscribe(Subscriber<Integer> sub) {
		for (int i = 1; i <= 10; i++)
			sub.onNext(i);
		sub.onComplete();
	}
};

Subscriber<Integer> sub = new Subscriber {
	void onNext(Integer i) { println(i); }
	void onComplete() { println("Complete"); }
	void onError(Throwable t) { t.printStackTrace(); }
};

pub.subscribe(sub);
