import algorithm.AlgorithmManager;
import algorithm.Graphics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import support.input.Console;
import support.input.Validate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    private static AlgorithmManager solver = new AlgorithmManager();
    private static ChartPanel p;
    private static Validate validator = new Validate();
    private static Graphics g = new Graphics();

    private static DefaultTableModel model = new DefaultTableModel();
    private static JTable table = new JTable(model);

    public static JFrame getWindow() {
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        window.setBounds(0, 0, dimension.width, dimension.height);
        window.setTitle("mathematics_lab3_335099");
        return window;

    }

    public static void main(String[] args) {
        int functionNumber = 1;
        int methodNumber = 1;
        JFrame window = getWindow();
        JPanel panel = new JPanel();
        Label label = new Label();
        GridLayout dataAndGraphics = new GridLayout(1, 3);
        GridLayout grid = new GridLayout(10, 6);
        panel.setLayout(grid);
        grid.setHgap(10);
        ArrayList<JButton> startMethod = new ArrayList<JButton>();
        CheckboxGroup oneFunction = new CheckboxGroup();
        Checkbox firstFunction = new Checkbox("3x^3 + 2x^2 + x - 3", oneFunction, false);
        Checkbox secondFunction = new Checkbox("0.5x^2 - x", oneFunction, false);
        Checkbox thirdFunction = new Checkbox("sin(x) + 0.5x^2 - 1", oneFunction, false);
        Checkbox fourthFunction = new Checkbox("cos(x) + sin(x)", oneFunction, false);
        Checkbox fifthFunction = new Checkbox("x^3 - x^2 - 2x", oneFunction, false);
        panel.add(firstFunction);
        panel.add(secondFunction);
        panel.add(thirdFunction);
        panel.add(fourthFunction);
        panel.add(fifthFunction);
        CheckboxGroup oneMethod = new CheckboxGroup();
        JTextField binarySearchInput = new JTextField();
        JButton binarySearchButton = new JButton("Посчитать методом правых прямоугольников!");
        Label binarySearchLabel = new Label();
        Checkbox firstMethod = new Checkbox("Метод левых прямоугольников", oneMethod, false);
        firstMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом левых прямоугольников!");
            }
        });
        Checkbox secondMethod = new Checkbox("Метод средних прямоугольников", oneMethod, false);
        secondMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом средних прямоугольников!");
            }
        });
        Checkbox thirdMethod = new Checkbox("Метод правых прямоугольников", oneMethod, true);
        thirdMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом правых прямоугольников!");
            }
        });
        Checkbox fourthMethod = new Checkbox("Метод Симпсона", oneMethod, false);
        fourthMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом Симпсона!");
            }
        });
        Checkbox fifthMethod = new Checkbox("Метод трапеций", oneMethod, false);
        fifthMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом трапеций!");
            }
        });
        panel.add(firstMethod);
        panel.add(secondMethod);
        panel.add(thirdMethod);
        panel.add(fourthMethod);
        panel.add(fifthMethod);
        /*JTextField secantsInput = new JTextField();
        JButton secantsButton = new JButton("Посчитать методом секущих!");
        Label secantsLabel = new Label();
        JTextField iterationInput = new JTextField();
        JButton iterationButton = new JButton("Посчитать методом простой итерации!");
        Label iterationLabel = new Label();*/
        panel.add(binarySearchInput);
        panel.add(binarySearchButton);
        panel.add(binarySearchLabel);
        /*panel.add(secantsInput);
        panel.add(secantsButton);
        panel.add(secantsLabel);
        panel.add(iterationInput);
        panel.add(iterationButton);
        panel.add(iterationLabel);*/

        binarySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oneFunction.getSelectedCheckbox() == null){
                    binarySearchLabel.setText("Выберите, пожалуйста, одну функцию из списка");
                }
                else if (validator.validateBorders(binarySearchInput.getText())){
                    binarySearchLabel.setText("");
                    BigDecimal first, second, accuracy;
                    String[] temp = binarySearchInput.getText().strip().split(" ");
                    for (int i = 0; i < temp.length; i++){
                        temp[i] = temp[i].replace(",", ".");
                    }
                    first = new BigDecimal(temp[0]);
                    second = new BigDecimal(temp[1]);
                    accuracy = new BigDecimal(temp[2]);
                    String function = oneFunction.getSelectedCheckbox().getLabel();
                    String method = oneMethod.getSelectedCheckbox().getLabel();
                    int functionNumber, methodNumber;
                    functionNumber = -1;
                    methodNumber = -1;
                    switch (function){
                        case "3x^3 + 2x^2 + x - 3":
                            functionNumber = 1;
                            break;
                        case "0.5x^2 - x":
                            functionNumber = 2;
                            break;
                        case "sin(x) + 0.5x^2 - 1":
                            functionNumber = 3;
                            break;
                        case "cos(x) + sin(x)":
                            functionNumber = 4;
                            break;
                        case "x^3 - x^2 - 2x":
                            functionNumber = 5;
                            break;
                    }
                    switch (method){
                        case "Метод левых прямоугольников":
                            methodNumber = 1;
                            break;
                        case "Метод средних прямоугольников":
                            methodNumber = 2;
                            break;
                        case "Метод правых прямоугольников":
                            methodNumber = 3;
                            break;
                        case "Метод Симпсона":
                            methodNumber = 4;
                            break;
                        case "Метод трапеций":
                            methodNumber = 5;
                            break;
                    }
                    /*System.out.println(functionNumber);
                    System.out.println(methodNumber);
                    System.out.println(first);
                    System.out.println(second);
                    System.out.println(accuracy);*/
                    ArrayList<ArrayList<BigDecimal>> answer = solver.solve(methodNumber, functionNumber, first, second, accuracy);
                    //DefaultTableModel model = new DefaultTableModel();
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    table = new JTable(model);
                    /*for (int i = 0; i < answer.get(0).size(); i++){
                        Console.print(answer.get(0).get(i).toString(), "г");
                    }*/
                    for (int i = 0; i < answer.get(2).size(); i++){
                        Console.print(answer.get(2).get(i).toString(), "г");
                    }
                    BigDecimal[] temp1 = new BigDecimal[4];
                    String[] temp2 = new String[]{"Индекс", "x", "y", "Добавлено в значение интеграла"};
                    for (int i = 0; i < temp2.length; i++){
                        model.addColumn(i);
                    }
                    model.addRow(temp2);
                    for (int i = 0; i < answer.get(0).size(); i++){
                        if (i < (answer.get(0).size() - 1)) {
                            temp1[0] = new BigDecimal(i);
                        }
                        else{
                            temp1[0] = new BigDecimal(0);
                        }
                        temp1[1] = answer.get(0).get(i);
                        temp1[2] = answer.get(1).get(i);
                        temp1[3] = answer.get(2).get(i);
                        model.addRow(temp1);
                    }
                    /*JFrame tableWindow = getWindow();
                    JPanel tablePanel = new JPanel();
                    tablePanel.add(table);
                    tableWindow.add(tablePanel);
                    tableWindow.setVisible(true);*/
                    JFreeChart chart = g.generateChart(first, second, functionNumber);
                    p.setChart(chart);
                }
                else{
                    //solver.solve(1, oneFunction.getSelectedCheckbox());
                    binarySearchLabel.setText("Число пробел число пробел число(0.000001 <= точность <= 1.0)");
                }
            }
        });
        panel.add(label);
        window.add(panel);
        window.setLayout(dataAndGraphics);
        JPanel newPanel = new JPanel();
        JFreeChart chart = g.generateChart(new BigDecimal(-3), new BigDecimal(3), 3);
        p = new ChartPanel(chart);
        p.setPreferredSize(new Dimension(500, 700));
        p.setMouseZoomable(true);
        p.setMouseWheelEnabled(true);
        newPanel.add(p);
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        window.add(newPanel);
        window.add(tablePanel);
        window.getLayout().removeLayoutComponent(newPanel);
        window.setVisible(true);
        /*JTable table = new JTable(100, 10);
        JFrame tableWindow = getWindow();
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        tableWindow.add(tablePanel);
        tableWindow.setVisible(true);*/

        /*secantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });

        iterationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });*/


    }

}