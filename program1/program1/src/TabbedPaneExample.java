//
//import java.io.IOException;
//import javax.swing.JFrame;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Cell;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author yanti
// */
//public class TabbedPaneExample extends JFrame {
//
//    private JTabbedPane tabbedPane;
//    private JPanel panel1;
//    private JPanel panel2;
//    private JPanel panel3;
//    private JPanel panel4;
//    JFrame frame = new JFrame();
//    JLabel filenm = new JLabel();
//    String filePath;
//
//    public TabbedPaneExample() {
//        setTitle("Simple Weka");
//        setSize(300, 200);
//        setBackground(Color.gray);
//
//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new BorderLayout());
//        getContentPane().add(topPanel);
//
//        createPage1();
//        createPage2();
//        createPage3();
//        createPage4();
//
//        //create the tabbed pane
//        tabbedPane = new JTabbedPane();
//        tabbedPane.addTab("Preprocessing", panel4);
//        tabbedPane.addTab("Feature Selection", panel2);
//        tabbedPane.addTab("Classify", panel3);
//        //tabbedPane.addTab("pre", panel4);
//        topPanel.add(tabbedPane, BorderLayout.CENTER);
//
//    }
//
//    private void RemoveDuplicates(String selectedFile) throws FileNotFoundException, IOException {
//
//
//        HSSFWorkbook workbook_Output;
//        HSSFWorkbook workbook_Output_Dup;
//        HSSFWorkbook workbook_Output_Final;
//        HSSFWorkbook workbook_Output_Final_2 = null;
//        HSSFSheet sheet_Output;
//        HSSFSheet sheet_Output_Dup;
//        HSSFSheet sheet_Output_Final;
//        HSSFRow row_Output;
//        HSSFRow row_Output_Dup;
//        HSSFRow row_Output_Final;
//        HSSFCell cell;
//        HSSFRow row_Output_dup;
//        //  Set<Integer> markrowdup = new HashSet<Integer>();
//        //List<HSSFCell> cellStoreList=new ArrayList<HSSFCell>();
//
//
//
//        FileInputStream fistream_Output = new FileInputStream(selectedFile);
//        workbook_Output = new HSSFWorkbook(new POIFSFileSystem(fistream_Output));
//        File file_dup = new File("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output.xls");
//        FileOutputStream fistream_output_dup = new FileOutputStream(file_dup);
//        workbook_Output.write(fistream_output_dup);
//        fistream_output_dup.close();
//
//        FileInputStream fistream_Output_Dup = new FileInputStream("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output.xls");
//        workbook_Output_Dup = new HSSFWorkbook(fistream_Output_Dup);
//
//        //File file_final_2 = new File("C:\\Users\\Satya\\Desktop\\Output_CRQ.xls");
//
//
//
//        for (int sheetnum = 0; sheetnum < workbook_Output.getNumberOfSheets(); sheetnum++) {
//
//            Set<Integer> markrowdup = new HashSet<Integer>();
//            List<HSSFCell> cellStoreList = new ArrayList<HSSFCell>();
//
//            sheet_Output_Dup = workbook_Output_Dup.getSheetAt(sheetnum);
//            sheet_Output = workbook_Output.getSheetAt(sheetnum);
//
//            int lastrow = sheet_Output.getLastRowNum();
//            for (int i = 1; i <= lastrow; i++) {
//                row_Output_Dup = sheet_Output_Dup.getRow(i);
//                row_Output = sheet_Output.getRow(i);
//
//                for (int j = i; j <= lastrow; j++) {
//                    row_Output_dup = sheet_Output.getRow(j);
//                    if (i != j) {
//                        if (row_Output.getCell(1).getStringCellValue() == row_Output_dup.getCell(1).getStringCellValue()) {
//                            markrowdup.add(j);
//                            System.out.println(row_Output.getCell(1).getStringCellValue() + " : Duplicte row num: " + j);
//
//                        }
//                    }
//
//                }
//            }
//            Object[] o = markrowdup.toArray();
//            Arrays.sort(o);
//
//            for (Object object : o) {
//                int row = ((Integer) object).intValue();
//                row_Output_Dup = sheet_Output_Dup.getRow(row);
//                sheet_Output_Dup.removeRow(row_Output_Dup);
//            }
//            File file_final = new File("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output_Final.xls");
//            FileOutputStream fostream_final = new FileOutputStream(file_final);
//            workbook_Output_Dup.write(fostream_final);
//            fostream_final.close();
//
//
//
//            FileInputStream fistream_final = new FileInputStream(file_final);
//            workbook_Output_Final = new HSSFWorkbook(fistream_final);
//            sheet_Output_Final = workbook_Output_Final.getSheetAt(sheetnum);
//
//            for (int i = 0; i <= sheet_Output_Final.getLastRowNum(); i++) {
//                row_Output_Final = sheet_Output_Final.getRow(i);
//                if (row_Output_Final == null) {
//                    System.out.println("Row is null");
//                } else {
//                    Iterator<Cell> iterator_output_final = row_Output_Final.cellIterator();
//                    while (iterator_output_final.hasNext()) {
//                        cell = (HSSFCell) iterator_output_final.next();
//                        cellStoreList.add(cell);
//
//                    }
//                }
//            }
//            Iterator<HSSFCell> iterator_final_data = cellStoreList.iterator();
//            File file_final_2 = new File("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output_CRQ.xls");
//            FileOutputStream fostream_final_2 = new FileOutputStream(file_final_2);
//            if (workbook_Output_Final_2 == null) {
//                workbook_Output_Final_2 = new HSSFWorkbook();
//            }
//            workbook_Output_Final_2.createSheet(workbook_Output.getSheetName(sheetnum));
//            HSSFSheet temp = workbook_Output_Final_2.getSheetAt(sheetnum);
//            int i = 0;
//            int temp_row = 0;
//            HSSFRow row = temp.createRow(temp_row);
//            while (iterator_final_data.hasNext()) {
//                HSSFCell cell2 = iterator_final_data.next();
//                row.createCell(i).setCellValue(cell2.getStringCellValue());
//                System.out.print(cell2.getStringCellValue() + ":  :");
//                i++;
//                if (i == 4) {
//                    System.out.println();
//                    i = 0;
//                    temp_row++;
//                    row = temp.createRow(temp_row);
//                }
//
//            }
//            workbook_Output_Final_2.write(fostream_final_2);
//
//            //fostream_final_2.close();
//            fistream_final.close();
//            fistream_Output.close();
//            fostream_final_2.flush();
//            fostream_final_2.close();
//
//        }
//        fistream_Output.close();
//        fistream_Output_Dup.close();
//
//    }
//
//    private void showOpenFileDialog1() {
//        JFileChooser fileChooser = new JFileChooser();
//        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents", "docx", "xlsx", "pptx"));
//        fileChooser.setAcceptAllFileFilterUsed(true);
//        int result = fileChooser.showOpenDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            filenm.setText(selectedFile.getName());
//            filePath = selectedFile.getAbsolutePath();
//           System.out.println("path:" + filePath.toString());
//        }
//    }
//
//    public void createPage1() {
//        panel1 = new JPanel();
//        panel1.setLayout(null);
//
//        JLabel label1 = new JLabel("Username:");
//        label1.setBounds(10, 15, 150, 20);
//        panel1.add(label1);
//
//        JTextField field = new JTextField();
//        field.setBounds(10, 35, 150, 20);
//        panel1.add(field);
//
//        JLabel label2 = new JLabel("Password:");
//        label2.setBounds(10, 60, 150, 20);
//        panel1.add(label2);
//
//        JTextField fieldPas = new JTextField();
//        fieldPas.setBounds(10, 80, 150, 20);
//        panel1.add(fieldPas);
//
//    }
//
//    public void createPage2() {
//        panel2 = new JPanel();
//        panel2.setLayout(null);
//        Insets insets = panel2.getInsets();
//
//
//        JButton choose = new JButton("Choose");
//        choose.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
////            statusLabel.setText( commentTextArea.getText());        
//                try {
//                    final JCheckBox CfsSubsetEval = new JCheckBox("CfsSubsetEval");
//                    final JCheckBox ChiSquaredAttributeEval = new JCheckBox("ChiSquaredAttributeEval");
//                    final JCheckBox InfoGainAttributeEval = new JCheckBox("InfoGainAttributeEval");
//                    final JCheckBox GainRatioAttributeEval = new JCheckBox("GainRatioAttributeEval");
//                    final JCheckBox WrapperSubsetEval = new JCheckBox("WrapperSubsetEval");
//
////                    KNN.addItemListener(new ItemListener() {
////
////                        public void itemStateChanged(ItemEvent ie) {
////                            // int retval = JOptionPane.showConfirmDialog(null, "message",
////                            // "title", JOptionPane.OK_CANCEL_OPTION);
////                        }
////                    });
////
////                    KNN.addActionListener(new ActionListener() {
////
////                        @Override
////                        public void actionPerformed(ActionEvent e) {
////                            int retval = JOptionPane.showConfirmDialog(null, "message",
////                                    "title", JOptionPane.OK_CANCEL_OPTION);
////                        }
////                    });
//
//                    JPanel panel = new JPanel();
//                    Dimension size = panel.getPreferredSize();
//                    Insets insets1 = panel.getInsets();
//                    panel.add(CfsSubsetEval).setBounds(25 + insets1.left, 15 + insets1.top, size.width, size.height);
//                    panel.add(ChiSquaredAttributeEval).setBounds(50 + insets1.left, 15 + insets1.top, size.width, size.height);
//                    panel.add(InfoGainAttributeEval).setBounds(75 + insets1.left, 15 + insets1.top, size.width, size.height);
//                    panel.add(GainRatioAttributeEval).setBounds(25 + insets1.left, 70 + insets1.top, size.width, size.height);
//                    panel.add(WrapperSubsetEval).setBounds(25 + insets1.left, 55 + insets1.top, size.width, size.height);
//                    
//                    JOptionPane optionPane = new JOptionPane();
//                    optionPane.setOptionType(JOptionPane.showConfirmDialog(null, panel, "Algorithm", JOptionPane.OK_CANCEL_OPTION));
//                    } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        Dimension size = choose.getPreferredSize();
//        choose.setBounds(25 + insets.left, 15 + insets.top, size.width, size.height);
//        panel2.add(choose, BorderLayout.WEST);
//        panel2.add(new JLabel("Selected Attributes")).setBounds(25 + insets.left, 65 + insets.top, 150, size.height);
//        
//        panel2.add(new TextArea());
//        
//
////        panel2.add(new JButton("North"), BorderLayout.NORTH);
////        panel2.add(new JButton("South"), BorderLayout.SOUTH);
////        panel2.add(new JButton("Easth"), BorderLayout.EAST);
////        panel2.add(new JButton("West"), BorderLayout.WEST);
////        panel2.add(new JButton("Center"), BorderLayout.CENTER);
//
//
//    }
//
//    public void createPage3() {
////        panel3 = new JPanel();
////        panel3.setLayout(new GridLayout(3, 2));
////
////        panel3.add(new JLabel("Field 1:"));
////        panel3.add(new TextArea());
////        panel3.add(new JLabel("Field 2:"));
////        panel3.add(new TextArea());
////        panel3.add(new JLabel("Field 3:"));
////        panel3.add(new TextArea());
//
////        JPanel JPane1 = new JPanel();
////        JPanel JPane2 = new JPanel();
////        frame.add(JPane1,BorderLayout.WEST);
////        frame.add(JPane2,BorderLayout.EAST);
////        panel3.add(JPane1,BorderLayout.WEST);
////        panel3.add(JPane2,BorderLayout.EAST);
//        panel3 = new JPanel();
//        panel3.setLayout(null);
//        Insets insets = panel3.getInsets();
//
//
//        final JCheckBox KNN = new JCheckBox("KNN");
//        final JCheckBox SVM = new JCheckBox("SVM");
//        final JCheckBox bagging = new JCheckBox("Bagging");
//        final JTextField fieldCh1 = new JTextField();
//        fieldCh1.setBounds(110, 20, 150, 20);
//        JButton choose = new JButton("Choose");
//        choose.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
////            statusLabel.setText( commentTextArea.getText());        
//                try {                    
//                                 
//                    JPanel panel = new JPanel();
//                    panel.add(KNN);
//                    panel.add(SVM);
//                    panel.add(bagging);
//                    JOptionPane optionPane = new JOptionPane();
//                    optionPane.setOptionType(JOptionPane.showConfirmDialog(null, panel, "Algorithm", JOptionPane.OK_CANCEL_OPTION));
//                    if (optionPane.getOptionType()==JOptionPane.OK_OPTION) { 
//                        System.out.println( "OK_OPTION" ); 
//                        if(KNN.isSelected())
//                        {
//                            fieldCh1.setText(KNN.getText());
//                        }
//                        else if(SVM.isSelected())
//                        {
//                            fieldCh1.setText(SVM.getText());
//                        }
//                        else if(bagging.isSelected())
//                        {
//                            fieldCh1.setText(bagging.getText());
//                        }
//                        else if(KNN.isSelected() && SVM.isSelected())
//                        {
//                            fieldCh1.setText(KNN.getText()+","+SVM.getText());
//                        }
//                        else if(KNN.isSelected() && SVM.isSelected() && bagging.isSelected())
//                        {
//                            fieldCh1.setText(KNN.getText()+","+SVM.getText()+","+bagging.getText());
//                        }
//                         else if(bagging.isSelected() && SVM.isSelected())
//                        {
//                            fieldCh1.setText(bagging.getText()+","+SVM.getText());
//                        }
//                    }
//                    else { 
//                        System.out.println( "NO_OPTION" ); }
//
//                } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        Dimension size = choose.getPreferredSize();
//        choose.setBounds(25 + insets.left, 15 + insets.top, size.width, size.height);
//        panel3.add(choose, BorderLayout.WEST);
//
//        
//        panel3.add(fieldCh1, BorderLayout.WEST);
//        
//        
//        JTextArea jt = new JTextArea();
//        jt.setBounds(300, 0, 630, 620);
//        panel3.add(jt, BorderLayout.EAST);
//
//
//        JLabel TestOption = new JLabel("Test Option");
//        TestOption.setBounds(25, 50, 150, 20);
//        panel3.add(TestOption, BorderLayout.WEST);
//
//        JRadioButton use = new JRadioButton("Use training set");
//        JRadioButton supplied = new JRadioButton("Supplied test set");
//        JRadioButton cross = new JRadioButton("Cross-validation  Folds");
//        JRadioButton percen = new JRadioButton("Percentage split");
//        panel3.add(use, BorderLayout.WEST);
//        use.setBounds(25, 70, 150, 20);
//        panel3.add(supplied);
//        supplied.setBounds(25, 90, 150, 20);
//        panel3.add(cross, BorderLayout.WEST);
//        cross.setBounds(25, 110, 155, 20);
//
//        JTextField fieldCh = new JTextField();
//        fieldCh.setBounds(180, 110, 60, 20);
//        panel3.add(fieldCh, BorderLayout.WEST);
//
//        panel3.add(percen, BorderLayout.WEST);
//        percen.setBounds(25, 130, 150, 20);
//
//        ButtonGroup group = new ButtonGroup();
//        group.add(use);
//        group.add(supplied);
//        group.add(cross);
//        group.add(percen);
//
//        JButton start = new JButton("Start");
//        start.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
////            statusLabel.setText( commentTextArea.getText());        
//                try {
//                    showOpenFileDialog1();
//
//                } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//
//        size = start.getPreferredSize();
//        start.setBounds(25, 160, 150, 20);
//        panel3.add(start, BorderLayout.WEST);
//
//    }
//
//    public void createPage4() {
//        panel4 = new JPanel();
//        panel4.setLayout(null);
//        Insets insets = panel4.getInsets();
//
//        JButton openButton = new JButton("Open File");
//        openButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
////            statusLabel.setText( commentTextArea.getText());        
//                try {
//                    showOpenFileDialog1();
//
//                } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//
//        Dimension size = openButton.getPreferredSize();
//        openButton.setBounds(25 + insets.left, 15 + insets.top, size.width, size.height);
//
//        //Add check box
//        final JCheckBox chkRedundant = new JCheckBox("Data Redundant");
//        final JCheckBox chkMissVal = new JCheckBox("Fill Missing Value");
//
//        chkRedundant.setBounds(25, 60, 200, 20);
//        chkMissVal.setBounds(25, 90, 200, 20);
//
//        JButton showButton1 = new JButton("Run");
//        showButton1.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
////            statusLabel.setText( commentTextArea.getText());        
//                try {
//                    if (chkRedundant.isSelected() == true) {
//                        RemoveDuplicates(filePath.toString());
//                    } else {
//                        System.out.println("tidak remove");
//                    }
//                } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        size = showButton1.getPreferredSize();
//        showButton1.setBounds(25, 120, 200, 20);
//
//        //      //show label: name of the selected file
//        filenm.setBounds(125, 20, 200, 20);
//        size = filenm.getPreferredSize();
//        //filenm.setBounds(65+insets.left, 15+insets.top, size.width, size.height);
//
//
//
//        panel4.add(openButton);
//        panel4.add(filenm);
//
//        panel4.add(showButton1);
//        panel4.add(chkRedundant);
//        panel4.add(chkMissVal);
//
////        if (chkRedundant.isSelected() == true) {
////            try {
////                RemoveDuplicates(filePath);
////            } catch (FileNotFoundException ex) {
////                ex.printStackTrace();
////            } catch (IOException ex) {
////                ex.printStackTrace();
////            }
////        }
//
//        //panel4.add(new TextArea("Easth"), BorderLayout.EAST);
////        panel4.add(new TextArea("WEST"), BorderLayout.WEST);
////        panel4.add(new TextArea("SOUTH"), BorderLayout.SOUTH);
////        panel4.add(new JButton("West"), BorderLayout.WEST);
////        panel4.add(new JButton("Center"), BorderLayout.CENTER);
//    }
//
//    public static void main(String args[]) {
//
//        TabbedPaneExample mainFrame = new TabbedPaneExample();
//        mainFrame.setVisible(true);
//        
//
//
//    }
//
//    private void showOpenFileDialog() {
//        JFileChooser fileChooser = new JFileChooser();
//        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
//        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents", "docx", "xlsx", "pptx"));
//        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
//        fileChooser.setAcceptAllFileFilterUsed(true);
//        int result = fileChooser.showOpenDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//
//        }
//    }
////     private void showCheckBoxDemo()
////     {
////         final JCheckBox chkRedundant =  new JCheckBox("Data Redundant");
////         final JCheckBox chkMissVal =  new JCheckBox("Fill Missing Value");
////         //final JCheckBox chkRedundant =  new JCheckBox("Data Redundant");
////         chkRedundant.setMnemonic(KeyEvent.VK_C);
////         chkMissVal.setMnemonic(KeyEvent.VK_M);
////         panel4.add(chkRedundant);
////         panel4.add(chkMissVal);
////         chkRedundant.addItemListener(new ItemListener() {
////         public void itemStateChanged(ItemEvent e)
////         {
//////             statusLabel.setText("");
////         }});
////     }
//}
