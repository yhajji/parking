package com.nespresso.sofa.interview.parking;

import com.nespresso.sofa.interview.parking.bays.Bay;
import com.nespresso.sofa.interview.parking.bays.BayState;

/**
 * Handles the parking mechanisms: park/unpark a car (also for disabled-only
 * bays) and provides a string representation of its state.
 */
public class Parking {

	private final Bay[] bays;
	private final Positioner positioner;

	public Parking(Bay[] bays, Positioner positioner) {
		this.bays = bays;
		this.positioner = positioner;
	}

	/**
	 * @return the number of available parking bays left
	 */
	public int getAvailableBays() {
		int available = 0;
		for (Bay bay : bays) {
			if (bay.check(BayState.EMPTY) || bay.check(BayState.DISABLEDEMPTY) )
				available++;
		}
		return available;
	}

	/**
	 * Park the car of the given type ('D' being dedicated to disabled people)
	 * in closest -to pedestrian exit- and first (starting from the parking's
	 * entrance) available bay. Disabled people can only park on dedicated bays.
	 *
	 *
	 * @param carType
	 *            the car char representation that has to be parked
	 * @return bay index of the parked car, -1 if no applicable bay found
	 */
	public int parkCar(final char carType) {
		if (carType == 'D') {
			return parkDisabled(carType);
		} else {
			return parkNormal(carType);
		}
	}

	private int parkNormal(final char carType) {
		for (Integer position : positioner.normalPossitions()) {
			Bay bay = bays[position.intValue()];
			if (bay.check(BayState.EMPTY)) {
				bay.setCarRepresentation(String.valueOf(carType));
				bay.changeState();
				return position.intValue();
			}
		}
		return parkFirstAvailble();
	}

	private int parkFirstAvailble() {
		for (int i = 0; i < bays.length; i++) {
			if (bays[i].check(BayState.EMPTY)) {
				bays[i].changeState();
				return i;
			}
		}
		return -1;
	}

	private int parkDisabled(final char carType) {
		for (Integer position : positioner.disabledPossitions()) {
			Bay bay = bays[position.intValue()];
			if (bay.check(BayState.DISABLEDEMPTY)) {
				bay.setCarRepresentation(String.valueOf(carType));
				bay.changeState();
				return position.intValue();
			}
		}
		return -1;
	}

	/**
	 * Unpark the car from the given index
	 *
	 * @param index
	 * @return true if a car was parked in the bay, false otherwise
	 */
	public boolean unparkCar(final int index) {
		if (bays[index].check(BayState.EMPTY) || bays[index].check(BayState.DISABLEDEMPTY)
				|| bays[index].check(BayState.PEDESTRIANEXIT)) {
			return false;
		}
		bays[index].changeState();
		return true;
	}

	/**
	 * Print a 2-dimensional representation of the parking with the following
	 * rules:
	 * <ul>
	 * <li>'=' is a pedestrian exit
	 * <li>'@' is a disabled-only empty bay
	 * <li>'U' is a non-disabled empty bay
	 * <li>'D' is a disabled-only occupied bay
	 * <li>the char representation of a parked vehicle for non-empty bays.
	 * </ul>
	 * U, D, @ and = can be considered as reserved chars.
	 *
	 * Once an end of lane is reached, then the next lane is reversed (to
	 * represent the fact that cars need to turn around)
	 *
	 * @return the string representation of the parking as a 2-dimensional
	 *         square. Note that cars do a U turn to continue to the next lane.
	 */
	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		int position;
		for (int i = 0; i < Math.sqrt(bays.length); i++) {
			for (int j = 0; j < Math.sqrt(bays.length); j++) {
				position = (i % 2 == 0) ? (int) (i * Math.sqrt(bays.length) + j)
						: (int) ((i + 1) * Math.sqrt(bays.length) - (j + 1));
				stringBuilder.append(bays[position]);
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString().replaceAll("\n$", "");
	}

}
