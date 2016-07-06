package carrierDelayJob.functions;

import org.apache.spark.api.java.function.Function2;

import carrierDelayJob.model.CarrierDelay;

public class ProduceDelay implements Function2<CarrierDelay, CarrierDelay, CarrierDelay> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public CarrierDelay call(CarrierDelay arg0, CarrierDelay arg1) throws Exception {
		return arg0.sum(arg1);
	}
}
