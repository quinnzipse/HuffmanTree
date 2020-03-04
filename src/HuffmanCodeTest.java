import jdk.jfr.Enabled;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

class HuffmanCodeTest {
    // Helper class to generate data for multiple tests
    private File createRandomFile(int flag) {
        Random ran = new Random();
        int stringLength;
        StringBuilder sb = new StringBuilder();
        switch(flag){
            case 0:
                stringLength = ran.nextInt(75) + 75;
                for (int i = 0; i < stringLength; i++) sb.append((char) (ran.nextInt(26) + 97));
                break;
            case 1:
                stringLength = ran.nextInt(800000) + 70000;
                for (int i = 0; i < stringLength; i++) sb.append((char) (ran.nextInt(26) + 97));
                break;
            case 2:
                stringLength = ran.nextInt(75) + 75;
                for (int i = 0; i < stringLength; i++) sb.append((char) (ran.nextInt(127)));
                break;
            case 3:
                stringLength = ran.nextInt(800000) + 70000;
                for (int i = 0; i < stringLength; i++) sb.append((char) (ran.nextInt(127)));
                break;
            default:
                return createRandomFile(3);
        }
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

    void print(String[] strings){
        for (int i = 0; i < strings.length; i++) {
            if(strings[i] == null) continue;
            if(i < 33 || i == 127) System.out.println(i + " " + strings[i]);
            else System.out.println((char) i + " " + strings[i]);
        }
    }

    @Disabled
    void charFrequencyTest() {
        File f = createRandomFile(4);
        HuffmanCode hf = new HuffmanCode(f.getName());
        int[] arr = hf.getCf();
        for (int i = 0; i < arr.length; i++) System.out.println("Char " + i + " was found " + arr[i] + " times.");
    }

    @Disabled
    void priorityTest() {
        File f = createRandomFile(4);
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
        System.out.println("Test 1: Small (a-z)(~100 characters)");
        File f = createRandomFile(0);
        HuffmanCode hf = new HuffmanCode(f.getName());
        print(hf.getPaths());
//        testHuffmanAccuracy(hf);

        System.out.println("\nTest 2: Medium (a-z)(Over 7000 characters)");
        f = createRandomFile(1);
        hf = new HuffmanCode(f.getName());
        print(hf.getPaths());
//        testHuffmanAccuracy(hf);

        System.out.println("\nTest 3: Large (0-127)(~100 characters)");
        f = createRandomFile(2);
        hf = new HuffmanCode(f.getName());
        print(hf.getPaths());
//        testHuffmanAccuracy(hf);

        System.out.println("\nTest 3: Extra Large (0-127)(Over 7000 characters)");
        f = createRandomFile(3);
        hf = new HuffmanCode(f.getName());
        print(hf.getPaths());
//        testHuffmanAccuracy(hf);

        System.out.println("\nTest 5: Manual (manualTest.txt)");
        System.out.println("The q's and c's should have the smallest encoding.");
        hf = new HuffmanCode("test.txt");
        print(hf.getPaths());
        testHuffmanAccuracy(hf);
    }

    private void testHuffmanAccuracy(HuffmanCode hf){
        String[] strings = hf.getPaths();
        int[] cf = hf.getCf(), stringLengths = new int[strings.length];
        for(int i=0; i<strings.length; i++) {
            if (strings[i] == null){
                stringLengths[i] = Integer.MAX_VALUE;
                continue;
            }
            stringLengths[i] = strings[i].length();
        }
        int count = 0, counter = 0;
        for(int i=0; i<strings.length; i++){
            if(strings[i] == null) continue;
            counter ++;
            int max = findMax(cf), min = findMin(stringLengths);
//            System.out.print("Max: " + max + " Min: " + min);
            if(stringLengths[max] == stringLengths[min]){
//                System.out.println(" - TRUE");
                count++;
            }
//            else System.out.println();
            cf[max] = -1;
            stringLengths[min] = Integer.MAX_VALUE;
        }
        if(count < counter/1.7) fail("Not a valid Huffman Encoding");
    }

    private int findMin(int[] arr) {
        // returns the index
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[min]) min = i;
        }
        return min;
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