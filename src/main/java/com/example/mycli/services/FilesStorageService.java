package com.example.mycli.services;

import com.example.mycli.model.ImageWrap;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    void init();
    void save(MultipartFile file, HttpServletRequest httpServletRequest);
    Resource load(Long id);

    ImageWrap loadMobile(Long id);

    void deleteAll();
    Stream<Path> loadAll();

    Resource loadUser(HttpServletRequest httpServletRequest);

    ImageWrap loadUserMobile(HttpServletRequest httpServletRequest);
}
