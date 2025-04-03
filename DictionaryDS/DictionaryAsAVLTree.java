package DictionaryDS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import AVLTree.AVLTree;
import AVLTree.BTNode;
import Exceptions.WordAlreadyExistsException;
import Exceptions.WordNotFoundException;

public class DictionaryAsAVLTree {
    private AVLTree<String> tree;

    public DictionaryAsAVLTree(String s) {
        tree = new AVLTree<>();
        tree.insertAVL(s);
    }

    public DictionaryAsAVLTree() {
        tree = new AVLTree<>();
    }

    public DictionaryAsAVLTree(File f) throws FileNotFoundException, WordAlreadyExistsException {
        tree = new AVLTree<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                if (!tree.search(word)) {
                    this.addWord(word);
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The file provide not found.");
        } catch (WordAlreadyExistsException e) {
            throw e;
        }
    }

    public void addWord(String s) throws WordAlreadyExistsException {
        if (tree.search(s)) {
            throw new WordAlreadyExistsException("Exception: Word is dublicated");
        }
        tree.insertAVL(s);
    }

    public boolean findWord(String s) {
        return tree.search(s);
    }

    public void deleteWord(String s) throws WordNotFoundException {
        if (!tree.search(s)) {
            throw new WordNotFoundException("Exception: Word not found.");
        }
        tree.deleteAVL(s);
    }

    public String[] findSimilar(String s) {
        ArrayList<String> similarWords = new ArrayList<>();
        if (tree.root == null)
            return similarWords.toArray(new String[similarWords.size()]);
        Queue<BTNode<String>> queue = new LinkedList<>();
        BTNode<String> node = tree.root;
        queue.add(node);
        s = s.trim();
        while (!queue.isEmpty()) {
            node = queue.poll();
            String word = (String) node.data;
            word = word.trim();
            if (isSimilar(word, s)) {
                similarWords.add(word);
            }
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        return similarWords.toArray(new String[similarWords.size()]);
    }

    private boolean isSimilar(String word1, String word2) {
        if (Math.abs(word1.length() - word2.length()) > 1) {
            return false;
        }
        int differences = 0;
        int i = 0;
        int j = 0;
        while (i < word1.length() && j < word2.length()) {
            if (word1.charAt(i) != word2.charAt(j)) {
                differences++;
                if (differences > 1) {
                    return false;
                }
                if (word1.length() > word2.length()) {
                    i++;
                } else if (word1.length() < word2.length()) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }
        return true;
    }

    public void printTree() {
        tree.printTree();
    }

    public void writeFile(File fileName) throws FileNotFoundException {

        try (PrintWriter writer = new PrintWriter(fileName);) {
            if (tree.root == null) {
                writer.write("The dictionary provided is empty!");
                return;
            }
            Queue<BTNode<String>> queue = new LinkedList<BTNode<String>>();
            BTNode<String> node = tree.root;
            queue.add(node);
            while (!queue.isEmpty()) {
                node = queue.poll();
                writer.write((String) node.data + "\n");
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The file provide not found.");
        }
    }
}