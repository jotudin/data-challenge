package fi.jyu.app;

import java.io.IOException;

import fi.jyu.data.twitter.Auth;
import fi.jyu.data.twitter.Client;
import fi.jyu.data.twitter.Config;


public class App {

	public static void main(String[] args) {
		Config config = new Config();
		Client client = null;
		
		args = new String[] {"search", "#Trump", "usa"};
		
		if (args.length == 0) {
			System.out.println("Provide a command: help, config, test or search.");
			System.exit(0);
		}
		
		switch (args[0]) {
		case "config": 
			config = Auth.doAuth(config);
			break;
		
		case "test":
			client = new Client(config);

			try {
				client.test();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "search":
			client = new Client(config);
			
			if (null != args[1] && !args[1].isEmpty()
					&& null != args[2] && !args[2].isEmpty()) {
				
				String geocode = "";
				
				if (args[2].equalsIgnoreCase("usa")) geocode = "39.8,-95.583068847656,2500km";
				if (args[2].equalsIgnoreCase("europe")) geocode = "51.0504,13.7373,2300km";
				
				try {
					client.search(args[1], geocode);
					System.out.println("Done.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			} else {
				System.out.println("Don't know what to search and from where.");
			}
			break;

		default:
			System.out.println("Usage");
			System.out.println("twelper.jar <help|config|test|search]> <search terms> <search area[usa|europe]>");
			System.out.println("Will fetch 5000 English tweets based on the criteria (terms and area).");
		}
	}

}
