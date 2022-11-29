package io.twotle.ssn.service;

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import io.twotle.ssn.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {

    @Value("${firebase.bucket}")
    private String fbbucket;

    public String uploadFiles(MultipartFile file, String nameFile)
            throws IOException, FirebaseAuthException {
        Bucket bucket = StorageClient.getInstance().bucket(fbbucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
        Blob blob = bucket.create(nameFile.toString(), content, file.getContentType());
        return blob.getMediaLink();
    }

    public String generateFilename(User user, MultipartFile file) {
        return user.getId().toString()+ UUID.randomUUID().toString()+".png";
    }
}
