package com.nespresso.sofa.interview.parking.bays;

public enum BayState {
	DISABLEDEMPTY("@") {
		@Override
		public BayState change() {
			return DISABLEDRESERVED;
		}
	},DISABLEDRESERVED("D") {
		@Override
		public BayState change() {
			return DISABLEDEMPTY;
		}
	},EMPTY("U") {
		@Override
		public BayState change() {
			return RESERVED;
		}
	},PEDESTRIANEXIT("=") {
		@Override
		public BayState change() {
			return 	this;		
		}
	},RESERVED("") {
		@Override
		public BayState change() {
			return EMPTY;
		}
	};
	private final String code;
	
	private BayState(String code) {
		this.code = code;
	}
	
	public abstract BayState change();
	
	@Override
	public String toString() {
		return code;
	}
}
