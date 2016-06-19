package markersJob.functions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.bson.BSONObject;

import markersJob.model.MarkerDelay;
import markersJob.model.MarkerInfo;
import scala.Tuple2;

public class ManagingRoutes implements PairFlatMapFunction<Tuple2<Object, BSONObject>, MarkerInfo, MarkerDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Iterable<Tuple2<MarkerInfo, MarkerDelay>> call(Tuple2<Object, BSONObject> arg) throws ParseException {
		List<Tuple2<MarkerInfo, MarkerDelay>> rtn = new ArrayList<Tuple2<MarkerInfo, MarkerDelay>>();
		
		String iataOrigin = (String) arg._2.get("OriginIata");
		String labelCityOrigin = (String) arg._2.get("OriginCity");
		String latitudeOrigin = (String) arg._2.get("OriginLatitude");
		String longitudeOrigin = (String) arg._2.get("OriginLongitude");
		String meanDepDelay = (String) arg._2.get("MeanDepDelay");
		Long meanDepDelayLong = parseTime(meanDepDelay);
		
		Integer delayDep0 = Integer.parseInt(arg._2.get("CountDelayDep0").toString());
		Integer delayDep15 = Integer.parseInt(arg._2.get("CountDelayDep15").toString());
		Integer delayDep60 = Integer.parseInt(arg._2.get("CountDelayDep60").toString());
		Integer delayDep3h = Integer.parseInt(arg._2.get("CountDelayDep3h").toString());
		Integer delayDep24h = Integer.parseInt(arg._2.get("CountDelayDep24h").toString());
		Integer delayDepOther = Integer.parseInt(arg._2.get("CountDelayDepOther").toString());
		
		MarkerInfo markerOrigin = new MarkerInfo(iataOrigin, labelCityOrigin, latitudeOrigin, longitudeOrigin);
		MarkerDelay markerOriginDelay = new MarkerDelay(meanDepDelayLong, 1, 0L, 0, delayDep0, delayDep15, delayDep60,
				delayDep3h, delayDep24h, delayDepOther, 0, 0, 0, 0, 0, 0);
		
		rtn.add(new Tuple2<MarkerInfo, MarkerDelay>(markerOrigin, markerOriginDelay));
		
		String iataDest = (String) arg._2.get("DestIata");
		String labelCityDest = (String) arg._2.get("DestCity");
		String latitudeDest = (String) arg._2.get("DestLatitude");
		String longitudeDest = (String) arg._2.get("DestLongitude");
		String meanArrDelay = (String) arg._2.get("MeanArrDelay");
		Long meanArrDelayLong = parseTime(meanArrDelay);
		
		Integer delayArr0 = Integer.parseInt(arg._2.get("CountDelayArr0").toString());
		Integer delayArr15 = Integer.parseInt(arg._2.get("CountDelayArr15").toString());
		Integer delayArr60 = Integer.parseInt(arg._2.get("CountDelayArr60").toString());
		Integer delayArr3h = Integer.parseInt(arg._2.get("CountDelayArr3h").toString());
		Integer delayArr24h = Integer.parseInt(arg._2.get("CountDelayArr24h").toString());
		Integer delayArrOther = Integer.parseInt(arg._2.get("CountDelayArrOther").toString());
		
		MarkerInfo markerDest = new MarkerInfo(iataDest, labelCityDest, latitudeDest, longitudeDest);
		MarkerDelay markerDestDelay = new MarkerDelay(0L, 0, meanArrDelayLong, 1, 0, 0, 0, 0, 0, 0, delayArr0, delayArr15, delayArr60,
				delayArr3h, delayArr24h, delayArrOther);
		
		rtn.add(new Tuple2<MarkerInfo, MarkerDelay>(markerDest, markerDestDelay));
		
		return rtn;
	}

	private Long parseTime(String meanDepDelay) throws ParseException {
		DateFormat df = new SimpleDateFormat("HH:mm");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date time = df.parse(meanDepDelay);
		
		return TimeUnit.MILLISECONDS.toHours(time.getTime())*60 + TimeUnit.MILLISECONDS.toMinutes(time.getTime());
	}
}
