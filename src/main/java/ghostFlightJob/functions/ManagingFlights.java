package ghostFlightJob.functions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import ghostFlightJob.model.AirplaneId;
import ghostFlightJob.model.FlightInfo;
import scala.Tuple2;

public class ManagingFlights implements PairFunction<Tuple2<Object, BSONObject>, AirplaneId, FlightInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<AirplaneId, FlightInfo> call(Tuple2<Object, BSONObject> arg) throws ParseException {
		String tailNumber = arg._2.get("TailNum").toString();
		String uniqueCarrier = arg._2.get("UniqueCarrier").toString();
		String flightYear = arg._2.get("Year").toString();
		String flightMonth = arg._2.get("Month").toString();
		
		String date = arg._2.get("FlightDate").toString();
		
		String depTime = arg._2.get("DepTime").toString();
		while(depTime.length()<4){
			depTime = "0".concat(depTime);
		}
		
		String origin = arg._2.get("Origin").toString();
		String dest = "";
		
		if((Double)arg._2.get("Diverted") == 0.0 || (Double)arg._2.get("DivReachedDest") == 0.0)
			dest = arg._2.get("Dest").toString();
		else
			dest = arg._2.get("Div1Airport").toString();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd:HHmm");
		Date dateWithTime = df.parse(date+":"+depTime);

		return new Tuple2<AirplaneId, FlightInfo>(new AirplaneId(tailNumber, uniqueCarrier, flightYear, flightMonth), new FlightInfo(dateWithTime, origin, dest));
	}
}