import {Component, Input, OnInit} from '@angular/core';
import {DocumentModel} from "./shared/document.model";

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {


  constructor() { }

  ngOnInit(): void {
  }


}
