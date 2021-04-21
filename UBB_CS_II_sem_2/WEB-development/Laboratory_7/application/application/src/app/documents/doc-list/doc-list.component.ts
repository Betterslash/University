import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DocumentService} from "../shared/document.service";
import {DocumentModel} from "../shared/document.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-doc-list',
  templateUrl: './doc-list.component.html',
  styleUrls: ['./doc-list.component.css']
})
export class DocListComponent implements OnInit {
  documents : DocumentModel[];
  typeForm: FormGroup;
  formatFilter : FormGroup;
  lastFilter: string = '';
  constructor(private builder :FormBuilder,
    private docService : DocumentService) { }

  ngOnInit(): void {
    this.formatFilter = this.builder.group({
      format : ['', Validators.required]
    });

    this.typeForm = this.builder.group({
      type : ['', Validators.required]
    });
    this.refreshList();
  }

  filterByType(value) {
    if(this.typeForm.valid){
      this.docService.filterType(value['type'])
        .subscribe(data => {
          this.documents = data;});
      this.formatFilter.reset();
      this.typeForm.reset();
      this.lastFilter = 'Last filter was Type with value ' + value.type + ' !';
    }else{
      alert('Please enter a valid format filter !');
    }
  }

  filterByFormat(value) {
    if(this.formatFilter.valid) {
      this.docService.filterFormat(value['format'])
        .subscribe(data => this.documents = data);
      this.formatFilter.reset();
      this.typeForm.reset();
      this.lastFilter = 'Last filter was Format with value ' + value.format + ' !';
    }else{
      alert('Please enter a valid format filter !');
    }
  }

  refreshList() {
    this.docService.read().subscribe(response => {console.log(response);
      this.documents = response;});
  }
}
