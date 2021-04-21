import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {DocumentModel} from "./document.model";
import {ServiceInterface} from "../../commons/service.interface";
import {Observable} from "rxjs";
new HttpHeaders({
  'Content-Type': 'application/json'
});

@Injectable({
  providedIn: 'root'
})
export class DocumentService implements ServiceInterface<DocumentModel>{

  private url = 'http://localhost/managers/index.php';

  constructor(private httpclient : HttpClient) { }

  delete(id: number) : Observable<any>{
    return this.httpclient.get(this.url + "?fun=deleteDoc&id=" + id);
  }


  filterType(type) : Observable<DocumentModel[]>{
    return this.httpclient.get<Array<DocumentModel>>(this.url+"?fun=filterType&type="+type);
  }

  filterFormat(format): Observable<DocumentModel[]>{
    return this.httpclient.get<Array<DocumentModel>>(this.url+"?fun=filterFormat&format="+format);
  }
  save(entity): Observable<any>{

    return this.httpclient.get(this.url
      +"?id="+entity['id']
      + "&title="+entity['title']
      + "&pageNumber=" + entity['pageNumber']
      + "&format="+entity['format']
      + "&type="+entity['type']
      + "&authorId="+entity['authorId']);
  }



  read(): Observable<DocumentModel[]> {
    return this.httpclient
      .get<Array<DocumentModel>>(this.url + '?fun=getDocs');
  }
}
