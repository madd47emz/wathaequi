package com.example.msgestiondocumentv2.Controller;


import com.example.msgestiondocumentv2.Dao.PaperRepo;
import com.example.msgestiondocumentv2.Dto.PaperRequest;
import com.example.msgestiondocumentv2.Dto.Citizen;
import com.example.msgestiondocumentv2.Entity.Attachement;
import com.example.msgestiondocumentv2.Entity.Paper;
import com.example.msgestiondocumentv2.Proxy.CitizenProx;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api")
public class Controller {
    @Autowired
    private CitizenProx citizenProx;


    @Autowired
    private GridFsTemplate gridFsTemplate;


    @Autowired
    private PaperRepo paperRepo;


//    @PreAuthorize("hasRole('ROLE_CITIZEN')")
//    @GetMapping("/header")
//    public String getSomeResource() {
//        return "hiii i am Citizen";
//
//    }
//    @PreAuthorize("hasRole('ROLE_AGENT')")
//    @GetMapping("/agent" )
//    public String getResource() {
//        return "hiii i am Agent";
//
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @RequestMapping(value="/adminping", method = RequestMethod.GET)
//    public String adminPing(){
//        return "Only Admins Can Read This";
//    }
    @GetMapping("user/documents/{nin}")
    public List<Paper> getUserDocuments(@PathVariable("nin") String nin,@RequestHeader("Authorization") String authorizationHeader){
        String bearerToken = authorizationHeader.replace("Bearer ", "");


        if(citizenProx.getUserDetails(nin, "Bearer " + bearerToken)!=null){
            return paperRepo.findPapersByUserId(nin);
        }
        return null;


    }

    @GetMapping("/naissance/user/{nin}")
    public ResponseEntity<byte[]> downloadFilledPDF(@PathVariable("nin") String nin,@RequestHeader("Authorization") String authorizationHeader

    ) throws IOException, DocumentException {
        String bearerToken = authorizationHeader.replace("Bearer ", "");

        // Use the Feign client to get user details with the bearer token
        Citizen user = citizenProx.getUserDetails(nin, "Bearer " + bearerToken);

        if(user!=null){
            if(paperRepo.existsPaperByUserIdAndNameIgnoreCase(nin,"Extrait de naissance")){


        PdfReader reader = new PdfReader("naissance.pdf");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, outputStream);
        AcroFields fields = stamper.getAcroFields();



        fields.setField("nameLatin", user.getFullNameLat());

        fields.setField("nameAr", user.getFullNameAr());
        fields.setField("dob", user.getBirthdate().toString());
        fields.setField("pob", user.getCommune());
            fields.setField("baladia", user.getCommune());
            fields.setField("wilaya", user.getWilaya());


        fields.setField("gender", user.getGender());
        if(user.getStatus()!="SINGLE"){
            if(user.getStatus()=="Married"){
                fields.setField("status", "تزوج من");
            }
            if(user.getStatus()=="Dead"){
                fields.setField("status", "توفي يوم");

            }
        }
        fields.setField("status", "/لاشيء/");
        stamper.close();
        reader.close();

        byte[] pdfBytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", user.getNin()+"birth_certificate.pdf");

        return new ResponseEntity<>(pdfBytes, headers, 200);}

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("download/folder/file/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (gridFSFile == null) {
            throw new FileNotFoundException("File not found with id: " + id);
        }

        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
        MediaType mediaType = MediaType.parseMediaType(gridFSFile.getMetadata().getString("contentType"));


        return ResponseEntity.ok()
                .contentType(mediaType)
                .headers(headers)
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/document")
    public String createDocument(@RequestBody PaperRequest payload,@RequestHeader("Authorization") String authorizationHeader){
        String bearerToken = authorizationHeader.replace("Bearer ", "");
        if(payload.getAgentId()!=payload.getUserId()){
            Citizen agent = citizenProx.getUserDetails(payload.getAgentId(), "Bearer " + bearerToken);
            Citizen user = citizenProx.getUserDetails(payload.getAgentId(), "Bearer " + bearerToken);
        if(user!=null && agent!=null){




        Paper paper = new Paper();
        paper.setName(payload.getName());
        paper.setType(payload.getType());
        paper.setCreated(payload.getCreated());
        paper.setExpiration(payload.getExpiration());
        paper.setDemandeId(payload.getDemandeId());
        paper.setAgentId(payload.getAgentId());
        paper.setUserId(payload.getUserId());

        return paperRepo.save(paper).getId();}

        return "actors id not found";}
        return "agent should not process himself";


    }

    @PatchMapping ("/document/folder/{id}")
    public List<String> setFolder(@RequestParam("file") MultipartFile[] file,@PathVariable("id") String id) throws IOException {
        List<Attachement> folder = new ArrayList<>();
        List<String> filesId = new ArrayList<>();
        if(paperRepo.existsById(id)){
            if (Objects.equals(paperRepo.findById(id).get().getType(), "folder")) {

                Paper paper = paperRepo.findById(id).get();
                for (int i = 0; i < file.length; i++) {
                    DBObject metaData = new BasicDBObject();
                    metaData.put("contentType", file[i].getContentType());

                    InputStream inputStream = file[i].getInputStream();

                    ObjectId fileId = gridFsTemplate.store(inputStream, file[i].getOriginalFilename(), metaData);

                    Attachement fileEntity = new Attachement();
                    filesId.add(fileId.toString());
                    fileEntity.setId(fileId.toString());
                    fileEntity.setFilename(file[i].getOriginalFilename());
                    fileEntity.setLength(file[i].getSize());
                    fileEntity.setContentType(file[i].getContentType());
                    folder.add(fileEntity);


                }
                paper.setFolder(folder);

                paperRepo.save(paper);
                return filesId;
            }
            filesId.add("only folder files that are allowed to dispose folder");
            return filesId;

        }
        filesId.add("Invalid document Id");
        return filesId;




    }

    @DeleteMapping("/documents/{id}")
    public String deleteDocument(@PathVariable String id) {
        if(paperRepo.existsById(id)){
            paperRepo.deleteById(id);
            return "Deleted";
        }

        return "invalid document id";
    }




}



