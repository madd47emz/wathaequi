package com.example.msgestiondocumentv2.Dao;



import com.example.msgestiondocumentv2.Entity.Attachement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepo extends MongoRepository<Attachement, String> {

}
