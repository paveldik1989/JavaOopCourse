package ru.academits.paveldik.temperature_view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame {
    private static final String[] SCALES = {"Celsius", "Fahrenheit", "Kelvin"};

    private final JTextField inputTemperature = new JTextField(8);
    private final JButton convertButton = new JButton("Convert");
    private final JTextField outputTemperature = new JTextField(8);
    private final JComboBox<String> inputScaleComboBox = new JComboBox<>(SCALES);
    private final JComboBox<String> outputScaleComboBox = new JComboBox<>(SCALES);

    public View() {
        JPanel panel = new JPanel();

        panel.add(new JLabel("Enter temperature:"));
        panel.add(inputTemperature);
        panel.add(inputScaleComboBox);
        panel.add(convertButton);
        panel.add(new JLabel("Converted temperature:"));
        panel.add(outputTemperature);
        panel.add(outputScaleComboBox);

        outputTemperature.setEditable(false);

        this.add(panel);
        this.setTitle("Temperature converter");
        this.setSize(900, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public double getInputTemperature() {
        return Double.parseDouble(inputTemperature.getText());
    }

    public void setOutputTemperature(double outputTemperature) {
        this.outputTemperature.setText(Double.toString(outputTemperature));
    }

    public void addConvertListener(ActionListener listenForConvertButton) {
        convertButton.addActionListener(listenForConvertButton);
    }

    public void addInputScaleListener(ActionListener listenForInputScale) {
        inputScaleComboBox.addActionListener(listenForInputScale);
    }

    public void addOutputScaleListener(ActionListener listenForOutputScale) {
        outputScaleComboBox.addActionListener(listenForOutputScale);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public int getInputScaleIndex() {
        return inputScaleComboBox.getSelectedIndex();
    }

    public int getOutputScaleIndex() {
        return outputScaleComboBox.getSelectedIndex();
    }
}