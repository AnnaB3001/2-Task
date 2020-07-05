package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WorkWithFile {
    public static MyLinkedList readFromFileLinkedList(String nameFile) throws FileNotFoundException {
        FileReader fileReader = new FileReader(nameFile);
        Scanner scanFile = new Scanner(fileReader);
        MyLinkedList<Double> list = new MyLinkedList<>();
        StringTokenizer tokenizer;
        if (scanFile.hasNextLine()) {
            tokenizer = new StringTokenizer(scanFile.nextLine(), " ");
            while (tokenizer.hasMoreTokens()) {
                list.addLast(Double.parseDouble(tokenizer.nextToken()));
            }
        }
        return list;
    }

    public String[] fileToString(String nameFile) throws FileNotFoundException {
        FileReader fileReader = new FileReader(nameFile);
        Scanner scanFile = new Scanner(fileReader);
        List<String> listArray = new ArrayList<>();
        while (scanFile.hasNextLine()) {
            listArray.add(scanFile.nextLine());
        }
        return listArray.toArray(new String[0]);
    }

    public static void saveLinkedListFromFile(MyLinkedList list, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for(Object value: list){
                bw.write(value.toString() + " ");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
