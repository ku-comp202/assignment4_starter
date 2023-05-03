package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import code.HashMapDH;
import given.HashEntry;

public class NGramAnalyzer {

    private HashMapDH<String, Integer> nGramMap;
    private HashMapDH<String, HashMapDH<String, Integer>> prefixMap;
    private int n;

    public NGramAnalyzer(int n) {
        this.n = n;
        // initialize nGramMap and prefixMap here!
    }

    public HashMapDH<String, Integer> getNextWordMap(String prefix) {
        return prefixMap.get(prefix);
    }

    public List<String> preprocess(String inputFile) {
        List<String> sentences = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().toLowerCase();
                line = removePunctuation(line);
                String[] tokens = line.split("\\.");
                for (String token : tokens) {
                    sentences.add(token.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sentences;
    }

    private String removePunctuation(String line) {
        Pattern pattern = Pattern.compile("[^a-zA-Z ]");
        Matcher matcher = pattern.matcher(line);
        return matcher.replaceAll("");
    }

    /*
     * This function updates nGramMap given a list of preprocessed
     * sentences. 
     * It uses n-grams as keys and updates the count of encountered n-grams;
     * You can use Java StringBuilder or simply String concatenation 
     *   to construct n-grams. 
     */
    public void buildNGram(List<String> sentences) {
        for (String sentence : sentences) {
            String[] words = sentence.split("\\s+");
            
            /**
            * Your code goes here.
            * 1. Iterate over the list of words
            * 2. Construct grams (take a look into the pdf for a detailed
            *                     explanation what a gram is)
            * 3. Increment the count for the encountered gram using nGramMap
            */
        }
    }

    public int countTwoWordCollocation(String firstWord, String secondWord) {
        String collocation = firstWord + " " + secondWord;
        Integer count = nGramMap.get(collocation);
        return count == null ? 0 : count;
    }

    
    // given another instance of NGramAnalyzerm this function computes the
    // similarty score between their nGramMap(s). Maximum score is 1, 
    // if they are absolutely same. 
    public double compareNGram(NGramAnalyzer otherAnalyzer) {
        int intersection = 0; // the number of intersected ngrams
        int union = 0; // the number of total ngrams
        
        /**
        * Your code goes here.
        * 1. Iterate over the keys in the current nGramMap
        * 2. Check whether they are present in the otherAnalyzer's nGramMap
        * 3. If they are, update the intersection and the union accordingly
        *    intersection is Math.min(count1, count2)
        *    union is Math.max(count1, count2);
        * 4. If they are not, update the union accordingly
        */

        // to scale the number to 1, we need to add the values of the keys that
        // are present in the other nGramMap, but not this one
        for (String key : otherAnalyzer.nGramMap.keySet()) {
            if (nGramMap.get(key) == null) {
                union += otherAnalyzer.nGramMap.get(key);
            }
        }

        return (double) intersection / union;
    }

    
    /*
     * This function returns a List of topN most frequent NGrams in nGramMap 
     *     You can use any sorting function (even the built-in Java ones)
     * 
     */
    public List<String> getMostFrequentNGrams(int topN) {

        List<String> mostFrequentNGrams = new ArrayList<>();
        
        /**
         * Your code goes here
         */
        
        return mostFrequentNGrams;
    }

    
    /*
     * This function updates prefixMap given a list of preprocessed
     *   sentences. It's purpose is to count the number of times a specific 
     *   last word (suffix) in the n-gram was preceded by every prefix 
     *   (an n-gram of length n-1, let's call it [n-1]-gram).
     * As you can infer from the data types of prefixMap, it uses a prefix
     *   [n-1]-grams as keys which map to another HashMapDH (value)
     * Each HashMapDH that serves as a value stores the words (suffixes) that 
     *   follow the "key" prefixes. In this HashMapDH each key is a suffix, 
     *   while the value is the count of cases when this suffix was
     *   encountered after the n-gram. (Read the pdf for an example)
     * 
     */
    public void buildPrefixMap(List<String> sentences) {
        for (String sentence : sentences) {
            String[] words = sentence.split("\\s+");
            
            /**
            * Your code goes here.
            * 1. Iterate over the list of words
            * 2. Construct [n-1]-grams 
            * 3. Get the value (HashMapDH) of this [n-1]-gram
            * 4. If there is none, create a new one and add the next
            *     word in the sentence (suffix) to it. 
            * 5. If there is one, check whether the HashMapDH contains the 
            *     current suffix. If it does, increment it. Otherwise put as 1
            */
            
        }
    }

}
