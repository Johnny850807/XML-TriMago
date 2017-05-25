package API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleGeocodingAPI {
	String SERVER = "https://maps.googleapis.com/maps/api/";
	String API_KEY = "AIzaSyBGiJJ7Zmr6Kqx9wJk8Lc2ZaRt6un4Ibt4";
	@GET("geocode/json")
	Call<LocationResponse> getLocation(@Query("address")String address , 
			@Query("key") String key);
}
