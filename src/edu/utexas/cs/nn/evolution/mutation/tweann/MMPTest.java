package edu.utexas.cs.nn.evolution.mutation.tweann;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.utexas.cs.nn.MMNEAT.MMNEAT;
import edu.utexas.cs.nn.evolution.EvolutionaryHistory;
import edu.utexas.cs.nn.evolution.crossover.network.TWEANNCrossover;
import edu.utexas.cs.nn.evolution.genotypes.TWEANNGenotype;
import edu.utexas.cs.nn.graphics.DrawingPanel;
import edu.utexas.cs.nn.networks.TWEANN;
import edu.utexas.cs.nn.parameters.Parameters;

public class MMPTest {

	TWEANNGenotype tg1;
	MMP mmp1;
	final int MUTATIONS1 = 30;

	@After
	public void tearDown() throws Exception {
		MMNEAT.clearClasses();
	}
	
	@Before
	public void setUp() throws Exception { 
		MMNEAT.clearClasses();
		Parameters.initializeParameterCollections(
				new String[] { "io:false", "netio:false", "recurrency:false", "mmpRate:1.0" });
		MMNEAT.loadClasses();
		tg1 = new TWEANNGenotype(MMNEAT.networkInputs, MMNEAT.networkOutputs, 0);
		MMNEAT.genotype = tg1.copy();
		EvolutionaryHistory.initArchetype(0);
		mmp1 = new MMP();
	}
	@Test
	public void test_moduleMutation() {
		assertEquals(tg1.numOut, 4);
		mmp1.mutate(tg1);
		assertEquals(tg1.numModules, 2);
		assertEquals(tg1.numOut, 8);
		TWEANN t1 = tg1.getPhenotype();
		int module1Index = tg1.numIn;
		int module2Index = t1.neuronsPerModule() + 1 + module1Index;
		assertTrue(tg1.getLinkBetween(tg1.nodes.get(module1Index + 0).innovation, tg1.nodes.get(module2Index + 0).innovation) != null);
		assertTrue(tg1.getLinkBetween(tg1.nodes.get(module1Index + 1).innovation, tg1.nodes.get(module2Index + 1).innovation) != null);
		assertTrue(tg1.getLinkBetween(tg1.nodes.get(module1Index + 2).innovation, tg1.nodes.get(module2Index + 2).innovation) != null);

	}
		{
		Parameters.initializeParameterCollections(new String[] { "io:false", "recurrency:false", "mmdRate:0.1" });
		MMNEAT.loadClasses();
		TWEANNGenotype tg1 = new TWEANNGenotype(5, 2, 0);
		MMNEAT.genotype = tg1.copy();
		EvolutionaryHistory.initArchetype(0);
		TWEANNGenotype tg2 = new TWEANNGenotype(5, 2, 0);

		final int MUTATIONS1 = 30;

		for (int i = 0; i < MUTATIONS1; i++) {
			tg1.mutate();
			tg2.mutate();
		}

		DrawingPanel p1 = new DrawingPanel(TWEANN.NETWORK_VIEW_DIM, TWEANN.NETWORK_VIEW_DIM, "Net 1");
		DrawingPanel p2 = new DrawingPanel(TWEANN.NETWORK_VIEW_DIM, TWEANN.NETWORK_VIEW_DIM, "Net 2");
		p2.setLocation(TWEANN.NETWORK_VIEW_DIM + 10, 0);
		tg1.getPhenotype().draw(p1, true);
		tg2.getPhenotype().draw(p2, true);

		new MMP().mutate(tg1);
		DrawingPanel p3 = new DrawingPanel(TWEANN.NETWORK_VIEW_DIM, TWEANN.NETWORK_VIEW_DIM, "Net 1 MMD");
		p3.setLocation(0, TWEANN.NETWORK_VIEW_DIM + 10);
		tg1.getPhenotype().draw(p3, true);

		new MMP().mutate(tg2);
		DrawingPanel p4 = new DrawingPanel(TWEANN.NETWORK_VIEW_DIM, TWEANN.NETWORK_VIEW_DIM, "Net 2 MMD");
		p4.setLocation(TWEANN.NETWORK_VIEW_DIM + 10, TWEANN.NETWORK_VIEW_DIM + 10);
		tg2.getPhenotype().draw(p4, true);

		TWEANNCrossover cross = new TWEANNCrossover();
		TWEANNGenotype new2 = (TWEANNGenotype) cross.crossover(tg1, tg2);

		DrawingPanel p5 = new DrawingPanel(TWEANN.NETWORK_VIEW_DIM, TWEANN.NETWORK_VIEW_DIM, "Cross Result 1");
		p5.setLocation(2 * (TWEANN.NETWORK_VIEW_DIM + 10), 0);
		tg1.getPhenotype().draw(p5, true);

		DrawingPanel p6 = new DrawingPanel(TWEANN.NETWORK_VIEW_DIM, TWEANN.NETWORK_VIEW_DIM, "Cross Result 2");
		p6.setLocation(2 * (TWEANN.NETWORK_VIEW_DIM + 10), TWEANN.NETWORK_VIEW_DIM + 10);
		new2.getPhenotype().draw(p6, true);

	}
}
