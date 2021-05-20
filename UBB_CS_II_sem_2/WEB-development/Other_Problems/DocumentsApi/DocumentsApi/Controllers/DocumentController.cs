using System;
using System.Collections.Generic;
using DocumentsApi.Data;
using DocumentsApi.Dto;
using DocumentsApi.Mappers;
using DocumentsApi.Model;
using DocumentsApi.Validators;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace DocumentsApi.Controllers
{
    [ApiController]
    [Route("api/documents")]
    public class DocumentController : ControllerBase, ICrudController<DocumentDto>
    {
        private readonly DocumentContext _context;

        private IGeneralMapper<DocumentEntity, DocumentDto> documentMapper = new DocumentMapper();

        private readonly DocumentValidator _documentValidator = new();
        public DocumentController(DocumentContext context)
        {
            _context = context;
        }

        [HttpGet]
        public List<DocumentDto> read()
        {
            var response = documentMapper.FromEntitiesToDto(_context.Document.ToListAsync().Result);
            
            foreach (var documentDto in response)
            {
                _documentValidator.validate(documentDto);
            }

            return response;
        }
        
        [HttpGet("{id}")]
        public DocumentDto getById(int id)
        {
            var response = documentMapper.FromEntityToDto(_context.Document.Find(id));
            _documentValidator.validate(response);
            return response;
        }

        [HttpPost]
        public DocumentDto save([FromBody] DocumentDto entity)
        {
            _documentValidator.validate(entity);
            _context.Document.Add(documentMapper.FromDtoToEntity(entity));
            _context.SaveChanges();
            return entity;
        }

        [HttpPut("{id}")]
        public DocumentDto update(int id, [FromBody] DocumentDto entity)
        {
            var updatebleEntity = _context.Document.Find(id);
            if (entity != null)
            {
                if (updatebleEntity != null)
                {
                    updatebleEntity.Description = entity.Description;
                    updatebleEntity.AuthorId = entity.authorId;
                    _context.SaveChanges();
                    var response =documentMapper.FromEntityToDto(_context.Document.Update(updatebleEntity).Entity);
                    _documentValidator.validate(response);
                    return response;
                }
            }

            throw new Exception("");
        }

        [HttpDelete("{id}")]
        public void delete(int id)
        {
            var entityToDelete = _context.Document.Find(id);
            _context.Document.Remove(entityToDelete);
            _context.SaveChanges();
        }
    }
    
}