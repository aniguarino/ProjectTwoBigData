package ghostFlightJob.functions;

import org.apache.spark.api.java.function.PairFunction;

import ghostFlightJob.model.AirplaneId;
import ghostFlightJob.model.CompanyId;
import ghostFlightJob.model.Counters;
import scala.Tuple2;

public class TrasformToCompany implements PairFunction<Tuple2<AirplaneId, Counters>, CompanyId, Counters> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<CompanyId, Counters> call(Tuple2<AirplaneId, Counters> t) throws Exception {
		return new Tuple2<CompanyId, Counters>(new CompanyId(t._1.getUniqueCarrier(), t._1.getYear(), t._1.getMonth()), t._2);
	}
}
