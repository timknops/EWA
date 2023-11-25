package nl.solar.app.models;

import java.util.Date;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import nl.solar.app.enums.ProjectStatus;

/**
 * Represents a project with a unique id, project name, team, client, due date,
 * and status.
 * 
 * @author Tim Knops
 */
@Entity
public class Project {

    @Id
    @SequenceGenerator(name = "project_id_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_generator")
    private long id;

    private String projectName;

    @OneToOne
    private Team team;
    private String client;
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(mappedBy = "project")
    private List<ResourceTemp> products;

    public Project(long id, String projectName, Team team, String client, Date dueDate, ProjectStatus status,
            List<ResourceTemp> products) {
        this.id = id;
        this.projectName = projectName;
        this.team = team;
        this.client = client;
        this.dueDate = dueDate;
        this.status = status;
        this.products = products;
    }

    public Project(long id) {
        this.id = id;
    }

    public Project() {
    }

    /**
     * Creates a dummy project with the given parameters.
     * 
     * @param currentId the current id
     * @return a dummy project
     */
    public static Project createDummyProject(long id) {
        Project project = new Project(id);

        // Generates a random date between 2022-01-01 and 2026-01-01.
        Date randomDueDate = randomDate(new Date(1640995200000L), new Date(1789568000000L));
        project.setDueDate(randomDueDate);

        ProjectStatus randomStatus;
        if (randomDueDate.before(new Date())) { // If the due date is in the past, the project is completed.
            randomStatus = ProjectStatus.COMPLETED;
        } else { // If the due date is in the future, the project is either upcoming or in
                 // progress.
            double random = Math.random();

            // 40% chance of being upcoming.
            randomStatus = random < 0.4 ? ProjectStatus.UPCOMING : ProjectStatus.IN_PROGRESS;
        }

        project.setStatus(randomStatus);

        // Generate a random client and project name.
        project.setClient("Client " + (int) (Math.random() * 100));
        project.setProjectName("Project " + (int) (Math.random() * 100));

        return project;
    }

    public boolean associateTeam(Team team) {
        if (this.team == null) {
            this.team = team;
            return true;
        }

        return false;
    }

    /**
     * Generates a random date between the given start and end date.
     * 
     * @param start the start date
     * @param end   the end date
     * @return a random date between the given start and end date
     */
    public static Date randomDate(Date start, Date end) {
        return new Date(start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime())));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj instanceof Project project) {
            return this.getId() == project.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
