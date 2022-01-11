package com.gxitsky.repository;

import com.gxitsky.entity.event.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
