package Experiment2.Sys4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main extends JFrame {
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JTextArea predefinedOutputArea;
    private JButton processButton;

    public Main() {
        setTitle("File Processor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        inputTextArea = new JTextArea();
        inputTextArea.setEditable(false);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);


        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);


        predefinedOutputArea = new JTextArea();
        predefinedOutputArea.setEditable(false);
        JScrollPane predefinedOutputScrollPane = new JScrollPane(predefinedOutputArea);

        inputTextArea.setRows(10);
        outputTextArea.setRows(10);
        predefinedOutputArea.setColumns(50);
        outputTextArea.setColumns(20);

        processButton = new JButton("Process Files");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processFiles();
            }
        });


        setLayout(new BorderLayout());
        add(inputScrollPane, BorderLayout.NORTH);
        add(new JPanel(new BorderLayout()) {{
            add(outputScrollPane, BorderLayout.CENTER);
            add(predefinedOutputScrollPane, BorderLayout.EAST);
        }}, BorderLayout.CENTER);
        add(processButton, BorderLayout.SOUTH);
    }

    private void processFiles() {
        File inFile = new File("E:\\input.txt");
        File outFile = new File("E:\\output.txt");

        try {

            try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
                StringBuilder inputContent = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    inputContent.append(line).append("\n");
                }
                inputTextArea.setText(inputContent.toString());
            }

            try (BufferedReader br = new BufferedReader(new FileReader(outFile))) {
                StringBuilder outputContent = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    outputContent.append(line).append("\n");
                }
                outputTextArea.setText(outputContent.toString());
            }

            String predefinedOutput = "起始阶段：流程以一个名为“输入文件”（input File）的矩形框为起点，这代表了数据处理的输入源头。\n预处理阶段：紧接着，“输入文件”之后是两个并行的“过滤器”（Filter）组件。这两个过滤器可能用于对数据进行初步的清洗、筛选或转换，以满足后续处理步骤的要求。它们之间通过向右的箭头表示数据流向。\n输入阶段：经过预处理的数据流进入名为“输入”（Input）的矩形框。此时，数据已经准备好进入下一步的详细处理。\n处理阶段：在“输入”矩形框的右侧，有两个并行的处理组件：“Shift”和另一个“过滤器”。这里，“Shift”可能用于对数据进行某种形式的位移或重组，而第二个“过滤器”则可能继续对数据进行进一步的筛选或转换。这两个组件之间同样通过向右的箭头表示数据流向。\n字母表化阶段：处理后的数据进入“字母表化器”（Alphabetizer）组件。这个组件可能用于对数据进行排序，特别是当数据包含字母或字符时，以确保它们按照某种特定的顺序（如字母顺序）排列。\n输出阶段：“字母表化器”之后是“输出”（Output）矩形框，表示处理后的数据已经准备好被输出或用于后续步骤。\n存储阶段：最后，数据被写入名为“输出文件”（Output File）的矩形框所代表的存储介质中。这是数据处理的最终阶段，也是数据流的终点。";
            predefinedOutputArea.setText(predefinedOutput);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error processing files: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}