package testProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		testBowp();
//		String testString="if(requiredReads < 1) throw new IllegalArgumentException(Cannot have a requiredReads number less than 1.)";
//		String[] mStrings=testString.split("[\\.\\s\\)\\(;:\"\\[\\]]|//]");
//		for (String string : mStrings) {
//			System.out.println(string);
//		}
	}

	public static void testBow() {
		Bow bow = new Bow();
		String changLog = "GET_METADATA(0, 0), UPDATE_METADATA(1, 1),UPDATE_PARTITION_ENTRIES(2, 2),FETCH_PARTITION_ENTRIES(3, 3),DELETE_PARTITION_ENTRIES(4, 4),INITIATE_FETCH_AND_UPDATE(5, 5),ASYNC_OPERATION_STATUS(6, 6)";
		Map<String, Integer> map = bow.bow(changLog);
		for (String string : map.keySet()) {
			System.out.println(string + "  " + map.get(string));
		}
		System.out.println("============================================");
		MessageBowFeature messageBowFeature = new MessageBowFeature();
		messageBowFeature.buildFeatureMap(changLog);
		for (String string : messageBowFeature.bowmaps.keySet())
			System.out.println(string + "     "
					+ messageBowFeature.bowmaps.get(string));
	}

	public static void testBowp() throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(new File(
				"741_1165.java")));
		String line;
		StringBuffer sbBuffer = new StringBuffer();
		while ((line = bReader.readLine()) != null) {
			sbBuffer.append(line + "\n");
		}
		bReader.close();
		SrcBowFeature bowFeature = new SrcBowFeature();
		bowFeature.buildFeatureMap(sbBuffer.toString());

		Bow bow = new Bow();
		Map<String, Integer> map = bow.bowP2(sbBuffer);

		Set<String> set = new TreeSet<>();
		set.addAll(bowFeature.bowmaps.keySet());
		set.addAll(map.keySet());

		Set<String> numDiffSet = new TreeSet<>();
		Set<String> s1 = new TreeSet<>();
		Set<String> s2 = new TreeSet<>();
		for (String string : set) {
			if (bowFeature.bowmaps.containsKey(string)
					&& map.containsKey(string)) {
				if (bowFeature.bowmaps.get(string).intValue() != map
						.get(string).intValue()) {
					numDiffSet.add(string);
				}
			} else if (bowFeature.bowmaps.containsKey(string)) {
				s1.add(string);
			} else {
				s2.add(string);
			}
		}

		System.out.println("===========数字不想等=============");
		for (String string : numDiffSet) {
			System.out.println(string + "       "
					+ bowFeature.bowmaps.get(string) + "      "
					+ map.get(string));
		}
		System.out.println("===========只在1中================");
		for (String string : s1) {
			if (bowFeature.bowmaps.get(string).intValue()!=0) {
				System.out.println(string + "     "
						+ bowFeature.bowmaps.get(string));
			}
			
		}
		System.out.println("============只在2中===============");
		for (String string : s2) {
			System.out.println(string + "     " + map.get(string));
		}
	}
}
