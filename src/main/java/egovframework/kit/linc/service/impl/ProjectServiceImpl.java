package egovframework.kit.linc.service.impl;

import egovframework.kit.linc.domain.Project;
import egovframework.kit.linc.domain.ProjectQueryCondition;
import egovframework.kit.linc.domain.repo.ProjectRepository;
import egovframework.kit.linc.dto.ProjectListDTO;
import egovframework.kit.linc.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Page<ProjectListDTO> findProjects(ProjectQueryCondition condition, Pageable pageable) {

        Page<Project> fetch = projectRepository.findDynamicQuery(condition, pageable);

        return fetch.map(ProjectListDTO::of);
    }
}
