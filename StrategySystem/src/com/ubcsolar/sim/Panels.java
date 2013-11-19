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
private double maxPower; /** the most it will generate under ideal conditions*/
private double maxTemp; /** the most amount of heat it can tolerate before shutting down */
private double deltaTemp; /* temperature increase per time step*/
private Environment enviro;   // current angle panels are w.r.t. sun
private double current;  // current current
private double voltage;  // current voltage
private double power;   // current voltage * current// need this?
private double temp;    // current temperature of the panels
private Track track; // current direction of the car w.r.t. sun



//------------END OF FIELDS, START OF CONSTRUCTORS ---------------------------

/** default constructor. Builds the panels with default heat, angle, and watts*/
public Panels(double temp, Environment newEnviro, double voltage, double current, Track newTrack){
	enviro = newEnviro;
	this.temp = enviro.getTemperature();
 track = newTrack;
 this.voltage = voltage;
 this.current = current;
 power = (voltage * current);
 Log.write("Panels created")
 ;
	
}
/** @todo implement this! Can hardcode the model in*/

public Panels(Track newTrack){
	double angle = newTrack.getAngle();
	double direction = newTrack.getDirection();
	Log.write("Panels created");
}
//NOAH: wanted to have track involved becase we'll need to know
// the angle of the panels. Not sure how to implement this. 
//ANDREW: we'll need the current heading and position of the car,
// maybe these both come from the same variable track position 
	//(which has both angle and direction?)
/**@todo do something*/


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

/** predicts the next state of the panels, and how much they generated
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
	if(doLog){Log.write("Panels made: " + powerGenerated + " Watts");
}
return powerGenerated;
	}


/** calculates the amount of power generated in the given time
 * @param time - the amount of time this iteration spans
 * @param worldEnviro - the environment the car is in
 * @return powerGenerated - the amount of power generated (in Watts) in the given time
 */

private double calculatePower(int time, Environment worldEnviro){
	double actualAngle = enviro.getSunAngle()-track.getAngle();
	double actualAngleRadians = ( actualAngle * Math.PI) / 180 ;         // converts the sun angle to radians 
	double sunEnergy = (Math.sin(actualAngleRadians) * enviro.getSunIntensity()); // calculates the watts per square meter on a flat surface
													   // calculates the actual angle of sun hitting the panels
	double powerAvailable = calculateOutput(sunEnergy);
	double powerProduced = calculatePanelEfficiency() * powerAvailable;
	double output = powerProduced * time;
	Log.write("Panels produced " + output + " watts of power");
	return output;
	 
 /** TODO implement this. Will need to develop a model */
 //Will need to take into account everything from the environment
 //will then need to multiply by the length of the instance. 
// TODO need to adjust for square meter collecting area
 }

// TODO implement panel efficiency: modifies power generated by some value based on the current temperature
// two possible methods, one to calculate efficiency based on a smooth curve of temp / maxTemp,
// the other method is to subtract 0.32% per degree in C
private double calculatePanelEfficiency(){
	double efficiencyLoss = temp * 0.32;
	double efficiency = 1 - efficiencyLoss;
	double eff1 = 1;
	if (efficiency <= 0){
		eff1 = 0;
	}
	else {eff1 = efficiency;}
	Log.write("Panels operating at " + eff1 + "efficiency");
	return eff1;
}

// TODO implement calculateOutput; 
// takes in available power and gives back the voltage and current associated with that on the power curve
// returns power * voltage
// TODO implement power curve information
private double calculateOutput(double powerAvailable){ 
	double currentOut = powerAvailable* 0.006;
	double voltageOut = 0.5;
	voltage = voltageOut;
	current = currentOut;
	power = currentOut * voltageOut;
	double output = voltageOut * currentOut;
	return output;
	
}

// TODO implement panel heating: defines deltaHeat as some value based on power produced, and adds deltaHeat to temp
// TODO: totally made up the cooling rate and deltaHeat values
private void calculateTemperature(){
	deltaTemp = power * 0.005;
	temp = enviro.getTemperature() + deltaTemp;
	double newTemp = coolingAmount();
	temp = newTemp;
	
	Log.write("Panels produced " + deltaTemp + " amount of heat");
	Log.write("Panels are currently at " + temp + " degrees celsius");
}

// TODO implement cooling formula for the panels based on speed, environmental temperature, and current temperature
// TODO implement cooling amount based on car speed 
private double coolingAmount(){
	double tempDifference = temp - enviro.getTemperature();
	double tempReduce = .05 * (tempDifference) * Math.exp(-.01);
	return tempReduce;// arbitrary amount of 5% cooling of the temperature difference
	
}

 // ----------------- Getters and Setters ---------------- //
 // gives the current voltage output

 public double voltage_from_panel(){
	 return this.voltage;
 }
 
 // gives the current current output
 public double current_from_panel(){
	 return this.current;
 }
 
 public double power_from_panel(){
	 return this.current * this.voltage;
 }
 
 // shows the heat produced for this iteration
 public double heat_from_panel(){
	 return this.deltaTemp;
 }
 
 // shows the current temp of the panels
 public double temp_from_panel(){
	 return this.temp;
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