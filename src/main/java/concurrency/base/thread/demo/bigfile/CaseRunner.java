package concurrency.base.thread.demo.bigfile;

import concurrency.base.util.Debug;

public class CaseRunner {
    public static void main(String[] args) throws Exception {
        if (0 == args.length) {
            int nthread = Runtime.getRuntime().availableProcessors() * 2;
            args = new String[]{"http://forspeed.onlinedown.net/down/newdown/1/17/PCQQ2020.zip", Integer.toString(nthread), "3"};
        }
        main0(args);
    }

    public static void main0(String[] args) throws Exception {
        final int argc = args.length;
        BigFileDownloader downloader = new BigFileDownloader(args[0]);

        // 下载线程数
        int workerThreadsCount = argc >= 2 ? Integer.valueOf(args[1]) : 2;
        long reportInterval = argc >= 3 ? Integer.valueOf(args[2]) : 2;

        Debug.info("downloading %s%nConfig:worker threads:%s,reportInterval:%s s.",
                args[0], workerThreadsCount, reportInterval);

        downloader.download(workerThreadsCount, reportInterval * 1000);
    }
}