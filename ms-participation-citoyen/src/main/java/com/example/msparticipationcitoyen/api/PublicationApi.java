package com.example.msparticipationcitoyen.api;

import com.example.msparticipationcitoyen.entities.Publication;
import com.example.msparticipationcitoyen.entities.Reply;
import com.example.msparticipationcitoyen.entities.TypePublication;
import com.example.msparticipationcitoyen.model.ReplyRequest;
import com.example.msparticipationcitoyen.repositories.PublicationRepository;
import com.example.msparticipationcitoyen.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ms-participation-citoyen")
public class PublicationApi {



    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    PublicationRepository publicationRepository;



    //Afficher publications par citoyen
    @GetMapping("citizen/{idC}/publications")
    public List<Publication> findPublicationsOfCitizen(@PathVariable(value = "idC") String idC) {
            return publicationRepository.findPublicationsByIdCitizen(idC);

    }


    //Afficher tous les avis
    @GetMapping("/avis")
    public List<Publication> findAvis() {
//        return publicationRepository.findPublicationsByTypePublication(TypePublication.Avis);
        return publicationRepository.findPublicationsByTypePublication(TypePublication.Avis);
    }


    //Afficher tous les signalement par citoyen

    @GetMapping("citizen/{idC}/signalements")
    public List<Publication> findSignalementCitizen(@PathVariable(value = "idC") String idC) {

        return publicationRepository.findPublicationsByTypePublicationAndIdCitizen(TypePublication.Signalement, idC);
    };



    //Afficher publications par commune and wilaya
    @GetMapping("/publications/{wilaya}/{commune}")

    public List<Publication> findPublicationsByCommune(@PathVariable(value = "commune") String commune,@PathVariable(value = "wilaya") String wilaya) {
        return publicationRepository.findPublicationsByCommuneAndWilaya(commune,wilaya);
    }


    //Afficher signalement par commune et wilaya
    @GetMapping("/signalement/{wilaya}/{commune}")

    public List<Publication> signalementsParCommuneWilaya(@PathVariable(value = "commune") String commune,@PathVariable(value = "wilaya") String wilaya) {
        return publicationRepository.findPublicationsByTypePublicationAndCommuneAndWilaya(TypePublication.Signalement,commune,wilaya);
    }

    //Afficher avis par commune et wilaya
    @GetMapping("/avis/{wilaya}/{commune}")

    public List<Publication> avisParCommuneWilaya(@PathVariable(value = "commune") String commune,@PathVariable(value = "wilaya") String wilaya) {
        return publicationRepository.findPublicationsByTypePublicationAndCommuneAndWilaya(TypePublication.Avis,commune,wilaya);
    }





    //Afficher reponces par publication
    @GetMapping("publications/{idP}/reponces")
    public List<Reply> findReplies(@PathVariable(value = "idP") Long idP) {
        Optional<Publication> existingPublication = publicationRepository.findById(idP);
        if (existingPublication.isPresent()) {
            return replyRepository.findRepliesByPublicationIdPublication(idP);
        } else {
            return null;
        }
    }

    // Creer une publication

    @PostMapping("/publications")
    public ResponseEntity<String> createPublication(
            @RequestParam("typePublication") String typePublication,
            @RequestParam("content") String content,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("adresse") String adresse,
            @RequestParam("commune") String commune,
            @RequestParam("wilaya") String wilaya,
            @RequestParam("datePublication") String datePublication,
            @RequestParam("fullNameCitizen") String fullNameCitizen,
            @RequestParam("idCitizen") String idCitizen
    ) {
        try {
            System.out.println("typePublication = " + typePublication + ", content = " + content + ", picture = " + picture + ", adresse = " + adresse + ", commune = " + commune + ", wilaya = " + wilaya + ", datePublication = " + datePublication + ", fullNameCitizen = " + fullNameCitizen + ", idCitizen = " + idCitizen);

            Publication publication = new Publication();
            publication.setTypePublication(TypePublication.valueOf(typePublication));
            publication.setContent(content);
            publication.setPicture(picture.getBytes());
            publication.setAdresse(adresse);
            publication.setCommune(commune);
            publication.setWilaya(wilaya);
            publication.setDatePublication(Date.valueOf(datePublication));
            publication.setFullNameCitizen(fullNameCitizen);
            publication.setIdCitizen(idCitizen);

            publicationRepository.save(publication);

            return ResponseEntity.ok("Publication created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create publication: " + e.getMessage());
        }
    }

    // Update a publication
    @PutMapping("/publications/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable("id") Long id, @RequestBody Publication publication) {
        Publication existingPublication = publicationRepository.findById(id).get();
        if (existingPublication != null) {

            existingPublication.setContent(publication.getContent());
            Publication updatedPublication = publicationRepository.save(publication);
            return ResponseEntity.ok(updatedPublication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Delete a publication
    @DeleteMapping("/publications/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable("id") Long id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        if (publication.isPresent()) {
            publicationRepository.deleteById(id);
            return ResponseEntity.ok("Publication deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new reply for a publication
    @PostMapping("/publications/{publicationId}/replies")
    public ResponseEntity<Reply> createReply(@PathVariable("publicationId") Long publicationId, @RequestBody ReplyRequest replyRequest) {
        Publication publication = publicationRepository.findById(publicationId).get();

        if (publication!=null) {
            Reply reply = new Reply();

            reply.setDateReply(replyRequest.getDateReply());
            reply.setPublication(publication);
            //reply.setPicture(replyRequest.getPicture());
            reply.setContent(replyRequest.getContent());
            Reply createdReply = replyRepository.save(reply);
            return ResponseEntity.ok(createdReply);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a reply
    @PutMapping("/publications/{publicationId}/reponces/{replyId}")
    public ResponseEntity<Reply> updateReply(@PathVariable("publicationId") Long publicationId,
                                             @PathVariable("replyId") Long replyId,
                                             @RequestBody Reply updatedReply) {
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        Optional<Reply> existingReply = replyRepository.findById(replyId);

        if (publication.isPresent() && existingReply.isPresent()) {
            Reply reply = existingReply.get();
            reply.setContent(updatedReply.getContent());
            // Update other fields of the reply

            Reply updatedReplyEntity = replyRepository.save(reply);
            return ResponseEntity.ok(updatedReplyEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a reply
    @DeleteMapping("/publications/{publicationId}/reponces/{replyId}")
    public ResponseEntity<String> deleteReply(@PathVariable("publicationId") Long publicationId,
                                              @PathVariable("replyId") Long replyId) {
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        Optional<Reply> reply = replyRepository.findById(replyId);

        if (publication.isPresent() && reply.isPresent()) {
            System.out.println("publicationId = " + publicationId + ", replyId = " + replyId);
            replyRepository.deleteById(replyId);
            return ResponseEntity.ok("Reply deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

