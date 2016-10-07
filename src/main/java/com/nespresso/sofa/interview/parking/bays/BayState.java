package com.nespresso.sofa.interview.parking.bays;

public enum BayState {
	DISABLEDEMTYBAY("@") {
		@Override
		public BayState changeState() {
			return DISABLEDRESERVEDBAY;
		}
	},DISABLEDRESERVEDBAY("D") {
		@Override
		public BayState changeState() {
			return DISABLEDEMTYBAY;
		}
	},EMPTYBAY("U") {
		@Override
		public BayState changeState() {
			return RESERVEDBAY;
		}
	},PEDESTRIANEXITBAY("=") {
		@Override
		public BayState changeState() {
			return 	this;		
		}
	},RESERVEDBAY("") {
		@Override
		public BayState changeState() {
			return EMPTYBAY;
		}
	};
	private final String code;
	
	private BayState(String code) {
		this.code = code;
	}
	
	public abstract BayState changeState();
	
	@Override
	public String toString() {
		return code;
	}
}
