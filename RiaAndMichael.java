import java.io.*;
import java.util.*;
public class RiaAndMichael
{
    static Scanner scan = new Scanner (System.in);
    public static void main (String[] args) throws Exception
    {
        System.out.println("Welcome, user!");
        boolean yes = true;
        while (yes){
            System.out.println("Type 0 to change the word list. Type anything else to keep it as the English word list.");
            String g = scan.nextLine();

            ArrayList<String> newList = new ArrayList<String>();
            if (g.equals("0"))
            {
                System.out.println("Please type in the file's full name (without quotations).");
                String newFile = scan.nextLine();
                File file1 = new File(newFile);
                Scanner sc1 = new Scanner (file1);
                while (sc1.hasNext())
                {
                    newList.add(sc1.nextLine());
                }
            }

            File file = new File ("englishwordlist_randomized.txt");
            Scanner sc = new Scanner (file);
            ArrayList<String> list = new ArrayList<String>();
            while (sc.hasNext())
            {
                list.add(sc.nextLine());
            }

            System.out.println("Type 1 to access All Vowels, 2 for Most Letters, 3 for Murica, 4 for Backwards,");
            System.out.println("5 for Odd/Even, 6 for Crossword Solver, 7 for Minus One, 8 for Plus One,");
            System.out.println("9 for Anagram, or 10 for Here or There! If you wish to exit, type in 25.");
            String f = scan.nextLine();
            while (!(f.equals("1")) && !(f.equals("2")) && !(f.equals("3")) && !(f.equals("4")) && !(f.equals("5"))
            && !(f.equals("6")) &&!(f.equals("7"))&&!(f.equals("8"))&&!(f.equals("9"))&&!(f.equals("10"))&&
            !(f.equals("25")))
            {
                f = scan.nextLine();
            }
            if (f.equals("1"))
                if (g.equals("0"))
                {
                    allVowels(newList);
                }
                else
                    allVowels(list);
            else if (f.equals("2"))
                if (g.equals("0"))
                {
                    mostLetters(newList);
                }
                else
                    mostLetters(list);
            else if (f.equals("3"))
                if (g.equals("0"))
                {
                    murica(newList);
                }
                else
                    murica(list);    
            else if (f.equals("4"))
                if (g.equals("0"))
                {
                    backwards(newList);
                }
                else
                    backwards(list);  
            else if (f.equals("5"))
                if (g.equals("0"))
                {
                    oddEven(newList);
                }
                else
                    oddEven(list);
            else if (f.equals("6"))
            {
                System.out.println("Please type in a word that you would like to use in Crossword Solver.");
                String w = scan.nextLine();
                if (g.equals("0"))
                {
                    crossword(newList, w);
                }
                else
                    crossword(list, w);}
            else if (f.equals("7"))
                if (g.equals("0"))
                {
                    minusOne(newList);
                }
                else
                    minusOne(list);
            else if (f.equals("8"))
                if (g.equals("0"))
                {
                    plusOne(newList);
                }
                else
                    plusOne(list);
            else if (f.equals("9"))
                if (g.equals("0"))
                {
                    anagrams(newList);
                }
                else
                    anagrams(list);
            else if (f.equals("10"))
            {
                System.out.println("Please type in a letter that you would like to use in Here or There.");
                char c = scan.next().charAt(0);
                System.out.println("Please type in an integer (position k).");
                int e = scan.nextInt();
                System.out.println("Please type in another integer (position n) larger than the previous.");
                int r = scan.nextInt();
                if (g.equals("0"))
                {
                    hereOrThere(newList, c, e, r);
                }
                else
                    hereOrThere(list, c, e, r);
            }
            else if(f.equals("25"))
            {
                System.exit(0);
            }

            System.out.println("Press Enter to return to the main menu.");
            scan.nextLine();
        }
    }

    private static void plusOne (ArrayList<String> wordArr)
    {
        //O(26kn) because for each letter(k) it adds, it goes through the alphabet (size 26) and for each word goes
        //through the word list which is (n) to check if it's a word
        System.out.println("Please input a word and the system will look for all words that exist when an additional" 
        +" letter is added");
        String in = scan.nextLine();
        ArrayList<Character> chars = new ArrayList<Character>();
        char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char temp;
        String word;
        for (int i = 0; i < in.length(); i++)
        {
            chars.add(in.charAt(i));
        }
        for (int j = 0; j < chars.size()+1; j++)
        {
            for (int k = 0; k < 25; k++)
            {
                chars.add(j, alpha[k]);
                word = charToString(chars);
                for (int l = 0; l <wordArr.size(); l++)
                {
                    if (word.equals(wordArr.get(l)))
                    {
                        System.out.println(word);
                    }
                }
                chars.remove(j);
            }
        }
    }

    private static void minusOne (ArrayList<String> wordArr)
    {
        //O(k^2 + kn) because for each word the program creates a test word
        // to see if the result is a word(k^2). the program then checks this
        // with the words in the list, it checks the list k times, making it
        // (kn) therefore the total is O(k^2 + kn).
        System.out.println("Please input a word and the system will look for all words that exist when a letter " 
        +"is removed");
        char temp;
        String word = scan.nextLine();
        ArrayList<Character> wordLetters = letters(word);
        String testWord = "";
        ArrayList<String> printLists = new ArrayList<String>();
        for (int i = 0; i < wordLetters.size(); i++)
        {
            temp = wordLetters.get(i);
            wordLetters.remove(i);
            for (int j = 0; j < wordLetters.size(); j++)
            {
                testWord += wordLetters.get(j);
            }
            for (int j = 0; j < wordArr.size(); j++)
            {
                if (testWord.equals(wordArr.get(j)) && !printLists.contains(testWord))
                {
                    printLists.add(testWord);
                    System.out.println(testWord);
                }
            }
            testWord = "";
            wordLetters.add(i, temp);
        }
    }

    private static ArrayList<Character> letters (String str)
    {
        // helper method for plusOne
        // Big Oh: O(k) because it goes through the for loop once for each letter at worst
        ArrayList<Character> chars = new ArrayList<Character>();
        for (int j = 0; j < str.length(); j++)
        {
            chars.add(str.charAt(j));
        }
        return chars;
    }

    private static String charToString(ArrayList<Character> chars)
    {
        // helper method for plusOne
        // Big Oh: O(k) because it goes through the for loop once for each letter at worst
        String str = "";
        for (int i = 0; i < chars.size(); i++)
        {
            str += chars.get(i);
        }
        return str;
    }

    private static void allVowels (ArrayList<String> wordArr)
    {
        //O(n(lg(n)) + kn^2) because for each word it checks all when the vowels are in the same postion (k) but different
        // and the rest of the word is the same and then compares each possible iteration (n^2) of the different vowel
        // positions with the word list (sorted (nlg(n))) to check if it is there.
        System.out.println("Be patient with this one");
        String[] wordSrt = new String[wordArr.size()];
        for( int i = 0; i < wordArr.size(); i++)
        {
            wordSrt[i] = wordArr.get(i);
        }
        wordSrt = mergeStr(wordSrt);
        ArrayList<String> printLists = new ArrayList<String>();
        ArrayList<String> print = new ArrayList<String>();
        int counter = 0;
        for (int i = 0; i < wordSrt.length; i++)
        {
            for(int j = 0; j < wordSrt.length; j++)
            {
                if (EqualsIgnoreVowels(wordSrt[i], wordSrt[j]) && !wordSrt[i].equals(wordSrt[j]))
                {
                    if (!onList(wordSrt[i], printLists))
                    {
                        counter++;
                        printLists.add(wordSrt[i]);
                    }
                    if (!onList(wordSrt[j], printLists))
                    {
                        counter++;
                        printLists.add(wordSrt[j]);
                    }
                }
            }
            if (counter == 5)
            {
                for (int j = printLists.size() - 5; j < printLists.size(); j++)
                {
                    print.add(printLists.get(j));
                }
            }
            if (print.size() == 5)
            {
                System.out.println(print);
            }
            print.clear();
            counter = 0;
        }
    }

    private static boolean onList(String str,ArrayList<String> list)
    {
        // helper method for allVowels
        // Big Oh: O(n) because it goes through the for loop once for each word at worst
        for (int i = 0; i < list.size(); i++)
        {
            if (str.equals(list.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean EqualsIgnoreVowels(String a, String b)
    {
        // helper method for allVowels
        // Big Oh: O(k) because it goes through the for loop once for each letter at worst
        char[] vowels = "aeiou".toCharArray();
        boolean equal = true;
        if(a.length() == b.length())
        {
            for(int i = 0; i < a.length() && equal; i++)
            {
                if ((a.charAt(i) != "a".charAt(0) && a.charAt(i) != "e".charAt(0)
                    && a.charAt(i) != "i".charAt(0) && a.charAt(i) != "o".charAt(0)
                    && a.charAt(i) != "u".charAt(0)) || (b.charAt(i) != "a".charAt(0)
                    && b.charAt(i) != "e".charAt(0) && b.charAt(i) != "i".charAt(0)
                    && b.charAt(i) != "o".charAt(0) && b.charAt(i) != "u".charAt(0)))
                {
                    if(a.charAt(i) == b.charAt(i))
                    {
                        equal = true;
                    }
                    else
                    {
                        equal = false;
                    }
                }
            }
        }
        else
        {
            return false;
        }
        return equal;
    }

    private static void anagrams(ArrayList<String> wordArr)
    {
        System.out.println("Please input a word and the system will look for all words that are anagrams");
        //big oh notation: O(n(k lg(k))) because the sorting of each word happens using
        // mergesort(klgk) and this happens with each word on the list (n), the result
        // is O(n(klg(k))).
        String in = scan.nextLine();
        char[] str1 = in.toCharArray();
        char[] str1Sort = mergeChar(str1);
        for (int i = 0; i < wordArr.size(); i++)
        {
            char[] str2 = wordArr.get(i).toCharArray();
            char[] str2Sort = mergeChar(str2);
            if (Arrays.equals(str1Sort,str2Sort))
            {
                System.out.println(str2);
            }
        }
    }

    private static void backwards(ArrayList<String> wordArr)
    {
        // Big Oh: O(klg(n) + nlg(n)) because the word array is sorted (nlg(n)) and that
        // for each word a binary search is done (lg(n)) and for each word a O(n)
        // process is done to get the reverse
        // O(k*lg(n) + nlg(n)) = O(klg(n) + nlg(n))
        String[] wordSrt = new String[wordArr.size()];
        ArrayList<String> printList = new ArrayList<String>();
        for( int i = 0; i < wordArr.size(); i++)
        {
            wordSrt[i] = wordArr.get(i);
        }
        wordSrt = mergeStr(wordSrt);
        String current = "";
        String reverseCurrent = "";
        for (int i = 0; i < wordArr.size(); i++)
        {
            current = wordArr.get(i);
            reverseCurrent = reverse(current);
            if (binarySearch(wordSrt, reverseCurrent) != -1 && !current.equals(reverseCurrent))
            {
                printList.add(current);
            }
        }
        String[] printArray = new String[printList.size()];
        for( int i = 0; i < printList.size(); i++)
        {
            printArray[i] = printList.get(i);
        }
        printArray = mergeStr(printArray);
        for( int i = 0; i < printArray.length; i++)
        {
            System.out.println(printArray[i]);
        }
    }

    private static String reverse(String in)
    {
        //helper method for backwards
        // Big Oh: O(k) because it goes through the for loop once for each letter at worst
        String reverse = "";
        for (int i = in.length() - 1; i >= 0; i--)
        {
            reverse = reverse + in.charAt(i);
        }
        return reverse;
    }

    private static void hereOrThere(ArrayList<String> list, char c, int k, int n)
    {
        // big Oh: O(n) - goes through the ArrayList for the length of list and counts the number
        // of times char c appears at either position k or n, in a word of at least length n, printing the
        // position that has the higher count.
        int countK = 0;
        int countN = 0;
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).length() >= n)
            {
                if (list.get(i).charAt(k-1)==c)
                {
                    countK++;
                }
                if(list.get(i).charAt(n-1)==c)
                {
                    countN++;
                }
            }
        }
        if (countK>countN)
            System.out.println("This letter appears more times at position "+k);
        else if (countN>countK)
            System.out.println("This letter appears more times at position "+n);
        else
            System.out.println("This letter appears the same number of times at positions k and n.");
    }

    private static void mostLetters(ArrayList<String> list)
    {
        // big Oh: O(26(n(k))) - for each letter in the alphabet (26), go through the whole list (n) and then
        // go through the letters of each word (k) and count the number of occurences of the letter in that word.
        // The index of the word with the highest occurences of the letter is saved in the variable index, and their
        // count is recorded in the variable newC. As any new word gets a higher count of that letter than the
        // previous word, newC becomes that new count and index is resaved as the new index. This continues for
        // every letter and each word is then saved into an array and printed in the order of the alphabet (word
        // with most a's is first, word with most b's is second, etc.).
        String[] done = new String[26];
        char[] lets = new char[26];
        lets[0]= 'a';
        lets[1] = 'b';
        lets[2] = 'c';
        lets[3] = 'd';
        lets[4] = 'e';
        lets[5] = 'f';
        lets[6] = 'g';
        lets[7] = 'h';
        lets[8] = 'i';
        lets[9] = 'j';
        lets[10] = 'k';
        lets[11] = 'l';
        lets[12] = 'm';
        lets[13] = 'n';
        lets[14] = 'o';
        lets[15] = 'p';
        lets[16] = 'q';
        lets[17] = 'r';
        lets[18] = 's';
        lets[19] = 't';
        lets[20] = 'u';
        lets[21] = 'v';
        lets[22] = 'w';
        lets[23] = 'x';
        lets[24] = 'y';
        lets[25] = 'z';
        for (int gh = 0; gh < 26; gh++)
        {
            int newC = 0;
            int index = 0;
            for (int i = 0; i <list.size(); i++)
            {
                int count = 0;
                char[] ch = list.get(i).toCharArray();
                for (int jk = 0; jk < ch.length; jk++)
                {
                    if (ch[jk]==lets[gh])
                    {
                        count++;
                    }
                }
                if (count>newC)
                {   newC=count;
                    index = i;}
                else if (count==newC)
                {
                    if (list.get(i).length()<list.get(index).length()) //tiebreaker #1: takes smaller word
                        index = i;
                    else if (list.get(i).length()==list.get(index).length()) //tiebreaker #2: takes word
                    // that does not start with the letter
                    {
                        char cc = list.get(i).charAt(0);
                        if (cc!=lets[gh])
                            index = i;
                    }
                }
            }
            done[gh] = list.get(index);
        }
        // prints in the order of the word with the most a's first, and then the word with the most b's second, etc.
        // (not alphabetically)
        for (int qq =0; qq<done.length; qq++)
        {
            System.out.println(done[qq]);
        }
    }

    private static void oddEven(ArrayList<String> list) //does not work?
    {
        // big Oh: O(n lg n) for the merge sort, and then for the rest of the program it's O(n(k(lg n))) - after
        // being sorted, for every element in the list, it goes through the word and saves the two words created
        // by the odd and even letters. Then it does a binary search to find either the odd or even word in the
        // list, and if found, prints out the original word.

        String[] listA = new String[list.size()];
        for (int g = 0; g < listA.length; g++)
        {
            listA[g] = list.get(g);
        }
        listA = mergeStr(listA);
        for (int i = 0; i < listA.length; i++)
        {
            if (listA[i].length() == 5)
            {
                String a = listA[i].substring(0, 1);
                String b = listA[i].substring(2, 3);
                String c = listA[i].substring(4, 5);
                String tot = a+b+c;
                if(binarySearch(listA,tot) != -1)
                    System.out.println(listA[i]);
            }
            else if (listA[i].length() > 5)
            {
                if (listA[i].length() %2==0)
                {
                    String odd = "";
                    String even = "";
                    for (int j = 0; j < (listA[i].length())/2; j++)
                    {
                        odd = odd+(listA[i].substring(j*2, j*2+1));
                        even = even + (listA[i].substring(j*2+1, j*2+2));
                    }
                    if (binarySearch(listA, odd)!= -1 || binarySearch(listA, even)!= -1)
                        System.out.println(listA[i]);
                }
                else
                {
                    String odd = "";
                    String even = "";
                    for (int j = 0; j < (listA[i].length())/2+1; j++)
                    {
                        odd = odd+(listA[i].substring(j*2, j*2+1));
                        if (j < (listA[i].length())/2)
                            even = even + (listA[i].substring(j*2+1, j*2+2));
                    }
                    if (binarySearch(listA, odd)!= -1 || binarySearch(listA, even)!= -1)
                        System.out.println(listA[i]);
                }
            }
        }
    }

    private static void murica(ArrayList<String> list)
    {
        // big Oh: O(n(k(j))) - goes through the whole list, and for every even length word it goes through the
        // array of state acronyms to see if each pair of letters in the word is equal to one of the states.
        // If one pair of letters matches a state acronym, then the state-array loop breaks and the next pair
        // of letters in the word are checked. If it does not match, then a count variable increases. Then,
        // after the loop that goes through the whole word, if the total count is less than 50, it means that the
        // word is completely made up of state acronyms.
        String[] states = new String[50];
        states[0] = "al";
        states[1] = "ak";
        states[2] = "az";
        states[3] = "ar";
        states[4] = "ca";
        states[5] = "co";
        states[6] = "ct";
        states[7] = "de";
        states[8] = "fl";
        states[9] = "ga";
        states[10] = "hi";
        states[11] = "id";
        states[12] = "il";
        states[13] = "in";
        states[14] = "ia";
        states[15] = "ks";
        states[16] = "ky";
        states[17] = "la";
        states[18] = "me";
        states[19] = "md";
        states[20] = "ma";
        states[21] = "mi";
        states[22] = "mn";
        states[23] = "ms";
        states[24] = "mo";
        states[25] = "mt";
        states[26] = "ne";
        states[27] = "nv";
        states[28] = "nh";
        states[29] = "nj";
        states[30] = "nm";
        states[31] = "ny";
        states[32] = "nc";
        states[33] = "nd";
        states[34] = "oh";
        states[35] = "ok";
        states[36] = "or";
        states[37] = "pa";
        states[38] = "ri";
        states[39] = "sc";
        states[40] = "sd";
        states[41] = "tn";
        states[42] = "tx";
        states[43] = "ut";
        states[44] = "vt";
        states[45] = "va";
        states[46] = "wa";
        states[47] = "wv";
        states[48] = "wi";
        states[49] = "wy";

        ArrayList<String> arr = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++)
        {
            int x = list.get(i).length();
            int count = 0;
            if (x%2 ==0)
            {
                for (int j = 0; j < (x/2); j++)
                {  
                    String y = list.get(i).substring(j*2, (j*2)+2);
                    for (int k = 0; k < states.length; k++)
                    {
                        if (y.equals(states[k]))
                        {
                            break;
                        }
                        count++;
                    }
                }
                if (count < 50)
                {
                    arr.add(list.get(i));
                }
            }
        }
        String[] arr1 = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++)
        {
            arr1[i] = arr.get(i);
        }
        arr1 = mergeStr(arr1);
        for (int i = 0; i < arr.size(); i++)
        {
            System.out.println(arr1[i]);
        }
    }

    private static void crossword(ArrayList<String> list, String w)
    {
        // big Oh: O(n(k)) - goes through the whole list, and for every word that is the same length as the String
        // parameter, it goes through the letters of the word and for every letter in the parameter that matches
        // a letter in the word in the list, a count variable increases. If, by the end of the loop going through
        // the letters of the word, and the count variable is equal to the number of given letters in the String
        // parameter, then this word gets printed.
        char[] ch = w.toCharArray();
        int qm = 0; //num of q marks in word
        for (int h = 0; h < ch.length; h++)
        {
            if (ch[h] == '?')
                qm++;
        }
        int lets = w.length()-qm;
        for (int i = 0; i < list.size(); i++)
        {
            int count = 0;
            if (list.get(i).length() == w.length())
            {
                for (int j = 0; j < ch.length; j++)
                {
                    if (list.get(i).substring(j, j+1).equals(w.substring(j, j+1)))
                    {
                        count++;
                    }
                }
            }
            if (count == lets)
                System.out.println(list.get(i));
        }
    }

    private static String[] mergeStr (String[] arr)
    {
        // big Oh: O(n lg n) - there are lg n steps of splitting and then merging again, each step is which is O(n)
        // ^for merge sort as a whole (including helper method mergeS)
        if (arr.length < 2) // base case
            return arr;

        String[] left = new String[arr.length/2];
        for (int i = 0; i < left.length; i++)
        {
            left[i] = arr[i];
        }
        String[] right = new String[arr.length - left.length];
        for (int i = 0; i < right.length; i++)
        {
            right[i] = arr[i+left.length];
        }

        left = mergeStr(left);
        right = mergeStr(right);

        String[] blah = mergeS(left, right);
        return blah;
    }

    private static String[] mergeS (String[] arr1, String[] arr2)
    {
        // big Oh for this method included mergeStr^
        String[] done = new String[arr1.length + arr2.length];

        int i = 0;
        int j = 0;
        int pos = 0;
        while (i < arr1.length && j < arr2.length)
        {
            if (arr1[i].compareTo(arr2[j]) < 0)
            {
                done[pos] = arr1[i];
                i++;
            }
            else
            {
                done[pos] = arr2[j];
                j++;
            }
            pos++;
        }

        while(i < arr1.length)
        {
            done[pos] = arr1[i];
            pos++;
            i++;
        }
        while(j < arr2.length)
        {
            done[pos] = arr2[j];
            pos++;
            j++;
        }
        return done;
    }

    private static char[] mergeChar (char[] arr)
    {
        // big Oh: O(n lg n) - same process as mergeStr, just for chars
        if (arr.length < 2) // base case
            return arr;

        char[] left = new char[arr.length/2];
        for (int i = 0; i < left.length; i++)
        {
            left[i] = arr[i];
        }
        char[] right = new char[arr.length - left.length];
        for (int i = 0; i < right.length; i++)
        {
            right[i] = arr[i+left.length];
        }

        left = mergeChar(left);
        right = mergeChar(right);

        char[] blah = mergeS(left, right);
        return blah;
    }

    private static char[] mergeS (char[] arr1, char[] arr2)
    {
        // helper method for mergeChar^
        char[] done = new char[arr1.length + arr2.length];

        int i = 0; // position in array1
        int j = 0; // position in array2
        int pos = 0; // position in done
        while (i < arr1.length && j < arr2.length)
        {
            if (arr1[i] < arr2[j])
            {
                done[pos] = arr1[i];
                i++;
            }
            else
            {
                done[pos] = arr2[j];
                j++;
            }
            pos++;
        }

        while(i < arr1.length)
        {
            done[pos] = arr1[i];
            pos++;
            i++;
        }
        while(j < arr2.length)
        {
            done[pos] = arr2[j];
            pos++;
            j++;
        }
        return done;
    }

    private static int binarySearch(String[] sortedArray, String val)
    {
        // big Oh: O(lg n) - looks at the middle element and compares to the value user is looking for; if the
        // middle element is too big, maxIndex decreases to this element - if element is too small, minIndex
        // increases - if element matches, returns true - stops when minIndex is larger than maxIndex.
        // Returns index that element is found at, and returns -1 if not found.
        int highIndex = sortedArray.length - 1;
        int lowIndex = 0;
        while (highIndex >= lowIndex)
        {
            int index = (highIndex + lowIndex) / 2;
            if (sortedArray[index].equals(val))
                return index;
            else if (sortedArray[index].compareTo(val) < 0)
                lowIndex = index+1;
            else
                highIndex = index-1;    
        }
        return -1;
    }
}