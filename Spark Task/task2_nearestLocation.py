from pyspark.context import SparkContext
from pyspark.sql.session import SparkSession
from pyspark.sql import functions as F
import requests
from pyspark.sql.types import StructType
from pyspark.sql.types import StructField
from pyspark.sql.types import StringType

def fetchData(df_partition):
    for row in range(df_partition.count()):
        lat=df_partition.select("_c1").collect()[row][0]
        longt=df_partition.select("_c2").collect()[row][0]
        temp=[]
        temp.append(lat)
        temp.append(longt)
        uri="http://places.zprk.io/v1/proxim/"+str(lat)+"/"+str(longt)+"/1000?withdist=true&sort=ASC"
        response_json=requests.get(uri).json()
        if(len(response_json['places'])!=0):
            temp.append(response_json['places'][0])
            poi=""
            poi=poi+str(temp[2])
            temp2=poi.split("|")
            temp2[0]=temp2[0][2:]
            poi_uri="http://places.zprk.io/v2/places/near_poi_id/"+temp2[0]+"?key=nearplacestestkey"
            poi_response_json=requests.get(poi_uri).json()
            if(len(poi_response_json['payload'])!=0):
                this_payload=(poi_response_json['payload'])
                this_payload_str=str(this_payload[0])
                this_payload_split=this_payload_str.split(",")
                temp.append(this_payload_split[1][12:])
                temp.append(this_payload_split[2][11:])
        
sc = SparkContext.getOrCreate();
spark = SparkSession(sc)
inFile="spark_local/places.parquet"
df = spark.read.parquet(inFile)
parts_to_files = df.repartition(10)
print(parts_to_files.rdd.getNumPartitions()) 
parts_to_files.write.format("csv").mode("overwrite").save("PartByIfa.csv")
schema = StructType([
  StructField('lat', StringType(), True),
  StructField('lng', StringType(), True),
  StructField('Co-ordinate', StringType(), True),
    StructField('Location', StringType(), True)
  ])
result=spark.createDataFrame([], schema)
for i in range(9):
    df_partition=spark.read.csv("PartByIfa.csv/part-0000"+str(i)+"-408a80b4-04f2-4508-8c99-6f3e478314a0-c000.csv")
    data=fetchData(df_partition)
    newRow = spark.createDataFrame(data, schema)
    final_result_df = result.union(newRow)
    result=final_result_df
result.show()