package cn.stylefeng.guns.controller;


import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;


/**
 * 类说明：文件上传下载工具类
 */
@Component
public class FtpOperation {
    private static final Logger LOGGER = LoggerFactory.getLogger(FtpOperation.class);

    @Value("${ftp.erp.username}")
    private String userName;

    @Value("${ftp.erp.password}")
    private String passWord;

    @Value("${ftp.erp.hostname}")
    private String ip;

    @Value("${ftp.erp.port}")
    private int port;

    @Value("${ftp.erp.path}")
    private String currentDir;     // 文件存放的目录

    public static final String DIRSPLIT = "/";

    // 下载的文件目录
    private String downloadDir;

    // ftp客户端
    private FTPClient ftpClient = new FTPClient();

    /**
     * 功能：上传文件附件到文件服务器
     *
     * @param buffIn:上传文件流
     * @param fileName：保存文件名称
     * @param needDelete：是否同时删除
     * @return
     * @throws IOException
     */
    public boolean uploadToFtp(InputStream buffIn, String fileName, boolean needDelete)
            throws FTPConnectionClosedException, IOException, Exception {
        boolean returnValue = false;
        // 上传文件
        try {
            // 建立连接
            connectToServer();
            // 设置传输二进制文件
            setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new IOException("failed to connect to the FTP Server:" + ip);
            }
            ftpClient.enterLocalPassiveMode();
               /* if(StringUtils.checkStr(CURRENT_DIR)){
                	if(!existDirectory(CURRENT_DIR)){
                		this.createDirectory(CURRENT_DIR);
                	}
                    ftpClient.changeWorkingDirectory(CURRENT_DIR);
                }*/
            // 上传文件到ftp
            returnValue = ftpClient.storeFile(fileName, buffIn);
            if (needDelete) {
                ftpClient.deleteFile(fileName);
            }
            // 输出操作结果信息
            if (returnValue) {
                LOGGER.info("uploadToFtp INFO: upload file  to ftp : succeed!");
            } else {
                LOGGER.info("uploadToFtp INFO: upload file  to ftp : failed!");
            }
            buffIn.close();
            // 关闭连接
            closeConnect();
        } catch (FTPConnectionClosedException e) {
            LOGGER.error("ftp连接被关闭！", e);
            throw e;
        } catch (Exception e) {
            returnValue = false;
            LOGGER.error("ERR : upload file  to ftp : failed! ", e);
            throw e;
        } finally {
            try {
                if (buffIn != null) {
                    buffIn.close();
                }
            } catch (Exception e) {
                LOGGER.error("ftp关闭输入流时失败！", e);
            }
            if (ftpClient.isConnected()) {
                closeConnect();
            }
        }
        return returnValue;
    }


    /**
     * 功能：根据文件名称，下载文件流
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public InputStream downloadFile(String filename)
            throws IOException {
        InputStream in = null;
        try {
            // 建立连接
            connectToServer();
            ftpClient.enterLocalPassiveMode();
            // 设置传输二进制文件
            setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new IOException("failed to connect to the FTP Server:" + ip);
            }
            ftpClient.changeWorkingDirectory(currentDir);

            // ftp文件获取文件
            in = ftpClient.retrieveFileStream(filename);
        } catch (FTPConnectionClosedException e) {
            LOGGER.error("ftp连接被关闭！", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("download file " + filename + " from ftp : failed!", e);
        }
        return in;
    }


    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            ftpClient.connect("214.72.20.106", 21); // 连接ftp服务器
            ftpClient.login("zbwallet", "ZB.wallet"); // 登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("connect failed...ftp服务器:");
            }
            System.out.println("connect successfu...ftp服务器:");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        /**
         * * 下载文件 *
         *
         * @param pathname
         *            FTP服务器文件目录 *
         * @param filename
         *            文件名称 *
         * @param localpath
         *            下载后的文件路径 *
         * @return
         */
        public boolean downloadFile2(String pathname, String filename, String localpath){
            boolean flag = false;
            OutputStream os = null;
            try {
                System.out.println("开始下载文件");
                initFtpClient();
                // 切换FTP目录
                boolean changeFlag = ftpClient.changeWorkingDirectory(pathname);
                System.out.println("changeFlag==" + changeFlag);

                ftpClient.enterLocalPassiveMode();
                ftpClient.setRemoteVerificationEnabled(false);
                // 查看有哪些文件夹 以确定切换的ftp路径正确
                String[] a = ftpClient.listNames();
                System.err.println(a[0]);

                FTPFile[] ftpFiles = ftpClient.listFiles();
                for (FTPFile file : ftpFiles) {
                    if (filename.equalsIgnoreCase(file.getName())) {
                        File localFile = new File(localpath + "/" + file.getName());
                        os = new FileOutputStream(localFile);
                        ftpClient.retrieveFile(file.getName(), os);
                        os.close();
                    }
                }
                ftpClient.logout();
                flag = true;
                System.out.println("下载文件成功");
            } catch (Exception e) {
                System.out.println("下载文件失败");
                e.printStackTrace();
            } finally {
                if (ftpClient.isConnected()) {
                    try {
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != os) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return flag;
        }

        /**
         * 转码[GBK -> ISO-8859-1] 不同的平台需要不同的转码
         *
         * @param obj
         * @return
         */
//    private String gbkToIso8859(Object obj) {
//        try {
//            if (obj == null)
//                return "";
//            else
//                return new String(obj.toString().getBytes("GBK"), "iso-8859-1");
//        } catch (Exception e) {
//            return "";
//        }
//    }

        /**
         * 设置传输文件的类型[文本文件或者二进制文件]
         *
         * @param fileType --BINARY_FILE_TYPE、ASCII_FILE_TYPE
         */
        private void setFileType ( int fileType){
            try {
                ftpClient.setFileType(fileType);
            } catch (Exception e) {
                LOGGER.error("ftp设置传输文件的类型时失败！", e);
            }
        }

        /**
         * 功能：关闭连接
         */
        public void closeConnect () {
            try {
                if (ftpClient != null) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (Exception e) {
                LOGGER.error("ftp连接关闭失败！", e);
            }
        }

        /**
         * 连接到ftp服务器
         */
        private void connectToServer () throws FTPConnectionClosedException, Exception {
            if (!ftpClient.isConnected()) {
                int reply;
                try {
                    ftpClient = new FTPClient();
                    ftpClient.connect(ip, port);
                    ftpClient.login(userName, passWord);
                    reply = ftpClient.getReplyCode();

                    if (!FTPReply.isPositiveCompletion(reply)) {
                        ftpClient.disconnect();
                        LOGGER.info("connectToServer FTP server refused connection.");
                    }
                    LOGGER.info("FTP 服务器ip:{} port:{} username:{} password:{}", ip, port, userName, passWord);
                } catch (FTPConnectionClosedException ex) {
                    LOGGER.error("服务器:IP：" + ip + "没有连接数！there are too many connected users,please try later", ex);
                    throw ex;
                } catch (Exception e) {
                    LOGGER.error("登录ftp服务器【" + ip + "】失败", e);
                    throw e;
                }
            }
        }

        // Check the path is exist; exist return true, else false.
        public boolean existDirectory (String path) throws IOException {
            boolean flag = false;
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            for (FTPFile ftpFile : ftpFileArr) {
                if (ftpFile.isDirectory()
                        && ftpFile.getName().equalsIgnoreCase(path)) {
                    flag = true;
                    break;
                }
            }
            return flag;
        }

        /**
         * 创建FTP文件夹目录
         *
         * @param pathName
         * @return
         * @throws IOException
         */
        public boolean createDirectory (String pathName) throws IOException {
            boolean isSuccess = false;
            try {
                isSuccess = ftpClient.makeDirectory(pathName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isSuccess;
        }

        /**
         * 带点的
         *
         * @param fileName
         * @return
         */
        public static String getExtention (String fileName){
            int pos = fileName.lastIndexOf(".");
            return fileName.substring(pos);
        }

        /**
         * 不带点
         *
         * @param fileName
         * @return
         */
        public static String getNoPointExtention (String fileName){
            int pos = fileName.lastIndexOf(".");
            return fileName.substring(pos + 1);
        }

        /**
         * 功能：根据当前时间获取文件目录
         *
         * @return String
         */
        public static String getDateDir (Date dateParam){
            Calendar cal = Calendar.getInstance();
            if (null != dateParam) {
                cal.setTime(dateParam);
            }
            int currentYear = cal.get(Calendar.YEAR);
            int currentMouth = cal.get(Calendar.MONTH) + 1;
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);
            //int currentHour = cal.get(Calendar.HOUR_OF_DAY);
            //return currentYear+FtpOperation.DIRSPLIT+currentMouth+FtpOperation.DIRSPLIT+currentDay+FtpOperation.DIRSPLIT+currentHour;
            return currentYear + FtpOperation.DIRSPLIT + currentMouth + FtpOperation.DIRSPLIT + currentDay;
        }


    }

