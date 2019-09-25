package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.PDSBoard;
import kr.ranian.bootboard.domain.PDSFile;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by ranian129@gmail.com on 2019-09-25
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
@Log
@Commit
public class PDSBoardRepositoryTest {

    @Autowired
    PDSBoardRepository repo;

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Test
    public void testInsertPDS() {

        PDSBoard pds = new PDSBoard();
        pds.setPname("DOCUMENT 1 - 2");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1, file2));

        log.info("try to save pds");

        repo.save(pds);
    }

    @Transactional
    @Test
    public void testUpdateFileName1() {

        Long fno = 1L;
        String newName = "updatedFile1.doc";

        int count = repo.updatePDSFile(fno, newName);
        log.info("update count:" + count);
    }

    @Transactional
    @Test
    public void testUpdateFileName2() {

        String newName = "updatedFile2.doc";
        Optional<PDSBoard> result = repo.findById(2L);
        result.ifPresent(pds -> {
            log.info("데이터가 존재하므로 update 시도 ");
            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(newName);

            int idx = pds.getFiles().indexOf(target);
            if (idx > -1) {
                List<PDSFile> list = pds.getFiles();
                list.remove(idx);
                list.add(target);
                pds.setFiles(list);
            }

            repo.save(pds);
        });
    }

    @Transactional
    @Test
    public void testDeletePDSFile() {

        Long fno = 2L;
        int count = repo.deletePDSFile(fno);
        log.info("DELETE PDSFile:" + count);
    }

    @Test
    public void testInsertDummies() {

        List<PDSBoard> list = new ArrayList<>();
        IntStream.range(1, 100).forEach(i -> {
            PDSBoard pds = new PDSBoard();
            pds.setPname("자료 " + i);

            PDSFile file1 = new PDSFile();
            file1.setPdsfile("file1.doc");

            PDSFile file2 = new PDSFile();
            file2.setPdsfile("file2.doc");

            pds.setFiles(Arrays.asList(file1, file2));
            log.info("try to save pds");

            list.add(pds);
        });

        repo.saveAll(list);
    }

    @Test
    public void testViewSummary() {
        repo.getSummary().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}