package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.FolderInit;
import com.example.mycli.model.ImageWrap;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
@Transactional
@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements FilesStorageService {
    private final Path root = Paths.get("uploads");
    private final UserService userService;
    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new FolderInit();
        }
    }

    @Override
    public void save(MultipartFile file, HttpServletRequest httpServletRequest) {
        try {
            String email = userService.getEmailFromToken(httpServletRequest);
            UserEntity user = userService.findByEmail(email);
            String extension = file.getOriginalFilename();
            assert extension != null;
            int size = extension.length();
            extension = extension.substring(size-5);
            String newName = user.getId().toString() + extension;
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(newName)));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(Long id) {
        try {
            Path file = root.resolve(id + ".jpeg");
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    @Override
    public ImageWrap loadMobile(Long id) {
        try {
            Path path = root.resolve(id + ".jpeg");
            Resource resource = new UrlResource(path.toUri());
            return getImageWrap(path, resource);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public Resource loadUser(HttpServletRequest httpServletRequest) {
        try {
            String email = userService.getEmailFromToken(httpServletRequest);
            UserEntity userEntity = userService.findByAuthDataEmail(email);
            Path file = root.resolve(userEntity.getId() + ".jpeg");
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    @Override
    public ImageWrap loadUserMobile(HttpServletRequest httpServletRequest) {
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity userEntity = userService.findByAuthDataEmail(email);
        try {
            Path path = root.resolve(userEntity.getId() + ".jpeg");
            Resource resource = new UrlResource(path.toUri());
            return getImageWrap(path, resource);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private ImageWrap getImageWrap(Path path, Resource resource) {
        byte[] content;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        if (resource.exists() || resource.isReadable()) {
            return new ImageWrap(content);
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }
}
