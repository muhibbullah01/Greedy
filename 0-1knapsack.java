import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapSack {

    public static double getMaxProfit(int[] wt, int[] pt, int capacity){
        ItemValue[] iVal = new ItemValue[wt.length];
        for(int i=0; i< wt.length; i++){
            iVal[i] = new ItemValue(wt[i], pt[i], i);
        }
        Arrays.sort(iVal, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });
        double totalProfit = 0d;
        for(ItemValue i: iVal){
            int currentWt = (int) i.wt;
            int currentPt = (int) i.pt;
            if(capacity - currentWt >= 0){
                capacity = capacity-currentWt;
                totalProfit += currentPt;
            }else {
                double fraction = ((double) capacity/(double)currentWt);
                totalProfit += (currentPt*fraction);
                capacity = (int)(capacity - (currentWt*fraction));
                break;
            }
        }
        return totalProfit;
    }
    static class ItemValue{
        Double cost;
        double wt, pt, ind;

        public ItemValue(int wt, int pt, int ind){
            this.wt = wt;
            this.pt = pt;
            this.ind = ind;
            cost = new Double(pt/wt );
        }
    }
    public static void main(String[] args){
        int[] wt = {2,3,5,7,1,4,1};
        int[] pt = {10,5,15,7,6,18,3};
        int capacity = 15;

        double maxProfit = getMaxProfit(wt, pt, capacity);
        System.out.println("Maximum profit we can obtain = " + maxProfit);
    }
}
