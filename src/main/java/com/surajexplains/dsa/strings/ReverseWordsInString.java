package com.surajexplains.dsa.strings;

public class ReverseWordsInString {

    /*

    Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
     */
    public String reverseWords(String s) {

        StringBuilder result = new StringBuilder();
        int endIndex = 0;
        int startIndex =0;
        for(int i = s.length() - 1; i>0;i--){

            endIndex = s.length() + 1;
            startIndex = 0;
            while (i>= 0 && s.charAt(i)==' '){
                i--;
            }

            endIndex = i;
            while(i>=0 && s.charAt(i) != ' '){
                i--;
            }
            startIndex = i+1;

            String word = s.substring(startIndex, endIndex+1);
            if (result.isEmpty()){
                result.append(word);
            }else {
                result.append(" ").append(word);
            }

        }
        return result.toString();
    }
}
