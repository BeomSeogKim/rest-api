package inflearn.whiteship.restapiwithspring.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class EventResource extends EntityModel<Event> {
    @JsonUnwrapped // event로 감싸는 것을 없애줌.
    private Event event;

//    public EventResource(Event event) {
//        this.event = event;
//    }
public EventResource(Event event, Link... links) {
    super(event, Arrays.asList(links));
    add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
}

    public Event getEvent() {
        return event;
    }
}
