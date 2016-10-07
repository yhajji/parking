package com.nespresso.sofa.interview.parking;

import java.util.ArrayList;
import java.util.List;

import com.nespresso.sofa.interview.parking.bays.Bay;
import com.nespresso.sofa.interview.parking.bays.BayState;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {

	private final int squarSize;
	private final List<Integer> pedestrianExitIndexs;
	private final List<Integer> disabledBayIndexs;

	public ParkingBuilder() {
		squarSize = 0;
		this.pedestrianExitIndexs = new ArrayList<>();
		this.disabledBayIndexs = new ArrayList<>();
	}

	public ParkingBuilder(int squarSize) {
		this.squarSize = squarSize;
		this.pedestrianExitIndexs = new ArrayList<>();
		this.disabledBayIndexs = new ArrayList<>();
	}

	public ParkingBuilder withSquareSize(final int size) {
		return new ParkingBuilder(size);
	}

	public ParkingBuilder withPedestrianExit(final int pedestrianExitIndex) {
		pedestrianExitIndexs.add(pedestrianExitIndex);
		return this;
	}

	public ParkingBuilder withDisabledBay(final int disabledBayIndex) {
		disabledBayIndexs.add(disabledBayIndex);
		return this;
	}

	public Parking build() {
		Bay[] bays = new Bay[squarSize * squarSize];
		for (Integer i = 0; i < bays.length; i++) {
			bays[i] = createBay(i);
		}

		return new Parking(bays, new Positioner(pedestrianExitIndexs, disabledBayIndexs));
	}

	private Bay createBay(Integer i) {
		if (pedestrianExitIndexs.contains(i))
			return new Bay(BayState.PEDESTRIANEXITBAY);
		else if (disabledBayIndexs.contains(i))
			return new Bay(BayState.DISABLEDEMTYBAY);
		return new Bay(BayState.EMPTYBAY);
	}
}
