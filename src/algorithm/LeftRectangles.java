package algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class LeftRectangles {

    BigDecimal a, b, accuracy, module, first, second;
    int functionNumber, iterationCount, n;

    public LeftRectangles(BigDecimal a, BigDecimal b, BigDecimal accuracy, int functionNumber){
        this.a = a;
        this.b = b;
        this.accuracy = accuracy;
        this.functionNumber = functionNumber;
        this.first = new BigDecimal(-100);
        this.second = new BigDecimal(100);
        this.module = first.subtract(second).abs();
        iterationCount = 0;
        n = 5;
    }

    public ArrayList<ArrayList<BigDecimal>> solve(){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        while ((module.compareTo(accuracy) > 0) || (iterationCount < 3)){
            first = second;
            second = new BigDecimal(0);
            answer.clear();
            answer.add(new ArrayList<BigDecimal>());
            answer.add(new ArrayList<BigDecimal>());
            answer.add(new ArrayList<BigDecimal>());
            BigDecimal h = b.subtract(a).divide(new BigDecimal(n).setScale(7, RoundingMode.HALF_EVEN));
            for (int i = 0; i < n; i++){
                BigDecimal temp = a.add(h.multiply(new BigDecimal(i)));
                //System.out.println(temp);
                answer.get(0).add(temp);
                answer.get(1).add(Functions.function(temp, functionNumber));
            }
            for (int i = 0; i < answer.get(1).size(); i++){
                second = second.add(answer.get(1).get(i).multiply(h));
                answer.get(2).add(answer.get(1).get(i).multiply(h));
            }
            module = second.subtract(first).abs();
            iterationCount += 1;
            n = n * 2;
            answer.get(0).add(new BigDecimal(0));
            answer.get(1).add(new BigDecimal(0));
            answer.get(2).add(second);
        }
        return answer;
    }

}
