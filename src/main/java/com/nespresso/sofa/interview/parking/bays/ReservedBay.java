package com.nespresso.sofa.interview.parking.bays;

public class ReservedBay extends BayState {
	
	@Override
	public void changeState(Bay bay) {
		bay.setBayState(new EmptyBay());
	}
	

}
