using System;
using System.Collections.Generic;
using DocumentsApi.Data;
using DocumentsApi.Model;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace DocumentsApi.Controllers
{
    [ApiController]
    [Route("api/documents")]
    public class DocumentController : ControllerBase, ICrudController<DocumentEntity>
    {
        private readonly DocumentContext _context;
        
        public DocumentController(DocumentContext context)
        {
            _context = context;
        }

        [HttpGet]
        public List<DocumentEntity> read()
        {
            
            return _context.Document.ToListAsync().Result;
        }
        
        [HttpGet("{id}")]
        public DocumentEntity getById(int id)
        {
            return _context.Document.Find(id);
        }

        [HttpPost]
        public DocumentEntity save([FromBody] DocumentEntity entity)
        {
            _context.Document.Add(entity);
            _context.SaveChanges();
            return entity;
        }

        [HttpPut("{id}")]
        public DocumentEntity update(int id, [FromBody] DocumentEntity entity)
        {
            var updatebleEntity = _context.Document.Find(id);
            if (entity != null)
            {
                if (updatebleEntity != null)
                {
                    updatebleEntity.Description = entity.Description;
                    updatebleEntity.AuthorId = entity.AuthorId;
                    _context.SaveChanges();
                    return _context.Document.Update(updatebleEntity).Entity;
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