import java.util.Arrays;


public class ClosestAntennaPair {

    private double closestDistance = Double.POSITIVE_INFINITY;
    private long counter = 0;

    public ClosestAntennaPair(Point2D[] aPoints, Point2D[] bPoints) {

//        for (Point2D point : aPoints){
//            System.out.println(point);
//        }
//        System.out.println("....................");
//
//        for (Point2D point : bPoints){
//            System.out.println(point);
//        }

        int aLength = aPoints.length;
        int bLength = bPoints.length;
        if (aLength == 0 || bLength == 0){
            return;
        }
        Point2D[] aPointsSortedByX = new Point2D[aLength];
        Point2D[] bPointsSortedByX = new Point2D[bLength];

        for (int i = 0; i < aLength; i++) {
            aPointsSortedByX[i] = aPoints[i];
        }
        for (int i = 0; i < bLength; i++) {
            bPointsSortedByX[i] = bPoints[i];
        }
        Arrays.sort(aPointsSortedByX, Point2D.Y_ORDER);
        Arrays.sort(aPointsSortedByX, Point2D.X_ORDER);
        Arrays.sort(bPointsSortedByX, Point2D.Y_ORDER);
        Arrays.sort(bPointsSortedByX, Point2D.X_ORDER);



        Point2D[] aPointsSortedByY = new Point2D[aLength];
        for (int i = 0; i < aLength; i++)
            aPointsSortedByY[i] = aPointsSortedByX[i];

        Point2D[] bPointsSortedByY = new Point2D[bLength];
        for (int i = 0; i < bLength; i++)
            bPointsSortedByY[i] = bPointsSortedByX[i];

        Point2D[] auxA = new Point2D[aLength];
        Point2D[] auxB = new Point2D[bLength];

        closestDistance = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY,
                auxA, auxB,  0, 0, aLength-1, bLength-1);

    }

    public double closest(Point2D[] aPointsSortedByX, Point2D[] bPointsSortedByX, Point2D[] aPointsSortedByY, Point2D[] bPointsSortedByY, Point2D[] auxA, Point2D[] auxB, int lowA, int lowB, int highA, int highB) {
        // please do not delete/modify the next line!
        counter++;

//        if ((highA < lowA && highB >= lowB) || (highB < lowB && highA >= lowA)){
//            return Double.POSITIVE_INFINITY;
//
//        }
//        if (highB != -1){
//            Arrays.sort(aPointsSortedByY,lowA,highA,Point2D.Y_ORDER);
//            Arrays.sort(aPointsSortedByY,lowA, highA,Point2D.Y_ORDER);
//            Arrays.sort(bPointsSortedByY,lowB,highB,Point2D.Y_ORDER);
//            Arrays.sort(bPointsSortedByY,lowB, highB,Point2D.Y_ORDER);
//        }


        if (highA < lowA && highB < lowB){
            return Double.POSITIVE_INFINITY;

        }

        if (highB == -1){
            Arrays.sort(aPointsSortedByY,lowA,highA+1,Point2D.Y_ORDER);
            return Double.POSITIVE_INFINITY;
        }
        if(highA < lowA){
            Arrays.sort(bPointsSortedByY,lowB,highB+1,Point2D.Y_ORDER);
            return Double.POSITIVE_INFINITY;
        }
        if(highB < lowB){
            Arrays.sort(aPointsSortedByY,lowA,highA+1,Point2D.Y_ORDER);
            return Double.POSITIVE_INFINITY;
        }

        if(highA == lowA){
            double temp = Double.POSITIVE_INFINITY;
            for(int i = lowB; i<=highB;i++){
                double dis1 = aPointsSortedByX[lowA].distanceTo(bPointsSortedByX[i]);
                if (dis1 < temp){
                    temp = dis1;
                }
            }
            Arrays.sort(bPointsSortedByY,lowB,highB+1,Point2D.Y_ORDER);
            return  temp;

        }else if(highB  == lowB ){
            double temp1 = Double.POSITIVE_INFINITY;
            for(int i = lowA; i<=highA;i++) {
                double dis2 = bPointsSortedByX[lowB].distanceTo(aPointsSortedByX[i]);
                if (dis2 < temp1) {
                    temp1 = dis2;
                }
            }
            Arrays.sort(aPointsSortedByY,lowA,highA+1,Point2D.Y_ORDER);
            return  temp1;
        }

        int midA = lowA + (highA - lowA) / 2;
        double median = aPointsSortedByX[midA].x();
        int midB = -1;
        for (int i = lowB; i<=highB; i++){
            if (bPointsSortedByX[i].x() <= median){
                midB++;
            }
        }

//        int midB = lowB + (highB - lowB) / 2;
//        int mid = (midA + midB) / 2;
//        if (mid > highA){
//            mid = midA;
//        }else if (mid > highB){
//            mid = midB;
//        }



//        int mid;
//        if (midA <= midB){
//            mid =midA;
//        }else{
//            mid = midB;
//        }

//        Point2D medianA = aPointsSortedByX[mid];
//        Point2D medianB = bPointsSortedByX[mid];
//        double median = ((medianA.x() + medianB.x()) / 2.0);

        double delta1 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB,  lowA, lowB, midA, midB);
        double delta2 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB,  midA+1, midB+1, highA, highB);
        double delta = Math.min(delta1, delta2);

        merge(aPointsSortedByY, auxA, lowA, midA, highA);
        merge(bPointsSortedByY, auxB, lowB, midB, highB);

        int m = 0;
        for (int i = lowA; i <= highA; i++) {
            if (Math.abs(aPointsSortedByY[i].x() - median) < delta)
                auxA[m++] = aPointsSortedByY[i];

        }
        System.out.println("m "+m);

        int n = 0;
        for (int i = lowB; i <= highB; i++) {
            if (Math.abs(bPointsSortedByY[i].x() - median) < delta)
                auxB[n++] = bPointsSortedByY[i];

        }
        System.out.println("n "+n);

//        auxA[m++] = medianA;
//        auxB[n++] = medianB;

        for (int i = 0; i < m; i++) {
            for(int j = 0; (j < n) && (auxB[j].y() - auxA[i].y() < delta); j++){
                double distance = auxA[i].distanceTo(auxB[j]);
                System.out.println(auxA[i] + "," +auxB[j] + distance );
                if (distance < delta) {
                    delta = distance;
                    if (distance < closestDistance) {
                        closestDistance = delta;
                    }
                }
            }
        }
        return delta;
    }

    public double distance() {
        return closestDistance;
    }

    public long getCounter() {
        return counter;
    }

    // stably merge a[low .. mid] with a[mid+1 ..high] using aux[low .. high]
    // precondition: a[low .. mid] and a[mid+1 .. high] are sorted subarrays, namely sorted by y coordinate
    // this is the same as in ClosestPair
    private static void merge(Point2D[] a, Point2D[] aux, int low, int mid, int high) {
        // copy to aux[]
        // note this wipes out any values that were previously in aux in the [low,high] range we're currently using

        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++];   // already finished with the low list ?  then dump the rest of high list
            else if (j > high) a[k] = aux[i++];   // already finished with the high list ?  then dump the rest of low list
            else if (aux[i].compareByY(aux[j]) < 0)
                a[k] = aux[i++]; // aux[i] should be in front of aux[j] ? position and increment the pointer
            else a[k] = aux[j++];
        }
    }
}