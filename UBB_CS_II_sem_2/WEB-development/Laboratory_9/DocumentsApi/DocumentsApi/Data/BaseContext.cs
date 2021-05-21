using DocumentsApi.Model;
using Microsoft.EntityFrameworkCore;

namespace DocumentsApi.Data
{
    public abstract class BaseContext<TB> where TB : BaseEntity
    {
        public DbSet<TB> Entities { get; set; }
    }
}