Spark.textFileStream("hdfs://...") /* Get file stream */
	.flatMap(_.split(" ")) /* Split into words */
	.map(x => (x, 1)).reduceByKey(_ + _) /* Count words */
