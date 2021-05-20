using DocumentsApi.Dto;

namespace DocumentsApi.Validators
{
    public interface IDtoValidator<Dto> where Dto : IBaseDto
    {
        public void validate(Dto dto);
    }
}