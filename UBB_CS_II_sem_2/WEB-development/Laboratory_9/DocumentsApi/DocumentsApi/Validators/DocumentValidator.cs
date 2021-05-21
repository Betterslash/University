using DocumentsApi.Dto;
using DocumentsApi.Exceptions;

namespace DocumentsApi.Validators
{
    public class DocumentValidator : IDtoValidator<DocumentDto>
    {
        public void validate(DocumentDto dto)
        {
            if (dto != null)
            {
                if(dto.Description == null)
                {
                    throw new ValidatorException("Description cannot be null!!");
                }
            }
            else
            {
                throw new ValidatorException("Dto cannot be null!!");
            }
        }
    }
}