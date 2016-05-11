
package edu.utexas.cs.nn.gridTorus.controllers;

import edu.utexas.cs.nn.gridTorus.TorusAgent;
import edu.utexas.cs.nn.gridTorus.TorusPredPreyGame;
import edu.utexas.cs.nn.gridTorus.TorusWorld;

/**
 *
 * @author Jacob Schrum
 * 
 * This abstract class that provides the framework for the other controller methods
 * in this package. 
 * 
 */
public abstract class TorusPredPreyController {

	// Movement offsets for each available action: UP, RIGHT, DOWN, LEFT
    public static int[][] actions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    
    /**
     * 
     * @param me: the controlled agent
     * @param game: the domain in which the game is being played
     * @return:  calls the other getAction method in order to
     *           with the world, predators, and prey so the controller classes
     *           can use them.
     */
    public int[] getAction(TorusAgent me, TorusPredPreyGame game){
        return getAction(me, game.getWorld(), game.getPredators(), game.getPrey());
    }
    /**
     * 
     * @param me: the controller
     * @param world: the domain on which the game is being played
     * @param preds: the predators
     * @param prey: the prey agents 
     * @return: an int array corresponding to the movement agent me should take.
     */
    public abstract int[] getAction(TorusAgent me, TorusWorld world, TorusAgent[] preds, TorusAgent[] prey);
}