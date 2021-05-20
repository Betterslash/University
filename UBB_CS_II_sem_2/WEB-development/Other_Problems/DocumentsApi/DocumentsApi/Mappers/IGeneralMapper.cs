using System.Collections.Generic;
using DocumentsApi.Dto;
using DocumentsApi.Model;

namespace DocumentsApi.Mappers
{
    public interface IGeneralMapper<E, Dto> where E : IBaseEntity where Dto : IBaseDto
    {
        E FromDtoToEntity(Dto dto);

        Dto FromEntityToDto(E entity);

        List<E> FromDtosToEntities(List<Dto> dtos);

        List<Dto> FromEntitiesToDto(List<E> entities);
    }
}