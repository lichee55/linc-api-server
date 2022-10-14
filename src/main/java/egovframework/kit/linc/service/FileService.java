package egovframework.kit.linc.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import egovframework.kit.linc.dto.FileDTO;

public interface FileService {
    List<FileDTO> saveFiles(List<MultipartFile> files) throws IOException;
}
