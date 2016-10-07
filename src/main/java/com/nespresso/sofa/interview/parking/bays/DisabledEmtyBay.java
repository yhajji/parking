package com.nespresso.sofa.interview.parking.bays;

public class DisabledEmtyBay extends BayState {

	@Override
	public void changeState(Bay bay) {
		bay.setBayState(new DisabledReservedBay());
	}

	@Override
	public String toString() {
		return "@";
	}

}
