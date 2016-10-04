
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.*;

class LinkedListFreq {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // initializing unsorted double array
        double dArr[] = {93.22, 91.22, 99.77, 96.22, 94.55, 83.22, 81.22, 89.77, 86.22, 84.55, 73.22, 71.22, 79.77, 76.22, 74.55};
        double difference;
        double clsInterval = 10, nmbrClsInterval;

        // let us print all the elements available in list
        for (double number : dArr) {
            System.out.println("Number = " + number);
        }

        // sorting array
        Arrays.sort(dArr);

        // let us print all the elements available in list
        double dArr1[];
        List<double[]> darrList = Arrays.asList(dArr);

        ArrayList<Double> dList = new ArrayList<Double>(dArr.length);
        System.out.println("The sorted double array is:");

        for (double d : dArr) {
            dList.add(d);
        }

        System.out.println(dList);

        //tableFrequency(dArr);
        difference = getMaxValue(dArr) - getMinValue(dArr);
        System.out.println("\nMax: " + getMaxValue(dArr));
        System.out.println("Min: " + getMinValue(dArr));
        System.out.println("difference: " + difference);
        nmbrClsInterval = difference / clsInterval;
        System.out.println("Number of Class Interval: " + bulat(nmbrClsInterval));

        int chunk = dList.size() / (int) bulat(nmbrClsInterval); // chunk size to divide
        String iy = null;
        ArrayList elementList = new ArrayList();
        ArrayList cb = new ArrayList();
        ArrayList roundedList = new ArrayList();
        ArrayList rounded1List = new ArrayList();
        System.out.println("elementList: " + elementList);

        for (int i = 0; i < dArr.length; i += chunk) {
            iy = Arrays.toString(Arrays.copyOfRange(dArr, i, i + chunk));
            String f = iy.split(",")[0].substring(1);
            double aDouble = Double.parseDouble(f);
            double rounded = (double) Math.round(aDouble * 10) / 10;

            String[] f1 = iy.split(",");

            String lastToken = f1[f1.length - 1];
            double aDouble1 = Double.parseDouble(lastToken.replaceAll("]", ""));
            double rounded1 = (double) Math.round(aDouble1 * 10) / 10;
            System.out.println(aDouble + " rounded is " + rounded);
            System.out.println(aDouble1 + " rounded is " + rounded1);

            elementList.add(iy);
            roundedList.add(rounded);
            rounded1List.add(rounded1);
        }
        System.out.println(" cb is " + cb);
        System.out.println("  elementList is " + elementList);

        ArrayList bts1 = new ArrayList();
        ArrayList bts2 = new ArrayList();
        ArrayList bts3 = new ArrayList();

        double roundedListDouble = 0;
        double rounded1ListDouble = 0;

//        for (int k = 0; k < elementList.size(); k++) {
//            String a = (String) elementList.get(k);
//            splitedElList = a.split(",");
//            for (int splt = 0; splt < splitedElList.length; splt++) {
//                l = ""+l + (splitedElList[splt]);
//                
//                //l.replaceFirst("[", "-");
//                
//            }
//
//        }
//        
//        String k = null;
//        String m = null;
//       
//            k = l.replace('[', '+');
//            m = k.replace(']', '+')+"";
//            
//        String h = (String) m;
//         String[] strValues = h.toString().split("+");              
//                ArrayList<String> aListNumbers = new ArrayList<String>(Arrays.asList(strValues));
//                System.out.println("elementList(a): " +aListNumbers.get(0) );

        for (int y = 0; y < elementList.size(); y++) {
            roundedListDouble = Double.parseDouble(roundedList.get(y).toString());
            rounded1ListDouble = Double.parseDouble(rounded1List.get(y).toString());
            bts1.add(roundedListDouble);
            bts2.add(rounded1ListDouble);
            for (int i = 0; i < dArr.length; i++) {

                if (dArr[i] > roundedListDouble && dArr[i] < rounded1ListDouble) {
//                    for (int h = 0; h < elementList.size(); h++) {
//                        if (uniqueList.contains(elementList.get(h))) {
////                            int elementCount =
////                                    Integer.parseInt(frequencyHash.get(elementList.get(h)).toString());
////                            elementCount++;
////                            frequencyHash.put(elementList.get(h), elementCount);
//                            System.out.println("  in is " + elementList.get(h));
//                        } else {
////                            uniqueList.add(elementList.get(h));
////                            frequencyHash.put(elementList.get(h), 1);
//                            System.out.println("nooo ");
//                        }
//                    }
                }

            }

        }
        System.out.println("\nroundedListDouble is " + bts1);
        System.out.println("rounded1ListDouble is " + bts2);
        String j = null;
        ArrayList yu = new ArrayList();
        if (bts1.size() == bts2.size()) {
            System.out.println("true");
            for (int u = 0; u < bts1.size(); u++) {
                j = bts1.get(u) + " " + bts2.get(u);
                yu.add(j);
            }
        }

        String[] strValues = yu.toString().split(",");
        String p = null;
        for (int y = 0; y < strValues.length; y++) {
            p = strValues[y].replace(']', ' ').replace('[', ' ').concat("-");
            
          }
        ArrayList k = new ArrayList();
        int a =1;
        for (int i = 0; i < strValues.length; i +=a) {
            iy = Arrays.toString(Arrays.copyOfRange(strValues, i, i +a));
           
           k.add(iy);
        }
         System.out.println(k.get(0));
        
    }

    // getting the maximum value
    public static double getMaxValue(double[] array) {
        double maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];

            }
        }
        return maxValue;
    }

// getting the miniumum value
    public static double getMinValue(double[] array) {
        double minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    public static double bulat(double r) {
        int decimalPlace = 0;
        BigDecimal bd = new BigDecimal(r);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
        r = bd.doubleValue();

        return r;
    }

    public static void tableFrequency(ArrayList elementList) {
//        ArrayList elementList = new ArrayList();
//
//        elementList.add(1);
//        elementList.add(1);
//        elementList.add(2);
//        elementList.add(2);
//        elementList.add(3);
//        elementList.add(4);
//        elementList.add(5);
//        elementList.add(5);

        Hashtable frequencyHash = new Hashtable();

        ArrayList uniqueList = new ArrayList();

        for (int i = 0; i < elementList.size(); i++) {
            if (uniqueList.contains(elementList.get(i))) {
                int elementCount =
                        Integer.parseInt(frequencyHash.get(elementList.get(i)).toString());

                elementCount++;
                frequencyHash.put(elementList.get(i), elementCount);
            } else {
                uniqueList.add(elementList.get(i));
                frequencyHash.put(elementList.get(i), 1);
            }
        }
        System.out.println(frequencyHash);
    }
}
