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

    private int sessionTime = 1500; // 1500sec = 25min
    private int elapsedTime = sessionTime * 1000; // 1000ms = 1second
    private int h = elapsedTime / 3600000; // We get elapsed hours
    private int m = (elapsedTime / 60000) % 60; // We get elapsed minutes
    private int s = (elapsedTime / 1000) % 60; // We get elapsed seconds
    boolean startClicked = false;
    // We convert the elapsed time to string
    String strH = String.format("%02d", h);
    String strM = String.format("%02d", m);
    String strS = String.format("%02d", s);


    public Form() {
        // add the time on the Jlabel
        lblTime.setText(strH + ":" + strM + ":" + strS);

        // Button START
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (!startClicked) {
                        btnStart.setText("STOP");
                        startClicked = true;
                        timer.start();
                    } else {
                        btnStart.setText("CONTINUE");
                        startClicked = false;
                        timer.stop();
                    }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimerInZero();
            }
        });
    }

    // Create Timer
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (elapsedTime != 0) {
                elapsedTime -= 1000;
                h = elapsedTime/3600000;
                m = (elapsedTime/60000) % 60;
                s = (elapsedTime/1000) % 60;
                String strH = String.format("%02d", h);
                String strM = String.format("%02d", m);
                String strS = String.format("%02d", s);
                lblTime.setText(strH + ":" + strM + ":" + strS);
            } else {
                stopTimerInZero();
            }
        }
    });

    public void stopTimerInZero() {
        timer.stop();
        elapsedTime = sessionTime*1000;
        h = elapsedTime/3600000;
        m = (elapsedTime/60000) % 60;
        s = (elapsedTime/1000) % 60;
        String strH = String.format("%02d", h);
        String strM = String.format("%02d", m);
        String strS = String.format("%02d", s);
        lblTime.setText(strH + ":" + strM + ":" + strS);
        btnStart.setText("Start");
        startClicked = false;
    }
}
