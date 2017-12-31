package lied.springframework.services;

import lied.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUnitsOfMeasure();

}
