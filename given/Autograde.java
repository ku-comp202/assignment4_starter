package given;

import code.HashMapDH;
import code.NGramAnalyzer;
import java.io.File;
import java.util.Arrays;
import java.util.List;

// DO NOT MODIFY 
// (unless you are just testing something, it should be unmodified when submitted)
public class Autograde {

    public static void main(String[] args) {
        runHashMapTests();
        runNGramTests();
        compareNGram();
        getMostFrequentNGrams();
        buildPrefixMap();
    }

    private static boolean nGramTest1() {
        NGramAnalyzer analyzer1 = new NGramAnalyzer(2);
        List<String> sentences1 = Arrays.asList("the quick brown fox", "the quick brown dog");
        analyzer1.buildNGram(sentences1);

        int collocationCount1 = analyzer1.countTwoWordCollocation("quick", "brown");
        if (collocationCount1 == 2) {
            System.out.println("nGram Test 1: Passed");
            return true;
        } else {
            System.out.println("nGram Test 1: Failed");
            return false;
        }
    }

    private static boolean nGramTest2() {
        NGramAnalyzer analyzer2 = new NGramAnalyzer(2);
        List<String> sentences2 = Arrays.asList("hello  world", "how  are you");
        analyzer2.buildNGram(sentences2);

        int collocationCount2 = analyzer2.countTwoWordCollocation("hello", "world");
        if (collocationCount2 == 1) {
            System.out.println("nGram Test 2: Passed");
            return true;
        } else {
            System.out.println("nGram Test 2: Failed");
            return false;
        }
    }

    private static boolean nGramTest3() {
        NGramAnalyzer analyzer3 = new NGramAnalyzer(2);
        List<String> sentences3 = Arrays.asList("apple", "banana", "orange");
        analyzer3.buildNGram(sentences3);

        int collocationCount3 = analyzer3.countTwoWordCollocation("apple", "banana");
        if (collocationCount3 == 0) {
            System.out.println("nGram Test 3: Passed");
            return true;
        } else {
            System.out.println("nGram Test 3: Failed");
            return false;
        }
    }

    private static void runNGramTests() {
        int passed = 0;
        int total = 0;

        if (nGramTest1()) {
            passed++;
        }
        total++;
        if (nGramTest2()) {
            passed++;
        }
        total++;
        if (nGramTest3()) {
            passed++;
        }
        total++;

        double score = (double) passed / total * 10;
        System.out.println("nGram Tests Passed: " + passed + "/" + total + ", Score: " + score + "/10");
    }

    private static boolean hashMapTest1() {
        HashMapDH<String, Integer> map1 = new HashMapDH<>();
        map1.put("one", 1);
        map1.put("two", 2);
        map1.put("three", 3);

        if (map1.get("one") == 1 && map1.get("two") == 2 && map1.get("three") == 3) {
            System.out.println("HashMap Test 1: Passed");
            return true;
        } else {
            System.out.println("HashMap Test 1: Failed");
            return false;
        }
    }

    private static boolean hashMapTest2() {
        HashMapDH<String, Integer> map2 = new HashMapDH<>(5, 0.6f);
        for (int i = 1; i <= 10; i++) {
            map2.put("key" + i, i);
        }
        int correctCount = 0;
        for (int i = 1; i <= 10; i++) {
            if (map2.get("key" + i) == i) {
                correctCount++;
            }
        }
        if (correctCount == 10) {
            System.out.println("HashMap Test 2: Passed");
            return true;
        } else {
            System.out.println("HashMap Test 2: Failed");
            return false;
        }
    }

    private static boolean hashMapTest3() {
        HashMapDH<String, Integer> map3 = new HashMapDH<>();
        map3.put("apple", 1);
        map3.put("banana", 2);
        map3.put("cherry", 3);
        List<String> keys = Arrays.asList("apple", "banana", "cherry");
        int keySetCorrectCount = 0;
        for (String key : map3.keySet()) {
            if (keys.contains(key)) {
                keySetCorrectCount++;
            }
        }
        if (keySetCorrectCount == 3) {
            System.out.println("HashMap Test 3: Passed");
            return true;
        } else {
            System.out.println("HashMap Test 3: Failed");
            return false;
        }
    }

    private static boolean hashMapTest4() {
        HashMapDH<String, Integer> map = new HashMapDH<>();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, i);
        }

        boolean keysValid = true;
        for (int i = 0; i < 100; i++) {
            if (!map.get("key" + i).equals(i)) {
                keysValid = false;
                break;
            }
        }

        if (map.size() == 100 && map.capacity() > 100 && keysValid) {
            System.out.println("HashMap Test 4: Passed");
            return true;
        } else {
            System.out.println("HashMap Test 4: Failed");
            return false;
        }
    }

    private static boolean hashMapTest5() {
        HashMapDH<String, Integer> map = new HashMapDH<>();
        map.put(null, 1);
        map.put("key", null);

        if (map.get(null) == null && map.get("key") == null) {
            System.out.println("HashMap Test 5: Passed");
            return true;
        } else {
            System.out.println("HashMap Test 5: Failed");
            return false;
        }
    }

    private static boolean hashMapTest6() {
        HashMapDH<String, Integer> map = new HashMapDH<>();
        map.put("key", 1);
        map.put("key", 2);

        if (map.size() == 1 && map.get("key") == 2) {
            System.out.println("HashMap Test 6: Passed");
            return true;
        } else {
            System.out.println("HashMap Test 6: Failed");
            return false;
        }
    }

    private static void runHashMapTests() {
        int passed = 0;
        int total = 0;

        if (hashMapTest1()) {
            passed++;
        }
        total++;
        if (hashMapTest2()) {
            passed++;
        }
        total++;
        if (hashMapTest3()) {
            passed++;
        }
        total++;
        if (hashMapTest4()) {
            passed++;
        }
        total++;
        if (hashMapTest5()) {
            passed++;
        }
        total++;
        if (hashMapTest6()) {
            passed++;
        }
        total++;

        double score = (double) passed / total * 60;
        System.out.println("HashMap Tests Passed: " + passed + "/" + total + ", Score: " + score + "/60");
    }

    public static void compareNGram() {
        int n = 2; // the desired n-gram length

        File folder = new File("dataset");
        File[] listOfFiles = folder.listFiles();

        for (int fileIndex1 = 0; fileIndex1 < listOfFiles.length; fileIndex1++) {
            for (int fileIndex2 = fileIndex1 + 1; fileIndex2 < listOfFiles.length; fileIndex2++) {
                String inputFile1 = listOfFiles[fileIndex1].getPath();
                String inputFile2 = listOfFiles[fileIndex2].getPath();

                if (inputFile1.equals(inputFile2)) {
                    continue;
                }

                NGramAnalyzer analyzer1 = new NGramAnalyzer(n);
                List<String> sentences1 = analyzer1.preprocess(inputFile1);
                analyzer1.buildNGram(sentences1);

                NGramAnalyzer analyzer2 = new NGramAnalyzer(n);
                List<String> sentences2 = analyzer2.preprocess(inputFile2);
                analyzer2.buildNGram(sentences2);

                double similarity = analyzer1.compareNGram(analyzer2);
                System.out.println("Similarity between " + listOfFiles[fileIndex1].getName()
                        + " and " + listOfFiles[fileIndex2].getName() + ": " + similarity);
            }
        }
    }

    public static void getMostFrequentNGrams() {
        int n = 3; // the desired n-gram length
        int topN = 5; // the desired number of most frequent n-grams to display

        File folder = new File("dataset");
        File[] listOfFiles = folder.listFiles();

        for (File inputFile : listOfFiles) {

            NGramAnalyzer analyzer = new NGramAnalyzer(n);
            List<String> sentences = analyzer.preprocess(inputFile.getPath());
            analyzer.buildNGram(sentences);

            List<String> mostFrequentNGrams = analyzer.getMostFrequentNGrams(topN);
            System.out.println(inputFile.getName() + " top " + topN + " most frequent " + n + "-grams:");
            for (String nGram : mostFrequentNGrams) {
                System.out.println(nGram);
            }

        }
    }

    public static void buildPrefixMap() {
        int n = 3; // the desired n-gram length

        File folder = new File("dataset");
        File[] listOfFiles = folder.listFiles();

        for (File inputFile : listOfFiles) {

            NGramAnalyzer analyzer = new NGramAnalyzer(n);
            List<String> sentences = analyzer.preprocess(inputFile.getPath());
            analyzer.buildNGram(sentences);
            analyzer.buildPrefixMap(sentences);

            // Example usage of the prefixMap
            String prefix = "for the"; // Change this to the desired prefix (N-1 gram)
            HashMapDH<String, Integer> nextWordMap = analyzer.getNextWordMap(prefix);
            System.out.print(inputFile.getName() + ". Most frequent next word for the prefix \"" + prefix + "\": ");
            if (nextWordMap != null) {
                Integer topValue = 0;
                String topKey = "";
                for (String key : nextWordMap.keySet()) {
                    if (nextWordMap.get(key) > topValue) {
                        topValue = nextWordMap.get(key);
                        topKey = key;
                    }
                }
                System.out.println(topKey + " | count: " + topValue);
            } else {
                System.out.println("N/A");
            }
        }
    }
}
