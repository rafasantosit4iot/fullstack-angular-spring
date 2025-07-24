package com.example.spring_basics.service.publisher;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.publisher.CreatePublisherDTO;
import com.example.spring_basics.dto.response.publisher.PublisherResponseDTO;
import com.example.spring_basics.mapper.publisher.PublisherMapper;
import com.example.spring_basics.model.Publisher;
import com.example.spring_basics.repository.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {
    
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherServiceImpl(PublisherMapper publisherMapper, PublisherRepository publisherRepository) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherResponseDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publisherMapper.toResponseListDTO(publishers);
    }

    @Override
    public PublisherResponseDTO createPublisher(CreatePublisherDTO createPublisherDTO) {
        Publisher publisher = publisherMapper.toEntity(createPublisherDTO);
        publisher = publisherRepository.save(publisher);
        return publisherMapper.toResponseDTO(publisher);
    }

    @Override
    public void deletePublisher(UUID publisherId) {
        publisherRepository.deleteById(publisherId);
    }

}
