package testProject;

import java.util.HashMap;
import java.util.Map;

public class MessageBowFeature extends BowFeature{


	

	public void buildFeatureMap(String message){
			Map<String, Integer> map = new HashMap<String, Integer>();;
			if(!message.contains("*** empty log message ***")){
				Map<String, Integer> splitmap = findSourceSpliter(message);
				String[] allwords = message.split(spliter);
				map = removeWhite(allwords);
				map.putAll(splitmap);
				addToMaps(map);
			}
			else
				map.put("@@", -1);
			maplist.add(map);
		}
	}