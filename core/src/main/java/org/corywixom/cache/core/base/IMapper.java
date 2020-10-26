package org.corywixom.cache.core.base;

import java.util.List;
import java.util.Set;

public interface IMapper<EEntity, EDTO> {
    EDTO toDTO(EEntity entity);

    EEntity toEntity(EDTO dto);

    List<EDTO> toDTOsSet(Set<EEntity> entities);

    Set<EEntity> toEntitiesSet(List<EDTO> dtos);

    List<EEntity> toEntitiesList(List<EDTO> dtos);

    List<EDTO> toDTOsList(List<EEntity> entities);

    boolean compare(EEntity entity, EDTO dto);
}
