package API;

import javax.xml.ws.Response;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class APIFactory {
	public static API createAPI(Class<? extends API> api) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{	
		final String SERVER = (String) api.getDeclaredField("SERVER")
				.get(new String());
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
			    .baseUrl(SERVER)
			    .build();
		return retrofit.create(api);		
	}
}
