package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame {
    MyLinkedList<Double> list;

    private JTable mainTable;
    private JButton addFirstButton;
    private JTextField textField1;
    private JButton removeLastButton;
    private JButton addLastButton;
    private JButton removeButton;
    private JButton removeAllButton;
    private JButton removeFirstButton;
    private JButton sortButton;
    private JButton openButton;
    private JButton saveButton;
    private JPanel mainPanel;

    public MainFrame() {
        super("Task 2");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        list = new MyLinkedList<>();
        JTableUtils.initJTableForArray(mainTable, 70, true, true, false, false);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("File(.txt)", "txt");
                fileOpen.addChoosableFileFilter(filter);
                int ret = fileOpen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileOpen.getSelectedFile();
                    String nameFile = file.getPath();
                    try {
                        list = WorkWithFile.readFromFileLinkedList(nameFile);
                        JTableUtils.writeMyLinkedListToJTable(mainTable, list);
                    } catch (NullPointerException | FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Ошибка загрузки файла!", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        addFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    list.addFirst(Double.parseDouble(textField1.getText()));
                    JTableUtils.writeMyLinkedListToJTable(mainTable, list);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Введены не правтльные данные!", "Output", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        addLastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    list.addLast(Double.parseDouble(textField1.getText()));
                    JTableUtils.writeMyLinkedListToJTable(mainTable, list);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Введены не правтльные данные!", "Output", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    list.remove(Double.parseDouble(textField1.getText()));
                    JTableUtils.writeMyLinkedListToJTable(mainTable, list);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Введены не правтльные данные!", "Output", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                list.removeAll();
                JTableUtils.writeMyLinkedListToJTable(mainTable, list);
            }
        });
        removeFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                list.removeFirst();
                JTableUtils.writeMyLinkedListToJTable(mainTable, list);
            }
        });
        removeLastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                list.removeLast();
                JTableUtils.writeMyLinkedListToJTable(mainTable, list);
            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                list.func1();
                JTableUtils.writeMyLinkedListToJTable(mainTable, list);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooserSave = new JFileChooser();
                if (fileChooserSave.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        WorkWithFile.saveLinkedListFromFile(list, file);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ошибка! Файл не сохранён!", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
    }
}
