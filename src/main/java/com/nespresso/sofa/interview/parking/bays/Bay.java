package com.nespresso.sofa.interview.parking.bays;

public class Bay {

	private BayState bayState;
	private String carRepresentation;

	public Bay(BayState bayState) {
		this.bayState = bayState;
	}

	public void setBayState(BayState bayState) {
		this.bayState = bayState;
	}

	public BayState getBayState() {
		return bayState;
	}

	public void setCarRepresentation(String carRepresentation) {
		this.carRepresentation = carRepresentation;
	}

	public void changeState() {
		bayState = bayState.changeState();
	}
	
	public boolean check(BayState bayState) {
		return this.bayState == bayState;
	}

	@Override
	public String toString() {
		if (bayState == BayState.RESERVEDBAY)
			return carRepresentation;
		return bayState.toString();
	}

}
