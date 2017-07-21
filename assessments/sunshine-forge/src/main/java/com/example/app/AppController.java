package com.example.app;

import com.example.space.SpaceModel;
import com.example.space.SpaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by localadmin on 7/20/17.
 */

@RestController
@RequestMapping("/spaces/{spaceId}/apps")
final class AppController {

    private final AppRepository repository;
    private final SpaceRepository spaceRepository;

    // Constructor
    public AppController(AppRepository repository, SpaceRepository spaceRepository) {
        this.repository = repository;
        this.spaceRepository = spaceRepository;
    }

    // Post
    @PostMapping
    ResponseEntity<AppModel> create(@RequestBody final AppModel app, @PathVariable final Long spaceId) {
//        AppModel appModel = repository.findOne(spaceId);
        System.out.println("*****************" + app.getAppName() + "*****************");
        if (app != null)
            return ResponseEntity.badRequest().build();
        //set space model to app, create method first in app model

        return new ResponseEntity<>(repository.save(app), HttpStatus.CREATED);
    }
}
