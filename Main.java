package webtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main
{
	public static void main(String[] args) 
	{
		String jsonstr = null;
		int inputID = 0;
		Reader reader = null;
		BufferedReader buff = null;
		
		// Read in input uID either from stdin or argument.
		try
		{
			if (System.in.available() > 0)
			{
				reader = new InputStreamReader(System.in);
				buff = new BufferedReader(reader);
				inputID = Integer.parseInt(buff.readLine());
			}
			else if (args.length > 0)
			{
				inputID = Integer.parseInt(args[0]);
			}
			else
			{
				System.out.println("No valid input was specified.");
				System.exit(1);
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
			System.exit(-1);
		}
		
		// get json string of user and then get all their friends
		jsonstr = Client.webGET(inputID);
		Client.showFriends(jsonstr);	
	}
}
