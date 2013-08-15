import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.*;

public class App {
	public static void main(String args[]) throws IOException {
		if(args == null || args.length == 0){
			Tests test = new Tests();
			test.RunTests();
			return;
		}

		File folder = new File(args[0]);
		File[] fileList = folder.listFiles();

		HashMap<String, Integer> masterHash = new HashMap<String, Integer>();

		for (Integer i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				FileReader inputStream = null;
				Parse p = new Parse();
				
				try {
		        		inputStream = new FileReader(fileList[i].getPath());
						p.BuildHash(inputStream);
				} finally {
		            if (inputStream != null) {
		                inputStream.close();
		            }
		        }

				// get the path
				HashMap<String, Integer> results = p.ReportHash();

				for (Map.Entry<String, Integer> entry : results.entrySet()) {
					String key = entry.getKey();
					if (masterHash.containsKey(key)){
						Integer prevValue = masterHash.get(key);
						masterHash.put(key, prevValue + entry.getValue());
					} else {
						masterHash.put(key, entry.getValue());
					}
				}
			}
		}

		HashMap<String, Integer> sortedHashMap = SortByValueDesc(masterHash);

		for (Map.Entry<String, Integer> entry : sortedHashMap.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}

	private static HashMap<String, Integer> SortByValueDesc(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
            	Integer val1 = m1.getValue();
            	Integer val2 = m2.getValue();
                return val2.compareTo(val1);
            }
        });

        HashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}