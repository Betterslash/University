using System.Collections.Generic;
using System.Linq;
using DocumentsApi.Dto;
using DocumentsApi.Model;

namespace DocumentsApi.Mappers
{
    public class DocumentMapper : IGeneralMapper<DocumentEntity, DocumentDto>
    {
        public DocumentEntity FromDtoToEntity(DocumentDto dto)
        {
            var documentEntity = new DocumentEntity();
            if (dto.Id != null) documentEntity.Id = (int) dto.Id;
            documentEntity.Description = dto.Description;
            documentEntity.AuthorId = dto.authorId;
            return documentEntity;
        }

        public DocumentDto FromEntityToDto(DocumentEntity entity)
        {
            var documentDto = new DocumentDto
            {
                Id = entity.Id, Description = entity.Description, authorId = entity.AuthorId
            };
            return documentDto;
        }

        public List<DocumentEntity> FromDtosToEntities(List<DocumentDto> dtos)
        {
            return dtos.Select(FromDtoToEntity).ToList();
        }

        public List<DocumentDto> FromEntitiesToDto(List<DocumentEntity> entities)
        {
            return entities.Select(FromEntityToDto).ToList();
        }
    }
}