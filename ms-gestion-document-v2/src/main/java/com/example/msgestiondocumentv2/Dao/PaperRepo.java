package com.example.msgestiondocumentv2.Dao;


import com.example.msgestiondocumentv2.Entity.Paper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepo extends MongoRepository<Paper, String> {
    List<Paper> findPapersByUserId(String userId);
    Boolean existsPaperByUserId(String userId);
    Boolean existsPaperByUserIdAndNameIgnoreCase(String userId, String name);
}
