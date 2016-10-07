package com.nespresso.sofa.interview.parking;

import java.util.ArrayList;
import java.util.List;


import com.nespresso.sofa.interview.parking.bays.Bay;
import com.nespresso.sofa.interview.parking.bays.DisabledEmtyBay;
import com.nespresso.sofa.interview.parking.bays.EmptyBay;
import com.nespresso.sofa.interview.parking.bays.PedestrianExitBay;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {
	
	private final int squarSize;
	private final List<Integer> pedestrianExitIndexs;
	private final List<Integer> disabledBayIndexs;
	
	public ParkingBuilder() {
		squarSize =0;
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
    	Bay[] bays = new Bay[squarSize*squarSize];
    	for(Integer i =0; i< bays.length; i++){
    		if(pedestrianExitIndexs.contains(i))
    			bays[i] = new Bay(new PedestrianExitBay());
    		else if (disabledBayIndexs.contains(i))
    			bays[i] = new Bay(new DisabledEmtyBay());
    		else
    			bays[i] = new Bay(new EmptyBay());
    	}
    	
    	return new Parking(bays, new Positioner(pedestrianExitIndexs, disabledBayIndexs));
    }
}
