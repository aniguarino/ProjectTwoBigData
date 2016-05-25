package routesJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import routesJob.model.AirportInfo;
import scala.Tuple2;

public class ManagingAirportFunction implements PairFunction<Tuple2<Object, BSONObject>, String, AirportInfo> {

	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<String, AirportInfo> call(Tuple2<Object, BSONObject> arg) {
		Object iataCode = arg._2.get("iata_code");
		String iataCodeString = null;
		if (iataCode instanceof String) {
			iataCodeString = (String) iataCode;
		}
		
		String country = (String) arg._2.get("iso_country");
		String name = (String) arg._2.get("name");
		
		String latitude = arg._2.get("latitude_deg").toString();
		String longitude = arg._2.get("longitude_deg").toString();

		return new Tuple2<String, AirportInfo>(iataCodeString, new AirportInfo(name, iataCodeString, country, latitude, longitude));
	}
}
