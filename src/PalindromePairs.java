import java.util.*;

/**
 * LeetCode #366
 *
 * O(4nlgn) = O(nlgn)
 *
 * 2 runs - first, insert it into the Trie.  THEN, on the second walkthrough, doing it in reverse and
 * taking into account first-letter cycles (and zero-length cycles!), attempt to find matches as stored.
 *
 * Alright, this is close, but spaghetti.  Draw it out on a whiteboard, remembering some conditions:
 * - Empty String
 * - Execute While Loop for extra runoff
 * - Keep track of which nodes go where and record it.
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        int length = words.length;
        Set<List<Integer>> retVal = new HashSet<>();
        if(length < 2) { return new ArrayList<>(); }

        //The map grabs all short -> long concatenations
        Map<String, List<Integer>> matches = new HashMap<>();
        //The Trie grabs all long -> short concatenations
        Trie trie = new Trie();

        String reversed;
        for(int i = 0 ; i < length; i++) {
            //Create Trie
            trie.insert(reverse(words[i]), i);

            //Create Map
            reversed = reverse(words[i]);
            if(matches.get(reversed) == null) {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                matches.put(reversed, temp);
            } else {
                matches.get(reversed).add(i);
            }

            for(int j = 0; j < reversed.length(); j++) {
                if(isPalindrome(reversed.substring(j))) {
                    String head = reversed.substring(0, j);
                    if(matches.get(head) == null) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(i);
                        matches.put(head, temp);
                    } else {
                        matches.get(head).add(i);
                    }
                }
            }
        }

        //Phase 2 - find matches
        for(int i = 0; i < length; i++) {
            String word = words[i];
            List<Integer> possibleMatches = matches.get(word);
            //Short - use Map
            if(possibleMatches != null) {
                for (Integer match : possibleMatches) {
                    if(i != match) {
                        retVal.add(Arrays.asList(i, match));
                    }
                }
            }

            //Long - use Trie
            if(words[i].length() == 0) {
                for(int j = 0; j < length; j++) {
                    if(i != j && isPalindrome(words[j])) {
                        retVal.add(Arrays.asList(i, j));
                    }
                }
            } else {
                for (Integer match : trie.findMatches(words[i])) {
                    if (i != match) {
                        retVal.add(Arrays.asList(i, match));
                    }
                }
            }
        }

        //Finally!
        return new ArrayList<>(retVal);
    }

    private String reverse(String word) {
        StringBuilder reverse = new StringBuilder();

        for(int i = word.length() - 1; i >= 0; i--)
        {
            reverse.append(word.charAt(i));
        }

        return reverse.toString();
    }

    private boolean isPalindrome(String str) {
        int n = str.length();
        for( int i = 0; i < n/2; i++ )
            if (str.charAt(i) != str.charAt(n-i-1)) return false;
        return true;
    }

    private class TrieNode {
        TrieNode(Character value) {
            this.value = value;
            this.children = new HashMap<>();
            this.matchingWords = new HashMap<>();
        }

        //Character in the Trie
        Character value;

        //Characters continuing in the word at hand
        Map<Character, TrieNode> children;

        //Words that would match if this is the final place in the word
        Map<String, Integer> matchingWords;
    }

    private class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode(null);
        }

        public void insert(String word, int matchNumber) {
            if(word.length() == 0) {
                root.matchingWords.put(word, matchNumber);
                return;
            }
            insertHelper(word, 0, root, matchNumber);
        }

        private void insertHelper(String word, int index, TrieNode currentNode, int matchNumber) {
            if(index >= word.length()) {
                currentNode.matchingWords.put(word, matchNumber);
                return;
            }

            //Get current character
            Character c = word.charAt(index);

            //If this node does not already contain the current character, add it
            if(!currentNode.children.containsKey(c)) {
                currentNode.children.put(c, new TrieNode(c));
            }


            insertHelper(word, index + 1, currentNode.children.get(c), matchNumber);
        }

        public List<Integer> findMatches(String word) {
            List<Integer> matches = new LinkedList<>();

            //Recursively find the rest
            if(word.length() > 0) {
                if(!root.matchingWords.isEmpty()) {
                    if(isPalindrome(word)) {
                        matches.addAll(root.matchingWords.values());
                    }
                }
                if(root.children.containsKey(word.charAt(0))) {
                    findMatchesHelper(word, root.children.get(word.charAt(0)), 1, matches);
                }
            }

            return matches;
        }

        private void findMatchesHelper(String word, TrieNode currentNode, int index, List<Integer> matches) {
            for(Map.Entry<String, Integer> currentWord : currentNode.matchingWords.entrySet()) {
                if(isPalindrome(word.concat(reverse(currentWord.getKey())))) {
                    matches.add(currentWord.getValue());
                }
            }

            if(index >= word.length()) {
                return;
            }

            //Fetch the current character
            Character currentChar = word.charAt(index);
            if(!currentNode.children.containsKey(currentChar)) {
                currentNode.children.put(currentChar, new TrieNode(currentChar));
            }

            findMatchesHelper(word, currentNode.children.get(currentChar), index + 1, matches);
        }
    }

    public static void main(String[] args) {
        PalindromePairs palindromePairs = new PalindromePairs();
//        String[] words = new String[] {"bat", "tab", "cat"};
//        System.out.println(palindromePairs.palindromePairs(words));
//
//        String[] words2 = new String[] {"abcd", "dcba", "lls", "s", "sssll"};
//        System.out.println(palindromePairs.palindromePairs(words2));
////
//        String[] words3 = new String[] {"a", ""};
//        System.out.println(palindromePairs.palindromePairs(words3));
//
//        String[] words4 = new String[] {"a","abc","aba",""};
//        System.out.println(palindromePairs.palindromePairs(words4));

        String[] words5 = new String[] {"a","b","c","ab","ac","aa"};
        System.out.println(palindromePairs.palindromePairs(words5));
//
        String[] words6 = new String[] {"a", "aa", "aaa"};
        System.out.println(palindromePairs.palindromePairs(words6));

        String[] words7 = new String[] {"ab","ba","abc","cba"};
        System.out.println(palindromePairs.palindromePairs(words7));

//        String[] words8 = new String[] {"abaabaa", "aaba"};
//        System.out.println(palindromePairs.palindromePairs(words8));
    }
}


