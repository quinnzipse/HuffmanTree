import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanCodeTest {
    // Helper class to generate data for multiple tests
    private File createRandomFile(){
        Random ran = new Random();
        int stringLength = ran.nextInt(800000)+70000;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<stringLength; i++) sb.append((char)(ran.nextInt(127)));
        File f = new File("exampleText.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(sb.toString());
            fw.flush();
            fw.close();
        } catch (IOException e){
            fail(e.getMessage());
        }
        return f;
    }
    // Helper class for charFrequency Test
    private <T> void printWithIterator(String s, Iterator<T> iterator){
        System.out.print(s + " ");
        int i=0;
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
            if(i%20 == 0) System.out.println();
            i++;
        }
        System.out.println();
    }

    @Test
    void charFrequencyTest(){
        File f = createRandomFile();
        HuffmanCode hf = new HuffmanCode(f.getName());
        if(cf.size() != uc.size()) fail("For some reason we have an unequal character and frequency list.");
        printWithIterator("The unique characters found are: ", uc.iterator());
        printWithIterator("The character counts are: ", cf.iterator());
    }

    @Test
    void constructTreeTest(){
        File f = createRandomFile();
        HuffmanCode hf = new HuffmanCode(f.getName());
        BinaryTree<Character> tree = hf.getTree();
        tree
    }

    @Test
    void fullTest(){
        fail("Not implemented yet.");
    }
}