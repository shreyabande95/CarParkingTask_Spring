import pyspark.sql.functions as F
from pyspark.sql.functions import col
data = spark.read.parquet("places.parquet")
operator_count = data.groupBy("devMake").agg(F.countDistinct("ifa"))
operator_count.toPandas()
sorted_operator_count=operator_count.sort(col('count(ifa)').desc())
print("Showing operator count in descending order:")
sorted_operator_count.show()

