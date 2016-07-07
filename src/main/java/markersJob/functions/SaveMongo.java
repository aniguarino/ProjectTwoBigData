package markersJob.functions;

import java.util.concurrent.TimeUnit;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import markersJob.model.MarkerDelay;
import markersJob.model.MarkerInfo;
import scala.Tuple2;

public class SaveMongo implements PairFunction<Tuple2<MarkerInfo, MarkerDelay>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<MarkerInfo, MarkerDelay> arg0) throws Exception {
		Long meanDelayDep = arg0._2.getMeanDelayDep()/arg0._2.getCountDep();
		Long meanDelayArr = arg0._2.getMeanDelayArr()/arg0._2.getCountArr();
		
		Double sumDep = arg0._2.getCarrierDelayDep()+arg0._2.getWeatherDelayDep()+arg0._2.getNASDelayDep()+arg0._2.getSecurityDelayDep()+arg0._2.getLateAircraftDelayDep();
		Double sumArr = arg0._2.getCarrierDelayArr()+arg0._2.getWeatherDelayArr()+arg0._2.getNASDelayArr()+arg0._2.getSecurityDelayArr()+arg0._2.getLateAircraftDelayArr();		
		
		Double carrierDelayPercentDep = arg0._2.getCarrierDelayDep()/sumDep*100;
		Double weatherDelayPercentDep = arg0._2.getWeatherDelayDep()/sumDep*100;
		Double NASDelayPercentDep = arg0._2.getNASDelayDep()/sumDep*100;
		Double securityDelayPercentDep = arg0._2.getSecurityDelayDep()/sumDep*100;
		Double lateAircraftDelayPercentDep = arg0._2.getLateAircraftDelayDep()/sumDep*100;
		
		Double carrierDelayPercentArr = arg0._2.getCarrierDelayArr()/sumArr*100;
		Double weatherDelayPercentArr = arg0._2.getWeatherDelayArr()/sumArr*100;
		Double NASDelayPercentArr = arg0._2.getNASDelayArr()/sumArr*100;
		Double securityDelayPercentArr = arg0._2.getSecurityDelayArr()/sumArr*100;
		Double lateAircraftDelayPercentArr = arg0._2.getLateAircraftDelayArr()/sumArr*100;
		
		BSONObject save = new BasicDBObject().
				append("Iata", arg0._1.getIata()).
				append("Latitude", arg0._1.getLatitude()).
				append("Longitude", arg0._1.getLongitude()).
				append("LabelCity", arg0._1.getLabel()).
				append("MeanDelayDep", longToStringMinutes(meanDelayDep * 60 * 1000)).
				append("MeanDelayArr", longToStringMinutes(meanDelayArr * 60 * 1000)).
				append("CountDelayDep0", arg0._2.getDelayDep0()).
				append("CountDelayDep15", arg0._2.getDelayDep15()).
				append("CountDelayDep60", arg0._2.getDelayDep60()).
				append("CountDelayDep3h", arg0._2.getDelayDep3h()).
				append("CountDelayDep24h", arg0._2.getDelayDep24h()).
				append("CountDelayDepOther", arg0._2.getDelayDepOther()).
				
				append("CarrierDelayDep", carrierDelayPercentDep).
				append("WeatherDelayDep", weatherDelayPercentDep).
				append("NASDelayDep", NASDelayPercentDep).
				append("SecurityDelayDep", securityDelayPercentDep).
				append("LateAircraftDelayDep", lateAircraftDelayPercentDep).
				
				append("CountDelayArr0", arg0._2.getDelayArr0()).
				append("CountDelayArr15", arg0._2.getDelayArr15()).
				append("CountDelayArr60", arg0._2.getDelayArr60()).
				append("CountDelayArr3h", arg0._2.getDelayArr3h()).
				append("CountDelayArr24h", arg0._2.getDelayArr24h()).
				append("CountDelayArrOther", arg0._2.getDelayArrOther()).
				
				append("CarrierDelayArr", carrierDelayPercentArr).
				append("WeatherDelayArr", weatherDelayPercentArr).
				append("NASDelayArr", NASDelayPercentArr).
				append("SecurityDelayArr", securityDelayPercentArr).
				append("LateAircraftDelayArr", lateAircraftDelayPercentArr);
		
		return new Tuple2<Object, BSONObject>(null, save);
	}
	
	private String longToStringMinutes(Long meanAirTime){
		String meanAirTimeHours = TimeUnit.MILLISECONDS.toHours(meanAirTime)+"";
		if(meanAirTimeHours.length() == 1)
			meanAirTimeHours = "0".concat(meanAirTimeHours);
		
		String meanAirTimeMin = TimeUnit.MILLISECONDS.toMinutes(meanAirTime)%60+"";
		if(meanAirTimeMin.length() == 1)
			meanAirTimeMin = "0".concat(meanAirTimeMin);
		
		return meanAirTimeHours+":"+meanAirTimeMin;
	}
}