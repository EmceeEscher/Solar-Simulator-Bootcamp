package com.ubcsolar.weather;

import java.util.ArrayList;
import java.util.List;

import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.ubcsolar.common.GeoCoord;
/*
 * Made this it's own class in case there are config settings we need to play with when we create the 
 * internet connection/use the ForcecastIO library. 
 * 
 * Also so that we can set it to run in it's own thread. 
 */
public class ForecastFactory {
	private final String API_KEY = "e4f99878991a762e85efbffc7db8d657";

	public ArrayList<ForecastIO> getForecasts(List<GeoCoord> spots){
		ArrayList<ForecastIO> toReturn = new ArrayList<ForecastIO>(spots.size());
		for(GeoCoord g : spots){
			toReturn.add(new ForecastIO("" + g.getLat(), "" + g.getLon(), ForecastIO.UNITS_SI, ForecastIO.LANG_ENGLISH, API_KEY));
		}
		System.out.println("Factory - Spots in: " + spots.size() + " forecasts: " + toReturn.size());
		return toReturn;
	}

}
