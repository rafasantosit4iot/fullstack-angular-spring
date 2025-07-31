package com.example.spring_basics.service.publisher;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.publisher.CreatePublisherDTO;
import com.example.spring_basics.dto.response.publisher.PublisherResponseDTO;
import com.example.spring_basics.mapper.publisher.PublisherMapper;
import com.example.spring_basics.model.Publisher;
import com.example.spring_basics.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public Page<PublisherResponseDTO> getPublishers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Publisher> publishers = publisherRepository.findAll(pageable);
        return publisherMapper.toResponseDTOPage(publishers);
    }

    @Override
    public List<PublisherResponseDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publisherMapper.toResponseDTOList(publishers);
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
