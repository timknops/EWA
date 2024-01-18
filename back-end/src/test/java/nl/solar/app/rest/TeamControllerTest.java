package nl.solar.app.rest;

import nl.solar.app.WebConfig;
import nl.solar.app.models.Team;
import nl.solar.app.models.Warehouse;
import nl.solar.app.repositories.EntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.Set;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test class for the TeamController.
 * This class tests the TeamController by testing the end-points.
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    EntityRepository<Warehouse> warehouseEntityRepository;

    @Value("${server.servlet.context-path}")
    private String servletContextPath;

    @BeforeEach
    public void setup() {
        webConfig.SECURED_PATHS = Set.of("/empty");
        if (servletContextPath == null) {
            servletContextPath = "/";
        }
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void testGetAllTeams() {
        ResponseEntity<Team[]> response = this.restTemplate
                .getForEntity("/inventory", Team[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Team[] teams = response.getBody();
        assertThat(teams.length, is(greaterThan(0)));
    }

    @Test
    public void testGetTeamById() {
        ResponseEntity<Team> response = restTemplate.getForEntity("/teams/1", Team.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateTeam() {
        Warehouse warehouse = warehouseEntityRepository.findAll().get(0);

        Team team = new Team();
        team.setTeam("TestTeam");
        team.setWarehouse(warehouse);
        team.setType(Team.TeamType.INTERNAL);

        ResponseEntity<Team> response = restTemplate.postForEntity("/teams", team, Team.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Team createdTeam = response.getBody();
        assertEquals("TestTeam", createdTeam.getTeam(), "Created team name should match");
    }
}
