package com.example.sociography.service;

import com.example.sociography.model.Request;
import com.example.sociography.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Optional<Request> findById(Integer id) {
        return requestRepository.findById(id);
    }

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public void deleteById(Integer id) {
        requestRepository.deleteById(id);
    }
    
    public List<Request> getRequestsByRecipientTypeAndRecipientIdAndStatus(String recipientType, int recipientId, String status) {
        return requestRepository.findByRecipientTypeAndRecipientIdAndStatus(recipientType, recipientId, status);
    }
    
    public Request updateRequestStatus(Integer id, String status) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            request.setStatus(status);
            return requestRepository.save(request);
        } else {
            return null; // Or handle this scenario as needed
        }
    }

    
}
