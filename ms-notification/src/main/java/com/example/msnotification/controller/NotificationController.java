package com.example.msnotification.controller;

import com.example.msnotification.entity.Contact;
import com.example.msnotification.entity.Notification;
import com.example.msnotification.entity.UserContact;
import com.example.msnotification.model.User;
import com.example.msnotification.proxy.UserProxy;
import com.example.msnotification.repositories.NotificationRepository;
import com.example.msnotification.repositories.UserContactRepository;
import com.example.msnotification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserContactRepository userContactRepository;

    @Autowired
    UserProxy userProxy;

    @PostMapping("/sendNotif/{nin}")
    public String processSMS(@PathVariable("nin") String nin,@RequestBody Notification notification){
        log.info("processSMS started" + notification.toString());
        UserContact userContact = userContactRepository.findUserContactByNin(nin);
        notificationRepository.save(new Notification(null,notification.getMessage(),nin));
        return notificationService.sendSms(userContact.getContact().getNumero(),notification.getMessage());
    }

    @PostMapping("/saveContact/{nin}")
    public UserContact saveContact(@PathVariable("nin") String nin,@RequestHeader("Authorization") String authorizationHeader,@RequestBody Contact contact){
        User n = userProxy.getUserDetails(nin,authorizationHeader);
        UserContact userContact = new UserContact(null,n.getNin(),n.getFullNameLat(),new Contact(contact.getNumero()));
        userContactRepository.save(userContact);
        return  userContact;
    }

    @GetMapping("/headerr")
    public String getrce() {
        return "hiii i am Citizen";
    }

    @GetMapping("/getUserInfos/{nin}")
    public User getNin(@PathVariable("nin") String nin,@RequestHeader("Authorization") String authorizationHeader){

         User n = userProxy.getUserDetails(nin,authorizationHeader);

        return n;
    }

    @PreAuthorize("hasRole('ROLE_AGENT')")
    @GetMapping("/agent" )
    public String getResource() {
        return "hiii i am Agent";
    }

}
