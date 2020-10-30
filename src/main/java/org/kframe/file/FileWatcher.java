package org.kframe.file;

import java.io.IOException;

/**
 * @author fangkun
 * @date 2020/10/30 10:53
 * @description: 文件监控接了
 */
public interface FileWatcher {
    public void initPath(String path) throws IOException;
}
