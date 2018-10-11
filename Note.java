package com.example.demo.bean;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Note {
    private static ArrayList<String> list = new ArrayList<String>();
    public Note() {

        try {

            File file = new File("a.json");
            if (file.exists() && file.length() > 0) {
                ObjectMapper objectMapper = new ObjectMapper();
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
                list = objectMapper.readValue(file, javaType);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Note note = new Note();
//        note.addGenesis("封面");
        note.addNote("1转账100");
        note.showList();

    }



    private static void addGenesis(String genesis) {
        if (list.size() > 0) {
            throw new RuntimeException("添加封面需要账本为空");
        }
        list.add(genesis);
        save();
    }

    private static void addNote(String note) {
        if (list.size() < 1) {
            throw new RuntimeException("添加账本需要先添加封面");
        }
        list.add(note);
        save();
    }

    private static void showList() {
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void save() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("a.json"), list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



