package com.example.msdocument.Controller;

import com.example.msdocument.Entity.Documents;
import com.example.msdocument.Repository.DocRep;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocController {
    @Autowired
    private DocRep docRep;
/**
    // Create a new document
    @PostMapping("/cree")
    public ResponseEntity<Documents> createDocument(@RequestBody Documents document) {
        Documents savedDocument = docRep.save(document);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }


    // Retrieve all documents
    @GetMapping("/all")
    public ResponseEntity<List<Documents>> getAllDocuments() {
        List<Documents> documentsList = docRep.findAll();
        return new ResponseEntity<>(documentsList, HttpStatus.OK);
    }

    // Retrieve a single document by ID
    @GetMapping("/{id}")
    public ResponseEntity<Documents> getDocumentById(@PathVariable("id") Long id) {
        Documents document = docRep.findById(id).orElse(null);
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(document, HttpStatus.OK);
        }
    }

    // Update a document
    @PutMapping("/{id}")
    public ResponseEntity<Documents> updateDocument(@PathVariable("id") Long id, @RequestBody Documents document) {
        Documents existingDocument = docRep.findById(id).orElse(null);
        if (existingDocument == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            existingDocument.setType(document.getType());
            existingDocument.setDateDeCreation(document.getDateDeCreation());
            existingDocument.setDateExpiration(document.getDateExpiration());
            existingDocument.setFile(document.getFile());
            existingDocument.setDemandes(document.getDemandes());

            Documents updatedDocument = docRep.save(existingDocument);
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        }
    }

    // Delete a document
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDocument(@PathVariable("id") Long id) {
        try {
            docRep.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

**/

}
