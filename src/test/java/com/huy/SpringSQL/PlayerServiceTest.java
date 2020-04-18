package com.huy.SpringSQL;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {
    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    public void whenFind_returnPlayer(){
        Long id = 4L;
        Player mock = new Player("Jimmy", 4L);
        Mockito.when(playerRepository.findById(id)).thenReturn(Optional.of(mock));
        Player actual = playerService.getById(id);
        assertEquals(4L, actual.getId());
        assertEquals("Jimmy", actual.getName());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void whenExceptionThrown_thenRuleIsApplied() {
        String name = "id";
        Long value = 5L;
        String reason = "does not exist";
        exceptionRule.expect(InvalidConfigurationPropertyValueException.class);
        exceptionRule.expectMessage("Property " + name + " with value '" + value + "' is invalid: " + reason);
        playerService.getById(value);
    }

    @Test(expected = InvalidConfigurationPropertyValueException.class)
    public void getByIDThrowException() {
        Long value = 5L;
        playerService.getById(value);
    }

    @Test(expected = InvalidConfigurationPropertyValueException.class)
    public void updateByIDThrowException() {
        Player mock = new Player("Jimmy", 5L);
        playerService.updateById(mock);
    }

    @Test(expected = InvalidConfigurationPropertyValueException.class)
    public void deleteByIDThrowException() {
        Long value = 5L;
        playerService.deleteById(value);
    }

    @Test
    public void whenSave_returnPlayerSaved(){
        Player mock = new Player("Jimmy", 4L);
        Mockito.when(playerRepository.save(mock)).thenReturn(mock);
        Player actual = playerService.save(mock);
        assertEquals("Jimmy", actual.getName());
        assertEquals(4L, actual.getId());
    }

}
