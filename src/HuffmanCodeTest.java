import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

class HuffmanCodeTest {
    // Helper class to generate data for multiple tests
    @Ignore
    private File createRandomFile(boolean flag) {
        Random ran = new Random();
        int stringLength = ran.nextInt(800000) + 70000;
        StringBuilder sb = new StringBuilder();
        if (flag) for (int i = 0; i < stringLength; i++) sb.append((char) (ran.nextInt(127)));
        for (int i = 0; i < stringLength; i++) sb.append((char) (ran.nextInt(26) + 97));
        File f = new File("exampleText.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(sb.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            fail(e.getMessage());
        }
        return f;
    }

    @Ignore
    void print(String[] strings){
        for (int i = 0; i < strings.length-1; i++) {
            if(strings[i] == null) continue;
            if(i < 32 || i == 127) System.out.println(i + " " + strings[i]);
            else System.out.println((char) i + " " + strings[i]);
        }
    }

    @Disabled
    void charFrequencyTest() {
        File f = createRandomFile(true);
        HuffmanCode hf = new HuffmanCode(f.getName());
        int[] arr = hf.getCf();
        for (int i = 0; i < arr.length; i++) System.out.println("Char " + i + " was found " + arr[i] + " times.");
    }

    @Disabled
    void priorityTest() {
        File f = createRandomFile(false);
        HuffmanCode hf = new HuffmanCode(f.getName());
        BinaryTree<Character> tree = hf.getTree();
        String s = tree.postOrderString();
        System.out.println(s);
    }

    @Disabled
    void testPaths() {
        HuffmanCode hf = new HuffmanCode("exampleText.txt");
        print(hf.getPaths());
    }

    @Test
    void fullTest() {
        System.out.println();
        File f = createRandomFile(false);
        HuffmanCode hf = new HuffmanCode(f.getName());
        print(hf.getPaths());
        testHuffmanAccuracy(hf);
        f = createRandomFile(true);
        hf = new HuffmanCode(f.getName());
        print(hf.getPaths());
        testHuffmanAccuracy(hf);
    }

    private void testHuffmanAccuracy(HuffmanCode hf){
        String[] strings = hf.getPaths();
        int[] cf = hf.getCf();
        for(int i=0; i<strings.length; i++){
            int max = findMax(cf);
        }
    }

    private int findMax(int[] arr) {
        // returns the index
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[max]) max = i;
        }
        return max;
    }
}