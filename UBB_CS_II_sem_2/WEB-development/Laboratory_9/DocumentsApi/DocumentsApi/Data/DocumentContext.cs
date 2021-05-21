using DocumentsApi.Model;
using Microsoft.EntityFrameworkCore;

namespace DocumentsApi.Data
{
    public class DocumentContext : DbContext
    {

        public DbSet<DocumentEntity> Document { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<DocumentEntity>()
                .HasKey(e => e.Id);
            modelBuilder.Entity<DocumentEntity>()
                .Property(f => f.Id)
                .ValueGeneratedOnAdd();
        }

        public DocumentContext(DbContextOptions<DocumentContext> options) : base(options)
        {
            
        }

    }
}