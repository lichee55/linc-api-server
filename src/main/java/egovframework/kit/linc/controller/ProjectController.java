package egovframework.kit.linc.controller;

import egovframework.kit.linc.cmm.ResponseForm;
import egovframework.kit.linc.domain.ProjectQueryCondition;
import egovframework.kit.linc.dto.ProjectListDTO;
import egovframework.kit.linc.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/api/project")
    public ResponseForm<Page<ProjectListDTO>> getProjects(
            ProjectQueryCondition condition,
            Pageable pageable) {
        try {

            Page<ProjectListDTO> projects = projectService.findProjects(condition, pageable);

            return new ResponseForm<>(projects, "success", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseForm<>(null, e.getMessage(), 500);
        }
    }
}
