import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
public class Search extends Porter {
    public static ArrayList<File> list = new ArrayList();
    public static Set<String> set = null;
    public static HashMap<String, HashMap<String, Double>> invert_index = new HashMap();
    public static HashMap<String, Double> query = new HashMap();
    public static HashMap<Object, Double> sec = new HashMap();
    public static HashMap<Object, Double> secd = new HashMap();
    public static Porter port = new Porter();
    public static Double id = 1.0;
    public static BufferedReader bufferedReader = null;
    public static FileInputStream inputfilename = null;
    
public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        File f = new File("MapEnglish");
        boolean bool;
        int i;
        bool = f.exists();
        String[] stopwords = {"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
        set = new HashSet(Arrays.asList(stopwords));
        File folder = new File("/media/rajeve/New Volume/IR_FINAL/en.docs.2011/en_BDNews24");
        listFilesForFolder(folder);
        int size = list.size();
        System.out.println("file size : " + size);
        int t = 0;
StringBuffer sr[] = new StringBuffer[list.size()];
        for (int k = 0; k < size; k++) {
    sr[t] = new StringBuffer(list.get(k).getName());
            t++;
           
        }
   try {
          if (bool == false) {
              for (int k = 0; k < size; k++) {
                    System.out.println(" k = " +k+" " +list.get(k).getName());
                     invertedindex(list.get(k));
                }
    System.out.println("Time before Write as aobject : " + (System.currentTimeMillis() - startTime));
                FileOutputStream fos = new FileOutputStream("MapEnglish");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(invert_index);
                oos.close();
        } else {
                FileInputStream fis = new FileInputStream("MapEnglish");
                ObjectInputStream ois = new ObjectInputStream(fis);
                @SuppressWarnings("unchecked")
                HashMap<String, HashMap<String, Double>> map = (HashMap<String, HashMap<String, Double>>) ois.readObject();
                ois.close();
                System.out.println("else ");
                File QueryFile = new File("/media/rajeve/New Volume/IR_FINAL/query.txt");
                int query_no = 126;
                FileReader fileReader = new FileReader(QueryFile);
                BufferedReader bufferReader = new BufferedReader(fileReader);
                PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true); 
                String s;
                while ((s = bufferReader.readLine()) != null) {
                	System.out.println("else "+query_no);
                 if (s.contains("॥") || s.contains(":") || s.contains("|")
                            || s.contains(",") || s.contains("!") || s.contains("?")) {
                        s = s.replace("॥", " ");
                        s = s.replace(":", " ");
                        s = s.replace("|", " ");
                        s = s.replace(",", " ");
                        s = s.replace("!", " ");
                        s = s.replace("?", " ");
                    }
                    StringTokenizer st = new StringTokenizer(s, " ");
                    while (st.hasMoreTokens()) {
                        String str = (st.nextToken());
                        if (set.contains(str)) {
                        } else {
                            String ss = port.stripAffixes(str);
                            if (!query.containsKey(ss)) {
                                query.put(ss, 1.0);
                            } else {
                                Double r = query.get(ss);
                                r++;
                                query.replace(ss, r);
                            }

                        }
                    }
                    Double wei;

                    for (Map.Entry qe : query.entrySet()) {
                
                        if (map.containsKey(qe.getKey())) {

                            wei = (Double) qe.getValue() * ((Double) Math.log(size / map.get(qe.getKey()).size()));
                 
                            sec.put(qe.getKey(), wei);
                        } else {
                            wei = 0.0;

                 
                            sec.put(qe.getKey(), wei);
                        }
                                     }
                    Double store = 0.0;
                    for (Map.Entry se : sec.entrySet()) {
                  
                        store += Math.pow((Double) se.getValue(), 2);
                    }
                    for (Map.Entry se : sec.entrySet()) {
                  
                        Double sss = (Double) se.getValue() / Math.sqrt(store);
                        secd.put(se.getKey(), sss);
                    }
                    ArrayList<Double> score = new ArrayList();
                    for ( i = 0; i < size; i++) {
                        score.add(0.0);
                    }
                     System.out.println();
                    for (int k = 0; k < size; k++) {
                    	 System.out.println("Queryno = "+query_no+ "Docno  = "+ k);
                        for (Map.Entry de : secd.entrySet()) {
                            if (map.containsKey((String) de.getKey())) {
                                if (map.get((String) de.getKey()).containsKey((String) list.get(k).getName())) {
                                    Double ddd = ((Double) map.get(de.getKey()).get((String) list.get(k).getName()) * (Double) Math.log(size / map.get(de.getKey()).size()));
                                    Double val = score.get(k);
                                    val += ((Double) de.getValue() * ddd) / size;
                                    score.add(k, val);
                                }

                            }
                        }
                         }

                   for (i = 0; i < list.size() - 1; i++) {
                        for (int j = 0; j < list.size(); j++) {
                            if (score.get(j) < score.get(i)) {
                                Collections.swap(score, i, j);
                                StringBuffer tes = sr[j];
                                sr[j] = sr[i];
                                sr[i] = tes;
                            }
                        }
                    }                    
                    for (i = 0; i < 20; i++) {
            out.write(query_no + " Q0 " + sr[i] + " " +(i+1)+" "+ score.get(i)+"\n");                         
                    }
                    query_no++;
                }
                out.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Total time taken : " + totalTime);
        }
    }

//this is for storing all path in arraylist
public static void listFilesForFolder(final File folder) {
int k=0;
    for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory())
            {
                listFilesForFolder(fileEntry);
            } else 
            {
                list.add(fileEntry);
System.out.println(" reading");
            }
        }
    }
 //inverted index code here  
    public static void invertedindex(File file) throws Exception {
        inputfilename = new FileInputStream(file);
        bufferedReader = new BufferedReader(new InputStreamReader(inputfilename, "UTF-8"));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
             s = s.replaceAll("\\<.*?>", " ");
            if (s.contains("॥") || s.contains(":") || s.contains("।")
                    || s.contains(",") || s.contains("!") || s.contains("?")) {
                s = s.replace("॥", " ");
                s = s.replace(":", " ");
                s = s.replace("।", " ");
                s = s.replace(",", " ");
                s = s.replace("!", " ");
                s = s.replace("?", " ");
            }
            StringTokenizer st = new StringTokenizer(s, " ");
            while (st.hasMoreTokens()) {
                String str = (st.nextToken());
                if (set.contains(str)) {
                } else {
                    String ss = port.stripAffixes(str);
                    if (!invert_index.containsKey(ss)) {
                        HashMap<String, Double> in = new HashMap<String, Double>();
                        in.put(file.getName(), id);
                        invert_index.put(ss, in);
                    } else if (invert_index.get(ss).containsKey(file.getName())) {
                        Double h = (Double) invert_index.get(ss).get(file.getName());
                        h = h + 1;
                        invert_index.get(ss).replace(file.getName(), h++);
                    } else {
                        HashMap<String, Double> inin = invert_index.get(ss);
                        inin.put(file.getName(), id);
                    }
                }
            }
        }
    }
}
