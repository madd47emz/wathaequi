package com.example.msparticipationcitoyen.api;

import com.example.msparticipationcitoyen.entities.Citizen;
import com.example.msparticipationcitoyen.entities.Publication;
import com.example.msparticipationcitoyen.entities.Reply;
import com.example.msparticipationcitoyen.entities.TypePublication;
import com.example.msparticipationcitoyen.proxy.CitizenProxy;
import com.example.msparticipationcitoyen.repositories.CitizenRepository;
import com.example.msparticipationcitoyen.repositories.PublicationRepository;
import com.example.msparticipationcitoyen.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PublicationApi {
    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    CitizenProxy citizenProxy;

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    PublicationRepository publicationRepository;

    @GetMapping("/citizen/{id}")
//GET http://localhost:8081/api/citizen/1
    Citizen getCitizenWithPublication(@PathVariable("id") Long id) {

        Citizen citizen = citizenRepository.findById(id).get();

        citizen.setCitizenAuth(citizenProxy.getCitizenAuth(id, "toparticipation_citoyen"));

        return citizen;
    }

//Afficher publications par citoyen
    @GetMapping("citizen/{idC}/publications")
    public List<Publication> findPublicationsByCitizenIdCitizen(@PathVariable(value = "idC") Long idC) {
        return publicationRepository.findPublicationsByCitizenIdCitizen(idC);


    }
    //Afficher publications par commune
    @GetMapping("/publications/{commune}")
    public List<Publication> findPublicationsByCommune(@PathVariable(value = "commune") String commune) {
        return publicationRepository.findPublicationsByCitizenCommune(commune);
    }
    //Afficher reponces par publication
    @GetMapping("publications/{idP}/reponces")
    public List<Reply> findReplies(@PathVariable(value = "idP") Long idP) {
        return replyRepository.findRepliesByPublicationIdPublication(idP);
    }

    // Creer une publication
    @PostMapping("/publications")
    public Publication createPublication(@RequestBody Publication publication) {
        return publicationRepository.save(publication);
    }

    // Update a publication
    @PutMapping("/publications/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable("id") Long id, @RequestBody Publication publication) {
        Optional<Publication> existingPublication = publicationRepository.findById(id);
        if (existingPublication.isPresent()) {
            publication.setIdPublication(id);
            Publication updatedPublication = publicationRepository.save(publication);
            return ResponseEntity.ok(updatedPublication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Delete a publication
    @DeleteMapping("/publications/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable("id") Long id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        if (publication.isPresent()) {
            publicationRepository.delete(publication.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new reply for a publication
    @PostMapping("/publications/{publicationId}/replies")
    public ResponseEntity<Reply> createReply(@PathVariable("publicationId") Long publicationId, @RequestBody Reply reply) {
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        if (publication.isPresent()) {
            reply.setPublication(publication.get());
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
    public ResponseEntity<Void> deleteReply(@PathVariable("publicationId") Long publicationId,
                                            @PathVariable("replyId") Long replyId) {
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        Optional<Reply> reply = replyRepository.findById(replyId);

        if (publication.isPresent() && reply.isPresent()) {
            replyRepository.delete(reply.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}