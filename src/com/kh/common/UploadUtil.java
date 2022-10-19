package com.kh.common;
import com.kh.board.model.vo.Attachment;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class UploadUtil{

    private String uploadPath;
    private ServletContext app;
    private static String upfilesPath;
    /* 생성 메서드 */
    public static UploadUtil create(ServletContext app) {
        upfilesPath="/resources/board_upfiles";
        UploadUtil uploadUtil = new UploadUtil();
        uploadUtil.setApp(app);
        uploadUtil.setUploadPath(app.getRealPath(upfilesPath));
        return uploadUtil;
    }
    private void setApp(ServletContext app) {
        this.app = app;
    }
    private void setUploadPath(String realPath) {
        this.uploadPath = realPath;
    }

    /* 파일 저장 */
    public Attachment saveFiles(Part filePart, String folderPath) {
        String realPath = this.uploadPath + File.separator + folderPath;
        String changeName=rename(filePart.getSubmittedFileName());
        String filePath = realPath +File.separator+ changeName;
        Attachment at=null;
        try(
                InputStream fis = filePart.getInputStream();
                OutputStream fos = new FileOutputStream(filePath);)
        {
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = fis.read(buf, 0, 1024)) != -1)
            {
                fos.write(buf, 0, len);
            }
            fos.flush();
            at=new Attachment(filePart.getSubmittedFileName(),changeName,upfilesPath+File.separator+folderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return at;
    }
    /*/resou 하위 폴더 경로 생성 */
    public String createFilePath(String category) {
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String[] paths = formatter.format(date).split("/");

        String result =category+File.separator+paths[0] + File.separator + paths[1] + File.separator + paths[2];

        createFolders(result);

        return result;
    }
    public boolean deleteFile(Attachment at){
        String FilePath=at.getFilePath();
        String fileName=at.getChangeName();
        File file=new File(app.getRealPath(FilePath)+File.separator+fileName);
        if(file.exists()){
            if(file.delete()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }
    private void createFolders(String paths) {

        File folders = new File(uploadPath, paths);
        if(!folders.exists())
            folders.mkdirs();
    }

    public String rename(String originName){
        //수정 파일명 : 파일업로드된 시간 (년월시분초)+5자리 랜덤값=>최대한 겹치지 않게
        //확장자 : 원본파일의 확장자 그대로

        //원본명 =>수정명
        // aaa.jpg =>20221017192625111111.jpg
        //
        String ext=originName.substring(originName.indexOf("."));
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String ranNum=String.valueOf((int)((Math.random()*90000)+10000));
        return formatter.format(date)+ranNum+ext;
    }
}