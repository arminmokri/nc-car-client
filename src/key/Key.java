/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key;

import client.parameters.Parameter;
import client.parameters.ProxyParameters;
import client.request.Request;
import global.GlobalVariable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author armin
 */
public class Key {

    private KeyEvent.Mode mode;
    private int code;
    private int heartbeatIntetval;
    private int heartbeatWaitTime;
    private boolean release;
    private Timer timer;

    public Key(int keyCode, KeyEvent.Mode mode) {
        this(keyCode, mode, 500, 500);

    }

    public Key(int keyCode, KeyEvent.Mode mode, int heartbeatIntetval, int heartbeatWaitTime) {
        this.code = keyCode;
        this.mode = mode;
        if (this.mode == KeyEvent.Mode.PRESS_HEARTBEAT_RELEASE) {
            this.heartbeatIntetval = heartbeatIntetval;
            this.heartbeatWaitTime = heartbeatWaitTime;
        } else {
            this.heartbeatIntetval = 0;
            this.heartbeatWaitTime = 0;
        }
        release = true;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(heartbeatIntetval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProxyParameters proxyParameters = new ProxyParameters();
                    proxyParameters.add(Parameter.ACTION, Parameter.KEY);
                    proxyParameters.add(Parameter.KEY_CODE, code + "");
                    proxyParameters.add(Parameter.KEY_EVENT, KeyEvent.Event.HEARTBEAT.name());
                    proxyParameters.add(Parameter.KEY_MODE, mode.name());
                    Request key_event = new Request(proxyParameters);
                    GlobalVariable.clientThread.Request(key_event);
                    String result = key_event.getResponseParameters().getValue("result");
                    String message = key_event.getResponseParameters().getValue("message");
                    if (result.equals(Parameter.RESULT_1)) {
                        //System.out.println(message);
                    } else {
                        throw new Exception(message);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void addEvent(java.awt.event.KeyEvent e) {
        if (e.getID() == java.awt.event.KeyEvent.KEY_PRESSED && this.isRelease()) {
            this.setPress();
        } else if (e.getID() == java.awt.event.KeyEvent.KEY_RELEASED && this.isPress()) {
            this.setRelease();
        }
    }

    private void setPress() {
        release = false;
        switch (mode) {
            case PRESS:
                try {
                    sendPressEvent();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case PRESS_RELEASE:
                try {
                    sendPressEvent();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case PRESS_HEARTBEAT_RELEASE:
                try {
                    sendPressEvent();
                    timer.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public boolean isPress() {
        return !release;
    }

    private void sendPressEvent() throws Exception {
        ProxyParameters proxyParameters = new ProxyParameters();
        proxyParameters.add(Parameter.ACTION, Parameter.KEY);
        proxyParameters.add(Parameter.KEY_CODE, code + "");
        proxyParameters.add(Parameter.KEY_EVENT, KeyEvent.Event.PRESS.name());
        proxyParameters.add(Parameter.KEY_MODE, mode.name());
        proxyParameters.add(Parameter.KEY_HEARTBEAT_INTERVAL, heartbeatIntetval + "");
        proxyParameters.add(Parameter.KEY_HEARTBEAT_WAIT_TIME, heartbeatWaitTime + "");
        Request key_event = new Request(proxyParameters);
        GlobalVariable.clientThread.Request(key_event);
        String result = key_event.getResponseParameters().getValue("result");
        String message = key_event.getResponseParameters().getValue("message");
        if (result.equals(Parameter.RESULT_1)) {
            //System.out.println(message);
        } else {
            throw new Exception(message);
        }
    }

    public void setRelease() {
        release = true;
        switch (mode) {
            case PRESS:
                break;
            case PRESS_RELEASE:
                try {
                    sendReleaseEvent();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case PRESS_HEARTBEAT_RELEASE:
                timer.stop();
                try {
                    sendReleaseEvent();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public boolean isRelease() {
        return release;
    }

    private void sendReleaseEvent() throws Exception {
        ProxyParameters proxyParameters = new ProxyParameters();
        proxyParameters.add(Parameter.ACTION, Parameter.KEY);
        proxyParameters.add(Parameter.KEY_CODE, code + "");
        proxyParameters.add(Parameter.KEY_EVENT, KeyEvent.Event.RELEASE.name());
        proxyParameters.add(Parameter.KEY_MODE, mode.name());
        Request key_event = new Request(proxyParameters);
        GlobalVariable.clientThread.Request(key_event);
        String result = key_event.getResponseParameters().getValue("result");
        String message = key_event.getResponseParameters().getValue("message");
        if (result.equals(Parameter.RESULT_1)) {
            //System.out.println(message);
        } else {
            throw new Exception(message);
        }
    }

    public int getCode() {
        return code;
    }

    public KeyEvent.Mode getMode() {
        return mode;
    }

}
