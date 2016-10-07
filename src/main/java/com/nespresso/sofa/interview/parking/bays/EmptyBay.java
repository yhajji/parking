package com.nespresso.sofa.interview.parking.bays;

public class EmptyBay extends BayState {

	@Override
	public void changeState(Bay bay) {
		bay.setBayState(new ReservedBay());
	}
	
	@Override
	public String toString() {
		return "U";
	}

}
