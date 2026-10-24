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
    public String getMainImage() {
        if (image == null) return null;
        return "/"+ Constants.PUBS_MEDIA_DIR +"/" + image;
    }

}