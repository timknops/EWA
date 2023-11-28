package nl.solar.app.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import nl.solar.app.exceptions.PreConditionFailedException;
import nl.solar.app.exceptions.ResourceNotFoundException;
import nl.solar.app.models.Project;
import nl.solar.app.models.views.ProjectView;
import nl.solar.app.repositories.jpaRepositories.ProjectRepositoryJpa;

/**
 * Controller class for managing project-related endpoints.
 * This class handles CRUD operations for projects.
 * 
 * @Author Tim Knops
 */
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepositoryJpa projectRepo;

    /**
     * Retrieves a list of all projects.
     *
     * @return A list of all projects.
     */
    @JsonView(ProjectView.Overview.class)
    @GetMapping(produces = "application/json")
    public List<Project> getAll() {
        return this.projectRepo.findAll();
    }

    /**
     * Retrieves a project by its id.
     *
     * @param id The id of the project to retrieve.
     * @return A ResponseEntity containing the retrieved project.
     * @throws ResourceNotFoundException If the project with the given ID does not
     *                                   exist.
     */
    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Project> getProject(@PathVariable Long id) throws ResourceNotFoundException {
        Project project = this.projectRepo.findById(id);

        // If the project doesn't exist, throw an error.
        if (project == null) {
            throw new ResourceNotFoundException("Project with id: " + id + " was not found");
        }

        return ResponseEntity.ok(project);
    }

    /**
     * Deletes a project by its id.
     *
     * @param id The id of the project to delete.
     * @return A ResponseEntity containing the deleted project.
     * @throws ResourceNotFoundException If the project with the given ID does not
     *                                   exist.
     */
    @DeleteMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Project> deleteProject(@PathVariable Long id) throws ResourceNotFoundException {
        Project project = this.projectRepo.delete(id);

        // If the project doesn't exist, throw an error.
        if (project == null) {
            throw new ResourceNotFoundException("Project with id: " + id + " was not found");
        }

        return ResponseEntity.ok(project);
    }

    /**
     * Creates a new project.
     *
     * @param project The project to create.
     * @throws ResourceNotFoundException If the project is null.
     * @return A ResponseEntity containing the created project.
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<Project> createProject(@RequestBody Project project) throws ResourceNotFoundException {
        if (project == null) {
            throw new ResourceNotFoundException("Project is null");
        }

        Project createdProject = this.projectRepo.save(project);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdProject.getId()).toUri();
        return ResponseEntity.created(location).body(createdProject);
    }

    /**
     * Updates a project by its id.
     *
     * @param id      The id of the project to update.
     * @param project The project to update.
     * @return A ResponseEntity containing the updated project.
     * @throws PreConditionFailedException If the project with the given ID does not
     *                                     exist.
     */
    @PutMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project)
            throws PreConditionFailedException {
        if (id != project.getId()) {
            throw new PreConditionFailedException("Id of the body and path do not match");
        }

        Project updatedProject = this.projectRepo.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    /**
     * Retrieves the information required for the add modal.
     *
     * @return ResponseEntity containing the add modal information in the response
     *         body.
     */
    @GetMapping(path = "/add", produces = "application/json")
    public ResponseEntity<Object> getAddModalInfo() throws ResourceNotFoundException {
        Object addModalInfo = this.projectRepo.getAddModalInfo();

        if (addModalInfo == null) {
            throw new ResourceNotFoundException("Add modal info not found");
        }

        return ResponseEntity.ok(addModalInfo);
    }
}
