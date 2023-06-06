package com.example.msparticipationcitoyen.api;

import com.example.msparticipationcitoyen.entities.Publication;
import com.example.msparticipationcitoyen.entities.Reply;
import com.example.msparticipationcitoyen.entities.TypePublication;
import com.example.msparticipationcitoyen.model.ReplyRequest;
import com.example.msparticipationcitoyen.repositories.PublicationRepository;
import com.example.msparticipationcitoyen.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Publication createPublication(@RequestBody Publication publication) {

        if (publication.getTypePublication() == null ||
                publication.getContent() == null ||
                publication.getDatePublication() == null ||
                publication.getIdCitizen() == null || publication.getWilaya() == null ||
                publication.getCommune() == null || publication.getFullNameCitizen() == null) {
            throw new IllegalArgumentException("Required fields are missing.");
        }

        return publicationRepository.save(publication);
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
        Optional<Publication> publication = publicationRepository.findById(publicationId);

        if (publication.isPresent()) {
            Reply reply = new Reply();

            reply.setDateReply(replyRequest.getDateReply());
            reply.getPublication().setIdPublication(publicationId);
            reply.setPicture(replyRequest.getPicture());
            reply.setContent(replyRequest.getContent());

//            reply.setIdAgent(replyRequest.getIdAgent());
//            reply.setEmployeeName(replyRequest.getEmployeeName());
            //reply.setPublication(publication.get());
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
            replyRepository.deleteById(replyId);
            return ResponseEntity.ok("Reply deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

