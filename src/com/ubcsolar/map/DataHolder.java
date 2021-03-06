/**
 * this class holds the map data in memory for access. One possible implementation is a database with a Driver.
 * Currently holds a list of points. 
 */
/* Note: may be able to use Google's Directions API for instructions, a layover, and automatic getting. 
 * How to decode it vound here: http://stackoverflow.com/questions/9217274/how-to-decode-the-google-directions-api-polylines-field-into-lat-long-points-in
 */

package com.ubcsolar.map;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ubcsolar.common.GeoCoord;

public class DataHolder {
	@SuppressWarnings("unused") //have to keep reference to parent
	private MapController myMapController;
	private String filename;
	private ArrayList<GeoCoord> theDataList;
	
	/**
	 * Constructor. 
	 * @param filename - file to load
	 * @param toAdd - parent class to reference
	 *  @throws IOException - Issue opening the specified file (permissions issues, pathname issues etc)
	 * @throws SAXException - issue parsing 
	 * @throws ParserConfigurationException - issue with the SAX parser.
	 */
	public DataHolder(String filename, MapController toAdd) throws IOException, SAXException, ParserConfigurationException{
		myMapController = toAdd;
		pureLoad(filename);
		this.filename = filename;
	}
	
	
	/**
	 * @return the filename currently loaded
	 */
	public String getFileName(){
		return filename;
	}
	
	/**
	 * @return all points currently loaded
	 */
	public ArrayList<GeoCoord> getAllPoints(){
		return theDataList;
	}
	
	
	/**
	 * garbages the data that was currently held, and loads the data contained in the file name
	 * @param filename - file to load from
	 * @throws IOException - Issue opening the specified file (permissions issues, pathname issues etc)
	 * @throws SAXException - issue parsing 
	 * @throws ParserConfigurationException - issue with the SAX parser.
	 */
	private void pureLoad(String filename) throws IOException, SAXException, ParserConfigurationException{
		theDataList = new ArrayList<GeoCoord>();
		SaxKmlParser parser = new SaxKmlParser();
		//ArrayList<GeoCoord> allPoints = new ArrayList<GeoCoord>();
		parser.parseToPoints(theDataList, filename);
		
		// for testing, print out the points that were just loaded
		/*
		System.out.println(allPoints.size());
		for(Point p:allPoints){
			System.out.println(p.getInformation() + " " + p.getLat() + "," + p.getLon() + "," + p.getElevationInMeters());
		}*/
		
	}
	
	
		/*	//note: tag name is case sensitive. 
			private static String getValue(String tag, Element element) {
			NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
			Node node = (Node) nodes.item(0);
			return node.getNodeValue();
			}*/
	}
	
	
	
	
	
	

