package org.corywixom.cache.core.base;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMapper<EEntity, EDTO> implements IMapper<EEntity, EDTO> {
    @Override
    public List<EDTO> toDTOsSet(Set<EEntity> entites) {
        return entites.stream()
            .map(entity -> toDTO(entity))
            .collect(Collectors.toList());
    }

    @Override
    public Set<EEntity> toEntitiesSet(List<EDTO> dtos) {
        return dtos.stream()
            .map(dto -> toEntity(dto))
            .collect(Collectors.toSet());
    }

    @Override
    public List<EEntity> toEntitiesList(List<EDTO> dtos) {
        return dtos.stream()
            .map(dto -> toEntity(dto))
            .collect(Collectors.toList());
    }

    @Override
    public List<EDTO> toDTOsList(List<EEntity> entities) {
        return entities.stream()
            .map(entity -> toDTO(entity))
            .collect(Collectors.toList());
    }

    @Override
    public boolean compare(EEntity entity, EDTO dto){
        EDTO compareDTO = toDTO(entity);

        return compareDTO.equals(dto);
    }
}
