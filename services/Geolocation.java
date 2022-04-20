package services;

import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;

public class Geolocation {

	private String apikey = "e54788bea91b45458adfe01eac17160f";
	private IPGeolocationAPI api;
	
	public Geolocation() {
		api = new IPGeolocationAPI(apikey);
	}
	
	public io.ipgeolocation.api.Geolocation getGeolocation() {
		
		// Get geolocation for IP address (1.1.1.1) and fields (geo, time_zone and currency)
		GeolocationParams geoParams = new GeolocationParams();
		geoParams.setFields("geo,time_zone,currency");

		io.ipgeolocation.api.Geolocation geolocation = api.getGeolocation(geoParams);

		// Check if geolocation lookup was successful
		if(geolocation.getStatus() == 200) {
		    System.out.println(geolocation.getCountryName());
		    System.out.println(geolocation.getCity());
		    return geolocation;
		} else {
		    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
		    return geolocation;
		}
	}
}
