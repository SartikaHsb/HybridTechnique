//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.LinkedHashSet;
//import org.apache.poi.util.IOUtils;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author yanti
// */
//public class NewClass {
//     @SuppressWarnings("resource")
//    BufferedReader reader = new BufferedReader(new FileReader(filename));
//    Set<String> lines = new LinkedHashSet<String>();
//    String line;
//    String delims = " ";
//    System.out.println("Read the duplicate contents now and writing to file");
//    while((line=reader.readLine())!=null){
//        line = line.trim(); 
//        StringTokenizer str = new StringTokenizer(line, delims);
//        while (str.hasMoreElements()) {
//            line = (String) str.nextElement();
//            lines.add(line);
//            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
//            for(String unique: lines){
//                writer.write(unique+" ");               
//            }
//            writer.close();
//        }
//    }
//    System.out.println(lines);
//    System.out.println("Duplicate removal successful");
//}
