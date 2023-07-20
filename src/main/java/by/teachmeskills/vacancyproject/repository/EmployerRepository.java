package by.teachmeskills.vacancyproject.repository;

import by.teachmeskills.vacancyproject.entity.Employer;
import by.teachmeskills.vacancyproject.entity.Vacancy;

import java.util.List;

public interface EmployerRepository {

    void createEmployer(Employer employer);

    void createVacancy(Vacancy vacancy);

    List<Vacancy> getVacanciesByEmployer(long employerId, int pageNumber);

    List<Vacancy> getActiveVacancy(long employerId, int pageNumber);
}
