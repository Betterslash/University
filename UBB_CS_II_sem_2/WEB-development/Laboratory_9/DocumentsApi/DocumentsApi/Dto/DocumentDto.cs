namespace DocumentsApi.Dto
{
    public class DocumentDto : IBaseDto
    {
        
        public int? Id { get; set; }

        public string Description { get; set; }
        
        public int authorId { get; set; } 
    }
}