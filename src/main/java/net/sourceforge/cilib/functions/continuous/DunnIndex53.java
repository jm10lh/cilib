/*
 * DunnIndex53.java
 * 
 * Created on July 18, 2007
 *
 * Copyright (C) 2003 - 2007
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.functions.continuous;

import java.util.ArrayList;

import net.sourceforge.cilib.problem.dataset.ClusterableDataSet.Pattern;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * This is the Dunn Index 53 due to Equations 24 and 28 in<br/>
 * @Article{ 678624, title = "Some New Indexes of Cluster Validity", author = "James C. Bezdek and
 *           Nikhil R. Pal", journal = "IEEE Transactions on Systems, Man, and Cybernetics, Part B:
 *           Cybernetics", pages = "301--315", volume = "28", number = "3", month = jun, year =
 *           "1998", issn = "1083-4419" }
 * @author Theuns Cloete
 */
public class DunnIndex53 extends DunnIndex33 {
	private static final long serialVersionUID = -5986491658596276019L;

	public DunnIndex53() {
		super();
	}

	/**
	 * This method implements Equation 24 in the above-mentioned article.
	 */
	@Override
	protected double calculateBetweenClusterSeperation(int i, int j) {
		double lhsAverage = 0.0, rhsAverage = 0.0;
		ArrayList<Pattern> lhs = arrangedClusters.get(i);
		ArrayList<Pattern> rhs = arrangedClusters.get(j);
		Vector lhsMean = dataset.getSetMean(lhs);
		Vector rhsMean = dataset.getSetMean(rhs);

		for (Pattern pattern : lhs) {
			lhsAverage += dataset.calculateDistance(pattern.data, rhsMean);
		}

		for (Pattern pattern : rhs) {
			rhsAverage += dataset.calculateDistance(pattern.data, lhsMean);
		}

		return (lhsAverage + rhsAverage) / (lhs.size() + rhs.size());
	}
}
