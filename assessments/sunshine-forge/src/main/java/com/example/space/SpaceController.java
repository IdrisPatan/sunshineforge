package com.example.space;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by localadmin on 7/20/17.
 */
@RestController
@RequestMapping("/spaces")
final class SpaceController {

    private final SpaceRepository repository;

    public SpaceController(SpaceRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    SpaceModel create(@RequestBody final SpaceModel space){
        return repository.save(space);
    }

    @GetMapping
    Iterable<SpaceModel>  list(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    SpaceModel read(@PathVariable final long id){
        return repository.findOne(id);
    }


    @PutMapping("/{id}")
    SpaceModel update(@PathVariable final long id, @RequestBody final SpaceModel space){
        SpaceModel oldSpace = repository.findOne(id);
        if (oldSpace !=null) {

            oldSpace.setName(space.getName());
            oldSpace.setMemory(space.getMemory());
            oldSpace.setDisk(space.getDisk());
        }
        return oldSpace;

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable final long id){
        if (repository.exists(id)) {
            repository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
