import org.apache.spark.sql.hive.orc._
import org.apache.spark.sql._

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

hiveContext.sql("show tables").collect.foreach(println)

val geolocation_temp1 = hiveContext.sql("select * from geolocation")

val drivermileage_temp1 = hiveContext.sql("select * from drivermileage")

geolocation_temp1.registerTempTable("geolocation_temp1")
drivermileage_temp1.registerTempTable("drivermileage_temp1")

val geolocation_temp2 = hiveContext.sql("SELECT driverid, count(driverid) occurance from geolocation_temp1  where event!='normal' group by driverid")

geolocation_temp2.registerTempTable("geolocation_temp2")

geolocation_temp2.collect.foreach(println)

val joined = hiveContext.sql("select a.driverid,a.occurance,b.totmiles from geolocation_temp2 a,drivermileage_temp1 b where a.driverid=b.driverid")

joined.registerTempTable("joined")

joined.collect.foreach(println)

val risk_factor_spark=hiveContext.sql("select driverid, occurance, totmiles, totmiles/occurance riskfactor from joined")

risk_factor_spark.registerTempTable("risk_factor_spark")

risk_factor_spark.collect.foreach(println)

hiveContext.sql("create table finalresults( driverid String, occurance bigint,totmiles bigint,riskfactor double) stored as orc").toDF()

risk_factor_spark.write.orc("risk_factor_spark")

hiveContext.sql("load data inpath 'risk_factor_spark' into table finalresults")

hiveContext.sql("select * from finalresults")