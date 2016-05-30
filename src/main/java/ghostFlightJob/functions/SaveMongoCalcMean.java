package ghostFlightJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import ghostFlightJob.model.CompanyId;
import ghostFlightJob.model.Counters;
import scala.Tuple2;

public class SaveMongoCalcMean implements PairFunction<Tuple2<CompanyId, Counters>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<CompanyId, Counters> arg0) throws Exception {
		String ghostFlightPercent = ((double)arg0._2.getCounterGhost()/(double)arg0._2.getCounterAll())*100+"%";
		
		BSONObject save = new BasicDBObject().
				append("UniqueCarrier", arg0._1.getUniqueCarrier()).
				append("Year", arg0._1.getYear()).
				append("Month", arg0._1.getMonth()).
				append("CountGhostFlight", arg0._2.getCounterGhost()).
				append("CountAllFlight", arg0._2.getCounterAll()).
				append("GhostFlightPercent", ghostFlightPercent);
		
		return new Tuple2<Object, BSONObject>(null, save);
	}

}
