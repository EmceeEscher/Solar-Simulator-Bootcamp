package com.ubcsolar.sim;
/**


This class models the solar panels and their generating capacity, 
based on the variables in the environment.
See Stanford's simulator here: http://solarcar.stanford.edu/design/systems/strategy/
It would be awesome if this class could do that ^^ (minus the GUI of course)
Not entirely sure how it should pass the power to the ElectricalController (watts? Voltage and Current?)
*/
/** Model must include shape and properties of panels. */

public class Panels{
// ---------------CLASS FIELDS -------------------------------
private int maxPower; /** the most it will generate under ideal conditions*/

//------------END OF FIELDS, START OF CONSTRUCTORS ---------------------------

/** default constructor. Builds the panels with the default model*/
public Panels(){
/** @todo implement this! Can hardcode the model in*/
maxPower=5;
}
public Panels(Track newTrack){
//NOAH: wanted to have track involved becase we'll need to know
// the angle of the panels. Not sure how to implement this. 
/**@todo do something*/
}

/** constructor, loads model from file
 * @param fileName - the name of the file to pull the model from. 
 */
public Panels(String fileName){
	//TODO get properties and model from file
	Log.write("Panels created");
}
/** loads the model from the given file. 
 * @param fileName - the file from which to load the model 
 */
private void loadModel(String fileName){
/** @todo implement this. */
maxPower = 5; //should come from file
}

// ---------------END OF CONSTRUCTORS, START OF PROGRAM!----------------------

/** perdicts the next state of the panels, and how much they generated
 * @param time - the amount of time in Miliseconds that this iteration represents
 * @param worldEnviro: - the world's current environment.
 * @param doLog - whether to log on this iteration or not (if True, log).
 * @return powerGenerated - how much energy generated in iteration. 
 */
public double nextPanels(int time, Environment worldEnviro, Boolean doLog){
//update heat
//update position/angle?? 
/** @todo find a way so the panels know what angle they're on. */
double powerGenerated = calculatePower(time, worldEnviro);
if(doLog){Log.write("Panels made: " + powerGenerated + " Watts");}
return powerGenerated;
}

/** calculates the amount of power generated in the given time
 * @param time - the amount of time this iteration spans
 * @param worldEnviro - the environment the car is in
 * @return powerGenerated - the amount of power generated (in Watts) in the given time
 */
 private double calculatePower(int time, Environment worldEnviro){
 /** @todo implement this. Will need to develop a model */
 //Will need to take into account eveything from the environment
 //will then need to multiply by the length of the instance. 
 return 10.0; //random amount chosen.
 }

/* was old way of doing it before implement 'nextPanel' Can probably delete, 
//but algorithm concept may be useful.  
//calculates the amount of power generated in the given amount of time. 
public Double getPower(int time, Environment enviro, Boolean doLog){

	//TODO much better algorithm needed here. 
	Double powerGenerated = ((double)maxPower/60.0)*time*((double)enviro.getSunIntensity()/100.0);
	if(doLog){
		Log.write("Panels made " + powerGenerated + " power");
	}
	return powerGenerated;
}*/

}