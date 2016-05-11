/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utexas.cs.nn.tasks.gridTorus;

/**
 * Imports needed parts to initialize the Controller, as in Torus agent and world, the controller, network, and statistic utilities.
 */
import edu.utexas.cs.nn.gridTorus.TorusAgent;
import edu.utexas.cs.nn.gridTorus.TorusWorld;
import edu.utexas.cs.nn.gridTorus.controllers.TorusPredPreyController;
import edu.utexas.cs.nn.networks.Network;
import edu.utexas.cs.nn.util.stats.StatisticsUtilities;

/**
 *
 * @author Jacob Schrum, Gabby Gonzalez
 * The following class extends the normal TorusPredPreyController to allow for a neural network.
 */
public class NNTorusPredPreyController extends TorusPredPreyController {
    /**
     * Initializes the network to be used.
     */
	private final Network nn;
    
	/**
	 * Takes in network and connects it to the controller
	 * @param nn
	 */
    public NNTorusPredPreyController(Network nn){ 
        this.nn = nn;
    }
    
    /**
     * Takes in all agents (me, world, preds, prey) to allow agent me to return best possible actions
     * @param all agents (me, world, preds, prey)
     * @return actions array
     */
    @Override
    public int[] getAction(TorusAgent me, TorusWorld world, TorusAgent[] preds, TorusAgent[] prey) { 
        double[] inputs = inputs(me,world,preds,prey);
        double[] outputs = nn.process(inputs);
        // Assume one output for each direction
        return actions[StatisticsUtilities.argmax(outputs)];
    }

    /**
     * Calculates inputs for the neural network in order to figure what action to take in getAction.
     * @param all agents (me, world, preds, prey)
     * @return
     */
    public double[] inputs(TorusAgent me, TorusWorld world, TorusAgent[] preds, TorusAgent[] prey) { 
        double[] inputs = new double[preds.length * 2];
        for(int i = 0; i < preds.length; i++) {
            inputs[(2*i)] = me.shortestXOffset(preds[i]) / (1.0*world.width());
            inputs[(2*i)+1] = me.shortestYOffset(preds[i]) / (1.0*world.height()); 
        }
        return inputs;
    }

    /**
     * Sets up the sensor labels for sensors to be used in network visualization.
     * @param numPreds
     * @return
     */
    public static String[] sensorLabels(int numPreds) { 
        String[] result = new String[numPreds * 2];
        for(int i = 0; i < numPreds; i++) {
            result[(2*i)] = "X Offset to Pred " + i;
            result[(2*i)+1] = "Y Offset to Pred " + i; 
        }
        return result;
    }
}