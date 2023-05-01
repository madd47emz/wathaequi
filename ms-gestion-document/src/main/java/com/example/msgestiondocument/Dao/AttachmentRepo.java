package com.example.msgestiondocument.Dao;


import com.example.msgestiondocument.Entity.Attachement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface AttachmentRepo extends MongoRepository<Attachement, String> {

}
