package meanDelay_StandardDeviationJob.functions;

import java.util.concurrent.TimeUnit;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import meanDelay_StandardDeviationJob.model.FlightId;
import meanDelay_StandardDeviationJob.model.FlightInfoDelay;
import scala.Tuple2;

public class SaveMongo implements PairFunction<Tuple2<FlightId, FlightInfoDelay>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<FlightId, FlightInfoDelay> arg0) throws Exception {
		Long meanDelay = 0L;
		
		if(arg0._2.getArrivalDelay().longValue() != 0L)
			meanDelay = (arg0._2.getArrivalDelay().longValue()/arg0._2.getCountFlight())*60*1000;
		
		String meanDelayHours = TimeUnit.MILLISECONDS.toHours(meanDelay)+"";
		if(meanDelayHours.length() == 1)
			meanDelayHours = "0".concat(meanDelayHours);
		
		String meanDelayMin = TimeUnit.MILLISECONDS.toMinutes(meanDelay)%60+"";
		if(meanDelayMin.length() == 1)
			meanDelayMin = "0".concat(meanDelayMin);
		
		Double standardDeviation = Math.sqrt(arg0._2.getIntermediateStandDeviation()/(arg0._2.getCountFlight()-1));
		
		BSONObject save = new BasicDBObject().
				append("UniqueCarrier", arg0._1.getUniqueCarrier()).
				append("Year", arg0._1.getYear()).
				append("Month", arg0._1.getMonth()).
				append("DayOfWeek", arg0._1.getDayOfWeek()).
				append("MeanDelay", meanDelayHours+":"+meanDelayMin).
				append("CountDenom", arg0._2.getCountFlight()).
				append("StandardDeviation", standardDeviation);
		
		return new Tuple2<Object, BSONObject>(null, save);
	}

}
