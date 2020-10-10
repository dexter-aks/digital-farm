package com.dexter.labs.digitalfarm.converter;

public interface DtoConverter<Entity, DTO> {

    public DTO convert(Entity entity);
}
