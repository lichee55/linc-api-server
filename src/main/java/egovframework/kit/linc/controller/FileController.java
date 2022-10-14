package egovframework.kit.linc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.kit.linc.cmm.ResponseForm;
import egovframework.kit.linc.dto.FileDTO;
import egovframework.kit.linc.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final FileService fileService;

    @PostMapping("/api/file/upload")
    @Operation(summary = "파일 업로드 요청", description = "파일 업로드 요청")
    public ResponseForm<List<FileDTO>> uploadFiles(@RequestPart List<MultipartFile> files) {

        try {
            if (files != null && files.size() > 0) {
                List<FileDTO> savedFiles = fileService.saveFiles(files);
                return new ResponseForm<>(savedFiles, "success", 200);
            }
            throw new Exception("파일이 없습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseForm<>(null, e.getMessage(), 500);
        }

    }
}
