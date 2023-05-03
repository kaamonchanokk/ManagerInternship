package demo.backend.ManageInternship.repository.CustomRepository;

import demo.backend.ManageInternship.model.payload.request.RequestOfInternRequest;

public interface CustomRequestRepository {
    void insertRequest(RequestOfInternRequest requestOfInternRequest);

    void updateRequest(RequestOfInternRequest requestOfInternRequest);

    void deleteRequest(RequestOfInternRequest requestOfInternRequest);

    void updateStatusRequest(RequestOfInternRequest requestOfInternRequest);
}
