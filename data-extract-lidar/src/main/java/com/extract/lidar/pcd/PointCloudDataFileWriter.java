package com.extract.lidar.pcd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.*;

@Slf4j
@SpringBootConfiguration
@ConfigurationProperties(prefix = "pcd")
public class PointCloudDataFileWriter {

    private String path;

    private final boolean append = false;

    private final String charset = "UTF-8";

    public void createAndFlush(String fileName, String content) {
        File file = new File(path + fileName);
        boolean createNewFile = false;
        boolean exists = file.exists();
        if (exists) {
            log.warn("文件已经存在:{}", file.getAbsolutePath());
        } else {
            try {
                createNewFile = file.createNewFile();
            } catch (IOException e) {
                log.error("创建新文件:{} 异常:{}", file.getAbsolutePath(), e.getLocalizedMessage());
            }
        }
        if (createNewFile) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file, append);
            } catch (FileNotFoundException e) {
                log.error("文件不存在");
            }
            if(fileOutputStream != null){
                OutputStreamWriter outputStreamWriter = null;
                try {
                     outputStreamWriter = new OutputStreamWriter(fileOutputStream, charset);
                } catch (UnsupportedEncodingException e) {
                    log.error("创建OutputStreamWriter异常:{}", e.getLocalizedMessage());
                }
                if(outputStreamWriter != null){
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
                    printWriter.write(content);
                    printWriter.flush();
                    log.debug("文件写入成功:{}", file.getAbsolutePath());
                }
            }
        }else{
            log.error("创建新文件异常:{}", file.getAbsolutePath());
        }


    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
