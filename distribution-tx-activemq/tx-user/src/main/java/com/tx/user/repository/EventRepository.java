package com.tx.user.repository;

import com.tx.user.entity.event.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
