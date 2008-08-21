/*
 * EntityCloneInitialisationBuilderTest.java
 *
 * Created on April 24, 2006, 2:26 PM
 *
 *
 * Copyright (C) 2003 - 2006 
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
 *
 */
package net.sourceforge.cilib.algorithm.initialisation;

import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.entity.Topology;
import net.sourceforge.cilib.entity.topologies.GBestTopology;
import net.sourceforge.cilib.problem.OptimisationProblem;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Class to test the concept of initialising a <tt>Topology</tt> of
 * <tt>Entity</tt> objects, by cloning a given prototype <tt>Entity</tt>.
 *  
 * @author Gary Pampara
 */
@RunWith(JMock.class)
public class ClonedPopulationInitialisationStrategyTest {
	private Mockery context = new JUnit4Mockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	/**
	 * Test that the initialisation of the entity does indeed mean that the initialised
	 * Entity is added to the topology and the Entity is not the same as the original.
	 */
	@Test
	public void initialiseClonedTopology() {
		@SuppressWarnings("unchecked")
		final Topology<Entity> topology = context.mock(GBestTopology.class);
		final Entity entity = context.mock(Entity.class);
		final OptimisationProblem problem = context.mock(OptimisationProblem.class);
		
		final PopulationInitialisationStrategy initialisationBuilder = new ClonedPopulationInitialisationStrategy();
		initialisationBuilder.setEntityType(entity);
		initialisationBuilder.setEntityNumber(20);
		
		context.checking(new Expectations() {{
			exactly(20).of(entity).getClone(); // The prototype entity is cloned exactly 20 times.
			
			// The cloned entity will be added to the topology
			exactly(20).of(topology).add(with(any(Entity.class)));
			will(returnValue(true));
		}});
		
		initialisationBuilder.initialise(topology, problem);
	}

}