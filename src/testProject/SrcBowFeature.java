package testProject;

import java.util.Map;
/**
 * 老师的源码提取类,继承自BowFeature.
 * @author niu
 *
 */
public class SrcBowFeature extends BowFeature{
	
	public void buildFeatureMap(String con){
			String content = con;
			Map<String, Integer> splitmap = findSourceSpliter(content);
			String[] allwords = content.split(spliter);
			Map<String, Integer> map = removeWhite(allwords);
			map.putAll(splitmap);
			maplist.add(map);
			addToMaps(map);
	}
}
