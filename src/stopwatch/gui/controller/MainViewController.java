/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopwatch.gui.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Rasmus
 */
public class MainViewController implements Initializable {

    @FXML
    private Label lblTime;

    private StringProperty mTimer;
    private double mTimeTracker;

    private DecimalFormat mFormatter;

    private TimerTask mTask;

    public MainViewController() {
        mTimer = new SimpleStringProperty();
        mTimeTracker = 0;
        mFormatter = new DecimalFormat("0.##");
        mTimer.setValue(mFormatter.format(mTimeTracker));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTime.textProperty().bind(mTimer);
    }

    @FXML
    private void handleStartButton(ActionEvent event) {
        //Creates a TimerTask to specify what to be done.
        mTask = new TimerTask() {
            @Override
            public void run() {
                mTimeTracker += 0.01;
                Platform.runLater(() -> {
                    mTimer.setValue(mFormatter.format(mTimeTracker));
                });
            }
        };
        //Creates a timer to control when to do the TimerTask.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(mTask, 10, 10);
    }

    @FXML
    private void handleStopButton(ActionEvent event) {
        try {
            mTask.cancel();
        } catch (RuntimeException ex) {

        }
    }

}
