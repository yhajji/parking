package com.nespresso.sofa.interview.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Positioner {

	private final List<Integer> pedestrianExitIndexs;
	private final List<Integer> disabledBayIndexs;

	public Positioner(List<Integer> pedestrianExitIndexs, List<Integer> disabledBayIndexs) {
		Collections.sort(pedestrianExitIndexs);
		this.pedestrianExitIndexs = pedestrianExitIndexs;
		this.disabledBayIndexs = disabledBayIndexs;
	}

	public List<Integer> disabledPossitions() {
		Collections.reverse(disabledBayIndexs);
		return disabledBayIndexs;
	}

	public List<Integer> normalPossitions() {
		List<Integer> closestToPedestrianExit = new ArrayList<>();
		for (Integer pedestrianExitIndex : pedestrianExitIndexs) {
			closestToPedestrianExit.add(pedestrianExitIndex - 1);
			closestToPedestrianExit.add(pedestrianExitIndex + 1);
		}
		return closestToPedestrianExit;
	}

}
