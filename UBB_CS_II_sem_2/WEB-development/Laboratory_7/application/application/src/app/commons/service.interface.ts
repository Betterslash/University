import {Observable} from "rxjs";

export interface ServiceInterface<E>{
  read() : Observable<E[]>;
  save(entity);
  delete(id : number);
}
