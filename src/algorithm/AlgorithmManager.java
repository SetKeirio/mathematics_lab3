package algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AlgorithmManager {
    public ArrayList<ArrayList<BigDecimal>> solve(int method, int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        switch (method) {
            case 1:
                answer = leftRectangles(function, left, right, accuracy);
                break;
            case 2:
                answer = middleRectangles(function, left, right, accuracy);
                break;
            case 3:
                answer = rightRectangles(function, left, right, accuracy);
                break;
            case 4:
                answer = simpson(function, left, right, accuracy);
                break;
            case 5:
                answer = trapezium(function, left, right, accuracy);
                break;
        }
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> leftRectangles(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        LeftRectangles method = new LeftRectangles(left, right, accuracy, function);
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> middleRectangles(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        MiddleRectangles method = new MiddleRectangles(left, right, accuracy, function);
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> rightRectangles(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        RightRectangles method = new RightRectangles(left, right, accuracy, function);
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> simpson(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        Simpson method = new Simpson(left, right, accuracy, function);
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> trapezium(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        Trapezium method = new Trapezium(left, right, accuracy, function);
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }


}
