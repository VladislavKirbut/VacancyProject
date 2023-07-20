package by.teachmeskills.vacancyproject.repository;

import by.teachmeskills.vacancyproject.entity.Candidate;
import by.teachmeskills.vacancyproject.entity.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Log4j2
public class CandidateJpaRepository implements CandidateRepository {

    private static final int MAX_COUNT_RESPONSES = 10;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCandidate(Candidate candidate) {
        entityManager.persist(candidate);

        log.info("Created candidate: {}" + candidate.getEmail());
    }

    @Override
    public void saveResponse(Response response) {
        entityManager.persist(response);

        log.info("Created response by {}", response.getCandidate());
    }

    @Override
    public List<Response> getResponsesByVacancyId(long vacancyId, int pageNumber) {
        TypedQuery<Response> query = entityManager.createQuery("""
                    SELECT response
                    FROM Response response
                    JOIN FETCH response.candidate
                    WHERE response.vacancy = :vacancyId
                """, Response.class)
                .setMaxResults(MAX_COUNT_RESPONSES)
                .setFirstResult((pageNumber - 1) * MAX_COUNT_RESPONSES);

        query.setParameter("vacancyId", vacancyId);

        return query.getResultList();
    }

    @Override
    public List<Response> getResponsesByEmployerId(long employerId, int pageNumber) {
        TypedQuery<Response> query = entityManager.createQuery("""
                    SELECT response
                    FROM Response response
                    JOIN FETCH response.candidate
                    JOIN FETCH response.vacancy
                    WHERE response.vacancy.employer = :employerId
                """, Response.class)
                .setMaxResults(MAX_COUNT_RESPONSES)
                .setFirstResult((pageNumber - 1) * MAX_COUNT_RESPONSES);
        query.setParameter("employerId", employerId);


        return query.getResultList();
    }

}
