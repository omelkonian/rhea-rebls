Stream.configure(new HazelcastDistributionStrategy(
  RxjavaEvaluationStrategy::new,
  RosEvaluationStrategy::new));

Stream<Mat> image =
  Stream.from(new RosTopic<>("/camera"))
        .map(CvImage::toCvCopy);

Stream<Mat> initial = image.first().repeat();

Stream.zip(image, initial, Pair::new)
      .sample(100, TimeUnit.MILLISECONDS)
      .timeout(1, TimeUnit.MINUTES)
      .filter(Surveillance::motionDetected)
      .map(Pair::snd)
      .subscribe(window::showImage);

boolean motionDetected(Pair<Mat,Mat> pair) {
  Mat m1 = pair.getLeft(), m2 = pair.getRight(), m = new Mat();
  Core.absdiff(m1, m2, m);
  Imgproc.threshold(m, m, 80, 255, Imgproc.THRESH_BINARY);
  Imgproc.erode(m, m, Imgproc.MORPH_RECT(3,3));
  for (int i = 0; i < m.rows(); i++)
	for (int j = 0; j < m.cols(); j++) {
	  double[] pixel = m.get(i, j);
 	  double sum = pixel[0]  + pixel[1] + pixel[2];
	  if (sum > 50) return true;
  }
  return false;
}
