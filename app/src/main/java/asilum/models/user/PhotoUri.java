package asilum.models.user;

import javax.persistence.Embeddable;

@Embeddable
public class PhotoUri {
    private String photoUri;

    public PhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public PhotoUri() {
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
