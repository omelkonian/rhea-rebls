Stream.configure(new HazelcastDistributionStrategy(
   	RxjavaEvaluationStrategy::new,
   	RosEvaluationStrategy::new,
   	MqttEvaluationStrategy::new));

Topic<RobotCommand> vel = new RosTopic<>("/robot/cmd");

Stream<Proximity> ble =
     Stream.from(new MqttTopic<>("/ble"));

ble.filter(Proximity::isNear)
     .map(d -> Commands.SPEED_UP)
     .subscribe(vel);

ble.filter(Proximity::isFar)
     .map(d -> Commands.SLOW_DOWN)
     .subscribe(vel);
