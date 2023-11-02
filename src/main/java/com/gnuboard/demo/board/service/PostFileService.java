package com.gnuboard.demo.board.service;


import com.gnuboard.demo.board.domain.BoardFile;
import com.gnuboard.demo.board.domain.BoardPost;
import com.gnuboard.demo.board.domain.PostFile;
import com.gnuboard.demo.board.repository.BoardFileRepository;
import com.gnuboard.demo.board.repository.BoardPostRepository;
import com.gnuboard.demo.board.repository.PostFileRepository;
import com.gnuboard.demo.user.adaptor.MemberAdaptor;
import com.gnuboard.demo.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostFileService {

    private final PostFileRepository postFileRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardPostRepository boardPostRepository;

    @Value("${file.upload.path}")
    private String uploadPath;


    public void uploadFile(List<MultipartFile> fileList , BoardPost boardPost , MemberAdaptor memberAdaptor) throws IOException {
        BoardFile boardFile = null;
        if(fileList != null){
            for(MultipartFile mf : fileList){

                //파일저장 로직 필요
                String addPath = getTimeStemp();
                String originalName = mf.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf(".")+1);
                log.info("file name : {}",originalName);
                log.info("file extension : {}",extension);

                String saveName = UUID.randomUUID().toString()+"."+extension;
                //uploadPath = uploadPath+ File.separator+ DateUtil.getDateTimeAsString();
                uploadPath = getUploadPath(DateUtil.getDateTimeAsString())+File.separator;
                        //uploadPath = uploadPath+"/"+ DateUtil.getDateTimeAsString();
                log.info("uploadPath : {}" , uploadPath);
                mf.transferTo(new File(uploadPath+saveName));


                boardFile = BoardFile.builder()
                        .source(uploadPath)
                        .content(mf.getOriginalFilename())
                        .download(0)
                        .createdAt(LocalDateTime.now())
                        .createdBy(Long.valueOf(1))
                        .modifierAt(LocalDateTime.now())
                        .modifiedBy(Long.valueOf(1))
                        //.postFile(new PostFile())
                        .filesize(Long.valueOf(mf.getSize()).intValue())
                        .originalName(originalName)
                        .extensionName(extension)
                        .savedName(saveName)
                        .build();

                PostFile postFile = PostFile.builder()
                        .delYn("Y")
                        .boardPost(boardPost)
                        .boardFile(boardFile)
                        .build();

                boardFileRepository.save(boardFile);
                postFileRepository.save(postFile);

            }
        }else{
            log.info("fileList is null");
        }



        //




    }



    public String getTimeStemp(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }


    /**
     * 업로드 경로 반환
     * @return 업로드 경로
     */
    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }
    /**
     * 업로드 경로 반환
     * @param addPath - 추가 경로
     * @return 업로드 경로
     */
    private String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }


    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path - 업로드 경로
     * @return 업로드 경로
     */
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }
}
