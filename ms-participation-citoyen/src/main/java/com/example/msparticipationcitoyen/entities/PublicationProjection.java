package com.example.msparticipationcitoyen.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "toauth_admin", types= Publication.class)
public interface PublicationProjection {
    public Long getIdPublication();

    @Value("#{target.typePublication}")
    public TypePublication getTypePublication();

    @Value("#{target.content}")
    public String getContent();

    @Value("#{target.picture}")
    public String getPicture();

    @Value("#{target.datePublication}")
    public Date getDatePublication();

    //replies
    @Value("#{target.reponces}")
    public Collection<Reply> getReponces();

    //

//    @Value("#{target.idCommune}")
//    public Long getIdCommune();

    @Value("#{target.citizen.fullNameLat}")
    public Long getFullNameLat();

    @Value("#{target.citizen.commune}")
    public String getCommune();

}
