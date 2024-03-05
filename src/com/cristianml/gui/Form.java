package com.cristianml.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form {
    private JButton btnReset;
    private JButton btnStart;
    private JLabel lblTitle;
    private JLabel lblTime;
    public JPanel panel;
    private JButton btnSkip;
    private JLabel lblIndicator;

    private int sessionTimePomo = 1500; // 1500sec = 25min
    private int sessionTimeBreak = 300; // 1500sec = 25min
    private int elapsedTime = sessionTimePomo * 1000; // 1000ms = 1second
    private int h = elapsedTime / 3600000; // We get elapsed hours
    private int m = (elapsedTime / 60000) % 60; // We get elapsed minutes
    private int s = (elapsedTime / 1000) % 60; // We get elapsed seconds
    boolean startClicked = false;
    // We convert the elapsed time to string
    String strH = String.format("%02d", h);
    String strM = String.format("%02d", m);
    String strS = String.format("%02d", s);
    private static JButton lastButtonClic = null;
    boolean isComplete = false;
    int count = 0;


    public Form() {
        // add the time on the Jlabel
        lblTime.setText(strH + ":" + strM + ":" + strS);
        lblIndicator.setText("Stop Timer");
        btnSkip.setVisible(!btnSkip.isVisible());

        // Button START
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (!startClicked) {
                        btnStart.setText("STOP");
                        startClicked = true;
                        timer.start();
                        // Show button if timer is run
                        btnSkip.setVisible(!btnSkip.isVisible());
                        lblIndicator.setText("Run Timer...");
                    } else {
                        btnStart.setText("CONTINUE");
                        startClicked = false;
                        timer.stop();
                        // Show button if timer is run
                        btnSkip.setVisible(!btnSkip.isVisible());
                        lblIndicator.setText("Stop Timer");
                    }
                lastButtonClic = btnStart;
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblIndicator.setText("Stop Timer");
                if (btnSkip.isVisible()) {
                    btnSkip.setVisible(!btnSkip.isVisible());
                }
                count++;
                lastButtonClic = btnReset;
                if (count != 4) {
                    stopTimerInZero(sessionTimePomo);
                } else {
                    lblIndicator.setText("Break");
                    count = 0;
                    stopTimerInZero(sessionTimeBreak);
                }
            }
        });
    }

    // Create Timer
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (elapsedTime != 0) {
                elapsedTime -= 1000;
                int h = elapsedTime/3600000;
                int m = (elapsedTime/60000) % 60;
                int s = (elapsedTime/1000) % 60;
                String strH = String.format("%02d", h);
                String strM = String.format("%02d", m);
                String strS = String.format("%02d", s);
                lblTime.setText(strH + ":" + strM + ":" + strS);
            } else {
                if (!lblIndicator.getText().equals("Break")) {
                    count++;
                }
                if (count == 4) {
                    lblIndicator.setText("Break");
                    elapsedTime = 300 * 1000;
                    count = 0;
                    stopTimerInZero(sessionTimeBreak);
                }
                stopTimerInZero(sessionTimePomo);
            }

        }
    });

    public void stopTimerInZero(int sessionTime) {
        timer.stop();
        elapsedTime = sessionTime*1000;
        h = elapsedTime/3600000;
        m = (elapsedTime/60000) % 60;
        s = (elapsedTime/1000) % 60;
        String strH = String.format("%02d", h);
        String strM = String.format("%02d", m);
        String strS = String.format("%02d", s);
        lblTime.setText(strH + ":" + strM + ":" + strS);
        btnStart.setText("START");
        startClicked = false;
    }
}
