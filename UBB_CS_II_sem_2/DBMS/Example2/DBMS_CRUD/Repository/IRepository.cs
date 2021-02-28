using DBMS_CRUD.Model;
using DBMS_CRUD.NewFolder;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBMS_CRUD.Repository
{
    interface IRepository<T>
    {
        void save(T obj);
        void delete(int id);
        void update(T obj);
    }
}
