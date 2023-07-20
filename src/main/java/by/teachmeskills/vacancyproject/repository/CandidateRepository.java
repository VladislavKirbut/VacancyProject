package by.teachmeskills.vacancyproject.repository;

import by.teachmeskills.vacancyproject.entity.Candidate;
import by.teachmeskills.vacancyproject.entity.Response;

import java.util.List;

public interface CandidateRepository {

    void saveCandidate(Candidate candidate);

    void saveResponse(Response response);

    List<Response> getResponsesByVacancyId(long vacancyId, int pageNumber);

    List<Response> getResponsesByEmployerId(long employerId, int pageNumber);
}
