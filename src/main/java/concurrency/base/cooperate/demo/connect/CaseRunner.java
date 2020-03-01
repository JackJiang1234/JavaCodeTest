package concurrency.base.cooperate.demo.connect;

import concurrency.base.util.Tools;

public class CaseRunner {
    final static AlarmAgent alarmAgent;

    static {
        alarmAgent = AlarmAgent.getInstance();
        alarmAgent.init();
    }

    public static void main(String[] args) throws InterruptedException {

        alarmAgent.sendAlarm("Database offline!");
        Tools.randomPause(12000);
        alarmAgent.sendAlarm("XXX service unreachable!");
    }
}
