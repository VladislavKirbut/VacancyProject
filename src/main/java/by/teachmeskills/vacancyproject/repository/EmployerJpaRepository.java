package by.teachmeskills.vacancyproject.repository;

import by.teachmeskills.vacancyproject.entity.Employer;
import by.teachmeskills.vacancyproject.entity.Vacancy;
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
public class EmployerJpaRepository implements EmployerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final int MAX_COUNT_VACANCIES = 10;

    @Override
    public void createEmployer(Employer employer) {
        entityManager.persist(employer);

        log.info("Created employer with id={}", employer.getId());
    }

    @Override
    public void createVacancy(Vacancy vacancy) {
        entityManager.persist(vacancy);

        log.info("Created vacancy: {}, {}", vacancy.getVacancyName(), vacancy.getEmployer());
    }

    @Override
    public List<Vacancy> getVacanciesByEmployer(long employerId, int pageNumber) {

        TypedQuery<Vacancy> query = entityManager.createQuery("""
                    SELECT vacancy
                    FROM Vacancy vacancy
                    WHERE vacancy.employer = :employerId
                    """, Vacancy.class).setMaxResults(MAX_COUNT_VACANCIES).setFirstResult((pageNumber - 1) * MAX_COUNT_VACANCIES);
        query.setParameter("employerId", employerId);

        return query.getResultList();
    }

    @Override
    public List<Vacancy> getActiveVacancy(long employerId, int pageNumber) {
        TypedQuery<Vacancy> query = entityManager.createQuery("""
                SELECT vacancy
                FROM Vacancy vacancy
                JOIN FETCH vacancy.employer
                WHERE vacancy.isActive is TRUE
                """, Vacancy.class)
                .setMaxResults(MAX_COUNT_VACANCIES)
                .setFirstResult((pageNumber - 1) * MAX_COUNT_VACANCIES);
        query.setParameter("employerId", employerId);

        return query.getResultList();
    }
}
