using System;
using System.Collections.Generic;
using System.Text;

namespace DBMS_CRUD.Services
{
    interface DBOService<T>
    {
        void ExecuteCreate(T dBObject);
        IList<T> ExcuteRead();
        void ExecuteUpdate(T dBObject);
        void ExcuteDelete(int id);
    }
}
