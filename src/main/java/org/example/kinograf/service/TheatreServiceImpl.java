package org.example.kinograf.service;

import org.example.kinograf.DTO.TheatreDTO;
import org.example.kinograf.mapper.TheatreMapper;
import org.example.kinograf.model.Theatre;
import org.example.kinograf.repository.TheatreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TheatreServiceImpl implements TheatreService {

//    List<TheatreDTO> getAllTheatres();
//    List<Theatre> getAllTheatreEntities();
//
//    Optional<TheatreDTO> getTheatreById(Long theatreId);
//    Optional<Theatre> getTheatreEntityById(Long theatreId);
//
//    TheatreDTO createTheatre(TheatreDTO theatreDTO);
//    TheatreDTO updateTheatre(TheatreDTO theatreDTO);
//    void deleteTheatre(int theatreId);

    private final TheatreRepository theatreRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public List<TheatreDTO> getAllTheatres() {
        List<Theatre> theatres = this.theatreRepository.findAll();
        List<TheatreDTO> theatresDTOs = new ArrayList<>();
        for (Theatre theatre : theatres) {
            theatresDTOs.add(TheatreMapper.toDTO(theatre));
        }
        return theatresDTOs;
    }

    @Override
    public Optional<TheatreDTO> getTheatreById(Long theatreId) {
        Optional<Theatre> theatre = theatreRepository.findById(theatreId);
        if (theatre.isPresent()) {
            return Optional.of(TheatreMapper.toDTO(theatre.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<Theatre> getAllTheatreEntities() {
        return theatreRepository.findAll();
    }

    @Override
    public Optional<Theatre> getTheatreEntityById(Long id) {
        return theatreRepository.findById(id);
    }

//    TheatreDTO updateTheatre(TheatreDTO theatreDTO);
//    void deleteTheatre(int theatreId);
    @Override
    public TheatreDTO createTheatre(String theatreName, int capacity) {
        Theatre theatre = new Theatre();
        theatre.setTheatreName(theatreName);
        theatre.setCapacity(capacity);

        Theatre saved = theatreRepository.save(theatre);
        return TheatreMapper.toDTO(saved);
    }

    @Override
    public TheatreDTO updateTheatre(Long id, String theatreName, int capacity) {
        Theatre existing = theatreRepository.findById(id).get();
        existing.setTheatreName(theatreName);
        existing.setCapacity(capacity);
        return TheatreMapper.toDTO(theatreRepository.save(existing));
    }

    @Override
    public void deleteTheatre(Long theatreId) {
        if (!theatreRepository.existsById(theatreId)) {
            throw new RuntimeException("Theatre not found");
        }
        theatreRepository.deleteById(theatreId);
    }

}
