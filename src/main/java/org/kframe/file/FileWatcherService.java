package org.kframe.file;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * @author fangkun
 * @date 2020/10/30 10:50
 * @description: 文件监控服务
 */
public class FileWatcherService implements FileWatcher {


    private WatchService watcher;

    public FileWatcherService() throws IOException {
        watcher = FileSystems.getDefault().newWatchService();
    }

    @Override
    public void initPath(String path) throws IOException {
        Path filePath = Paths.get(path);
        filePath.register(watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
    }


    public void listener() throws InterruptedException {
        while (true) {
            final WatchKey poll = watcher.take();
            final List<WatchEvent<?>> watchEvents = poll.pollEvents();
            for (WatchEvent<?> watchEvent : watchEvents) {
                final WatchEvent.Kind<?> kind = watchEvent.kind();
                System.out.println(kind);
//                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
//
//                }
//                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
//                    kind.name();
//                }
//
//                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
//
//                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            final FileWatcherService fileWatcherService = new FileWatcherService();
            fileWatcherService.initPath("D:\\aa\\");
            fileWatcherService.listener();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
