package ghostFlightJob.functions;

import java.util.Collections;
import java.util.List;

import org.apache.spark.api.java.function.PairFunction;

import com.google.common.collect.Lists;

import ghostFlightJob.model.AirplaneId;
import ghostFlightJob.model.Counters;
import ghostFlightJob.model.FlightInfo;
import scala.Tuple2;

public class CountGhostFlight implements PairFunction<Tuple2<AirplaneId, Iterable<FlightInfo>>, AirplaneId, Counters> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<AirplaneId, Counters> call(Tuple2<AirplaneId, Iterable<FlightInfo>> t) throws Exception {
		Integer countGhost = 0;
		Integer countAll = 0;
		
		List<FlightInfo> history = Lists.newArrayList(t._2);
		Collections.sort(history);
		
		String lastAirport = history.get(0).getDest();
		countAll++;
		
		for(FlightInfo current : history.subList(1, history.size())){
			if(!lastAirport.equals(current.getOrigin())){
				countGhost++;
			}
			lastAirport = current.getDest();
			countAll++;
		}
		
		return new Tuple2<AirplaneId, Counters>(t._1, new Counters(countGhost, countAll, ""));
	}
}
