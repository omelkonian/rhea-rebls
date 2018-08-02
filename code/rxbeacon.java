Stream.configure(new HazelcastDistributionStrategy(
   	RxjavaEvaluationStrategy::new,
   	MqttEvaluationStrategy::new));

Stream.from(ReactiveBeacons.observe())
          .map(Beacon::getProximity)
          .subscribe(new MqttTopic<>("/ble"));
