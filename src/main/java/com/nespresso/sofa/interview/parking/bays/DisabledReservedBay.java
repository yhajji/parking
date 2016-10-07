package com.nespresso.sofa.interview.parking.bays;

public class DisabledReservedBay extends BayState {

	@Override
	public void changeState(Bay bay) {
		bay.setBayState(new DisabledEmtyBay());
	}
	
	@Override
	public String toString() {
		return "D";
	}

}
