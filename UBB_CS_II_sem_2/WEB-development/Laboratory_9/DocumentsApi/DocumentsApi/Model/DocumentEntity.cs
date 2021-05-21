using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DocumentsApi.Model
{
    public class DocumentEntity : IBaseEntity
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        
        [Column("description")]
        public string Description { get; set; }

        [Column("author_id")]
        public int AuthorId { get; set; }
    }
}