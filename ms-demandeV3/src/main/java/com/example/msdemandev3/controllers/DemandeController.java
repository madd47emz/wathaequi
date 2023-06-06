package com.example.msdemandev3.controllers;

import com.example.msdemandev3.entity.Demande;
import com.example.msdemandev3.model.Citizen;
import com.example.msdemandev3.proxy.CitizenProx;
import com.example.msdemandev3.repositories.DemandeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class DemandeController {

   @Autowired
    private DemandeRepository demandeRepository;
   @Autowired
   private CitizenProx citizenProx;


   /** Create demannde by using DemandeRequest     -------not working--------------
    * @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String demander(@RequestBody DemandeRequest demandeRequest){
        serviceDemande.demander(demandeRequest);
        return "Demande effectué avec success";
    }

    @PostMapping("/test")
    public Demande inserer(@RequestBody Demande demande) {
    return demandeRepository.save(demande);
    }
    }**/


   @GetMapping("/headerr")
   public String getrce() {
       return "hiii i am Citizen";}
    @PreAuthorize("hasRole('ROLE_AGENT')")@GetMapping("/agent" )
    public String getResource() {
        return "hiii i am Agent";}
    //Get all demandes
   //Get all demandes


    //////to afficher all verify if it's already asigned
    /**@PreAuthorize("hasRole('ROLE_AGENT')")
   @GetMapping("all")
       public List<Demande> getAllDemandes() {
       return demandeRepository.findDemandesByEtats("CREATED");
   }
**/
   //create a new demande

    @PostMapping("demander")
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande, @RequestHeader("Authorization") String authorizationHeader) {
        String bearerToken = authorizationHeader.replace("Bearer ", "");
        Citizen user = citizenProx.getUserDetails(demande.getIdUtilisateur(), "Bearer " + bearerToken);
        if (user != null) {

            demande.setDateDeCreation(new Date());
            demande.setWilaya(user.getWilaya());
            demande.setCommune(user.getCommune());
            demande.setEtats("CREATED");
            Demande savedDemande = demandeRepository.save(demande);
            return new ResponseEntity<>(savedDemande, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(new Demande(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getUserInfo/{nin}")
    public Citizen getNin(@PathVariable("nin") String nin, @RequestHeader("Authorization") String authorizationHeader){
       Citizen citizen=citizenProx.getUserDetails(nin,authorizationHeader);
       return citizen;
    }

    /////////////////////////////////////////////////////////////
    @GetMapping("/getDemandeParCommune/{wilaya}/{commune}")
    public List<Demande> getDemande(@PathVariable("wilaya") String wilaya,@PathVariable("commune") String commune){

       return demandeRepository.findDemandesByWilayaAndCommune(wilaya, commune);
    }



    //Get all demandes by id
    @GetMapping("/{id}")
    public Demande getDemandeById(@PathVariable Long id) {
        return demandeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Demande not found with id " + id));
    }

    //Update a demande by ID

    @PutMapping("/{id}")
    public Demande updateDemande(@PathVariable Long id, @RequestBody Demande updatedDemande) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Demande not found with id " + id));

        demande.setDemandeNum(updatedDemande.getDemandeNum());
        demande.setDateDeCreation(updatedDemande.getDateDeCreation());
        demande.setEtats(updatedDemande.getEtats());

        return demandeRepository.save(demande);
    }

    //delete demande by its id
    @PreAuthorize("hasRole('ROLE_AGENT')")
    @DeleteMapping("/{id}")
    public void deleteDemande(@PathVariable Long id) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Demande not found with id " + id));

        demandeRepository.delete(demande);
    }

// /demandes/{demandeId}/etat   Update the etat of a demande (possible values: missing file , ready, wait)
@PreAuthorize("hasRole('ROLE_AGENT')")
    @PutMapping("/{demandeId}/etats")
    public Demande updateEtats(@PathVariable Long demandeId, @RequestParam String etats) {
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new NotFoundException("Demande not found with id " + demandeId));

        if (!isValidEtat(etats)) {
            throw new BadRequestException("Invalid etat value: " + etats);
        }

        demande.setEtats(etats);

        return demandeRepository.save(demande);
    }



    private boolean isValidEtat(String etats) {
        return etats.equals("missing file") || etats.equals("ready") || etats.equals("wait");
    }



    ////////////////////////////////////////
    @PreAuthorize("hasRole('ROLE_AGENT')")
    @PutMapping("/{id}/assign/{nin}")
    public Demande assignAgent(@PathVariable Long id, @PathVariable String nin) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Demande not found with id " + id));

        // Check if the agent's NIN is different from the demande's idUtilisateur
        if (demande.getIdUtilisateur().equals(nin)) {
            throw new BadRequestException("Agent's NIN cannot be the same as the idUtilisateur of the Demande");
        }

        demande.setIdAgent(nin);
        demande.setEtats("wait");

        return demandeRepository.save(demande);
    }


    //////////////////////////////////////////////////////
    @GetMapping("user/demande/{nin}")
    public List<Demande> getUserDemande(@PathVariable("nin") String nin,@RequestHeader("Authorization") String authorizationHeader){
        String bearerToken = authorizationHeader.replace("Bearer ", "");

        // Use the Feign client to get user details with the bearer token
        Citizen user = citizenProx.getUserDetails(nin, "Bearer " + bearerToken);
        if(user!=null){
            return demandeRepository.findDemandeByIdUtilisateur(nin);
        }
        return null;

    }

    ////////////////////////////////////////////////////////
    ///afficher all demande d'un agent
    @PreAuthorize("hasRole('ROLE_AGENT')")
    @GetMapping("agent/demande/{nin}")
    public List<Demande> getAgentDemande(@PathVariable("nin") String nin,@RequestHeader("Authorization") String authorizationHeader){
        String bearerToken = authorizationHeader.replace("Bearer ", "");

        Citizen user = citizenProx.getUserDetails(nin, "Bearer " + bearerToken);
        if(user!=null){
            return demandeRepository.findDemandeByIdAgent(nin);
        }
        return null;

    }







    /** just Ideas of APIs not necessary
     * add documents to demande
     *  @PostMapping("/{id}/documents")
     *     public Demande addDocument(@PathVariable Long id, @RequestBody Documents document) {
     *         Demande demande = demandeRepository.findById(id)
     *                 .orElseThrow(() -> new NotFoundException("Demande not found with id " + id));
     *
     *         demande.getDocuments().add(document);
     *
     *         return demandeRepository.save(demande);
     *     }
     *
     * Delete document from demande
     *     @DeleteMapping("/{id}/documents/{documentId}")
     *     public Demande removeDocument(@PathVariable Long id, @PathVariable Long documentId) {
     *         Demande demande = demandeRepository.findById(id)
     *                 .orElseThrow(() -> new NotFoundException("Demande not found with id " + id));
     *
     *         List<Documents> documents = demande.getDocuments();
     *         documents.removeIf(document -> document.getId().equals(documentId));
     *
     *         return demandeRepository.save(demande);
     *     }
     *
     *  // /utilisateurs/{utilisateurId}/demandes
     *           @GetMapping("/{utilisateurId}/demandes")
     *           public List<Demande> getDemandesByUtilisateurId(@PathVariable Long utilisateurId) {
     *               Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
     *                 *                 .orElseThrow(() -> new NotFoundException("Utilisateur not found with id " + utilisateurId));
     *
     *               return utilisateur.getDemandes();
     *          }
     *
     */



}
