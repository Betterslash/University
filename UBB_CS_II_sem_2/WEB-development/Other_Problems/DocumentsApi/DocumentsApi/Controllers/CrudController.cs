using System.Collections.Generic;
using DocumentsApi.Dto;

namespace DocumentsApi.Controllers
{
    public interface ICrudController<TM> where TM : IBaseDto
    {
        List<TM> read();

        TM getById(int id);

        TM save(TM entity);

        TM update(int id,TM entity);

        void delete(int id);
    }
}