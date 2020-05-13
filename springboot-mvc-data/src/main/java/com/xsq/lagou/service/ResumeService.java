package com.xsq.lagou.service;

import com.xsq.lagou.pojo.Resume;

import java.util.List;

/**
 * @ClassName ResumeService
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 15:39
 **/
public interface ResumeService {

    List<Resume> queryResumeList() throws Exception;

    void addNewResume(Resume resume) throws Exception;

    void delResume(Long id) throws Exception;

    void updateResume(Resume resume);
}
