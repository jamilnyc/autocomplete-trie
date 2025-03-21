import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Autocomplete implements IAutocomplete {
    Node root;
    int k;

    public Autocomplete() {
        root = new Node();
    }

    @Override
    public void addWord(String word, long weight) {
        if (!isValidWord(word)) {
            return;
        }

        word = word.toLowerCase();
        Node currentNode = root;
        root.incrementPrefixes();
        String partialWord = "";
        for (int i = 0; i < word.length(); i++) {
            // This gets the index into the Node's references array
            int currentCharacter = word.charAt(i) - 'a';
            partialWord += (char) currentCharacter;

            if (currentNode.getReferences()[currentCharacter] == null) {
                currentNode.getReferences()[currentCharacter] = new Node();
            }

            // If this is the last character of the word, that means we have a complete word
            if (currentCharacter == word.length() - 1) {
                Node n = new Node(word, weight);
                n.setWords(1);
                currentNode.getReferences()[currentCharacter] = n;
            }

            currentNode.getReferences()[currentCharacter].incrementPrefixes();
            currentNode = currentNode.getReferences()[currentCharacter];
        }
    }

    @Override
    public Node buildTrie(String filename, int k) {
        // Read from the file, one line at a time
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line;
        try {
            while((line = br.readLine()) != null) {
                line = line.toLowerCase().trim();
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    continue;
                }

                addWord(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return root;
    }

    @Override
    public Node getSubTrie(String prefix) {
        Node currentNode = root;

        for(int i = 0; i < prefix.length(); i++) {
            int currentCharacter = prefix.charAt(i) - 'a';
            currentNode = currentNode.getReferences()[currentCharacter];
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    @Override
    public int countPrefixes(String prefix) {
        Node subTrie = getSubTrie(prefix);
        if (subTrie == null) {
            return 0;
        }
        return subTrie.getPrefixes();
    }

    @Override
    public List<ITerm> getSuggestions(String prefix) {
        return null;
    }

    public static boolean isValidWord(String word) {
        // String is one or more alphabetic characters
        return word.matches("[a-zA-Z]+");
    }


}
