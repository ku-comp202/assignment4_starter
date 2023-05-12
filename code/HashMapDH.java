package code;

import java.lang.reflect.Array;
import java.util.ArrayList;

import given.AbstractHashMap;
import given.HashEntry;
import code.CustomHash;

/*
 * The file should contain the implementation of a hashmap with:
 * - Open addressing for collision handling
 * - Double hashing for probing. The double hash function should be of the form: q - (k mod q)
 * - Multiply-Add-Divide (MAD) for compression: (a*k+b) mod p
 * - Resizing (to double its size) and rehashing when the load factor gets above a threshold
 * 
 * Some helper functions are provided to you. We suggest that you go over them.
 * 
 * You are not allowed to use any existing java data structures other than for the keyset method
 */
public class HashMapDH<Key, Value> extends AbstractHashMap<Key, Value> {

    // The underlying array to hold hash entries (see the HashEntry class)
    private HashEntry<Key, Value>[] buckets;

    //Do not forget to call this when you need to increase the size!
    @SuppressWarnings("unchecked")
    protected void resizeBuckets(int newSize) {
        // Update the capacity
        N = nextPrime(newSize);
        buckets = (HashEntry<Key, Value>[]) Array.newInstance(HashEntry.class, N);
        n = 0; // Add this line to reset the size of the hashmap when resizing
    }

    // The threshold of the load factor for resizing
    protected float criticalLoadFactor;

    // The prime number for the secondary hash
    int dhP;

    /*
     * ADD MORE FIELDS IF NEEDED
     * 
     */
    
    private final HashEntry<Key, Value> DEFUNCT = new HashEntry<Key, Value>(null, null);
    
    // Default constructor
    public HashMapDH() {
        this(101);
    }

    public HashMapDH(int initSize) {
        this(initSize, 0.6f);
    }

    public HashMapDH(int initSize, float criticalAlpha) {
        N = initSize;
        criticalLoadFactor = criticalAlpha;
        resizeBuckets(N);

        // Set up the MAD compression and secondary hash parameters
        updateHashParams();

        /*
         * ADD MORE CODE IF NEEDED
         * 
         */
    }
    
    /*
     * ADD MORE METHODS IF NEEDED
     * 
     */
    
    /*
     * Calculates the hash value by compressing the given hashcode. Note that
     * you need to use the Multiple-Add-Divide method. The class variables "a"
     * is the scale, "b" is the shift, "mainP" is the prime which are calculated
     * for you. Do not include the size of the array here
     *
     * Make sure to include the absolute value since there maybe integer
     * overflow!
     */
    protected int primaryHash(int hashCode) {
        /**
         * Your code goes here
         */
    }

    /*
     * The secondary hash function. Remember you need to use "dhP" here!
     * Use the CustomHash implementation in this one
     */
    protected int secondaryHash(int hashCode) {
        /**
         * Your code goes here
         */
    }

    @Override
    public int hashValue(Key key, int iter) {
        int hashCode = Math.abs(CustomHash.customHashCode(key));
        return Math.abs(primaryHash(hashCode) + iter * secondaryHash(hashCode)) % N;
    }

    /*
     * checkAndResize checks whether the current load factor is greater than the
     * specified critical load factor. If it is, the table size should be
     * increased to 2*N and recreate the hash table for the keys (rehashing). Do
     * not forget to re-calculate the hash parameters and do not forget to
     * re-populate the new array!
     */
    @SuppressWarnings("unchecked")
    protected void checkAndResize() {
        /**
         * Your code goes here
         */
    }

    /**
     * The method that finds the location of a Value in the array given a Key 
     * and returns it (use the find(k) method)
     */
    public Value get(Key k) {
        Value result = null;
        
        /**
         * Your code goes here
         */
        
        return result;
    }

    /*
     * The method that puts a KeyValue pair (HashEntry) into the HashMap
     * 
     * To properly implement it: 
     * 1. You need to make sure there are available places to put it in the array
     * 2. Get the intended place to put it using hashing
     * 3. Do collision resolution with double hashing when necessary
     * 4. If there is a collision, return the value that is stored in the last
     *        intended place where you wanted to place the new pair before collision
     */
    public Value put(Key k, Value v) {
        Value oldValue = null;
        
        /**
         * Your code goes here
         */
        
        return oldValue;
    }
    /*
     * The method that finds an index where the KeyValue pair is stored given a Key
     * 
     * To properly implement it: 
     * 1. Get the place where it is supposed to be using hashing
     * 2. Since there might be collisions, you need to check whether it is 
     *      in fact the key that is given
     * 3. If it is not, use double hashing and check if the new place holds the 
     *      desired KeyValue pair
     * 4. Repeat steps 2&3 until you find it or you made N repetitions
     * 5. If you made N repetitions and didn't find the value, return -1.
     *       Otherwise, return the location (index) in the array
     */
    public int find(Key k) {
        if (k == null) {
            return -1;
        }
        
        int repeats = 0; 
        int location = -1;
        
        /**
         * Your code goes here
         */

        if (repeats == N || buckets[location] == null) {
            return -1;
        } else {
            return location;
        }
    }

    // This is the only function where you are allowed to use an existing Java
    // data structure!
    public Iterable<Key> keySet() {
        ArrayList<Key> keyset = new ArrayList<Key>();
        
        // collect all the keys to the keyset and return them 
        
        return keyset;
    }

    @Override
    protected void updateHashParams() {
        super.updateHashParams();
        dhP = nextPrime(N / 2);
    }

}
