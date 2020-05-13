package com.xsq.lagou.service.impl;

import com.xsq.lagou.dao.ResumeDao;
import com.xsq.lagou.pojo.Resume;
import com.xsq.lagou.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ResumeServiceImpl
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 15:40
 **/
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;



    @Override
    public List<Resume> queryResumeList() throws Exception {
        return resumeDao.findAll();
    }

    @Override
    public void addNewResume(Resume resume) throws Exception {
        resumeDao.save(resume);
    }

    @Override
    public void delResume(Long id) throws Exception {
        resumeDao.deleteById(id);
    }

    @Override
    public void updateResume(Resume resume) {
        resumeDao.save(resume);

    }
}
