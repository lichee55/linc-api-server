package egovframework.kit.linc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import egovframework.kit.linc.domain.Files;
import egovframework.kit.linc.domain.repo.FilesRepository;
import egovframework.kit.linc.dto.FileDTO;
import egovframework.kit.linc.service.FileService;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FilesRepository fileRepository;

    @Value("${external.static.url.inbound}")
    private String filePath;


    @Override
    @Transactional
    public List<FileDTO> saveFiles(List<MultipartFile> files) throws IOException {
        // TODO Auto-generated method stub
        List<FileDTO> savedFiles = new LinkedList<FileDTO>();
        for (MultipartFile file : files) {
            String fileId = UUID.randomUUID().toString();
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName != null
                    ? originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
                    : null;
            originalFileName = originalFileName != null
                    ? originalFileName.substring(0, originalFileName.lastIndexOf("."))
                    : null;
            Long fileSize = file.getSize();
            String dbPath = "/image/" + fileId + "." + fileExtension;

            File newFile = new File(filePath, fileId + "." + fileExtension);
            if (!newFile.exists()) {
                boolean mkdirs = newFile.mkdirs();
                if (!mkdirs) {
                    throw new IOException("Failed to create directory.");
                }
            }

            file.transferTo(newFile);

            Files savedFile = Files.builder().name(originalFileName).path(newFile.getPath()).size(fileSize)
                    .ext(fileExtension).url(dbPath).build();
            fileRepository.save(savedFile);
            savedFiles.add(FileDTO.of(savedFile));
        }

        return savedFiles;
    }

}
