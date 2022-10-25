package ru.academits.paveldik.temperature.view;

import ru.academits.paveldik.temperature.model.Scale;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View implements Viewable {
    private Scale[] scales;

    private JFrame frame;
    private JTextField inputTemperature;
    private JTextField outputTemperature;
    private JComboBox<Scale> inputScale;
    private JComboBox<Scale> outputScale;
    private JButton convertButton;

    @Override
    public void setScales(Scale[] scales) {
        this.scales = scales;
    }

    @Override
    public void run() {
        frame = new JFrame();
        inputTemperature = new JTextField(8);
        outputTemperature = new JTextField(8);
        inputScale = new JComboBox<>(scales);
        outputScale = new JComboBox<>(scales);
        convertButton = new JButton("Convert");

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 0;
        constraints1.gridy = 0;
        constraints1.gridheight = 1;
        constraints1.gridwidth = 2;
        panel.add(new JLabel("Input temperature"), constraints1);

        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.gridx = 2;
        constraints2.gridy = 0;
        constraints2.gridheight = 1;
        constraints2.gridwidth = 2;
        panel.add(new JLabel("Output temperature"), constraints2);

        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.gridx = 0;
        constraints3.gridy = 1;
        constraints3.gridheight = 1;
        constraints3.gridwidth = 1;
        constraints3.fill = GridBagConstraints.BOTH;
        panel.add(inputTemperature, constraints3);

        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.gridx = 1;
        constraints4.gridy = 1;
        constraints4.gridheight = 1;
        constraints4.gridwidth = 1;
        constraints4.fill = GridBagConstraints.BOTH;
        panel.add(inputScale, constraints4);

        GridBagConstraints constraints5 = new GridBagConstraints();
        constraints5.gridx = 2;
        constraints5.gridy = 1;
        constraints5.gridheight = 1;
        constraints5.gridwidth = 1;
        constraints5.fill = GridBagConstraints.BOTH;
        panel.add(outputTemperature, constraints5);
        outputTemperature.setEditable(false);

        GridBagConstraints constraints6 = new GridBagConstraints();
        constraints6.gridx = 3;
        constraints6.gridy = 1;
        constraints6.gridheight = 1;
        constraints6.gridwidth = 1;
        constraints6.fill = GridBagConstraints.BOTH;
        panel.add(outputScale, constraints6);

        GridBagConstraints constraints7 = new GridBagConstraints();
        constraints7.gridx = 0;
        constraints7.gridy = 3;
        constraints7.gridheight = 1;
        constraints7.gridwidth = 4;
        constraints7.fill = GridBagConstraints.BOTH;
        panel.add(convertButton, constraints7);

        frame.add(panel);
        frame.setTitle("Temperature converter");
        frame.setSize(450, 150);

        Dimension minDimension = new Dimension(400, 120);
        frame.setMinimumSize(minDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void addInputScaleListener(ActionListener listenForInputScale) {
        inputScale.addActionListener(listenForInputScale);
    }

    @Override
    public Scale getInputScale() {
        return (Scale) inputScale.getSelectedItem();
    }

    @Override
    public void addOutputScaleListener(ActionListener listenForOutputScale) {
        outputScale.addActionListener(listenForOutputScale);
    }

    @Override
    public Scale getOutputScale() {
        return (Scale) outputScale.getSelectedItem();
    }

    @Override
    public void addConvertListener(ActionListener listenForConvertButton) {
        convertButton.addActionListener(listenForConvertButton);
    }

    @Override
    public double getInputTemperature() {
        return Double.parseDouble(inputTemperature.getText());
    }

    @Override
    public void setOutputTemperature(double outputTemperature) {
        this.outputTemperature.setText(String.format("%.2f", outputTemperature));
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }
}