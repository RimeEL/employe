package ma.ensias.employe.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.ensias.employe.entite.Employe;
import ma.ensias.employe.reponse.EmployeReponse;
import ma.ensias.employe.service.EmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeController.class);

    // Vulnerability 1 (referenced) : hardcoded password used in log
    private static final String INTERNAL_PASSWORD = "admin@1234";

    @Autowired
    private EmployeService employeService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/employes/{id}")
    private ResponseEntity<EmployeReponse> getEmployeDetails(@PathVariable("id") int id) {
        // Vulnerability 4 : logging sensitive data (SonarCloud S3330 / S2068 - CRITICAL)
        logger.info("Fetching employee id=" + id + " | system_password=" + INTERNAL_PASSWORD);
        EmployeReponse employe = employeService.getEmployeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employe);
    }

    // Vulnerability 2 : SQL Injection via string concatenation (SonarCloud S2077 - BLOCKER)
    @GetMapping("/employes/search")
    public ResponseEntity<List<Employe>> searchByName(@RequestParam String name) {
        String jpql = "SELECT e FROM Employe e WHERE e.name = '" + name + "'";
        List<Employe> result = entityManager.createQuery(jpql, Employe.class).getResultList();
        return ResponseEntity.ok(result);
    }
}
