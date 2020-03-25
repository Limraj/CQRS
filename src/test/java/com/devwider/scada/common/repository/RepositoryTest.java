package com.devwider.scada.common.repository;

import com.devwider.scada.cqrs.Event;
import com.devwider.scada.cqrs.FailedCommandEvent;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.event.AddedScriptEvent;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.AddUserCommand;
import com.devwider.scada.system.user.command.RemoveUserCommand;
import com.devwider.scada.system.user.event.RemovedUserEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RepositoryTest {

    private Repository<Event> repository = Repository.crud(Event.class);
    private Event event;
    private Event eventSecond;
    private Event eventThird;

    @Before
    public void setup() {
        event = new FailedCommandEvent(null);
        event = repository.create(event);

        eventSecond = new AddedScriptEvent(Script.builder().build(), null);
        eventSecond = repository.create(eventSecond);

        eventThird = new RemovedUserEvent(User.builder().build(), null);
        eventThird = repository.create(eventThird);
    }

    @After
    public void reset() {
        repository.remove(event.getId());
        repository.remove(eventSecond.getId());
        repository.remove(eventThird.getId());
    }

    @Test
    public void test_read_by_id() {

        //when:
        Event result = repository.read(event.getId());

        //then:
        assertEquals(result, event);
    }

    @Test
    public void test_readByUuid_when_readByUuid_then_objectWithThisUuid() {

        //when:
        Event result = repository.read(event.getUuid().toString());

        //then:
        assertEquals(event.getUuid(), result.getUuid());
        assertEquals(event, result);
    }

    @Test
    public void test_create_given_object_when_create_then_thisObjectInRepo() {
        reset();
        //given:
        Event expected = new FailedCommandEvent(new RemoveUserCommand(null));

        //when:
        event = repository.create(expected);

        //then:
        assertEquals(1, repository.readAll().size());
        assertNotEquals(0, event.getId());
        assertEquals(expected, event);
    }

    @Test
    public void test_readAll_given_list_when_readAll_then_thisList() {
        //given:
        List<Event> expected = new ArrayList<>();
        expected.add(event);
        expected.add(eventSecond);
        expected.add(eventThird);

        //when:
        List<Event> events = repository.readAll();

        //then:
        assertEquals(3, events.size());
        assertEquals(expected, events);
    }

    @Test
    public void test_update() {
        //TODO
    }

    @Test
    public void test_removeByUuid_when_readByUuid_then_not_null_and_when_invoke_removeByUuid_and_readByThisUuid_then_null() {
        //when:
        Event event = repository.read(eventThird.getUuid().toString());

        //then:
        assertNotEquals(null, event);

        //when:
        repository.remove(eventThird.getUuid().toString());

        //and:
        Event eventRemoved = repository.read(eventThird.getUuid().toString());

        //then:
        assertEquals(null, eventRemoved);

    }

    @Test
    public void test_removeById_when_readById_then_not_null_and_when_invoke_removeById_and_readByThisId_then_null() {
        //when:
        Event event = repository.read(eventThird.getId());

        //then:
        assertNotEquals(null, event);

        //and when:
        repository.remove(eventThird.getId());

        //and:
        Event eventRemoved = repository.read(eventThird.getId());

        //then:
        assertEquals(null, eventRemoved);
    }
}