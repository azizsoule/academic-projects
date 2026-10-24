package com.app.ecommerce.models;

import com.app.ecommerce.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "pub")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPub;

    String label;

    String image;

    boolean forSlider;

    @Transient
    public String getImagePath() {
        return Constants.MEDIA_SERVER_ENDPOINT + "/pubs/" + image;
    }

}