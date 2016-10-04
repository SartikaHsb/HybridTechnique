//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.Random;
//import weka.classifiers.Classifier;
//import weka.classifiers.Evaluation;
//import weka.classifiers.lazy.IBk;
//import weka.core.Instance;
//import weka.core.Instances;
//import weka.core.SerializationHelper;
//import java.io.IOException;
//import javax.swing.JFrame;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.event.KeyEvent;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.InputStream;
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
//import weka.classifiers.functions.SMO;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author yanti
// */
//public class simpleweka extends JFrame {
//
//    private JTabbedPane tabbedPane;
//    private JPanel panel1;
//    private JPanel panel2;
//    private JPanel panel3;
//    private JPanel panel4;
//    JFrame frame = new JFrame();
//    JLabel filenm = new JLabel();
//    JLabel filept = new JLabel();
//    public String filePath;
//    StringBuilder stb = new StringBuilder();
//
//    public simpleweka() {
//        setTitle("Simple Weka");
//        setSize(300, 200);
//        setBackground(Color.gray);
//
//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new BorderLayout());
//        getContentPane().add(topPanel);
//
//        createPage3();
//        createPage4();
//
//        //create the tabbed pane
//        tabbedPane = new JTabbedPane();
//        tabbedPane.addTab("Preprocessing", panel4);
////        tabbedPane.addTab("Feature Selection", panel2);
//        tabbedPane.addTab("KNN", panel3);
//        //tabbedPane.addTab("pre", panel4);
//        topPanel.add(tabbedPane, BorderLayout.CENTER);
//
//    }
//
//    private void RemoveDuplicates() throws FileNotFoundException, IOException {
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
//        try {
//            System.out.println("ini:"+filenm.getText().toString());
//            // modify path
//            String path  = filenm.getText().toString();
//            ArrayList a = new ArrayList<>();
//            String[] path_file = path.split("\\\\");
//            for(int i=0;i<path_file.length;i++)
//            {
//                if(i !=path_file.length-1)
//                {
//                    path_file[i] +="\\\\";
//                }
//                
//            }
//            
//            for(String s : path_file)
//            {
//                stb.append(s);
//            }
//            
//            System.out.println(stb.toString());
//            
//            FileInputStream fistream_Output = new FileInputStream(stb.toString());
//            workbook_Output = new HSSFWorkbook(new POIFSFileSystem(fistream_Output));
//            InputStream ipt = simpleweka.class.getResourceAsStream("build.txt");
//            String workingDir = System.getProperty("user.dir");
//	   //File fileName = new File( chooser.getSelectedFile( ) + ".log" );
//    if(filePath == null)
//        return;
////    if(filePath.exists())
////    {
////        actionDialog = JOptionPane.showConfirmDialog(this,
////                           "Replace existing file?");
////        // may need to check for cancel option as well
////        if (actionDialog == JOptionPane.NO_OPTION)
////            return;
////    }
//    // okay to write file
////    BufferedWriter outFile = new BufferedWriter( new FileWriter( filePath ) );
////    outfil
////    
////            SerializationHelper s = new SerializationHelper();
////                            s.write("KnnData.arff", workingDir);
////                            filept.setText(s.toString());
//            File file_dup = new File("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output.xls");
//            
//            FileOutputStream fistream_output_dup = new FileOutputStream(file_dup);
//            workbook_Output.write(fistream_output_dup);
//            fistream_output_dup.close();
//
//            JLabel kl = new JLabel();
//            kl.setBounds(25, 160, 200, 20);
//            kl.setText(file_dup.toString());
//            panel4.add(kl);
//
//            FileInputStream fistream_Output_Dup = new FileInputStream("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output.xls");
//            workbook_Output_Dup = new HSSFWorkbook(fistream_Output_Dup);
//
//            //File file_final_2 = new File("C:\\Users\\Satya\\Desktop\\Output_CRQ.xls");
//
//            for (int sheetnum = 0; sheetnum < workbook_Output.getNumberOfSheets(); sheetnum++) {
//
//                Set<Integer> markrowdup = new HashSet<Integer>();
//                List<HSSFCell> cellStoreList = new ArrayList<HSSFCell>();
//
//                sheet_Output_Dup = workbook_Output_Dup.getSheetAt(sheetnum);
//                sheet_Output = workbook_Output.getSheetAt(sheetnum);
//
//                int lastrow = sheet_Output.getLastRowNum();
//                for (int i = 1; i <= lastrow; i++) {
//                    row_Output_Dup = sheet_Output_Dup.getRow(i);
//                    row_Output = sheet_Output.getRow(i);
//
//                    for (int j = i; j <= lastrow; j++) {
//                        row_Output_dup = sheet_Output.getRow(j);
//                        if (i != j) {
//                            if (row_Output.getCell(1).getStringCellValue() == row_Output_dup.getCell(1).getStringCellValue()) {
//                                markrowdup.add(j);
//                                System.out.println(row_Output.getCell(1).getStringCellValue() + " : Duplicte row num: " + j);
//
//                            }
//                        }
//
//                    }
//                }
//                Object[] o = markrowdup.toArray();
//                Arrays.sort(o);
//
//                for (Object object : o) {
//                    int row = ((Integer) object).intValue();
//                    row_Output_Dup = sheet_Output_Dup.getRow(row);
//                    sheet_Output_Dup.removeRow(row_Output_Dup);
//                }
//                File file_final = new File("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output_Final.xls");
//                FileOutputStream fostream_final = new FileOutputStream(file_final);
//                workbook_Output_Dup.write(fostream_final);
//                fostream_final.close();
//
//                FileInputStream fistream_final = new FileInputStream(file_final);
//                workbook_Output_Final = new HSSFWorkbook(fistream_final);
//                sheet_Output_Final = workbook_Output_Final.getSheetAt(sheetnum);
//
//                for (int i = 0; i <= sheet_Output_Final.getLastRowNum(); i++) {
//                    row_Output_Final = sheet_Output_Final.getRow(i);
//                    if (row_Output_Final == null) {
//                        System.out.println("Row is null");
//                    } else {
//                        Iterator<Cell> iterator_output_final = row_Output_Final.cellIterator();
//                        while (iterator_output_final.hasNext()) {
//                            cell = (HSSFCell) iterator_output_final.next();
//                            cellStoreList.add(cell);
//
//                        }
//                    }
//                }
//                Iterator<HSSFCell> iterator_final_data = cellStoreList.iterator();
//                File file_final_2 = new File("E:\\kuliah\\semester 8\\TA2\\RemoveRedundantData\\Output_CRQ.xls");
//                FileOutputStream fostream_final_2 = new FileOutputStream(file_final_2);
//                if (workbook_Output_Final_2 == null) {
//                    workbook_Output_Final_2 = new HSSFWorkbook();
//                }
//                workbook_Output_Final_2.createSheet(workbook_Output.getSheetName(sheetnum));
//                HSSFSheet temp = workbook_Output_Final_2.getSheetAt(sheetnum);
//                int i = 0;
//                int temp_row = 0;
//                HSSFRow row = temp.createRow(temp_row);
//                while (iterator_final_data.hasNext()) {
//                    HSSFCell cell2 = iterator_final_data.next();
//                    row.createCell(i).setCellValue(cell2.getStringCellValue());
//                    System.out.print(cell2.getStringCellValue() + ":  :");
//                    i++;
//                    if (i == 4) {
//                        System.out.println();
//                        i = 0;
//                        temp_row++;
//                        row = temp.createRow(temp_row);
//                    }
//
//                }
//                workbook_Output_Final_2.write(fostream_final_2);
//
//                //fostream_final_2.close();
//                fistream_final.close();
//                fistream_Output.close();
//                fostream_final_2.flush();
//                fostream_final_2.close();
//
//            }
//            fistream_Output.close();
//            fistream_Output_Dup.close();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
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
//            filenm.setText(selectedFile.getAbsolutePath());
//            filept.setText(selectedFile.getName());
//            filePath = selectedFile.getAbsolutePath();
//            
//        }
//    }
//
//    //page for classify
//    public void createPage3() {
//        panel3 = new JPanel();
//        panel3.setLayout(null);
//        Insets insets = panel3.getInsets();
//
//
//        
//        final JTextField fieldCh1 = new JTextField();
//        final JTextArea jt = new JTextArea();
//        jt.setBounds(300, 0, 630, 620);
//        fieldCh1.setBounds(110, 20, 150, 20);
//        
//        panel3.add(fieldCh1, BorderLayout.WEST);
//        panel3.add(jt, BorderLayout.EAST);
//
//       
//        JButton start = new JButton("Start");
//        start.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
////            statusLabel.setText( commentTextArea.getText());        
//                try {
//                     try {
//
//                       
//                            BufferedReader datafile = readDataFile(filept.getText().toString());
//System.out.println("pathini:"+filept.getText().toString());
//                            Instances data = new Instances(datafile);
//                            data.setClassIndex(data.numAttributes() - 1);
//
//
//
//                            Classifier ibk = new IBk();
//                            ibk.buildClassifier(data);
//                            SerializationHelper s = new SerializationHelper();
//                            s.write("KnnData.model", ibk);
//
//                            //evaluation
//                            Evaluation eval = new Evaluation(data);
////                          cross validation
//                            Random rand = new Random(1);   //using seed =1
//                            int folds = 10;
//                            eval.crossValidateModel(ibk, data, folds, rand);
//
//
//                            // there must be testing data to use this method below
//                            //eval.evaluateModel(ibk, data); [1]
//                            System.out.println(eval.toSummaryString("\nResults\n======\n", false));
//                            jt.setText(eval.toSummaryString("\nResults\n======\n", false) + "\n" + eval.toMatrixString());
//
//                            //System.out.println("\Confusion Matrix\n======\n");
//                            //System.out.println(eval.toMatrixString());
//
//                        
//
//                } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//
//        
//        start.setBounds(25, 160, 150, 20);
//        panel3.add(start, BorderLayout.WEST);
//
//    }
//
//    public void createPage4() {
//        panel4 = new JPanel();
//        panel4.setLayout(null);
//        final Insets insets = panel4.getInsets();
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
//        openButton.setBounds(25 + insets.left, 5 + insets.top, size.width, size.height);
//
//        //Add check box
//        final JCheckBox chkRedundant = new JCheckBox("Data Redundant");
//        final JCheckBox chkMissVal = new JCheckBox("Feature Selection");
//
//        JLabel lblFS1 = new JLabel("Select Preprocessing: ");
//        lblFS1.setBounds(25 + insets.left, 35 + insets.top, 155, size.height);
//        chkRedundant.setBounds(45, 60, 200, 20);
//        chkMissVal.setBounds(45, 90, 130, 20);
//        JButton choose = new JButton("Choose Algorithm");
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
//                } catch (Exception ex) {
//                    Logger.getLogger(FileExtensionFilterDemo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//
//        choose.setBounds(175, 90, 150, 20);
//        panel4.add(choose, BorderLayout.WEST);
//
//
//
//        JButton showButton1 = new JButton("Run");
//        showButton1.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
////            statusLabel.setText( commentTextArea.getText());        
//                try {
//                    if (chkRedundant.isSelected() == true) {
//                        RemoveDuplicates();
//                    } else if (chkMissVal.isSelected() == true) {
//                        panel4.add(new JLabel("Selected Attributes")).setBounds(25, 160, 200, 20);
//
//                        panel4.add(new TextArea());
//
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
//        filenm.setBounds(125, 20, 350, 20);
//        size = filenm.getPreferredSize();
//        filept.setBounds(125, 20, 350, 20);
//        size = filept.getPreferredSize();
//        filept.hide();
//        //filenm.setBounds(65+insets.left, 15+insets.top, size.width, size.height);
//        panel4.add(openButton);
//        panel4.add(lblFS1);
//        panel4.add(filenm);
//        panel4.add(filept);
//
//        panel4.add(showButton1);
//        panel4.add(chkRedundant);
//        panel4.add(chkMissVal);       
//    }
//
//    public static void main(String args[]) {
//
//        simpleweka mainFrame = new simpleweka();
//        mainFrame.setVisible(true);
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
//
//    public static BufferedReader readDataFile(String filename) {
//        BufferedReader inputReader = null;
//
//        try {
//            inputReader = new BufferedReader(new FileReader(filename));
//        } catch (FileNotFoundException ex) {
//            System.err.println("File not found: " + filename);
//        }
//        return inputReader;
//    }
//}
