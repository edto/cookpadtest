package webtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//EXTERNAL LIBRARIES
import org.json.*;


public class Client
{
	private static final String API_URL = "http://fg-69c8cbcd.herokuapp.com/user/";

	/**
	 * Takes a user id and runs a GET request on the API. Returns JSON string.
	 * @param uID
	 * @return output
	 */
	public static String webGET(int uID)
	{
		try
		{
			String read_buff;
			String output = "";

			URL url = new URL(API_URL + uID);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200)
			{
				throw new RuntimeException("HTTP error: " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			while ((read_buff = br.readLine()) != null)
			{
				output = output + read_buff;
			}

			conn.disconnect();
			return output;
		  }
		
		  catch (MalformedURLException e)
		  {
			e.printStackTrace();
			System.exit(-1);
		  }
		  catch (IOException e)
		  {
			e.printStackTrace();
			System.exit(-1);
		  }
		
		return null;
	}
	
	/**
	 * Takes a JSON string and creates objects to traverse "friends" array and then gets their names.
	 * Repeats this for mutual friends.
	 * 
	 * @param json
	 */
	public static void showFriends(String json)
	{
		String jsonstr = "";
		String m_jsonstr = "";
		JSONObject user = new JSONObject(json);
		JSONObject friend = null;
		JSONObject m_friend = null;
		JSONArray arfrs = user.getJSONArray("friends");
		JSONArray armutualfrs = null;
		
		String username = user.getString("name");
		System.out.println("USERNAME: " + username + "\n--------------");
		
		
		for (int i = 0; i < arfrs.length(); i++)
		{
			// create json objects for all friends and print their names
			jsonstr = webGET(arfrs.getInt(i));
			friend = new JSONObject(jsonstr);
			System.out.println("Friend: " + friend.getString("name"));
			
			// create mutual friends array for each friend
			armutualfrs = friend.getJSONArray("friends");

			for (int j = 0; j < armutualfrs.length(); j++)
			{
				// create json objects for mutual friends and print their names
				m_jsonstr = webGET(armutualfrs.getInt(j));
				m_friend = new JSONObject(m_jsonstr);
				System.out.println("Mutual friend of " + friend.getString("name") +": " + m_friend.getString("name"));
			}
		}
	}

}
