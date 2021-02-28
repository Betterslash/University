using DBMS_CRUD.Model;
using System.Collections.Generic;

namespace DBMS_CRUD.Repository
{
    class CRUDRepository<T> : IRepository<T> where T : DBObject
    {
        private IList<T> representation;
        public CRUDRepository() {
            representation = new List<T>();
        }
        public IList<T> Representation => representation;
        public void delete(int id)
        {
            for (int i = 0; i < representation.Count; i++) {
                if (representation[i].getId() == id) {
                    representation.Remove(representation[i]);
                }
            }
        }

        public void save(T obj)
        {
            representation.Add(obj);
        }

        public void update(T obj)
        {
            for (int i = 0; i < representation.Count; i++)
            {
                if (representation[i].getId() == obj.getId())
                {
                    representation[i] = obj;
                }
            }
        }
    }
}
