using System.Collections.Generic;

namespace DocumentsApi.Controllers
{
    public interface ICrudController<TM>
    {
        List<TM> read();

        TM getById(int id);

        TM save(TM entity);

        TM update(int id,TM entity);

        void delete(int id);
    }
}