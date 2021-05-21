using Microsoft.EntityFrameworkCore;


namespace AngularLaboratoryBackend.Data
{
	public class DbWpContext : DbContext
	{
		public DbWpContext(DbContextOptions options) : base(options)
		{
		}

		public DbSet<Document> Documents { get; set; }
	}
}