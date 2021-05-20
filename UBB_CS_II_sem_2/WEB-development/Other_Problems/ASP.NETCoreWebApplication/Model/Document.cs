namespace ASP.NETCoreWebApplication.Model
{
    public class Document
    {
        private int Id { get; set; }
        private string Description { get; set; }
        private int AuthorId { get; set; }

        public Document(int id, string description, int authorId)
        {
            Id = id;
            Description = description;
            AuthorId = authorId;
        }

        public override string ToString()
        {
            return "Document : " + Id + " with description :" + Description + " written by : " + AuthorId;
        }
    }
}