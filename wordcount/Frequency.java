// Author: Tony Jin
// Date: 2/25/14
// 
// Description: Runs through the article at http://en.wikipedia.org/wiki/Machine_learning 
//              and returns the 10 most common words.
//
// Dependencies: java.util.*, java.io.*
//
// Compile: javac Frequency.java
// Run:     java Frequency.java
//

// Pardon the pauses, I'm not sure how to compile on here, so I'll work in parallel locally

import java.util.*;
import java.net.*;
import java.util.regex.*;


public class Frequency {
    public static void main(String[] args) throws Exception{
        // init scan
        Scanner in = new Scanner(new URL("http://en.wikipedia.org/wiki/Machine_learning").openStream());
        Map<String, Integer> map = new HashMap<String, Integer>();
        Pattern pattern = Pattern.compile(".*\\W+.*");
        
        // for each word
        while (in.hasNext()) {
            String word = in.next().toLowerCase();
            // need to check for non alphanumeric characters, continue
            
            Matcher match = pattern.matcher(word);
            if(match.find()) 
                continue;
            
            if(map.containsKey(word)) {
                Integer count = map.get(word);
                map.put(word, count + 1);
            }
            else
                map.put(word, 1);
        }
        
        
        // find top 10
        
        int low = 0;
        int counter = 0;
        // array is unoptimally efficient, adopted for time purposes
        int [] vals = new int[10];
        String [] keys = new String[10];
        int temp;
        String key;
        
        for (String str : map.keySet()) {
            int value = map.get(str);
            
            if (value > low) {
                int i;
                
                // find index to insert value
                for (i = vals.length - 1; i > 0; --i) 
                    if (value < vals[i])
                        break;
                
                        
                // shift array indexes over 
                for (int j = i; j < vals.length; j++) {
                    // do integers
                    temp = vals[j];     // store val of index
                    vals[j] = value;    // replace val with previous value
                    value = temp;       // update "previous value"
                    
                    // do strings
                    key = keys[j];      // store val of index
                    keys[j] = str;      // replace val with previous value
                    str = key;          // update "previous value"
                }
                low = vals[9];         // set new low
            }
        }
        
        for (int i = 0; i < vals.length; i++) 
            System.out.println(keys[i] + "\t" + vals[i]);
    }   
}